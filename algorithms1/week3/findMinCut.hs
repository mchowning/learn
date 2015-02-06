-- {-#OPTIONS_GHC -Wall -Werror #-}

{-
While there are more than 2 vertices:
- pick a remaining edge (u,v) uniformly at random
- merge/contract u and v into a single vertex
- remove self-loops
return cut represented by final 2 vertices
-}

-- min = 17?

import qualified Data.Set as Set
import Test.HUnit
import Data.List
import Data.List.Split
import System.Random
import Control.Applicative
import System.Environment

type Edge = (Int,Int)

main :: IO ()
main = do filename <- head <$> getArgs
          contents <- readFile filename
          let edges = removeDuplicates . readAllEdges $ contents

          gen <- newStdGen
          let randomNs =  randomRs (0, maxBound) gen :: [Int]
          print . minimum . take 50 . map (length . reduceToTwoVertices edges) $ chunksOf (length edges) randomNs

  where
    readAllEdges :: String -> [Edge]
    readAllEdges = concatMap (readEdgesForVertex . words) . lines

    readEdgesForVertex :: [String] -> [Edge]
    readEdgesForVertex (vertex:others) = map (createOrderedEdge vertex) others
    readEdgesForVertex _ = []

    createOrderedEdge :: String -> String -> Edge
    createOrderedEdge v1 v2 | i1 < i2 =   (i1,i2)
                            | otherwise = (i2,i1)
      where
        i1 = read v1
        i2 = read v2


reduceToTwoVertices :: [Edge] -> [Int] -> [Edge]
reduceToTwoVertices es (r:rs) | numberOfVertices es == 2 = es
                              | otherwise = reduceToTwoVertices (contractEdge (r `mod` length es) es) rs


numberOfVertices :: [Edge] -> Int
numberOfVertices = length . removeDuplicates . concatMap (\(x,y) -> [x,y])


contractEdge :: Int -> [Edge] -> [Edge]
contractEdge i es = let (v1,v2) = es !! i
                    in map (updateVertex v1 v2) . filter (edgeThatRemains v1 v2) $ es
  where
    edgeThatRemains :: Int -> Int -> Edge -> Bool
    edgeThatRemains v1 v2 e = not (containsVertex e v1 && containsVertex e v2)
      where
        containsVertex :: Edge -> Int -> Bool
        containsVertex (e1,e2) v = v == e1 || v == e2

    updateVertex :: Int -> Int -> Edge -> Edge
    updateVertex old new (e1,e2) = (updateIfMatch e1, updateIfMatch e2)
      where
        updateIfMatch :: Int -> Int
        updateIfMatch input = if input == old then new else input


removeDuplicates :: (Ord a) => [a] -> [a]
removeDuplicates = map head . group . sort





-----------------------------------------------------------------------------


testInput1 :: [Edge]
testInput1 = [ (1,2)
             , (1,3) ]

testInput2 :: [Edge]
testInput2 = [ (1,2)
             , (1,3)
             , (2,3) ]

testInput3 :: [Edge]
testInput3 = [ (1,2)
             , (2,1)
             , (2,3)
             , (2,4)]

reduceToTwoVerticesTests :: Test
reduceToTwoVerticesTests = TestList
  [ reduceToTwoVertices testInput2 (repeat 0) ~?= [ (2,3)
                                                  , (2,3) ]
  , reduceToTwoVertices testInput3 (repeat 2) ~?= [ (1,4)
                                                  , (4,1) ]
  , reduceToTwoVertices testInput3 (repeat 3) ~?= [ (4,3) ]
  ]

contractEdgeTests :: Test
contractEdgeTests = TestList
  [ contractEdge 0 testInput1 ~?= [ (2,3) ]
  , contractEdge 1 testInput1 ~?= [ (3,2) ]

  , contractEdge 0 testInput2 ~?= [ (2,3)
                                  , (2,3) ]
  , contractEdge 1 testInput2 ~?= [ (3,2)
                                  , (2,3) ]
  , contractEdge 2 testInput2 ~?= [ (1,3)
                                  , (1,3) ]

  , contractEdge 0 testInput3 ~?= [ (2,3)
                                  , (2,4) ]
  , contractEdge 1 testInput3 ~?= [ (1,3)
                                  , (1,4) ]
  , contractEdge 2 testInput3 ~?= [ (1,3)
                                  , (3,1)
                                  , (3,4) ]
  , contractEdge 3 testInput3 ~?= [ (1,4)
                                  , (4,1)
                                  , (4,3) ]
  ]


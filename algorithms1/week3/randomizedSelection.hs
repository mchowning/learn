{-#OPTIONS_GHC -Wall -Werror #-}

import System.Random
import Data.List
import Test.HUnit

randomizedSelection :: Int -> String -> Char
randomizedSelection i s = let (randomN,_) = next (mkStdGen 0)
                              randomIndex = randomN `mod` length s
                              pivot = s !! randomIndex
                              reducedS = delete pivot s
                              (lessThan, greaterThan) = partition (<pivot) reducedS
                           in if length lessThan + 1 == i
                              then pivot
                              else if length lessThan >= i
                                   then randomizedSelection i lessThan
                                   else randomizedSelection (i - (length lessThan + 1)) greaterThan

randomizedSelectionTests :: Test
randomizedSelectionTests = TestList
  [ randomizedSelection 1 "a" ~?= 'a'
  , randomizedSelection 1 "ab" ~?= 'a'
  , randomizedSelection 2 "ab" ~?= 'b'
  , randomizedSelection 2 "abc" ~?= 'b'
  , randomizedSelection 3 "abc" ~?= 'c'
  , randomizedSelection 2 "abcdefghijklmnop" ~?= 'b'
  , randomizedSelection 6 "abcdefghijklmnop" ~?= 'f' ]

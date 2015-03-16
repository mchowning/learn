{-#OPTIONS_GHC -Wall -Werror #-}


-- range(t) -> -10000,10000 (inclusive)
-- make sure there are two DISTINCT numbers that satisfay x+y = t

-- import Data.Functor
-- import System.Environment
import qualified Data.Set as S
-- import Test.HUnit

numCounter :: String -> IO ()
numCounter filename = do contents <- readFile ("sum_testcases/" ++ filename)
                         let fileNums = S.fromList . convertToListOfIntegers $ contents
                         let matchedInRange = S.foldr (sumChecker fileNums) S.empty fileNums
                         print . S.size $ matchedInRange

convertToListOfIntegers :: String -> [Int]
convertToListOfIntegers = map read . lines

sumChecker :: S.Set Int -> Int -> S.Set Int -> S.Set Int
sumChecker s i acc = foldr (totalChecker s i) acc [-10000..10000]

totalChecker :: S.Set Int -> Int -> Int -> S.Set Int -> S.Set Int
totalChecker s i total acc = if i /= diff && S.member diff s
                             then S.insert total acc
                             else acc
  where
    diff = total - i

-- stests :: IO Counts
-- stests = runTestTT (TestList [
--   numCounter "6" ~?= (6 :: Int)
--   ])
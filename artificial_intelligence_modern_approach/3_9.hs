{-#OPTIONS_GHC -Wall -Werror #-}

main :: IO ()
main = print "text"

data Side = LeftSide | RightSide

data State = State Side Int Int

otherSide :: Side -> Side
otherSide LeftSide = RightSide
otherSide _        = LeftSide

state :: State -> [State]
state (State _ _ _) = []
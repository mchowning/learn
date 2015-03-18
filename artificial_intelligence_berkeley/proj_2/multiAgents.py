# multiAgents.py
# --------------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


from util import manhattanDistance
from game import Directions
from random import randint
import random, util, math

from game import Agent

class ReflexAgent(Agent):
    """
      A reflex agent chooses an action at each choice point by examining
      its alternatives via a state evaluation function.

      The code below is provided as a guide.  You are welcome to change
      it in any way you see fit, so long as you don't touch our method
      headers.
    """


    def getAction(self, gameState):
        """
        You do not need to change this method, but you're welcome to.

        getAction chooses among the best options according to the evaluation function.

        Just like in the previous project, getAction takes a GameState and returns
        some Directions.X for some X in the set {North, South, West, East, Stop}
        """
        # Collect legal moves and successor states
        legalMoves = gameState.getLegalActions()

        # Choose one of the best actions
        scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices) # Pick randomly among the best

        "Add more of your code here if you want to"

        return legalMoves[chosenIndex]

    def evaluationFunction(self, currentGameState, action):
        """
        Design a better evaluation function here.

        The evaluation function takes in the current and proposed successor
        GameStates (pacman.py) and returns a number, where higher numbers are better.

        The code below extracts some useful information from the state, like the
        remaining food (newFood) and Pacman position after moving (newPos).
        newScaredTimes holds the number of moves that each ghost will remain
        scared because of Pacman having eaten a power pellet.

        Print out these variables to see what you're getting, then combine them
        to create a masterful evaluation function.
        """
        # Useful information you can extract from a GameState (pacman.py)
        successorGameState = currentGameState.generatePacmanSuccessor(action)
        newPos = successorGameState.getPacmanPosition()
        newFood = successorGameState.getFood()
        newGhostStates = successorGameState.getGhostStates()
        newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]

        "*** YOUR CODE HERE ***"
        # TESTING
        closestGhostValue = 999999
        for ghostState in newGhostStates:
            ghostDistance = util.manhattanDistance(ghostState.getPosition(), newPos)
            if ghostDistance < closestGhostValue:
                closestGhostValue = ghostDistance

        # goAfterGhosts = False
        # for scaredTimer in newScaredTimes:
        #     if scaredTimer > 5:
        #         goAfterGhosts = True

        closestGhostValue = min(closestGhostValue, 5)
        closestGhostValue = math.pow(closestGhostValue, 5)

        closestFood = 0
        for foodPos in newFood.asList():
            newFoodDistance = util.manhattanDistance(foodPos, newPos)
            if closestFood == 0 or newFoodDistance < closestFood:
                closestFood = newFoodDistance
        closestFood = math.pow(closestFood, 2)

        scoreValue = (30 * successorGameState.getScore()) + closestGhostValue - closestFood
        return scoreValue
        # TESTING

        # return successorGameState.getScore()

def scoreEvaluationFunction(currentGameState):
    """
      This default evaluation function just returns the score of the state.
      The score is the same one displayed in the Pacman GUI.

      This evaluation function is meant for use with adversarial search agents
      (not reflex agents).
    """
    return currentGameState.getScore()

class MultiAgentSearchAgent(Agent):
    """
      This class provides some common elements to all of your
      multi-agent searchers.  Any methods defined here will be available
      to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

      You *do not* need to make any changes here, but you can if you want to
      add functionality to all your adversarial search agents.  Please do not
      remove anything, however.

      Note: this is an abstract class: one that should not be instantiated.  It's
      only partially specified, and designed to be extended.  Agent (game.py)
      is another abstract class.
    """

    def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
        self.index = 0 # Pacman is always agent index 0
        self.evaluationFunction = util.lookup(evalFn, globals())
        self.depth = int(depth)

class MinimaxAgent(MultiAgentSearchAgent):
    """
      Your minimax agent (question 2)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action from the current gameState using self.depth
          and self.evaluationFunction.

          Here are some method calls that might be useful when implementing minimax.

          gameState.getLegalActions(agentIndex):
            Returns a list of legal actions for an agent
            agentIndex=0 means Pacman, ghosts are >= 1

          gameState.generateSuccessor(agentIndex, action):
            Returns the successor game state after an agent takes an action

          gameState.getNumAgents():
            Returns the total number of agents in the game

          gameState.isWin():
            Returns whether or not the game state is a winning state

          gameState.isLose():
            Returns whether or not the game state is a losing state
        """

        maxAction = None
        maxValue = -99999999
        for action in gameState.getLegalActions():
            newGameState = gameState.generateSuccessor(0, action)
            actionValue = self.minValue(newGameState, 1, self.depth)
            if actionValue > maxValue:
                maxValue = actionValue
                maxAction = action

        return maxAction

    def maxValue(self, gameState, agent, depthLimit):
        legalActions = gameState.getLegalActions(agent)
        if depthLimit == 0 or gameState.isWin() or gameState.isLose() or legalActions == None:
            return self.evaluationFunction(gameState)
        else:
            maxValue = -999999999
            for successorAction in legalActions:
                nextGameState = gameState.generateSuccessor(agent, successorAction)
                nextAgent = agent + 1
                thisValue = self.minValue(nextGameState, nextAgent, depthLimit)
                maxValue = max(maxValue, thisValue)
            return maxValue


    def minValue(self, gameState, agent, depthLimit):
        if gameState.isWin() or gameState.isLose():
            return self.evaluationFunction(gameState)
        else :
            minValue = 999999999
            legalActions = gameState.getLegalActions(agent)
            for successorAction in legalActions:
                nextGameState = gameState.generateSuccessor(agent, successorAction)
                if agent + 1 == gameState.getNumAgents():
                    nextAgent = 0
                    thisValue = self.maxValue(nextGameState, nextAgent, depthLimit - 1)
                else:
                    nextAgent = agent + 1
                    thisValue = self.minValue(nextGameState, nextAgent, depthLimit)
                minValue = min(minValue, thisValue)
            return minValue


class AlphaBetaAgent(MultiAgentSearchAgent):
    """
      Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action using self.depth and self.evaluationFunction
        """

        maxAction = None
        maxValue = -99999999
        alpha = -999999999
        beta = 999999999
        for action in gameState.getLegalActions():
            newGameState = gameState.generateSuccessor(0, action)
            actionValue = self.minValue(newGameState, 1, self.depth, alpha, beta)
            if actionValue > maxValue:
                maxValue = actionValue
                maxAction = action
            alpha = max(alpha, maxValue)
            # can never happen
            # if maxValue > beta:
            #     return maxValue

        return maxAction

    def maxValue(self, gameState, agent, depthLimit, alpha, beta):
        legalActions = gameState.getLegalActions(agent)
        if depthLimit == 0 or gameState.isWin() or gameState.isLose() or legalActions == None:
            return self.evaluationFunction(gameState)
        else:
            maxValue = -999999999
            for successorAction in legalActions:
                nextGameState = gameState.generateSuccessor(agent, successorAction)
                nextAgent = agent + 1
                thisValue = self.minValue(nextGameState, nextAgent, depthLimit, alpha, beta)
                maxValue = max(maxValue, thisValue)
                if maxValue > beta:
                    return maxValue
                alpha = max(alpha, maxValue)
            return maxValue


    def minValue(self, gameState, agent, depthLimit, alpha, beta):
        if gameState.isWin() or gameState.isLose():
            return self.evaluationFunction(gameState)
        else :
            minValue = 999999999
            legalActions = gameState.getLegalActions(agent)
            for successorAction in legalActions:
                nextGameState = gameState.generateSuccessor(agent, successorAction)
                if agent + 1 == gameState.getNumAgents():
                    nextAgent = 0
                    thisValue = self.maxValue(nextGameState, nextAgent, depthLimit - 1, alpha, beta)
                else:
                    nextAgent = agent + 1
                    thisValue = self.minValue(nextGameState, nextAgent, depthLimit, alpha, beta)
                minValue = min(minValue, thisValue)
                if minValue < alpha:
                    return minValue
                beta = min(beta, minValue)
            return minValue

class ExpectimaxAgent(MultiAgentSearchAgent):
    """
      Your expectimax agent (question 4)
    """

    def getAction(self, gameState):
        """
          Returns the expectimax action using self.depth and self.evaluationFunction

          All ghosts should be modeled as choosing uniformly at random from their
          legal moves.
        """

        maxAction = None
        maxValue = -99999999
        for action in gameState.getLegalActions():
            newGameState = gameState.generateSuccessor(0, action)
            actionValue = self.expectValue(newGameState, 1, self.depth)
            if actionValue > maxValue:
                maxValue = actionValue
                maxAction = action
            # can never happen
            # if maxValue > beta:
            #     return maxValue

        return maxAction

    def maxValue(self, gameState, agent, depthLimit):
        legalActions = gameState.getLegalActions(agent)
        if depthLimit == 0 or gameState.isWin() or gameState.isLose() or legalActions is None:
            return self.evaluationFunction(gameState)
        else:
            maxValue = -999999999
            for successorAction in legalActions:
                nextGameState = gameState.generateSuccessor(agent, successorAction)
                nextAgent = agent + 1
                thisValue = self.expectValue(nextGameState, nextAgent, depthLimit)
                maxValue = max(maxValue, thisValue)
            return maxValue


    def expectValue(self, gameState, agent, depthLimit):
        if gameState.isWin() or gameState.isLose():
            return self.evaluationFunction(gameState)
        else :

            legalActions = gameState.getLegalActions(agent)

            totalValue = 0.0
            for action in legalActions:
                nextGameState = gameState.generateSuccessor(agent, action)
                if agent + 1 == gameState.getNumAgents():
                    totalValue += self.maxValue(nextGameState, 0, depthLimit - 1)
                else:
                    totalValue += self.expectValue(nextGameState, agent + 1, depthLimit)
            averageValue = totalValue / len(legalActions)
            return averageValue


def betterEvaluationFunction(currentGameState):
    """
      Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
      evaluation function (question 5).

      DESCRIPTION: <write something here so we know what you did>

      gameState.getLegalActions(agentIndex):
      Returns a list of legal actions for an agent
          agentIndex=0 means Pacman, ghosts are >= 1

      gameState.generateSuccessor(agentIndex, action):
      Returns the successor game state after an agent takes an action
            gameState.getNumAgents():
      Returns the total number of agents in the game

      gameState.isWin():
      Returns whether or not the game state is a winning state

      gameState.isLose():
      Returns whether or not the game state is a losing state
    """

    ghostStates = currentGameState.getGhostStates()
    pacmanPosition = currentGameState.getPacmanPosition()
    food = currentGameState.getFood()
    foodList = currentGameState.getCapsules()
    ghostStates = currentGameState.getGhostStates()
    scaredTimes = [ghostState.scaredTimer for ghostState in ghostStates]
    gameScore = currentGameState.getScore()

    totalGhostDistances = 0
    for state in ghostStates:
        ghostDistance = util.manhattanDistance(state.getPosition(), pacmanPosition)
        # if ghostDistance < 5:
        if ghostDistance < 10:
            totalGhostDistances += ghostDistance

    closestFoodDistance = 0
    for foodPos in food.asList():
        newFoodDistance = util.manhattanDistance(foodPos, pacmanPosition)
        if closestFoodDistance == 0 or newFoodDistance < closestFoodDistance:
            closestFoodDistance = newFoodDistance

    if currentGameState.isWin():
        return 999999999999
    elif currentGameState.isLose():
        return -999999999999
    else:
        return gameScore - (10 * len(food.asList())) - (2 * closestFoodDistance) - totalGhostDistances


# Abbreviation
better = betterEvaluationFunction


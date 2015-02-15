package artificial_intelligence_planning.cannibal_missionary;

import java.util.*;

/**
 * Created by matt on 2/15/15.
 * Scenario is a river with 3 missionaries and 3 cannibals on the left with the goal being
 * to use a boat that only holds two people to get all 6 people across the river while
 * never allowing the cannibals to outnumber (and eat) the missionaries on any side.
 *
 * Uses depth-first search.  Is identical to breadth-first search version except that it uses
 * a stack instead of a queue.
 */
public class CannibalMissionaryDFS {

    private static Set<State> inspectedStates;
    private static Stack<List<State>> pathsToInspect;

    public static void main(String[] args) {
        inspectedStates = new HashSet<State>();
        pathsToInspect = new Stack<List<State>>();
        List<State> startState = new ArrayList<State>() {{
            add(new State(3, 3, true, 0, 0));
        }};
        pathsToInspect.push(startState);
        while (!pathsToInspect.isEmpty()) {
            List<State> path = pathsToInspect.pop();
            if (isFinished(path)) {
                printSolution(path);
                return;
            }
            addPaths(path);
        }
        System.out.println("No Solution Found.");
    }

    private static void printSolution(List<State> solution) {
        System.out.println("" + solution.size());
        for (State s : solution) {
            System.out.println(s);
        }
    }

    private static void addPaths(List<State> previousStateList) {

        State lastState = previousStateList.get(previousStateList.size() - 1);

        if (inspectedStates.contains(lastState)) {
            return;
        } else {
            inspectedStates.add(lastState);
        }

        List<State> newStates = possibleNextStates(lastState);
        for (State newState : newStates) {
            List<State> pathWithNewState = newPath(previousStateList, newState);
            pathsToInspect.push(pathWithNewState);
        }
    }

    private static List<State> newPath(List<State> previousPath, State newState) {
        List<State> result = new ArrayList<State>();
        for (State previousState : previousPath) {
            result.add(previousState);
        }
        result.add(newState);
        return result;
    }

    private static boolean isFinished(List<State> stateList) {
        State state = stateList.get(stateList.size() - 1);
        return state.rightMissionaries == 3 && state.rightCannibals == 3;
    }

    private static List<State> possibleNextStates(State startState) {

        int[][] possibleRightMoves = { {2,0}, {1,1}, {0,2} };
        int[][] possibleLeftMoves = { {-2, 0}, {-1, 0}, { 0,-1}, {-1,-1}, {0, -2} };

        int[][] possibleMoves;
        if (startState.isLeftShore) {
            possibleMoves = possibleRightMoves;
        } else {
            possibleMoves = possibleLeftMoves;
        }

        List<State> results = new ArrayList<State>();
        for (int[] moves : possibleMoves) {
            State nextState = move(moves[0], moves[1], startState);
            if (isValid(nextState)) {
                results.add(nextState);
            }
        }
        return results;
    }

    private static State move(int missionaries, int cannibals, State startState) {
        State result = new State(startState);
        result.isLeftShore = !result.isLeftShore;
        result.leftMissionaries -= missionaries;
        result.rightMissionaries += missionaries;
        result.leftCannibals -= cannibals;
        result.rightCannibals += cannibals;
        return result;
    }

    private static boolean isValid(State state) {
        return state.leftCannibals >= 0 &&
                state.rightCannibals >= 0 &&
                (state.leftMissionaries == 0 || state.leftMissionaries >= state.leftCannibals) &&
                (state.rightMissionaries == 0 || state.rightMissionaries >= state.rightCannibals);
    }

    private static class State {
        private boolean isLeftShore;
        private int leftMissionaries;
        private int leftCannibals;
        private int rightMissionaries;
        private int rightCannibals;

        public State(int leftMissionaries,
                     int leftCannibals,
                     boolean isLeftShore,
                     int rightMissionaries,
                     int rightCannibals)
        {
            this.leftMissionaries = leftMissionaries;
            this.leftCannibals = leftCannibals;
            this.isLeftShore = isLeftShore;
            this.rightMissionaries = rightMissionaries;
            this.rightCannibals = rightCannibals;
        }

        public State(State state) {
            this(state.leftMissionaries, state.leftCannibals, state.isLeftShore, state.rightMissionaries, state.rightCannibals);
        }

        @Override
        public String toString() {
            String shore = isLeftShore ? "left" : "right";
            return String.format("%d %d %s %d %d", leftMissionaries, leftCannibals, shore, rightMissionaries, rightCannibals);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (isLeftShore != state.isLeftShore) return false;
            if (leftCannibals != state.leftCannibals) return false;
            if (leftMissionaries != state.leftMissionaries) return false;
            if (rightCannibals != state.rightCannibals) return false;
            if (rightMissionaries != state.rightMissionaries) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (isLeftShore ? 1 : 0);
            result = 31 * result + leftMissionaries;
            result = 31 * result + leftCannibals;
            result = 31 * result + rightMissionaries;
            result = 31 * result + rightCannibals;
            return result;
        }
    }
}

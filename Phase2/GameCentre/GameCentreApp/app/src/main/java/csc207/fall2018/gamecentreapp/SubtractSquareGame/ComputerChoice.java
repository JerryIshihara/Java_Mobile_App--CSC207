package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;


public class ComputerChoice {

    private SubtractSquareState currentState;

    private ArrayList<ComputerChoice> children;

    private int score;


    public ComputerChoice() {
    }

    private ComputerChoice(SubtractSquareState state) {
        this.currentState = state;
        this.children = new ArrayList<>();
        this.score = 0;
    }

    private class MiniMaxGame extends SubtractSquareGame {
        SubtractSquareGame game;
        SubtractSquareState state;

        MiniMaxGame(SubtractSquareGame game, SubtractSquareState state) {
            super(game.getCurrentState().getP1Name(), game.getCurrentState().getP2Name());
            this.game = game;
            this.state = state;
        }

        MiniMaxGame getNewCurrentState(SubtractSquareState state) {
            return new MiniMaxGame(this.game, state);
        }


    }

    public int iterativeMiniMax(SubtractSquareGame game) {
        MiniMaxGame miniMaxGame = new MiniMaxGame(game, game.getCurrentState());
        return iterativeMiniMax(miniMaxGame);
    }

    /**
     * Check whether the current total of miniMaxGame is a square number.
     *
     * @param miniMaxGame the current MiniMaxGame to check
     * @return whether the current total of miniMaxGame is a square number
     */
    private boolean dealWithSquare(MiniMaxGame miniMaxGame) {
        return (miniMaxGame.game.checkSquare(miniMaxGame.game.getCurrentState().getCurrentTotal()));

    }

    /**
     * Check whether the current total of miniMaxGame is a square number plus two.
     *
     * @param miniMaxGame the current MiniMaxGame to check
     * @return whether the current total of miniMaxGame is a square number plus two
     */
    private boolean dealWithSquarePlusTwo(MiniMaxGame miniMaxGame) {
        int i = miniMaxGame.game.getCurrentState().getCurrentTotal();
        int k = 1;
        while (k < i) {
            if (k * k + 2 == i) {
                return true;
            }
            k++;
        }
        return false;
    }

    /**
     * Check whether the current total of miniMaxGame is a square number plus five.
     *
     * @param miniMaxGame the current MiniMaxGame to check
     * @return whether the current total of miniMaxGame is a square number plus five
     */
    private boolean dealWithSquarePlusFive(MiniMaxGame miniMaxGame) {
        int i = miniMaxGame.game.getCurrentState().getCurrentTotal();
        int k = 1;
        while (k < i) {
            if (k * k + 5 == i) {
                return true;
            }
            k++;
        }
        return false;
    }

    private int iterativeMiniMax(MiniMaxGame miniMaxGame) {
        if (miniMaxGame.game.is_over()) {
            return 0;
        }
        if (dealWithSquare(miniMaxGame)) {
            return miniMaxGame.game.getCurrentState().getCurrentTotal();
        }
        if (dealWithSquarePlusTwo(miniMaxGame)) {
            return miniMaxGame.game.getCurrentState().getCurrentTotal() - 2;
        }
        if (dealWithSquarePlusFive(miniMaxGame)) {
            return miniMaxGame.game.getCurrentState().getCurrentTotal() - 5;
        }
        if (miniMaxGame.state.getCurrentTotal() > 40) {
            ArrayList<Integer> moves = miniMaxGame.state.getPossibleMoves();
            int index = getRandomInt(moves.size() - 1);
            return moves.get(index);
        }
        ArrayList<ComputerChoice> collection = new ArrayList<>();
        ComputerChoice node = new ComputerChoice(miniMaxGame.game.getCurrentState());
        collection.add(node);
        ComputerChoice node1 = new ComputerChoice();
        while (!collection.isEmpty()) {
            node1 = collection.remove(collection.size() - 1);
            MiniMaxGame newGame = miniMaxGame.getNewCurrentState(node1.currentState);
            if (newGame.state.getCurrentTotal() == 0) {
                node1.score = (newGame.state.isP1_turn()) ? 1 : -1;
            } else if (node1.children.size() == 0) {
                dealWithEmpty(node1, collection);
            } else {
                dealWithMax(node1);
            }
        }
        return findMiniMax(node1);
    }


    private void dealWithEmpty(ComputerChoice node1, ArrayList<ComputerChoice> collection) {
        ArrayList<SubtractSquareState> states = new ArrayList<>();
        ArrayList<ComputerChoice> nodes = new ArrayList<>();
        for (int m : node1.currentState.getPossibleMoves()) {
            states.add(node1.currentState.makeMove(String.valueOf(m)));
        }
        for (SubtractSquareState state : states) {
            nodes.add(new ComputerChoice(state));
        }
        node1.children = nodes;
        collection.add(node1);
        collection.addAll(nodes);
    }

    private void dealWithMax(ComputerChoice node1) {
        ArrayList<Integer> empty = new ArrayList<>();
        for (ComputerChoice node : node1.children) {
            empty.add(-1 * node.score);
        }
        node1.score = Collections.max(empty);
    }

    private int findMiniMax(ComputerChoice node1) {
        ArrayList<Integer> scores = new ArrayList<>();
        for (ComputerChoice node : node1.children) {
            scores.add(node.score);
        }
        int mini = Collections.min(scores);
        int miniIndex = scores.indexOf(mini);
        return node1.currentState.getPossibleMoves().get(miniIndex);
    }


    /**
     * Return a random integer between min and max, inclusive.
     *
     * @param max the maximum for thr random integer.
     * @return a random integer between min and max, inclusive.
     */
    private int getRandomInt(int max) {
        Random randInt = new Random();
        return randInt.nextInt(max + 1);
    }
}





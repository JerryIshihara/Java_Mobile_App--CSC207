package csc207.fall2018.gamecentreapp.SubtractSquareGame;

import java.util.Collections;
import java.util.ArrayList;


public class MiniMaxNode {

    private SubtractSquareState currentState;

    private ArrayList<MiniMaxNode> children;

    private int score;


    public MiniMaxNode() {
    }

    private MiniMaxNode(SubtractSquareState state) {
        this.currentState = state;
        this.children = new ArrayList<>();
        this.score = 0;
    }


    // private MiniMaxNode(SubtractSquareState currentState, int idNum){
    //    this.currentState = currentState;
    //  this.idNum = idNum;
    //  this.children = null;
    //  this.score = 0;
    // this.move = 0;
    //  }

    //  private MiniMaxNode(SubtractSquareState currentState, int idNum, int move ){
    //   this.currentState = currentState;
    //  this.idNum = idNum;
    //  this.move = move;
    //   this.children = null;
    // this.score = 0;
    //}
//
//    public int iterativeMiniMax(SubtractSquareGame game) {
//        if (game.is_over()) {
//            return 0;
//        }
//        ArrayList<MiniMaxNode> collection = new ArrayList<>();
//        MiniMaxNode node = new MiniMaxNode(game.getCurrentState());
//        collection.add(node);
//        MiniMaxNode node1 = new MiniMaxNode();
//        while (!collection.isEmpty()) {
//            node1 = collection.remove(collection.size() - 1);
//            game.setCurrentState(node1.currentState);
//            if (game.is_over()) {
//                dealWithEnd(game, node1);
//            } else if (node1.children.size() == 0) {
//                dealWithEmpty(node1, collection);
//            } else {
//                dealWithMax(node1);
//            }
//        }
//        return findMiniMax(node1);
//    }
//
//    private void dealWithEnd(SubtractSquareGame game, MiniMaxNode node1) {
//        game.setCurrentState(node1.currentState);
//        if (game.getWinner().equals(game.getCurrentPlayerName())) {
//            node1.score = 1;
//        } else if (!game.getWinner().equals(game.getP1Name()) && !game.getWinner().equals(game.getP2Name())) {
//            node1.score = 0;
//        } else {
//            node1.score = -1;
//        }
//    }

    private void dealWithEmpty(MiniMaxNode node1, ArrayList<MiniMaxNode> collection) {
        ArrayList<SubtractSquareState> states = new ArrayList<>();
        ArrayList<MiniMaxNode> nodes = new ArrayList<>();
        for (int m : node1.currentState.getPossibleMoves()) {
            states.add(node1.currentState.makeMove(String.valueOf(m)));
        }
        for (SubtractSquareState state : states) {
            nodes.add(new MiniMaxNode(state));
        }
        node1.children = nodes;
        collection.add(node1);
        for (MiniMaxNode node : nodes) {
            collection.add(node);
        }
    }

    private void dealWithMax(MiniMaxNode node1) {
        ArrayList<Integer> empty = new ArrayList<>();
        for (MiniMaxNode node : node1.children) {
            empty.add(-1 * node.score);
        }
        node1.score = Collections.max(empty);
    }

    private int findMiniMax(MiniMaxNode node1) {
        ArrayList<Integer> scores = new ArrayList<>();
        for (MiniMaxNode node : node1.children) {
            scores.add(node.score);
        }
        int mini = Collections.min(scores);
        int miniIndex = scores.indexOf(mini);
        return node1.currentState.getPossibleMoves().get(miniIndex);
    }


    // Helper class
    private class GameAndScore {

        int score;

        SubtractSquareState currentState;

        SubtractSquareGame subtractSquareGame;

        GameAndScore(int score, SubtractSquareGame subtractSquareGame) {
            this.score = score;
            this.subtractSquareGame = subtractSquareGame;
            this.currentState = subtractSquareGame.getCurrentState();
        }


        SubtractSquareGame getSubtractSquareGame() {
            return subtractSquareGame;
        }

        int getScore() {
            return score;
        }

        void setScore(int score) {
            this.score = score;
        }
    }


    public ArrayList<Integer> recursiveMiniMax(GameAndScore gameScore) {

        SubtractSquareGame game = gameScore.getSubtractSquareGame();

        ArrayList<Integer> moves = game.getCurrentState().getPossibleMoves();

        String player = game.getCurrentPlayerName();

        ArrayList<Integer> scores = new ArrayList<>();

        ArrayList<GameAndScore> choices = new ArrayList<>();

        for (int i : moves) {
            SubtractSquareGame g = gameScore.getSubtractSquareGame();
            g.applyMove(String.valueOf(i));
            choices.add(new GameAndScore(-2, g));
        }

        for (GameAndScore children : choices) {
            if (children.getSubtractSquareGame().getWinner().equals(player)) {
                gameScore.setScore(1);
                children.setScore(-1);
                scores.add(-1);
                int index = choices.indexOf(children);
                ArrayList<Integer> a = new ArrayList<>();
                a.add(moves.get(index));
                a.add(-1);
                return a;
            } else if (children.getSubtractSquareGame().is_over()) {
                children.setScore(1);
                scores.add(1);
            } else {
                MiniMaxNode m = new MiniMaxNode();
                ArrayList<Integer> tmp = m.recursiveMiniMax(children);
                scores.add(tmp.get(1));
            }
        }
        return moves;

//        int min = Collections.min(scores);
//        gameScore.setScore(-1 * min);
//        ArrayList<Integer> a = new ArrayList<>();
//        for (GameAndScore children : choices) {
//            if (children.getScore() == min) {
//                a.add(moves.get(choices.indexOf(children)));
//                a.add(gameScore.getScore());
//            }
//        }
//        return a;
    }

    public int recursiveMiniMax(SubtractSquareGame game) {
        GameAndScore gameAndScore = new GameAndScore(-1, game);
        return recursiveMiniMax(gameAndScore).get(0);
    }
}





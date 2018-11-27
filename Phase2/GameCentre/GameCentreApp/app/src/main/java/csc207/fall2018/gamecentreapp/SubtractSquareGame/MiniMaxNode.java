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

    private class MiniMaxGame extends SubtractSquareGame{
        SubtractSquareGame  game;
        SubtractSquareState state;
        MiniMaxGame(SubtractSquareGame game, SubtractSquareState state){
            super(game.getCurrentState().getP1Name(), game.getCurrentState().getP2Name());
            this.game = game;
            this.state = state;
        }
        MiniMaxGame getNewCurrentState(SubtractSquareState state){
            return new MiniMaxGame(this.game, state);
        }
        String getP1Name(){return this.game.getCurrentState().getP1Name();}
        String getP2Name(){return this.game.getCurrentState().getP2Name();}
    }
    public int iterativeMiniMax(SubtractSquareGame game){
        MiniMaxGame miniMaxGame = new MiniMaxGame(game, game.getCurrentState());
        return iterativeMiniMax(miniMaxGame);
    }

    private boolean dealWithSquare(MiniMaxGame miniMaxGame){
        return (miniMaxGame.game.checkSquare(miniMaxGame.game.getCurrentState().getCurrentTotal()));

    }

    private boolean dealWithSquarePlusTwo(MiniMaxGame miniMaxGame){
        int i = miniMaxGame.game.getCurrentState().getCurrentTotal() ;
        int k = 0;
        while(k < i){
            if(k * k + 2 == i){return true;}
            k++;
        }
        return false;
    }

    private boolean dealWithSquarePlusFive(MiniMaxGame miniMaxGame){
        int i = miniMaxGame.game.getCurrentState().getCurrentTotal() ;
        int k = 0;
        while(k < i){
            if(k * k + 5 == i){return true;}
            k++;
        }
        return false;
    }

   private int iterativeMiniMax(MiniMaxGame miniMaxGame) {
       if (miniMaxGame.game.is_over()) {
           return 0;
      }
       if(dealWithSquare(miniMaxGame)) {
           return miniMaxGame.game.getCurrentState().getCurrentTotal();
       }
       if(dealWithSquarePlusTwo(miniMaxGame)){
           return miniMaxGame.game.getCurrentState().getCurrentTotal() - 2;
       }
       if(dealWithSquarePlusFive(miniMaxGame)){
           return miniMaxGame.game.getCurrentState().getCurrentTotal() - 5;
       }
       if (miniMaxGame.state.getCurrentTotal() > 40){
           return 1;
       }
       ArrayList<MiniMaxNode> collection = new ArrayList<>();
       MiniMaxNode node = new MiniMaxNode(miniMaxGame.game.getCurrentState());
       collection.add(node);
       MiniMaxNode node1 = new MiniMaxNode();
       while (!collection.isEmpty()) {
           node1 = collection.remove(collection.size() - 1);
           MiniMaxGame newGame = miniMaxGame.getNewCurrentState(node1.currentState);
           if (newGame.state.getCurrentTotal() == 0) {
               dealWithEnd(newGame, node1);
           } else if (node1.children.size() == 0) {
               dealWithEmpty(node1, collection);
           } else {
               dealWithMax(node1);
           }
       }
        return findMiniMax(node1);
   }

   private void dealWithEnd(MiniMaxGame newGame, MiniMaxNode node1) {
        MiniMaxGame newGame2 = newGame.getNewCurrentState(node1.currentState);
       String winner = (newGame2.state.isP1_turn()) ? newGame2.state.getP2Name() : newGame2.state.getP1Name();
       String player = newGame2.state.isP1_turn() ? newGame2.state.getP1Name() : newGame2.state.getP2Name();
       if (winner.equals(player)) {
           node1.score = 1;
       } else if (!winner.equals(newGame2.state.getP1Name()) && !winner.equals(newGame2.state.getP2Name())) {
           node1.score = 0;
       } else {
           node1.score = -1;
       }
   }

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





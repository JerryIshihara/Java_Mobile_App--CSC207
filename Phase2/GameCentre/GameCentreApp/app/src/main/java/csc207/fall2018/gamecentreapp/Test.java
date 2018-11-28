package csc207.fall2018.gamecentreapp;

import java.util.ArrayList;
import java.util.Iterator;

import csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareGame;

public class Test {

    public static void main(String[] args) {
//        UserManager userManager = UserManager.getInstance();
//        User user = new User("Jerry", "Jerry");
//        System.out.println(userManager.isRegistered(user));
//        userManager.signUp(user);
//        System.out.println(userManager.isRegistered(user));
//        userManager.logOut();
//        System.out.println(userManager.isRegistered(user));
//        UserManager userManager1 = UserManager.getInstance();
//        System.out.println(userManager1.isRegistered(user));
//        System.out.println(userManager1.checkUserValidation(user));
//        String str = "1a";
//        System.out.println(str.matches("-?(0|[1-9]\\d*)"));
//
//        SubtractSquareGame subtractSquareGame = new SubtractSquareGame("Jerry", "Anna");
//
//        System.out.println(subtractSquareGame.getCurrentPlayerName());
//
//        System.out.println(subtractSquareGame.getCurrentState().getCurrentTotal());
//
//        subtractSquareGame.applyMove("9");
//
//        System.out.println(subtractSquareGame.getCurrentPlayerName());

//        SubtractSquareGame subtractSquareGame = new SubtractSquareGame("", "");
//        System.out.println(subtractSquareGame.getCurrentState().getCurrentTotal());
//        subtractSquareGame.applyMove("9");
//        subtractSquareGame.applyMove("9");
//        subtractSquareGame.applyMove("9");
////        Iterator<> iterator = new  subtractSquareGame.pastStates.iterator();
//        System.out.println(subtractSquareGame.getCurrentState().getCurrentTotal());
//        subtractSquareGame.undoMove();
//        System.out.println(subtractSquareGame.getCurrentState().getCurrentTotal());
//        subtractSquareGame.undoMove();
//        System.out.println(subtractSquareGame.getCurrentState().getCurrentTotal());

//        String str = "00:00";
//        str = str.replace(":", "");
//        int i = Integer.valueOf(str);
//        System.out.println(str);
//        System.out.println(i);
        float intTime = 10;
        int intScore = (int) (100 * (1 - (intTime/(intTime + 200))));
        System.out.println(intScore);
        float intTime1 = 40;
        int intScore1 = (int) (100 * (1 - (intTime1/(intTime1 + 200))));
        System.out.println(intScore1);
    }
}



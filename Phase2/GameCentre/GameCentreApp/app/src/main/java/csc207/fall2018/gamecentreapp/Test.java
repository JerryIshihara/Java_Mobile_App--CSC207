package csc207.fall2018.gamecentreapp;

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
        String str = "1a";
        System.out.println(str.matches("-?(0|[1-9]\\d*)"));

        SubtractSquareGame subtractSquareGame = new SubtractSquareGame("Jerry", "Anna");

        System.out.println(subtractSquareGame.getCurrentPlayerName());

        System.out.println(subtractSquareGame.getCurrentState().getCurrentTotal());

        subtractSquareGame.applyMove("9");

        System.out.println(subtractSquareGame.getCurrentPlayerName());
    }
}



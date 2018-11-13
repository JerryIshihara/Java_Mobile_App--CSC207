package csc207.fall2018.gamecentreapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A class storing all users' information and managing users to play the games.
 */
public class UserManager {
    /**
     * A static variable to check whether there is only one UserManager initialized.
     */
    private static UserManager userManagerInstance = null;
    /**
     * The name of a file which is used to collect all users'unformation.
     */
    private static final String userInfo = "userInfo.ser";
    /**
     * A list containing users.
     */
    private static List<User> userList = new ArrayList<>();
    /**
     *  The current user.
     */
    private static User currentUser = null;

    /**
     * Read the file containing users'information.
     */
    private static void readFile() {
        try {
            FileInputStream fileIn = new FileInputStream(userInfo);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userList = (ArrayList<User>)in.readObject();
            fileIn.close();
        } catch (Exception i) {
            writeFile();
        }
    }

    /**
     * Write a file containing the users'information.
     */
    private static void writeFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(userInfo);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(userList);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * A constructor of the UserManager.
     */
    private UserManager() {
    }

    /**
     * Check whether there is only one UserManager initialized.
     * @return a non-null UserManager.
     */
    public static UserManager getInstance() {
        if (userManagerInstance == null) {
            userManagerInstance = new UserManager();
        }
        return userManagerInstance;
    }

    /**
     * Check whether UserManager stores the user's information.
     * @param user an User playing the game
     * @return boolean value whether the user's information is stored in the UserManager.
     */
    public boolean hasThisUser(User user) {
        readFile();
        if (userList.size() == 0) return false;
        else for (User u : userList) {
            if (user.equals(u)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether user is able to login.
     * @param user an user playing the game.
     * @return  the boolean value whether user can login.
     */
    public boolean checkUserValidation(User user) {
        readFile();
        if (userList.size() == 0) return false;
        else for (User u : userList) {
            if (u.equals(user) & u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether currentUser is null.
     * @return boolean value whether there is someone logged in.
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Login the gameApp
     * @param user an User playing then game.
     */
    public void logIn(User user) {
        readFile();
        if (!isLoggedIn() && checkUserValidation(user)) {
            currentUser = user;
        }
    }

    /**
     * Logout the gameApp
     */
    public void logOut() {
        currentUser = null;
    }

    /**
     * Sign up for a new account.
     * @param user an User playing the game.
     */
    public void signUp(User user) {
        readFile();
        if (!hasThisUser(user)) {
            if (currentUser == null) {
                currentUser = user;
                userList.add(user);
                writeFile();
            }
        }
    }

//
//    private static void addUser(User user) {

    /**
     * Return the name of the current user.
     */
    public static String getCurrentUserName() {
        if (currentUser != null) {
            return currentUser.getUserName();
        } else {
            return "1";
        }

    }

    public static String testHasFile(){
        try {
            FileInputStream fileIn = new FileInputStream(userInfo);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList tmp = (ArrayList<User>) in.readObject();
            in.close();
            fileIn.close();
            if(tmp.size() != 0) return "has file in local";
        } catch (Exception i) {
            return "error";
        }
        return "what happened";
    }
}

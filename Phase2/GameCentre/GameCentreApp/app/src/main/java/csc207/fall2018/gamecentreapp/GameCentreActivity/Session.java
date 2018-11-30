package csc207.fall2018.gamecentreapp.GameCentreActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import csc207.fall2018.gamecentreapp.DataBase.DataBase;

public class Session {
    /**
     * static Session session
     */
    private static Session session;

    /**
     * static ataBase dataBase;
     */
    private static DataBase dataBase;

    /**
     * static User currentUser
     */
    private static User currentUser;

    /**
     * the constructor of the Session class.
     */
    private Session() {
    }

    /**
     * Get the instance
     *
     * @param context the context.
     * @return one instance of the Session class.
     */
    public static Session getInstance(Context context) {
        if (session == null) {
            dataBase = new DataBase(context);
            session = new Session();
        }
        return session;
    }

    /**
     * logout
     *
     * @param view the view
     */
    public void logOut(View view) {
        currentUser = null;
        Intent returnIntent = new Intent(view.getContext(), MainActivity.class);
        view.getContext().startActivity(returnIntent);
    }

    /**
     * Return the name of the current user.
     *
     * @return the name of the current user
     */
    public String getCurrentUserName() {
        if (currentUser != null) {
            return currentUser.getUserName();
        } else {
            return "No Username";
        }
    }

    /**
     * Display the current name.
     *
     * @param textView the textView.
     */
    void displayCurrentName(TextView textView) {
        textView.setText(getCurrentUserName());
    }

    /**
     * Attempt to register.
     *
     * @param view the view
     * @param user the user
     */
    void registerAttempt(View view, User user) {
        int index = dataBase.registerUser(user);
        String userName = user.getUserName();
        String userPassword = user.getPassword();
        if (userName.equals("") || userPassword.equals("")) {
            Toast.makeText(view.getContext(), "Username or password cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (index == 1) {
            currentUser = user;
            Intent registerIntent = new Intent(view.getContext(), UserSpecificActivity.class);
            view.getContext().startActivity(registerIntent);
            // already registered
        } else {
            Toast.makeText(view.getContext(), "Already has this user", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Attempt to login.
     *
     * @param view the view.
     * @param user the user.
     */
    void loginAttempt(View view, User user) {
        int index = dataBase.loginUser(user);
        if (index == 1) {
            currentUser = user;
            Intent loginIntent = new Intent(view.getContext(), UserSpecificActivity.class);
            view.getContext().startActivity(loginIntent);
            // wrong information input
        } else if (index == -1) {
            Toast.makeText(view.getContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(view.getContext(), "No such user, please register first", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Delete all the information for the current user.
     *
     * @param view the view.
     */
    public void deleteAllInfoBelongCurrentUser(View view) {
        dataBase.deleteUser(currentUser.getUserName());
        logOut(view);
    }

    /**
     * Delete the game information.
     *
     * @param view the view
     */
    void adminDeleteGameInfo(View view) {
        dataBase.adminDeleteGameInfo();
        Toast.makeText(view.getContext(), "Done", Toast.LENGTH_SHORT).show();
    }
}


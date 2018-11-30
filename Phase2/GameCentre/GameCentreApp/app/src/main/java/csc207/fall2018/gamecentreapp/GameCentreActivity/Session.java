package csc207.fall2018.gamecentreapp.GameCentreActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import csc207.fall2018.gamecentreapp.DataBase.DataBase;

public class Session {

    private static Session session;

    private static DataBase dataBase;

    private static User currentUser;

    private Session() {
    }

    public static Session getInstance(Context context) {
        if (session == null) {
//            userDataBase = new UserDataBase(context);
            dataBase = new DataBase(context);
            session = new Session();
        }
        return session;
    }

    public void logOut(View view) {
        currentUser = null;
        Intent returnIntent = new Intent(view.getContext(), MainActivity.class);
        view.getContext().startActivity(returnIntent);
    }

    public String getCurrentUserName() {
        if (currentUser != null) {
            return currentUser.getUserName();
        } else {
            return "No Username";
        }
    }


//    public void loginAttempt(View view, User user) {
//        if (isRegistered(user)) {
//            // check the validation
//            if (userDataBase.checkUserValidation(user)) {
//                loginUser(user);
//                Intent loginIntent = new Intent(view.getContext(), UserSpecificActivity.class);
//                view.getContext().startActivity(loginIntent);
//                // wrong information input
//            } else {
//                Toast.makeText(view.getContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
//            }
//            // not registered
//        } else {
//            Toast.makeText(view.getContext(), "No such user, please register first", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//    public void registerAttempt(View view, User user) {
//        String userName = user.getUserName();
//        String userPassword = user.getPassword();
//        if (userName.equals("") || userPassword.equals("")) {
//            Toast.makeText(view.getContext(), "Username or password cannot be empty", Toast.LENGTH_SHORT).show();
//        } else if (!isRegistered(user)) {
//            // sign up user and login user
//            signUp(user);
//            Intent registerIntent = new Intent(view.getContext(), UserSpecificActivity.class);
//            view.getContext().startActivity(registerIntent);
//            // already registered
//        } else {
//            Toast.makeText(view.getContext(), "Already has this user", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void displayCurrentName(TextView textView) {
        textView.setText(getCurrentUserName());
    }

    public void registerAttempt(View view, User user) {
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

    public void loginAttempt(View view, User user) {
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

    public void deleteAllInfoBelongCurrentUser(View view) {
        dataBase.deleteUser(currentUser.getUserName());
        logOut(view);
    }

    public void adminDeleteGameInfo(View view) {
        dataBase.adminDeleteGameInfo();
        Toast.makeText(view.getContext(), "Done", Toast.LENGTH_SHORT).show();
    }
}


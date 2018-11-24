package csc207.fall2018.gamecentreapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A class dealing with Registering.
 */
public class RegisterActivity extends AppCompatActivity {

//    UserDataBase userDataBase;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        userDataBase = new UserDataBase(this);
//    }
//
//    public void onclickRegisterButton(View view) {
//
//        // first input information
//        final EditText userNameInput = (EditText) findViewById(R.id.userName);
//        final EditText userPasswordInput = (EditText) findViewById(R.id.password);
//        String userName = userNameInput.getText().toString();
//        String userPassword = userPasswordInput.getText().toString();
//
//        // create new User
//        User user = new User(userName, userPassword);
//
//        if (!userDataBase.isRegistered(user)) {
//            userDataBase.signUpUser(user);
//            Intent registerIntent = new Intent(getApplicationContext(), UserSpecificActivity.class);
//            startActivity(registerIntent);
//        } else {
//            Toast.makeText(getApplicationContext(), "Already registered", Toast.LENGTH_SHORT).show();
//        }
//    }


    private final static String FILE_NAME = "userManager.ser";

    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userManager = UserManager.getInstance();
        saveToFile(FILE_NAME);
    }

    /**
     * Dealing with clicking on Register button.
     *
     * @param view A view of the model
     */
    public void onclickRegisterButton(View view) {

        // first input information
        final EditText userNameInput = (EditText) findViewById(R.id.userName);
        final EditText userPasswordInput = (EditText) findViewById(R.id.password);
        String userName = userNameInput.getText().toString();
        String userPassword = userPasswordInput.getText().toString();

        // create new User
        User user = new User(userName, userPassword);
        loadFromFile(FILE_NAME);

        // check if the user has not yet registered
        if (!userManager.isRegistered(user)) {
            // sign up user and login user
            userManager.signUp(user);
            Intent registerIntent = new Intent(getApplicationContext(), UserSpecificActivity.class);
            startActivity(registerIntent);
            // already registered
        } else {
            Toast.makeText(getApplicationContext(), "Already has this user", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Dealing with clicking on Return button.
     *
     * @param view A view of the model
     */

    public void onclickToLogin(View view) {
        Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(returnIntent);
    }

    private void loadFromFile(String fileName) {
        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                userManager = (UserManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(userManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}

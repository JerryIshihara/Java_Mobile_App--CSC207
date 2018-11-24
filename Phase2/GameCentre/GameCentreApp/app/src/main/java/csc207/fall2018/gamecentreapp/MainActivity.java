package csc207.fall2018.gamecentreapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * A class dealing with main page.
 */
public class MainActivity extends AppCompatActivity {

//    UserDataBase userDataBase;


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        userDataBase = new UserDataBase(this);
//    }
//
//    public void onclickLoginButton(View view) {
//        // first input information
//        final EditText userNameInput = (EditText) findViewById(R.id.userName);
//        final EditText userPasswordInput = (EditText) findViewById(R.id.password);
//        String userName = userNameInput.getText().toString();
//        String userPassword = userPasswordInput.getText().toString();
//
//        User user = new User(userName, userPassword);
//
//        // check if the user has registered
//        if (userDataBase.isRegistered(user)) {
//            // check the validation
//            if (userDataBase.checkUserValidation(user)) {
////                userDataBase.loginUser(user);
//                Intent loginIntent = new Intent(getApplicationContext(), UserSpecificActivity.class);
//                startActivity(loginIntent);
//                // wrong information input
//            } else {
//                Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
//            }
//            // not registered
//        } else {
//            Toast.makeText(getApplicationContext(), "No such user, please register first", Toast.LENGTH_SHORT).show();
//        }
//    }


//——————————————————————————————————————————————————————————————————————————————>>> SQLite


    private final static String FILE_NAME = "userManager.ser";

    private UserManager userManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userManager = UserManager.getInstance();
        saveToFile(FILE_NAME);
    }

    /**
     * Dealing with clicking on Login button.
     *
     * @param view A view of the model/
     */
    public void onclickLoginButton(View view) {
        // first input information
        final EditText userNameInput = findViewById(R.id.userName);
        final EditText userPasswordInput = findViewById(R.id.password);
        String userName = userNameInput.getText().toString();
        String userPassword = userPasswordInput.getText().toString();

        User user = new User(userName, userPassword);
        loadFromFile(FILE_NAME);

        // check if the user has registered
        if (userManager.isRegistered(user)) {
            // check the validation
            if (userManager.checkUserValidation(user)) {
                userManager.loginUser(user);
                Intent loginIntent = new Intent(getApplicationContext(), UserSpecificActivity.class);
                startActivity(loginIntent);
                // wrong information input
            } else {
                Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
            }
            // not registered
        } else {
            Toast.makeText(getApplicationContext(), "No such user, please register first", Toast.LENGTH_SHORT).show();
        }
    }


    public void onclickCreateAccount(View view) {
        Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(registerIntent);
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

package csc207.fall2018.gamecentreapp.GameCentreActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import csc207.fall2018.gamecentreapp.R;
//import csc207.fall2018.gamecentreapp.UserManager;

/**
 * A class dealing with Registering.
 */
public class RegisterActivity extends AppCompatActivity {

    /**
     * helper object storing information of current user.
     */
    private Session session; //Controller


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        session = Session.getInstance(this);
    }

    /**
     * Dealing with clicking on sign up button.
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
        // loadFromFile(FILE_NAME)
        session.registerAttempt(view, user);
    }

    /**
     * Dealing with clicking on already have an account.
     *
     * @param view A view of the model
     */
    public void onclickToLogin(View view) {
        Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(returnIntent);
    }

}

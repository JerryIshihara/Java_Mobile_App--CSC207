package csc207.fall2018.gamecentreapp.GameCentreActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import csc207.fall2018.gamecentreapp.R;


/**
 * A class dealing with main page.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * helper object storing information of current user.
     */
    private Session session; //Controller


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = Session.getInstance(this);
    }

    /**
     * Dealing with clicking on Login button.
     *
     * @param view a view of the model
     */
    public void onclickLoginButton(View view) {
        // first input information
        final EditText userNameInput = findViewById(R.id.userName);
        final EditText userPasswordInput = findViewById(R.id.password);
        String userName = userNameInput.getText().toString();
        String userPassword = userPasswordInput.getText().toString();

        User user = new User(userName, userPassword);
        session.loginAttempt(view, user);
    }


    /**
     * Dealing with clicking on 'create account'
     *
     * @param view A view of the model.
     */
    public void onclickCreateAccount(View view) {
        Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(registerIntent);
    }

}

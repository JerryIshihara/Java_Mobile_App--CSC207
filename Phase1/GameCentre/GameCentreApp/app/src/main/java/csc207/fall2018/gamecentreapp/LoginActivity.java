package csc207.fall2018.gamecentreapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A class dealing with the login page.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Deal with clicking  Login Attempt button.
     *
     * @param view A view of the model.
     */
    public void onClickLoginAttempt(View view) throws Exception {

        // first input information
        final EditText userNameInput = findViewById(R.id.usernameLoginText);
        final EditText userPasswordInput = findViewById(R.id.passwordLoginText);
        String userName = userNameInput.getText().toString();
        String userPassword = userPasswordInput.getText().toString();

        // create new User
        User newUser = new User(userName, userPassword);
        UserManager userManager = UserManager.getInstance();

        // check user validation
        boolean canLogin = (!userManager.isLoggedIn()) & userManager.checkUserValidation(newUser);
        if (canLogin) {
            userManager.logIn(newUser);
            Intent loginIntent = new Intent(getApplicationContext(), UserSpecificActivity.class);
            startActivity(loginIntent);
        } else if (!userManager.hasThisUser(newUser)) {
            TextView feedBack = findViewById(R.id.infoFeedBack);
            feedBack.setText(R.string.NeedRegister);
        } else if (!userManager.checkUserValidation(newUser)) {
            TextView feedBack = findViewById(R.id.infoFeedBack);
            feedBack.setText(R.string.wrongPassword);
        }
    }

    /**
     * Deal with clicking Return button
     *
     * @param view A view of the model.
     */
    public void onClickReturn(View view) {

        Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(returnIntent);
    }
}

package csc207.fall2018.gamecentreapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A class dealing with Registering.
 */
public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    /**
     * Dealing with clicking on Register button.
     *
     * @param view A view of the model
     */
    public void onclickRegisterButton(View view) throws Exception {

        // first input information
        final EditText userNameInput = (EditText) findViewById(R.id.usernameRegisterText);
        final EditText userPasswordInput = (EditText) findViewById(R.id.passwordRegisterText);
        String userName = userNameInput.getText().toString();
        String userPassword = userPasswordInput.getText().toString();

        // create new User
        User newUser = new User(userName, userPassword);
        UserManager userManager = UserManager.getInstance();

        // check user validation
        boolean canRegister = !userManager.hasThisUser(newUser);

        // forward
        if (canRegister) {
            userManager.signUp(newUser);
            Intent registerIntent = new Intent(getApplicationContext(), UserSpecificActivity.class);
            startActivity(registerIntent);
        } else if (userManager.hasThisUser(newUser)) {
            TextView feedBack = findViewById(R.id.infoFeedBack);
            feedBack.setText(R.string.ExistUser);
        }
    }

    /**
     * Dealing with clicking on Return button.
     *
     * @param view A view of the model
     */
    public void onClickReturn(View view) {
        Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(returnIntent);
    }
}

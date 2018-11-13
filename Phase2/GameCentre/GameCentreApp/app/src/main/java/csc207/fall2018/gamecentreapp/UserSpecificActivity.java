package csc207.fall2018.gamecentreapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import csc207.fall2018.gamecentreapp.SlidingTileGame.SlidingTileStartingActivity;

/**
 * A class dealing with user's playing the game
 */
public class UserSpecificActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_specific);

        // show username on top
        TextView userName = findViewById(R.id.centreUserName);
        String userNameInstance = UserManager.getCurrentUserName();
        userName.setText(userNameInstance);
    }

    /**
     * Deal with clicking Logout
     * @param view  a view of the model.
     */
    public void onClickLogOut(View view){
        UserManager userManager = UserManager.getInstance();
        userManager.logOut();
        Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(returnIntent);
    }

    /**
     * Deal with clicking launching sliding tiles game
     * @param view a view of the model.
     */
    public void launchSlidingTileGame(View view) {
        Intent newGame = new Intent(getApplicationContext(), SlidingTileStartingActivity.class);
        startActivity(newGame);
    }
}


package csc207.fall2018.gamecentreapp.GameCentreActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import csc207.fall2018.gamecentreapp.Dialogs.DeleteUserDialog;
import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareActivities.SubtractSquareGameCentreActivity;
import csc207.fall2018.gamecentreapp.Sudoku.SudokuActivity.SudokuGameStartingActivity;

import csc207.fall2018.gamecentreapp.slidingtiles.SlidingTileActivity.StartingActivity;

/**
 * A class dealing with user centre page
 */
public class UserSpecificActivity extends AppCompatActivity {


    /**
     * helper object storing information of current user
     */
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_specific);
        session = Session.getInstance(this);
        display();
    }


    /**
     * Dealing with clicking return
     *
     * @param view a view of the model.
     */
    public void onClickLogOut(View view) {
        session.logOut(view);
    }

    /**
     * Dealing with clicking on sliding tile.
     *
     * @param view a view of the model.
     */
    public void onClickSlidingTileGame(View view) {
        Intent slidingTileGameIntent = new Intent(getApplicationContext(), StartingActivity.class);
        startActivity(slidingTileGameIntent);
    }

    /**
     * Dealing with clicking on subtract square
     *
     * @param view a view of the model.
     */
    public void onclickSubtractSquareGame(View view) {
        Intent startSubtractSquare = new Intent(getApplicationContext(), SubtractSquareGameCentreActivity.class);
        startActivity(startSubtractSquare);
    }

    /**
     * Dealing with clicking on sudoku
     *
     * @param view a view of the model
     */
    public void onclickSudokugame(View view) {
        Intent startSudoku = new Intent(getApplicationContext(), SudokuGameStartingActivity.class);
        startActivity(startSudoku);
    }

    /**
     * Dealing with clicking on Delete User
     *
     * @param view a view of the model
     */
    public void onclickDeleteUser(View view) {
        openDialog();
    }

    /**
     * Dealing with clicking on delete all game data
     *
     * @param view a view of the model
     */
    public void onclickDeleteAllInfo(View view) {
        session.adminDeleteGameInfo(view);
    }

    private void display() {
        session.displayCurrentName(findViewById(R.id.centreUserName));
    }

    /**
     * Call DeleteUserDialog class to delete current user in the database.
     */
    private void openDialog() {
        DeleteUserDialog deleteUserDialog = new DeleteUserDialog();
        deleteUserDialog.show(getSupportFragmentManager(), "Delete User Info");
    }
}


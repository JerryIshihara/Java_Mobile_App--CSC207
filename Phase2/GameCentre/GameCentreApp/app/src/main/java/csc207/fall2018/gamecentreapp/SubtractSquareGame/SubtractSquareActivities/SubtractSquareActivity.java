package csc207.fall2018.gamecentreapp.SubtractSquareGame.SubtractSquareActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import csc207.fall2018.gamecentreapp.R;
import csc207.fall2018.gamecentreapp.Dialogs.UndoPaymentDialog;

public class SubtractSquareActivity extends AppCompatActivity {


    private SubtractSquareController subtractSquareController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract_square);

        subtractSquareController = new SubtractSquareController(this);
        subtractSquareController.startGame(findViewById(R.id.input));
    }

    /**
     * OnClickListener for the Button goBack. When clicked, navigated back to the
     * SubtractSquareGameCentreActivity.
     */
    public void onclickGoBack(View view) {
        subtractSquareController.exit(this);
        Intent goBackIntent = new Intent(getApplicationContext(), SubtractSquareGameCentreActivity.class);
        startActivity(goBackIntent);
    }

    /**
     * OnClickListener for the Button Enter. When clicked, enter the number in the EditText input.
     */
    public void onclickEnter(View view) {
        EditText input = findViewById(R.id.input);
        subtractSquareController.enterNumber(view, input);
    }


    /**
     * OnClickListener for the Button undo. When clicked, going back to the state before last input.
     */
    public void onclickUndo(View view) {
        boolean undo = subtractSquareController.undoMove(view);
        if (!undo) openDialog();

    }

    /**
     * Open the payment dialog.
     */
    private void openDialog() {
        UndoPaymentDialog undoPaymentDialog = new UndoPaymentDialog();
        undoPaymentDialog.show(getSupportFragmentManager(), "Payment");
    }

}

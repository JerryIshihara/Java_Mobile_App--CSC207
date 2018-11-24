package csc207.fall2018.gamecentreapp.Sudoku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import csc207.fall2018.gamecentreapp.R;

public class GameInterface extends AppCompatActivity {

    AnimationDrawable timeAnimation;
    private Integer levelMessage;
    private GridView gridOfSudoku;
    AlertDialog.Builder enterNumber;
    ArrayAdapter<Object> gridViewArrayAdapter;
    ArrayList<Object> allValue;
    EditText input;
    ArrayList<Integer> okPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_interface);
        gridOfSudoku = findViewById(R.id.sudokuGrid);
        levelMessage = getIntent().getExtras().getInt("Level_Message");
        input = new EditText(this);
        ImageView timeImage = (ImageView) findViewById(R.id.image_anim);
        timeImage.setBackgroundResource(R.drawable.timer_animation);
        timeAnimation = (AnimationDrawable) timeImage.getBackground();

        enterNumber = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Type in new number")
                .setView(input)
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position = getIntent().getExtras().getInt("position");
                        if (checkInvalidity(input.getText().toString())){
                            int newValue = Integer.parseInt(input.getText().toString());
                            enteredValue(position,newValue);
                            gridViewArrayAdapter.notifyDataSetChanged();
                        } else{
                            Toast.makeText(GameInterface.this, "Please enter a valid number",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null);
        generateGrid();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void generateGrid() {
        SudokuGenerator.getInstance(levelMessage).reset();
        ArrayList<ArrayList<Integer>> sudoku =
                SudokuGenerator.getInstance(levelMessage).getFinalSudoku();
        allValue = new ArrayList<>();
        okPosition = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            int x = i % 9;
            int y = i / 9;
            if (sudoku.get(x).get(y) == 0){
                okPosition.add(i);
                allValue.add("");
            }else{
                allValue.add(sudoku.get(x).get(y));
            }

        }
        gridViewArrayAdapter = new ArrayAdapter<Object>
                (this, android.R.layout.simple_list_item_1, allValue){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                int color = Color.parseColor("#DB7093");
                if (okPosition.contains(position)) {
                    view.setBackgroundColor(color);
                }
                return view;
            }
        };
        gridOfSudoku.setAdapter(gridViewArrayAdapter);
        gridOfSudoku.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (okPosition.contains(position)){
                            //Restate EditText input because the view is added to dialog layout before
                            //so it has a parent layout right now, must remove it or restate a new view.
                            getIntent().putExtra("position",position);
                            input = new EditText(GameInterface.this);
                            enterNumber.setView(input);
                            enterNumber.show();
                        }
                    }
                }
        );

    }


    public void startAgain(View v) {
        Button restart = (Button) v;
        Animation bounce = AnimationUtils.loadAnimation(GameInterface.this,R.anim.bounce);
        restart.setAnimation(bounce);
        generateGrid();
    }

    public void endGameCheck(View v){
        ArrayList<ArrayList<Integer>> sudoku
                = SudokuGenerator.getInstance(0).getFinalSudoku();


        int counter = 0;
        int currentPosition = 0;
        while(currentPosition < 81){
            int x = currentPosition / 9;
            int y = currentPosition % 9;
            if (SudokuGenerator.getInstance(0)
                    .checkAll(sudoku.get(x).get(y), x, y, sudoku)){
                counter++;
            }
            currentPosition++;
        }
        if (counter == 81){
            Toast.makeText(GameInterface.this, "Congratulation",
                    Toast.LENGTH_LONG).show();
            //------------Have timer stop and score logged at here---------------------
            //-------------------------------------------------------------------------
        }else{
            Toast.makeText(GameInterface.this, "Failed",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void enteredValue(int position, int newValue){
        allValue.set(position,newValue);
        SudokuGenerator.getInstance(0).changeValue(position,newValue);
    }

    public boolean checkInvalidity(String input){
        return (input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3")
                || input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7")
                || input.equals("8") || input.equals("9"));
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_to_right,R.anim.slide_to_left);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        timeAnimation.start();
    }


}



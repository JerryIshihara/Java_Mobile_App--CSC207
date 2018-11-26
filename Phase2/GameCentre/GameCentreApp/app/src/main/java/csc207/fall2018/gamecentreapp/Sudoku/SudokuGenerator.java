package csc207.fall2018.gamecentreapp.Sudoku;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class SudokuGenerator {


    private static SudokuGenerator instance;
    private Random rand= new Random();
    private ArrayList<ArrayList<Integer>> finalSudoku;


    private SudokuGenerator(int level){
        finalSudoku = constructor(level);
    }


    static SudokuGenerator getInstance(int level){
        if (instance == null){
            instance = new SudokuGenerator(level);
        }
        return instance;
    }

    private ArrayList<ArrayList<Integer>> constructor (int level){

        ArrayList<ArrayList<Integer>> grid = createBlankSudoku();
        ArrayList<ArrayList<Integer>> available = createAvailable();


        int pos = 0;
        while(pos < 81){
            int row = pos / 9;
            int col = pos % 9;

            if (available.get(pos).size() != 0){

                int i = rand.nextInt(available.get(pos).size());
                int number = available.get(pos).get(i);

                if (checkAll(number,row,col,grid)){
                    grid.get(row).set(col,number);
                    available.get(pos).remove(i);
                    pos++;
                }else {
                    available.get(pos).remove(i);
                }
            }else{
                grid.get(row).set(col,0);
                available.set(pos,oneToNine());
                pos--;
            }
        }
        generateHoles(grid,level);
        return grid;
    }


    private void generateHoles(ArrayList<ArrayList<Integer>> grid, int level){
        int numHoles = level * 9;
        ArrayList<Integer> posList = new ArrayList<>();
        for(int i=0; i<81; i++){
            posList.add(i);
        }
        Collections.shuffle(posList);
        for (int j=0; j < numHoles; j++){
            int row = posList.get(j) / 9;
            int col = posList.get(j) % 9;
            grid.get(row).set(col, 0);
        }
    }

    void reset(){
        instance = null;
    }


    ArrayList<ArrayList<Integer>> getFinalSudoku() {
        return finalSudoku;
    }

    void changeValue(int position, int value){
        int x = position % 9;
        int y = position / 9;
        finalSudoku.get(x).set(y,value);
    }

    private ArrayList<ArrayList<Integer>> createBlankSudoku(){
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            grid.add(i,new ArrayList<Integer>());
            for (int j = 0; j<=8; j++){
                //add check horizontal, vertical, and square method
                //if all conditions satisfies, then add this value.
                grid.get(i).add(0);
            }
        }
        return grid;
    }


    private ArrayList<Integer> oneToNine(){
        return new ArrayList<Integer>() {{
            add(1); add(2); add(3); add(4); add(5); add(6);
            add(7); add(8); add(9);
        }};
    }


    private ArrayList<ArrayList<Integer>> createAvailable(){
        ArrayList<ArrayList<Integer>> available = new ArrayList<>();
        for (int i = 0; i < 81; i++){
            available.add(i, oneToNine());
        }
        return available;
    }



    private boolean checkHorizontal(int inputValue, int row, int col, ArrayList<ArrayList<Integer>> grid){
        for (int i = 0; i< 9; i++){
            if (inputValue == grid.get(row).get(i) && i!=col){
                return false;
            }
        }
        return true;
    }


    private boolean checkVertical(int inputValue, int row, int col, ArrayList<ArrayList<Integer>> grid){
        for (int i = 0; i < 9; i++){
            if (inputValue == grid.get(i).get(col) && i!=row){
                return false;
            }
        }
        return true;
    }

    private boolean checkSquare(int inputValue, int row, int col, ArrayList<ArrayList<Integer>> grid){
        int xGroup = (col/3);
        int yGroup = (row/3);
        for (int i = 0; i<3; i++){
            for (int j = 0; j< 3; j++){
                if (inputValue == grid.get(yGroup*3+i).get(xGroup*3+j) && (row!= (yGroup*3+i) || col!=(xGroup*3+j))){
                    return false;
                }
            }
        }
        return true;
    }


    boolean checkAll(int input, int row, int col, ArrayList<ArrayList<Integer>> grid){
        return (checkHorizontal(input,row,col,grid)
                && checkVertical(input,row,col,grid)
                &&checkSquare(input,row,col,grid));
    }


//    public void print(ArrayList<ArrayList<Integer>> grid){
//        for (int x = 0; x<=8; x++){
//            for (int y = 0; y<=8; y++){
//                System.out.print(grid.get(x).get(y) + " ");
//            }
//            System.out.println();
//        }
//    }
}





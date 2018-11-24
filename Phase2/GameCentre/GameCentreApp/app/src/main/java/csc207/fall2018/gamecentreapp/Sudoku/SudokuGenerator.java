package csc207.fall2018.gamecentreapp.Sudoku;


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SudokuGenerator {


    private static SudokuGenerator instance;
    private Random rand= new Random();
    private ArrayList<ArrayList<Integer>> finalSudoku;

    private SudokuGenerator(int level){
        finalSudoku = constructor(level);
    }


    public static SudokuGenerator getInstance(int level){
        if (instance == null){
            instance = new SudokuGenerator(level);
        }
        return instance;
    }

    public ArrayList<ArrayList<Integer>> constructor (int level){

        ArrayList<ArrayList<Integer>> grid = initializeZero();
        ArrayList<ArrayList<Integer>> available = createAvailable();


        int currentPosition = 0;
        while(currentPosition < 81){
            int x = currentPosition / 9;
            int y = currentPosition % 9;

            if (available.get(currentPosition).size() != 0){

                int i = rand.nextInt(available.get(currentPosition).size());
                int number = available.get(currentPosition).get(i);

                if (checkAll(number,x,y,grid)){
                    grid.get(x).set(y,number);
                    available.get(currentPosition).remove(i);
                    currentPosition++;
                }else {
                    available.get(currentPosition).remove(i);
                }
            }else{
                grid.get(x).set(y,0);
                available.set(currentPosition,oneToNine());
                currentPosition--;
            }
        }
        digger(grid,level);
        return grid;
    }


    private void digger (ArrayList<ArrayList<Integer>> grid, int holes){
        for (int x = 0; x<=8; x++){
            int[] rand = {1,2,3,4,5,6,7,8,0};
            shuffleArray(rand);
            for (int y = 1; y <= holes; y++){
                grid.get(x).set(rand[y],0);

            }
        }
    }

    public void reset(){
        instance = null;
    }


    public ArrayList<ArrayList<Integer>> getFinalSudoku() {
        return finalSudoku;
    }

    ArrayList<ArrayList<Integer>> changeValue(int position, int value){
        int x = position % 9;
        int y = position / 9;
        finalSudoku.get(x).set(y,value);
        return finalSudoku;
    }

    private ArrayList<ArrayList<Integer>> initializeZero(){
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        for (int x = 0; x<=8; x++){
            grid.add(x,new ArrayList<Integer>());
            for (int y = 0; y<=8; y++){
                //add check horizontal, vertical, and square method
                //if all conditions satisfies, then add this value.
                grid.get(x).add(0);
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
        ArrayList<Integer> rand = oneToNine();
        for (int i = 0; i < 81; i++){
            rand = new ArrayList<>(rand);
            available.add(i, rand);
        }
        return available;
    }


    private void shuffleArray(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }


    public boolean checkHorizontal(int inputValue, int x, int y, ArrayList<ArrayList<Integer>> grid){
        for (int i = 0; i<=8; i++){
            if (inputValue == grid.get(i).get(y)){
                return false;
            }
        }
        return true;
    }


    public boolean checkVertical(int inputValue, int x, int y, ArrayList<ArrayList<Integer>> grid){
        for (int i = 0; i<=8; i++){
            if (inputValue == grid.get(x).get(i)){
                return false;
            }
        }
        return true;
    }

    public boolean checkSquare(int inputValue, int x, int y, ArrayList<ArrayList<Integer>> grid){
        int xGroup = (x/3);
        int yGroup = (y/3);
        for (int i = 0; i<= 2; i++){
            for (int j = 0; j<= 2; j++){
                if (inputValue == grid.get(xGroup*3+i).get(yGroup*3+j)){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean checkAll(int input, int x, int y, ArrayList<ArrayList<Integer>> grid){
        return (checkHorizontal(input,x,y,grid)
                && checkVertical(input,x,y,grid)
                &&checkSquare(input,x,y,grid));
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





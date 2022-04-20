package sudoku.gamelogic.utils;

import sudoku.model.Grid;
import java.util.ArrayList;


// Static class used for checking if Sudoku solution user submits is valid and correct

public final class SolutionCheckerUtil {

    // written for good practice in using static, utility class
    // ensures this class won't be overridden/ instantiated
    private SolutionCheckerUtil(){
        throw new AssertionError("not mean to be instantiated");
    }

    // If grid total (value of all the cells) is less than (gridSize*1), then that means that the grid is only partially filled, thus solution fails
    private static boolean isGridFilled(Grid grid, int size){
        return (grid.getGridTotal() < size);
    }

    // used to verify whether one row/ column (list of Cells) contain the set of 1 - (gridSize)
    private static boolean checkRowOrColContainsSequence(ArrayList<Integer> values){
        for(int index=1; index<10; index++){
            if(!values.contains(index)){
                return false;
            }
        }
        return true;
    }


    // Combines the solution checking methods to produce a string to notify user why solution fails
    public static String checkSolution(Grid grid){
        String message = "";
        // check if grid is filled or not
        message = "Check if grid is filled...\n";
        if(isGridFilled(grid, grid.getGridSize())){
            return message + "Solution is incorrect! Grid is only partially filled or empty!";
        }

        // check the rows + column
        message += "Checking rows...\n";
        for(Grid.Row r: grid.getGrid()){
            if(!checkRowOrColContainsSequence(grid.getArrayOfCellValuesInRow(r.getRowOfCells()))){
                return message + "Solution is incorrect! A row does not contain the correct sequence";
            }
        }

        message += "Checking columns...\n";
        for(int index=0; index<9; index++){
            if(!checkRowOrColContainsSequence(grid.getArrayOfCellValuesInRow(grid.getGridColumn(index)))){
                return message + "Solution is incorrect! A column does not contain the correct sequence";
            }
        }
       return ("Solution is correct!");
    }
}

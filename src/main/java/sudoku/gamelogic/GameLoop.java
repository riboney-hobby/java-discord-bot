package sudoku.gamelogic;

import sudoku.model.Grid;

import java.util.HashMap;

public abstract class GameLoop {


    // MOVE SOMEWHERE ELSE
    public static String cleanInput(String input) {
        String modifiedInput = input.replaceAll("\\s", "");
        return modifiedInput.replaceAll("\\W", "");
    }


    public void setCellValue(HashMap<String, Integer> move, Grid grid) {
        // Variables to hold data from digit of playerMove
        int x = move.get("X") - 1;
        int y = move.get("Y") - 1;
        int value = move.get("Value");

        // gets the specified cell
        // First, get the row from the Y value
        // Then get the cell from the X value
        Grid.Row row = grid.getGrid().get(y);
        Grid.Cell cell = row.getRowOfCells().get(x);

        cell.setValue(value);
    }

    // Process User Input
    //public abstract String inputLoop();

    public abstract void gameStartUp();

    //public abstract void runGame();
}

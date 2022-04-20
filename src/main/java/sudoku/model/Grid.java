package sudoku.model;

import java.io.Serializable;
import java.util.ArrayList;

// Grid object represents arraylist of rows

public class Grid implements Serializable {

    // Display

    // Represents one "cell" on gameboard
    // If cell value is 0, then display placeholder (can be "." or "*")
    // Otherwise, cells should display numerical values for sudoku
    public class Cell {

        // Represents value of the cell
        private int value;

        // Constructors
        public Cell(int value){
            this.value = value;
        }

        // Getters & Setters
        public int getValue() {
            return value;
        }
        public void setValue(int value){
            this.value = value;
        }

        // Compares two Cell objects by their `value`
        @Override
        public boolean equals(Object object) {
            if(object == null) {
                return false;
            }

            if(getClass() != object.getClass()) {
                return false;
            }

            Cell compared = (Cell) object;

            if(this.getValue() != compared.getValue()){
                return false;
            }
            return true;
        }

        // this is supposed to be for displaying Cell on board
        // If value is "0" (no value), then it should be displayed with placeholder "."
        // refactor this to another class that handles displaying objects
        public String cellToString(String placeHolder) {
            return (this.getValue() == 0) ? (" " + placeHolder + " ") : (" " + this.value + " "); //change this later to account for custom whitepspace input
        }
    }

    // Row object represents one ArrayList of Cells
    public class Row {

        // Each row consists of an arraylist of Cells; this represents the rows of a sudoku board
        private ArrayList<Cell> rowOfCells;

        // Constructor
        public Row() {
            this.rowOfCells = new ArrayList<>();
        }


        // Getters & Setters
        public ArrayList<Cell> getRowOfCells() {
            return this.rowOfCells;
        }

        public void addCellToRow(Cell cell) {
            this.rowOfCells.add(cell);
        }

        // Returns the sum of all values of cells in a row
        // Used for calculating if row is valid for Sudoku solution
        public int getRowTotal(){
            int total  = 0;
            for(Cell cell: this.rowOfCells){
                total += cell.getValue();
            }

            return total;
        }

        // used for displaying the game board
        // Creates the row for board game
        public String rowToString(String placeHolder){
            String rowString = "";
            for(int index=0; index<this.rowOfCells.size(); index++){
                Grid.Cell cell = this.rowOfCells.get(index);
                rowString += ((index+1) % 3 == 0) ? (cell.cellToString(placeHolder) + " | ") : (cell.cellToString(placeHolder));
            }
            return rowString;
        }
    }

    // GRID CLASS STARTS HERE

    // Is supposed to represent the game board
    // gridRows represents ArrayList of Rows (which represents ArrayList of cells)
    private ArrayList<Row> gridRows;

    // Used to configure size of the gameboard (9x9, 4x4, etc)
    private int gridSize;


    // Constructors
    public Grid(int gridSize) {
        this.gridSize = gridSize;
        this.gridRows = new ArrayList<>();
    }

    // initializes the gridRows, by adding cells to each row object, and then adding row object to Grid
    /*
        Y
        Y
        Y
        0 X X X     --> this is what the y and x coordinates refer to
     */
    public void createGrid() {
        // creates "gridSize" amount of rows
        for(int yCoordinateIndex = 0; yCoordinateIndex < this.gridSize; yCoordinateIndex++) {
            Row newRow = new Row();

            // creates "gridSize" amount of cells for each row
            for(int xCoordinateIndex = 0; xCoordinateIndex < this.gridSize; xCoordinateIndex++) {
                Cell newCell = new Cell(0);
                newRow.addCellToRow(newCell);
            }

            // add "row" to Grid
            this.gridRows.add(newRow);
        }
    }

    // Same as createGrid(), except this initializes the Cells with values to recreate/ copy sudoku boards
    public void createGrid(String sample){
        int counter = 0;
        for(int yCoordinateIndex = 0; yCoordinateIndex < this.gridSize; yCoordinateIndex++) {
            Row newRow = new Row();

            for(int xCoordinateIndex = 0; xCoordinateIndex < this.gridSize; xCoordinateIndex++) {
                Cell newCell = new Cell(Character.getNumericValue(sample.charAt(counter)));
                newRow.addCellToRow(newCell);
                counter++;
            }

            this.gridRows.add(newRow);
        }
    }


    // returns a column of cells in the grid (so for column numberX, column of cells = row0.get(numberX), row1.get(numberX), row2.get(numberX), ..etc
    public ArrayList<Cell> getGridColumn(int columnNumber){
        ArrayList<Cell> column = new ArrayList<>();
        for(Row r: this.gridRows){
            column.add(r.getRowOfCells().get(columnNumber));
        }
        return column;
    }

    // this method produces the Int values of cells in an ArrayList (either row or Column)
    // used for solution-checking purposes
    public ArrayList<Integer> getArrayOfCellValuesInRow(ArrayList<Cell> rowOrCol){
        ArrayList<Integer> arrayOfCellValues = new ArrayList<>();
        for(Cell cell: rowOrCol){
            arrayOfCellValues.add(cell.getValue());
        }
        return arrayOfCellValues;
    }

    public ArrayList<Row> getGrid(){
        return this.gridRows;
    }

    // outputs the total values of all cells in grid
    // used solution-checking purposes
    public int getGridTotal(){
        int total = 0;
        for(Row row: this.gridRows){
            total += row.getRowTotal();
        }

        return total;
    }

    public int getGridSize(){
        return this.gridSize;
    }

    // Displays game board; placeHolder is for representing empty cells
    public String gridAsString(String placeHolder){
        String gridString = "";
        int counter = 1;
        for(Grid.Row row: this.gridRows){
            gridString += (counter % 3 == 0) ? (row.rowToString(placeHolder) + "\n-----------------------------------\n") : (row.rowToString(placeHolder) + "\n");
            counter++;
        }
        return gridString;
    }

    // Solely for Discord purposes; refactor later
    public String gridAsString(String placeHolder, String format){
        return format + gridAsString(placeHolder) + "\n" + format;
    }
}









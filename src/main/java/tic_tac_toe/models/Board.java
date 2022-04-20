package tic_tac_toe.models;

import java.util.ArrayList;

public class Board {

    private ArrayList<Block> board;
    private char[][] boardToCharArray;

    public Board() {
        this.board = new ArrayList<Block>();
        this.boardToCharArray = new char[3][3];
        this.boardToCharArray = getBoardValues();
        createBoard();
    }

    private void createBoard() {
        this.board.add(new UpperCenterBlock());
        this.board.add(new UpperCenterBlock());
        this.board.add(new BottomBlock());
    }

    public ArrayList<Block> getBoard(){
        return this.board;
    }

    public char[][] getBoardValues(){
        for(int rowIndex=0; rowIndex<this.boardToCharArray.length; rowIndex++){
            for(int columnIndex=0; columnIndex<this.boardToCharArray[rowIndex].length; columnIndex++){
                this.boardToCharArray[rowIndex][columnIndex] = this.board.get(rowIndex).getColumnValue(columnIndex+1);
            }
        }
        return this.boardToCharArray;
    }

    public char[] getBoardRowValues(int row){
        char[] boardRow = new char[3];
        for(int index=0; index<boardRow.length;index++){
            boardRow[index] = this.boardToCharArray[row][index];
        }
        return boardRow;
    }

    // NOT DONE YET

//    public char[] getBoardColumnValues(int column){
////
////        char[] boardColumn = new char[3];
////        for(int index=0; index<boardColumn.length;index++){
////            boardColumn[index] = this.boardToCharArray[column][index];
////        }
////    }
////
////
////
////    public char[] getBoardDiagonalLeftToRightValues(){
////        char[] boardDiagonal = new char[3];
////        char[] boardRow = getBoardRowValues();
////        int index = 0;
////        while(index < 3){
////            boardDiagonal[index] =
////        }
////    }

    // for displaying board
    @Override
    public String toString() {
        String s = "";
        for(Block b: this.board) {
            s += b.toString();
        }
        return s + "\n";
    }
}

package tic_tac_toe.logic;

import tic_tac_toe.models.Board;
import tic_tac_toe.models.Player;

public class InputOperations {

    // string prompt for input
    public String promptForInput(Player pl) {
        return (pl.name() + ", please enter your move in X,Y format, where x and y are coordinates ranging from 1-3") + ("\nPlease enter 'q' to quit.\n");
    }

    // validate input
    public boolean isInputValid(String input){
        // Takes user input, and removes all characters/ spaces other than alphanumeric characters
        String modifiedInput = input.replaceAll("\\s\\w", "");
        // if less than 2 in size (input should be x, y)
        if(modifiedInput.length() != 2){
            return false;
        }

        if(!Character.isDigit(modifiedInput.charAt(0)) || !Character.isDigit(modifiedInput.charAt(1))){
            return false;
        }

        // Checks for range of input; range should be 1-3
        int xCoordinate = Integer.parseInt(String.valueOf(modifiedInput.charAt(0)));
        if(xCoordinate > 3 || xCoordinate < 1){
            return false;
        }

        int yCoordinate = Integer.parseInt(String.valueOf(modifiedInput.charAt(1)));
        if(yCoordinate > 3 || yCoordinate < 1){
            return false;
        }

        return true;
    }

    public void setMoveOnBoard(Board board, Player pl, int x, int y) {
        board.getBoard().get(y).setCrossOrNoughtInRow(pl.toString().charAt(0), x);
    }

    // Quit game? Or keep playing
    private boolean keepPlaying(String input){
        if(!input.toLowerCase().startsWith("q")){
            return true;
        }
        else{
            return false;
        }
    }
}

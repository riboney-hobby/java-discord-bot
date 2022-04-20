package sudoku.platform.cli;

import sudoku.gamelogic.utils.MoveValidationUtil;
import sudoku.gamelogic.utils.SolutionCheckerUtil;
import sudoku.gamelogic.GameLoop;
import sudoku.model.GameTexts;
import sudoku.model.Grid;
import sudoku.model.PlayerMove;

import java.util.Scanner;

public class ConsoleGameLoop extends GameLoop {

	// Class Variables
	private Grid grid;
	private Scanner scanner;
	private String input;
	private String preset;
	
	// Constructor
	public ConsoleGameLoop(Grid grid, Scanner scanner, String preset){
		this.grid = grid;
		this.scanner = scanner;
		this.input = "";
		this.preset = preset;
	}

	public ConsoleGameLoop(Grid grid, Scanner scanner) {
		this(grid, scanner, "");
	}

	
	// Methods

	//@Override
	public String inputLoop(){
		boolean keepInputting = true;
		String unCleanInput = "", cleanedInput = "";

		while(keepInputting) {
			System.out.print(GameTexts.userPromptAsString);
			unCleanInput = this.scanner.nextLine();

			// End game
			if(unCleanInput.equals("-1")) {
				System.out.println(GameTexts.endGamePromptString);
				return "-1";
			}

			if(unCleanInput.toLowerCase().startsWith("c")){
				System.out.println("\nChecking grid...");
				return "c";
			}
//			else if(input.toLowerCase().startsWith("c")){
//				// add logic for checking board for win
//			}
			else{
				cleanedInput = cleanInput(unCleanInput);
			}

			if(MoveValidationUtil.inputIsValid(cleanedInput, this.grid.getGridSize())) {
				keepInputting = false;
			}
			else{
				System.out.println(GameTexts.wrongInputString);
				return "wrong input";
			}
		}
		return cleanedInput;
	}

	@Override
	public void gameStartUp(){
		// Create gameboard with the preset value
		if(!this.preset.isEmpty()){
			this.grid.createGrid(this.preset);
		}
		// Create empty gameboard if preset is empty or if grid total is zero (i.e. has no values in it)
		else if(this.grid.getGridTotal() == 0){
			this.grid.createGrid();
		}

	}

	//@Override
	public void runGame(){
		PlayerMove move;
		gameStartUp();
		boolean keepPlaying = true;

		while(keepPlaying){
			System.out.println(GameTexts.titleGame);
			System.out.println(this.grid.gridAsString("*"));

			this.input = inputLoop();

			// end game/ break loop
			if(this.input.equals("-1")){
				break;
			}

			if(this.input.equals("c")){
				System.out.println(SolutionCheckerUtil.checkSolution(this.grid));
				break;
			}

			// set move
			move = new PlayerMove(this.input);
			setCellValue(move.getMoveMap(), this.grid);
		}

//		// Ask user to save grid object
//		System.out.print("Do you want to save this board to file? Enter Yes or No ");
//		char response = this.scanner.nextLine().toLowerCase().charAt(0);
//		if(response == 'y'){
//			saveGridToFile();
//		}

		System.out.println("Game has ended!");
	}

//
}

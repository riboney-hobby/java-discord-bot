package sudoku.platform.cli;

import sudoku.model.Grid;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class ConsoleApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//String preset = SudokuMakerUtil.createPuzzle(9);
		//runGameWithSample(scanner, preset);
		runGameNormal(scanner);

//		String preset = "7\t2\t6\t4\t9\t3\t8\t1\t5\n" +
//				"3\t1\t5\t7\t2\t8\t9\t4\t6\n" +
//				"4\t8\t9\t6\t5\t1\t2\t3\t7\n" +
//				"8\t5\t2\t1\t4\t7\t6\t9\t3\n" +
//				"6\t7\t3\t9\t8\t5\t1\t2\t4\n" +
//				"9\t4\t1\t3\t6\t2\t7\t5\t8\n" +
//				"1\t9\t4\t8\t3\t6\t5\t7\t2\n" +
//				"5\t6\t7\t2\t1\t4\t3\t8\t9\n" +
//				"2\t3\t8\t5\t7\t9\t4\t6\t1\n";
		//System.out.println(preset);
		//preset = preset.replaceAll("\\s", "");
		//runGameNormal(scanner);
		//runGameWithFileObject(scanner);
	}

	public static Object getObjectFromFile(File file){
		try{
			FileInputStream in = new FileInputStream(file);
			ObjectInputStream objIn = new ObjectInputStream(in);

			Object obj = objIn.readObject();
			System.out.println("Object has been read from the file");
			objIn.close();
			return obj;
		} catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	// temporary methods/ refactor later

	public static void runGameWithFileObject(Scanner scanner){
		File file = new File("../Collab-Sudoku-Solver/res/sudoku_grid");
		Grid grid = (Grid) getObjectFromFile(file);
		ConsoleGameLoop consoleGameLoop = new ConsoleGameLoop(grid, scanner);

		consoleGameLoop.runGame();
	}

	public static void runGameWithSample(Scanner scanner, String preset){
		Grid grid = new Grid(9);
		ConsoleGameLoop consoleGameLoop = new ConsoleGameLoop(grid, scanner, preset);
		consoleGameLoop.runGame();
	}

	public static void runGameNormal(Scanner scanner){
		Grid grid = new Grid(9);
		ConsoleGameLoop consoleGameLoop = new ConsoleGameLoop(grid, scanner);

		consoleGameLoop.runGame();
	}
}

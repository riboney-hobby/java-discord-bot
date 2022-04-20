package sudoku.gamelogic.utils;

// Retrieve sudoku samples randomly from txt files (one sample, one file)
// method to take sample, replace 81-17 characters with 0, and return the sample


import sudoku.gamelogic.GameLoop;
import sudoku.platform.cli.ConsoleGameLoop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public final class SudokuMakerUtil {

    // It is said that for utility classes, its good to declare class as final, and to create private no-arg constructors that throws an exception
    private SudokuMakerUtil(){
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static String createPuzzle(int size) {
        String sample = randomSample(size);
        int totValues = size*size;
        StringBuilder sampleCleaned = new StringBuilder(GameLoop.cleanInput(sample));
        Random num = new Random();
        for(int index=0; index<(totValues); index++){
            int indexRand = num.nextInt(totValues);
            sampleCleaned.setCharAt(indexRand, '0');
        }
        return sampleCleaned.toString();
    }

    /*
        TO-DOS:
            - Add file containing 4x4 puzzles
            - Improve randomSample method to directly return line from File, instead of storing entire file in array, line by line
            - Handle FileNotException instead of throwing it
     */

    private static String randomSample(int size){
        File file = null;
        ArrayList<String> sample = new ArrayList<>();
        Random random = new Random();
        if(size == 9){
            file = new File("./src/main/resources/nine_by_nine.txt");
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scanner.hasNextLine()){
            sample.add(scanner.nextLine());
        }

        return sample.get(random.nextInt(sample.size()));
    }
}

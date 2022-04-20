package sudoku.gamelogic.utils;

// This class checks if user made valid input
public final class MoveValidationUtil {

    // written for good practice in using static, utility class
    // ensures this class won't be overridden/ instantiated
    private MoveValidationUtil(){
        throw new AssertionError("not for instantiation");
    }

    // checks if input passes validation tests
    public static boolean inputIsValid(String input, int range) {
        if(inputIsCorrectLength(input) && inputsAreIntegers(input) && inputsAreInRange(input, range)) {
            return true;
        }
        return false;
    }

    // moves consist of string with size 3 (X-Y-VALUE); check if input string is 3 in size (see PlayerMove.java)
    private static boolean inputIsCorrectLength(String input) {
        return input.length() == 3;
    }

    // check if value is within 0-range
    // Example, for 9x9 grid, move is invalid if it is 10 or -1
    private static boolean validateRange(int num, int range) {
        return (num >=0) && (num<=range);
    }

    // runs validateRange on each digit in String
    private static boolean inputsAreInRange(String input, int range) {
        int number;
        for(int index = 0; index < input.length(); index++) {
            number = Integer.parseInt(input.charAt(index) + "");
            if(!validateRange(number, range)){
                return false;
            }
        }
        return true;
    }

    // check if string is only numeric characters
    // See (PlayerMove.java)
    private static boolean inputsAreIntegers(String input) {
        boolean charIsInteger;
        for(int index = 0; index < input.length(); index++) {
            charIsInteger = Character.isDigit(input.charAt(index));
            if(!charIsInteger) {
                return false;
            }
        }
        return true;
    }
}

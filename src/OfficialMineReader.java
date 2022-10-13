import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * TCSS 360 A
 * Assignment 1: Minesweeper
 * 10/10/22
 *
 * @author Nicholas Zhuk, Jashanpreet Jandu, Kevin Nguyen
 * @version 1.0
 */

/**
 * This method reads in the input and output text files.
 */
public class OfficialMineReader {

    private final String OUTPUT_FILE = ".\\src\\Minefield-Output.txt";
    private final File MY_INPUT;

    private String mineOutputStr;
    private List<char[][]> mineList = new ArrayList<>();
    private char[][] mineArray;

    private boolean fileRead = false;
    private boolean fileWrote = false;


    /**
     * Calls methods needed to read and write the input file.
     */
    OfficialMineReader(final File THE_INPUT) throws IOException {
        Scanner scan = new Scanner(System.in);
        MY_INPUT = THE_INPUT;
        readFile(scan);
        convertMines();
        writeFile();
    }

    /**
     * Scans through file and uses .nextInt() to grab the m and n value of the mineField
     * Uses the firstNum and secondNum values to initialize a mineField array with the proper dimensions
     * Scanner iterates through File to insert '*' mines into arrays or zeros.
     * If a mine is inserted into array, updateMines() is called at that location in the array, and the array
     * updates the surrounding elements
     */
    private void readFile(Scanner scanner) throws IOException {

        fileRead = true;
        int firstNum = 0;   //y value
        int secondNum = 0; //x value
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                firstNum = scanner.nextInt();
                secondNum = scanner.nextInt();
                if (firstNum == 0 && secondNum == 0) {
                    break;
                }
            }
            scanner.nextLine();
            char[][] mineField = new char[firstNum + 2][secondNum + 2];
            for (int i = 1; i < mineField.length - 1; i++) {
                String c = scanner.nextLine();
                for (int j = 1; j < mineField[i].length - 1; j++) {
                    if (c.charAt(j - 1) == '*') {
                        mineField[i][j] = '*';

                        mineField = updateMines(i, j, mineField);
                        mineArray = mineField;
                    } else if (!Character.isDigit(mineField[i][j])) {
                        mineField[i][j] = '0';
                    }
                }
            }
            mineList.add(mineField);
        }
    }

    /**
     * Increments the 8 elements around the mine to indicate that a mine is there
     *
     * @param theY  is the index indicating where along the Y axis the mine is
     * @param theX  is the index indicating where along the X axis the mine is
     * @param array is the 2D array that is being updated
     */
    public char[][] updateMines(int theY, int theX, char[][] array) {


        /** below the mine
         * 000
         * 0*0
         * 0X0
         */
        if (Character.isDigit(array[theY + 1][theX])) {
            array[theY + 1][theX]++;
        } else if (array[theY + 1][theX] != '*') {
            array[theY + 1][theX] = '1';
        }

        /** below and to the left of the mine
         * 000
         * 0*0
         * X00
         */
        if (Character.isDigit(array[theY + 1][theX - 1])) {
            array[theY + 1][theX - 1]++;
        } else if (array[theY + 1][theX - 1] != '*') {
            array[theY + 1][theX - 1] = '1';
        }

        /** below and to the right of the mine
         * 000
         * 0*0
         * 00X
         */
        if (Character.isDigit(array[theY + 1][theX + 1])) {
            array[theY + 1][theX + 1]++;
        } else if (array[theY + 1][theX + 1] != '*') {
            array[theY + 1][theX + 1] = '1';
        }

        /** to the right of the mine
         * 000
         * 0*X
         * 000
         */
        if (Character.isDigit(array[theY][theX + 1])) {
            array[theY][theX + 1]++;
        } else if (array[theY][theX + 1] != '*') {
            array[theY][theX + 1] = '1';
        }

        /** to the left of the mine
         * 000
         * X*0
         * 000
         */
        if (Character.isDigit(array[theY][theX - 1])) {
            array[theY][theX - 1]++;
        } else if (array[theY][theX - 1] != '*') {
            array[theY][theX - 1] = '1';
        }

        /** above and to the right of the mine
         * 00X
         * 0*0
         * 000
         */
        if (Character.isDigit(array[theY - 1][theX + 1])) {
            array[theY - 1][theX + 1]++;
        } else if (array[theY - 1][theX + 1] != '*') {
            array[theY - 1][theX + 1] = '1';
        }

        /** above the mine
         * 0X0
         * 0*0
         * 000
         */
        if (Character.isDigit(array[theY - 1][theX])) {
            array[theY - 1][theX]++;
        } else if (array[theY - 1][theX] != '*') {
            array[theY - 1][theX] = '1';
        }

        /** above and to the left of the mine
         * X00
         * 0*0
         * 000
         */
        if (Character.isDigit(array[theY - 1][theX - 1])) {
            array[theY - 1][theX - 1]++;
        } else if (array[theY - 1][theX - 1] != '*') {
            array[theY - 1][theX - 1] = '1';
        }

        return array;
    }

    /**
     * this method will convert the field names and
     */
    private void convertMines() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < mineList.size(); i++) {
            str.append("Field #").append(i + 1).append(":\n");
            char[][] mineArray = mineList.get(i);
            for (int j = 1; j < mineArray.length - 1; j++) {
                for (int k = 1; k < mineArray[j].length - 1; k++) {
                    str.append(mineArray[j][k]);
                }
                str.append("\n");
            }
            str.append("\n");
        }
        mineOutputStr = str.toString();
    }

    /**
     * This method will print the minefield to.
     */
    private void writeFile() throws IOException {
        try (PrintStream out = new PrintStream(new FileOutputStream(OUTPUT_FILE))) {
            out.print(mineOutputStr);
            fileWrote = true;
        }
    }

    /**
     * This is used for the JNUnit Testing.
     * @return fileRead is the boolean that determines that MineReader reads the file.
     */
    public boolean isFileRead(){
        return fileRead;
    }

    /**
     * This is used for the JNUnit Testing.
     * @return fileWrote is the boolean that determines that MineReader writes the file.
     */
    public boolean isFileWrote(){
        return fileWrote;
    }


}

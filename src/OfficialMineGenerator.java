import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TCSS 360 A
 * Assignment 1: Minesweeper
 * 10/10/22
 *
 * @author Nicholas Zhuk, Jashanpreet Jandu, Kevin Nguyen
 * @version 1.0
 * <p>
 * This class is where the minefield cases are generated.
 */

public class OfficialMineGenerator {
    private final String outputFileName = ".\\Official-Minefield-Input.txt";
    private List<char[][]> mineList = new ArrayList<>();
    private String mineField = "";
    private char[][] mineFieldArr;


    /**
     * Calls methods needed to read and write the input files.
     *
     * @throws IOException
     */

    OfficialMineGenerator(final int theY, final int theX, final double thePer) throws IOException {

        buildMineArray(theY, theX, thePer);
        buildMineString();
        writeFile();
    }




    /**
     * Generates a 2D array representing the minefield.
     *
     * @param theRow         is the row of the 2D array
     * @param theCol         is the column of the 2D array.
     * @param theMinePercent is the percentage of which a mine will appear in the 2D array.
     */
    private void buildMineArray(int theRow, int theCol, double theMinePercent) {
        char[][] arrayField = new char[theRow][theCol];
        Random random = new Random();
        double r;
        for (int i = 0; i < arrayField.length; i++) {
            for (int j = 0; j < arrayField[i].length; j++) {
                r = random.nextDouble();
                if (r < theMinePercent) {
                    arrayField[i][j] = '*';
                } else {
                    arrayField[i][j] = '.';
                }
            }
        }
        mineFieldArr = arrayField;
        mineList.add(arrayField);
    }

    /**
     * this method will build the minefield and make sure that the first mine is not a mine
     */
    private void buildMineString() {
        StringBuilder str = new StringBuilder();
        for (char[][] array : mineList) {
            if (array.length != 0) {
                str.append(array.length).append(" ").append(array[0].length).append("\n");
            } else {
                str.append("0 0\n");
            }
            for (char[] chars : array) {
                for (char aChar : chars) {
                    str.append(aChar);
                }
                str.append("\n");
            }
        }
        mineField = str.toString();
    }

    /**
     * This method will print the minefield.
     *
     * @throws IOException
     */
    private void writeFile() throws IOException {
        try (PrintStream out = new PrintStream(new FileOutputStream(outputFileName))) {
            out.print(mineField);
        }
    }

    /**
     * This is used for the JNUnit Testing.
     * @return mineFieldArr is the built array that uses the parameters to generate an array
     */
    public char[][] getMineFieldArr(){
        return mineFieldArr;
    }

    /**
     * This is used for the JNUnit Testing.
     * @return mineField is the output generated string array.
     */
    public String getOutputString(){
        return mineField;
    }
}

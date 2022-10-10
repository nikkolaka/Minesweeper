import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MineReader {
    private final String inputFileName = ".\\src\\Minefield-Input.txt";
    private final String outputFileName = ".\\src\\Minefield-Output.txt";
    private String mineInputStr;
    private String mineOutputStr;
    private List<char[][]> mineList = new ArrayList<>();




    public MineReader() throws IOException {
        readFile();
        convertMines();
        writeFile();
    }
    //from file

    private void readFile() throws IOException {
        mineInputStr = Files.readString(Paths.get(inputFileName));
        Scanner scanner = new Scanner(mineInputStr);
        int firstNum = 0;
        int secondNum = 0;
        while(scanner.hasNext()){
            if(scanner.hasNextInt()){
                firstNum = scanner.nextInt();
                secondNum = scanner.nextInt();
            }
            scanner.nextLine();
            char[][] mineField = new char[firstNum+2][secondNum+2];

            for (int i = 1; i < mineField.length-1; i++) {
                String c = scanner.nextLine();
                for (int j = 1; j < mineField[i].length-1; j++) {
                    if(c.charAt(j-1) == '*'){
                        mineField[i][j] = '*';
                        updateMines(i,j,mineField);
                    } else if(!Character.isDigit(mineField[i][j])){
                        mineField[i][j] = '0';
                    }
                }
            }
            mineList.add(mineField);


        }

    }

    private void updateMines(int y, int x, char[][] array){

        if(Character.isDigit(array[y+1][x])){
            array[y+1][x]++;
        } else if(array[y+1][x] != '*'){
            array[y+1][x] = '1';
        }

        if(Character.isDigit(array[y+1][x-1])){
            array[y+1][x-1]++;
        } else if(array[y+1][x-1] != '*'){
            array[y+1][x-1] = '1';
        }

        if(Character.isDigit(array[y+1][x+1])){
            array[y+1][x+1]++;
        } else if(array[y+1][x+1] != '*'){
            array[y+1][x+1] = '1';
        }

        if(Character.isDigit(array[y][x+1])){
            array[y][x+1]++;
        }else if(array[y][x+1] != '*'){
            array[y][x+1] = '1';
        }

        if(Character.isDigit(array[y][x-1])){
            array[y][x-1]++;
        }else if(array[y][x-1] != '*'){
            array[y][x-1] = '1';
        }

        if(Character.isDigit(array[y-1][x+1])){
            array[y-1][x+1]++;
        }else if(array[y-1][x+1] != '*'){
            array[y-1][x+1] = '1';
        }

        if(Character.isDigit(array[y-1][x])){
            array[y-1][x]++;
        }else if(array[y-1][x] != '*'){
            array[y-1][x] = '1';
        }

        if(Character.isDigit(array[y-1][x-1])){
            array[y-1][x-1]++;
        }else if(array[y-1][x-1] != '*'){
            array[y-1][x-1] = '1';
        }


    }

    private void convertMines(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < mineList.size(); i++) {
            str.append("Field #").append(i+1).append(":\n");
            char[][] mineArray = mineList.get(i);
            for (int j = 1; j < mineArray.length-1; j++) {
                for (int k = 1; k < mineArray[j].length-1; k++) {
                    str.append(mineArray[j][k]);
                }
                str.append("\n");
            }
            str.append("\n");
        }

        mineOutputStr = str.toString();
    }



    private void writeFile() throws IOException{
        try (PrintStream out = new PrintStream(new FileOutputStream(outputFileName))) {
            out.print(mineOutputStr);
        }
    }



}

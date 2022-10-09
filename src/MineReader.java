import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        for (int i = 0; i < mineInputStr.length(); i++) {
            char c = mineInputStr.charAt(i);
            if(Character.isDigit(c)){
                int num = Integer.parseInt(String.valueOf(c));
                int secondNum = Integer.parseInt(String.valueOf(mineInputStr.charAt(i+2)));
                char [][] mineOutputArr = new char[num+2][secondNum+2];
                i += 4;
                for (int j = 1; j < mineOutputArr.length-1; j++) {
                    for (int k = 1; k < mineOutputArr[j].length-1; k++) {
                        if(mineInputStr.charAt(i) == '\n'){
                            i++;
                        }
                        if(mineInputStr.charAt(i) == '*'){
                            mineOutputArr[j][k] = mineInputStr.charAt(i);
                            updateMines(j, k, mineOutputArr);
                        }  else if(mineOutputArr[j][k] == 0){
                            mineOutputArr[j][k] = '0';
                        }
                        i++;

                    }
                }
                mineList.add(mineOutputArr);



            }
        }

    }

    private void updateMines(int y, int x, char[][] array){

        if(Character.isDigit(array[y+1][x])){
            array[y+1][x]++;
        } else if(array[y+1][x] == 0){
            array[y+1][x] = '1';
        }

        if(Character.isDigit(array[y+1][x-1])){
            array[y+1][x-1]++;
        } else if(array[y+1][x-1] == 0){
            array[y+1][x] = '1';
        }

        if(Character.isDigit(array[y+1][x+1])){
            array[y+1][x+1]++;
        } else if(array[y+1][x+1] == 0){
            array[y+1][x+1] = '1';
        }

        if(Character.isDigit(array[y][x+1])){
            array[y][x+1]++;
        }else if(array[y][x+1] == 0){
            array[y][x+1] = '1';
        }

        if(Character.isDigit(array[y][x-1])){
            array[y][x-1]++;
        }else if(array[y][x-1] == 0){
            array[y][x-1] = '1';
        }

        if(Character.isDigit(array[y-1][x+1])){
            array[y-1][x+1]++;
        }else if(array[y-1][x+1] == 0){
            array[y-1][x+1] = '1';
        }

        if(Character.isDigit(array[y-1][x])){
            array[y-1][x]++;
        }else if(array[y-1][x] == 0){
            array[y+1][x] = '1';
        }

        if(Character.isDigit(array[y-1][x+1])){
            array[y-1][x+1]++;
        }else if(array[y-1][x+1] == 0){
            array[y-1][x+1] = '1';
        }


    }

    private void convertMines(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < mineList.size(); i++) {
            str.append("Field #").append(i+1).append(":\n");
            char[][] mineArray = mineList.get(i);
            for (int j = 0; j < mineArray.length; j++) {
                for (int k = 0; k < mineArray[j].length; k++) {
                    str.append(mineArray[j][k]);
                }
                str.append("\n");
            }
        }

        mineOutputStr = str.toString();
    }



    private void writeFile() throws IOException{
        try (PrintStream out = new PrintStream(new FileOutputStream(outputFileName))) {
            out.print(mineOutputStr);
        }
    }



}

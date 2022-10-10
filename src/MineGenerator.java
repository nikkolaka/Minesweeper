
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MineGenerator {
    private final String outputFileName = ".\\src\\Minefield-Input.txt";
    private List<char[][]> mineList = new ArrayList<>();
    private String mineField = "";

    MineGenerator() throws IOException {

        buildMines();
        buildMineString();
        writeFile();


    }
    
    private void buildMines(){

        buildMineArray(0,0,0.0);
        buildMineArray(1,1, 1.0);
        buildMineArray(100,100, 0.05);
        for (int i = 0; i <= 100; i+= 20) {
            buildMineArray(i,i,0);
        }
        for (int i = 0; i <= 100; i+= 20) {
            buildMineArray(i,i,0.25);
        }
        for (int i = 0; i <= 100; i+= 20) {
            buildMineArray(i,i,0.5);
        }
        for (int i = 0; i <= 100; i+= 20) {
            buildMineArray(i,i, 1.0);
        }
        
        
    }

    private void buildMineArray(int n, int m, double minePercent){
        char[][] arrayField = new char[n][m];

        Random random = new Random();
        double r;

        for (int i = 0; i < arrayField.length; i++) {
            for (int j = 0; j < arrayField[i].length; j++) {
                r = random.nextDouble();
                if(r < minePercent){
                    arrayField[i][j] = '*';
                } else{
                    arrayField[i][j] = '.';
                }
            }
        }

        mineList.add(arrayField);
    }

    private void buildMineString(){
        StringBuilder str = new StringBuilder();

        for (char[][] array:mineList) {
            if(array.length != 0){
                str.append(array.length).append(" ").append(array[0].length).append("\n");
            } else{
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

    private void writeFile() throws IOException{
        try (PrintStream out = new PrintStream(new FileOutputStream(outputFileName))) {
            out.print(mineField);
        }
    }

}

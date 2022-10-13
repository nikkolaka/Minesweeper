import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class MineReaderTest {

    private static final String INPUT_FILE = ".\\Minefield-Input.txt";
    private static final String OUTPUT_FILE = ".\\Minefield-Input.txt";

    OfficialMineGenerator mg;
    OfficialMineReader mr;

    @Test
    void input_100_100_1() throws IOException {
        mg = new OfficialMineGenerator(100, 100, 100);
        char[][] field = mg.getMineFieldArr();

        assertEquals(100, field.length);
        assertEquals(100, field[0].length);
        assertEquals('*', field[0][0]);
    }

    @Test
    void input_1_1_1() throws IOException {
        mg = new OfficialMineGenerator(1, 1, 1);
        char[][] field = mg.getMineFieldArr();

        assertEquals(1, field.length);
        assertEquals(1, field[0].length);
        assertEquals('*', field[0][0]);
    }

    @Test
    void input_100_100_0() throws IOException {
        mg = new OfficialMineGenerator(100, 100, 0);
        char[][] field = mg.getMineFieldArr();

        assertEquals(100, field.length);
        assertEquals(100, field[0].length);
        assertEquals('.', field[0][0]);
    }

    @Test
    void input_1_1_0() throws IOException {
        mg = new OfficialMineGenerator(1, 1, 0);
        char[][] field = mg.getMineFieldArr();

        assertEquals(1, field.length);
        assertEquals(1, field[0].length);
        assertEquals('.', field[0][0]);
    }

    @Test
    void input_100_100_50() throws IOException {
        mg = new OfficialMineGenerator(100, 100, 0.5);
        char[][] field = mg.getMineFieldArr();

        assertEquals(100, field.length);
        assertEquals(100, field[0].length);

        boolean mine = false;
        boolean empty = false;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == '.') {
                    empty = true;
                }
                if (field[i][j] == '*') {
                    mine = true;
                }
            }
        }
        assertEquals(true, empty);
        assertEquals(true, mine);
    }

    @Test
    void update_mines() throws IOException {
        char[][] fieldInput = {{'.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.'}, {'.', '.', '*', '.', '.'}, {'.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.'},};

        mr = new OfficialMineReader(new File(".\\Minefield-Input.txt"));

        char[][] mrArray = mr.updateMines(2, 2, fieldInput);

        char[][] fieldOutput = {{'.', '.', '.', '.', '.'}, {'.', '1', '1', '1', '.'}, {'.', '1', '*', '1', '.'}, {'.', '1', '1', '1', '.'}, {'.', '.', '.', '.', '.'},};
        for (int i = 0; i < mrArray.length; i++) {
            for (int j = 0; j < mrArray[i].length; j++) {
                Assertions.assertEquals(fieldOutput[i][j], mrArray[i][j]);
            }
        }
    }

    @Test
    void gen_output_3_3_1() throws IOException {
        mg = new OfficialMineGenerator(3, 3, 1);
        String output = mg.getOutputString();

        String actual = "3 3\n***\n***\n***\n";

        for (int i = 0; i < output.length(); i++) {
            assertEquals(output.charAt(i), actual.charAt(i));
        }
    }

    @Test
    void gen_output_3_3_0() throws IOException {
        mg = new OfficialMineGenerator(3, 3, 0);
        String output = mg.getOutputString();

        String actual = "3 3\n...\n...\n...\n";

        for (int i = 0; i < output.length(); i++) {
            assertEquals(output.charAt(i), actual.charAt(i));
        }
    }


    @Test
    void gen_output_0_0_0() throws IOException {
        mg = new OfficialMineGenerator(0, 0, 0);
        String output = mg.getOutputString();

        assertEquals(4, output.length());
    }


    @Test
    void gen_output_1_1_1() throws IOException {
        mg = new OfficialMineGenerator(1, 1, 1);
        String output = mg.getOutputString();

        String actual = "1 1\n*\n";

        for (int i = 0; i < output.length(); i++) {
            assertEquals(output.charAt(i), actual.charAt(i));
        }
    }

    @Test
    void gen_output_1_1_0() throws IOException {
        mg = new OfficialMineGenerator(1, 1, 0);
        String output = mg.getOutputString();

        String actual = "1 1\n.\n";

        for (int i = 0; i < output.length(); i++) {
            assertEquals(output.charAt(i), actual.charAt(i));
        }
    }

    @Test
    void gen_output_100_100_0() throws IOException {
        mg = new OfficialMineGenerator(100, 100, 0);
        String output = mg.getOutputString();

        String actual = "100 100\n";
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                actual += ".";
            }
            actual += "\n";
        }
        for (int i = 0; i < output.length(); i++) {
            assertEquals(output.charAt(i), actual.charAt(i));
        }
    }

    @Test
    void file_is_read() throws IOException {
        mr = new OfficialMineReader(new File(".\\Minefield-Input.txt"));

        assertEquals(true, mr.isFileRead());
    }

    @Test
    void file_is_wrote() throws IOException {
        mr = new OfficialMineReader(new File(".\\Minefield-Input.txt"));

        assertEquals(true, mr.isFileWrote());
    }

    @Test
    void gen_output_100_100_1() throws IOException {
        mg = new OfficialMineGenerator(100, 100, 1);
        String output = mg.getOutputString();

        String actual = "100 100\n";
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                actual += "*";
            }
            actual += "\n";
        }
        for (int i = 0; i < output.length(); i++) {
            assertEquals(output.charAt(i), actual.charAt(i));
        }
    }
}

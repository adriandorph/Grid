import Saves.SnakeSaveFileWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class SnakeSaveFileWriterTest {
    @Test
    public void getDirPath(){
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(SnakeSaveFileWriter.getDirPath());
    }

    @Test
    public void saveNewHighscore() throws IOException {
        SnakeSaveFileWriter.saveNewHighScore(20);
        System.out.println(SnakeSaveFileWriter.readHighscore());
        assertEquals(20, SnakeSaveFileWriter.readHighscore());
    }
}
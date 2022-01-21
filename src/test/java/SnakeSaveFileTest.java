import Saves.SnakeSaveFile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class SnakeSaveFileTest {
    @Test
    public void getDirPath(){
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(SnakeSaveFile.getDirPath());
    }

    @Test
    public void saveNewHighscore() {
        SnakeSaveFile.saveNewHighScore(20);
        System.out.println(SnakeSaveFile.readHighscore());
        assertEquals(20, SnakeSaveFile.readHighscore());
    }
}
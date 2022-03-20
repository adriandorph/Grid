import Saves.FilePath;
import Saves.SnakeHighScoreFile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SnakeHighScoreFileTest {
    @Test
    public void getDirPath(){
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(FilePath.getDirPath());
    }
}
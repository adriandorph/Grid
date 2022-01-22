package Saves;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class SnakeHighScoreFile {

    public static void saveNewHighScore(int highscore) {
        try{
            Path path = FilePath.getFilePath("highscore");

            String score = ""+highscore;

            Files.write(path, score.getBytes());
        } catch (IOException ignored){}

    }

    public static int readHighscore() {
        try {
            Path path = FilePath.getFilePath("highscore");
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            String line = reader.readLine();
            reader.close();
            return Integer.parseInt(line);
        } catch (Exception ex) {
            return 0;
        }
    }
}


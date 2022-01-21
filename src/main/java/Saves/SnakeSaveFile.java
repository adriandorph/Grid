package Saves;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class SnakeSaveFile {

    public static void saveNewHighScore(int highscore) {
        try{
            StringBuilder filepath = new StringBuilder();
            filepath.append(getDirPath());

            if(!Files.exists(Path.of(filepath.toString()))){
                Files.createDirectory(Path.of(filepath.toString()));
            }

            if(System.getProperty("os.name").contains("Windows")) filepath.append("\\");
            else filepath.append("/");
            filepath.append("highscore");

            if(!Files.exists(Path.of(filepath.toString()))){
                Files.createFile(Path.of(filepath.toString()));
            }

            String score = ""+highscore;

            Files.write(Path.of(filepath.toString()), score.getBytes());
        } catch (IOException ignored){}

    }

    public static String getDirPath(){
        String os = System.getProperty("os.name");
        os = os.toLowerCase(Locale.ROOT);
        String homedir = System.getProperty("user.home");
        StringBuilder filepath = new StringBuilder();
        filepath.append(homedir);

        if(os.contains("windows")){
            filepath.append("\\AppData\\Roaming\\");
        } else if(os.contains("mac") || os.contains("osx")) {
            filepath.append("/Library/Application Support/");
        }

        filepath.append("Snake");
        return filepath.toString();
    }

    public static int readHighscore(){
        StringBuilder filepath = new StringBuilder();
        filepath.append(getDirPath());
        if(System.getProperty("os.name").contains("Windows")) filepath.append("\\");
        else filepath.append("/");
        filepath.append("highscore");

        if (Files.exists(Path.of(filepath.toString()))) {
            try (BufferedReader reader = Files.newBufferedReader(Path.of(filepath.toString()), StandardCharsets.UTF_8)) {
                String line = reader.readLine();
                reader.close();
                return Integer.parseInt(line);
            } catch (IOException ex) {
                ex.printStackTrace(); //handle an exception here
            }
        }
        return 0;
    }
}

package Saves.Settings;

import Saves.FilePath;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class StartUpSettings {
    public static void saveStartUpInGame(boolean startUpInGame) {
        try{
            Path path = FilePath.getFilePath("startUpInGame");

            String stringToBytes = ""+startUpInGame;

            Files.write(path, stringToBytes.getBytes());
        } catch (IOException ignored){}

    }

    public static boolean getStartUpInGame() {
        try {
            Path path = FilePath.getFilePath("startUpInGame");
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            String line = reader.readLine();
            reader.close();
            return line.equals(""+true);
        } catch (Exception ignored) {
            return false;
        }
    }
}

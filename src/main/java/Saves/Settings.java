package Saves;

import Model.Snake.ColorSheme;
import Model.Snake.SerializableColorScheme;
import javafx.scene.paint.Color;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Settings {
    private static List<ColorSheme> colorShemes = readColorSchemes();
    private static ColorSheme activeColorScheme = readActiveColorScheme();

    public static void setActiveColorScheme(ColorSheme colorScheme) {
        Settings.activeColorScheme = colorScheme;
        saveActiveColorScheme();
    }

    public static ColorSheme getActiveColorScheme(){
        return Settings.activeColorScheme;
    }

    public static void saveActiveColorScheme(){
        try {
            Path path = FilePath.getFilePath("activeColorScheme");
            FileOutputStream fos = new FileOutputStream(String.valueOf(path));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(new SerializableColorScheme(activeColorScheme));
            oos.close();
        } catch (IOException e) {
            System.out.println("Could Not Create Binary File");
            e.getMessage();
            e.printStackTrace();
        }
    }

    private static ColorSheme readActiveColorScheme(){
        try{
            Path path = FilePath.getFilePath("activeColorScheme");
            FileInputStream fis = new FileInputStream(String.valueOf(path));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream objis = new ObjectInputStream(bis);
            ColorSheme activeColorSheme = ((SerializableColorScheme) objis.readObject()).toColorScheme();
            if (activeColorSheme == null) throw new IOException("No active ColorScheme");
            objis.close();
            bis.close();
            fis.close();
            return activeColorSheme;

        } catch (IOException | ClassNotFoundException e){
            e.getMessage();
            e.printStackTrace();
            var activeColorSheme = getColorSchemes().get(0);
            Settings.setActiveColorScheme(activeColorSheme);
            return activeColorSheme;
        }
    }

    public static List<ColorSheme> generateDefaultColorSchemes(){
        return new ArrayList<>(List.of(new ColorSheme[]{
                new ColorSheme("Green",
                        Color.rgb(0,255,0),
                        Color.rgb(0,0,0),
                        Color.rgb(255,0,0),
                        Color.rgb(255,255,255),
                        Color.rgb(255,255,0),
                        Color.rgb(255,255,255)
                ),
                new ColorSheme("Blue",
                        Color.rgb(0,100,255),
                        Color.rgb(0,0,0),
                        Color.rgb(0,0,255),
                        Color.rgb(255,255,255),
                        Color.rgb(255,255,0),
                        Color.rgb(255,255,255)
                ),
                new ColorSheme("Red",
                        Color.rgb(255,0,0),
                        Color.rgb(0,0,0),
                        Color.rgb(255,0,0),
                        Color.rgb(255,255,255),
                        Color.rgb(255,255,0),
                        Color.rgb(255,255,255)
                ),
        }));
    }

    public static void addColorScheme(ColorSheme colorSheme){
        colorShemes.add(colorSheme);
        saveColorSchemes();
    }

    public static void setColorShemes(List<ColorSheme> colorShemes){
        Settings.colorShemes = colorShemes;
        saveColorSchemes();
    }

    public static List<ColorSheme> getColorSchemes(){
        return colorShemes;
    }

    public static void saveColorSchemes(){
        try {
            Path path = FilePath.getFilePath("colorSchemes");
            FileOutputStream fos = new FileOutputStream(String.valueOf(path));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            List<SerializableColorScheme> serializableColorSchemes = new LinkedList<>();
            for (ColorSheme colorSheme: colorShemes){
                serializableColorSchemes.add(new SerializableColorScheme(colorSheme));
            }

            oos.writeObject(serializableColorSchemes);
            oos.close();
        } catch (IOException e) {
            System.out.println("Could Not Create Binary File");
            e.printStackTrace();
        }
    }

    private static List<ColorSheme> readColorSchemes(){
        try{
            Path path = FilePath.getFilePath("colorSchemes");
            FileInputStream fis = new FileInputStream(String.valueOf(path));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream objis = new ObjectInputStream(bis);
            List<ColorSheme> colorShemes = new LinkedList<>();
            for (SerializableColorScheme scs: ((List<SerializableColorScheme>) objis.readObject())){
                colorShemes.add(scs.toColorScheme());
            }

            if (colorShemes.isEmpty()) throw new IOException("No ColorSchemes");
            objis.close();
            bis.close();
            fis.close();
            return colorShemes;

        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            var colorSchemes = generateDefaultColorSchemes();
            Settings.setColorShemes(colorSchemes);
            return colorSchemes;
        }
    }


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

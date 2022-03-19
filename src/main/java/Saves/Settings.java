package Saves;

import Model.Snake.ColorScheme;
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
    private static final List<ColorScheme> colorSchemes = generateDefaultColorSchemes();
    private static List<ColorScheme> customColorSchemes = readColorSchemes();
    private static ColorScheme activeColorScheme = readActiveColorScheme();

    public static void setActiveColorScheme(ColorScheme colorScheme) {
        Settings.activeColorScheme = colorScheme;
        saveActiveColorScheme();
    }

    public static ColorScheme getActiveColorScheme(){
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
            bos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Could Not Create Binary File");
        }
    }

    public static ColorScheme readActiveColorScheme(){
        try{
            Path path = FilePath.getFilePath("activeColorScheme");
            FileInputStream fis = new FileInputStream(String.valueOf(path));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream objis = new ObjectInputStream(bis);
            ColorScheme activeColorScheme = ((SerializableColorScheme) objis.readObject()).toColorScheme();
            if (activeColorScheme == null) throw new IOException("No active ColorScheme");
            objis.close();
            bis.close();
            fis.close();
            return activeColorScheme;

        } catch (IOException | ClassNotFoundException e){
            var activeColorScheme = getColorSchemes().get(0);
            Settings.setActiveColorScheme(activeColorScheme);
            return activeColorScheme;
        }
    }

    public static List<ColorScheme> generateDefaultColorSchemes(){
        return new ArrayList<>(List.of(new ColorScheme[]{
                //UI
                //Background
                //Head
                //Tail
                //Bits
                //Info

                new ColorScheme("Classic",
                        Color.rgb(0,255,0),     //UI
                        Color.rgb(0,0,0),       //Background
                        Color.rgb(255,0,0),     //Head
                        Color.rgb(255,255,255), //Tail
                        Color.rgb(255,255,0),   //Bits
                        Color.rgb(255,255,255)  //Info
                ),
                new ColorScheme("Blue",
                        Color.rgb(0,225,255),   //UI
                        Color.rgb(0,0,0),       //Background
                        Color.rgb(0,225,255),   //Head
                        Color.rgb(255,255,255), //Tail
                        Color.rgb(0,255,0),     //Bits
                        Color.rgb(255,255,255)  //Info
                ),
                new ColorScheme("Green",
                        Color.rgb(255,255,255), //UI
                        Color.rgb(0,150,0),     //Background
                        Color.rgb(255,255,255), //Head
                        Color.rgb(200,200,200), //Tail
                        Color.rgb(255,255,0),   //Bits
                        Color.rgb(255, 255,255) //Info
                ),
                new ColorScheme("White",
                        Color.rgb(0,0,0),       //UI
                        Color.rgb(255,255,255), //Background
                        Color.rgb(0,255,0),     //Head
                        Color.rgb(0,100,0),     //Tail
                        Color.rgb(255,0,255),   //Bits
                        Color.rgb(0,0,0)        //Info
                        )
        }));
    }

    /**
     * Adds colorScheme to customColorSchemes and saves the changes
     * @param colorScheme - Color scheme to be added
     */
    public static void addColorScheme(ColorScheme colorScheme){
        customColorSchemes.add(colorScheme);
        saveColorSchemes();
    }

    public static void setColorSchemes(List<ColorScheme> colorSchemes){
        Settings.customColorSchemes = colorSchemes;
        saveColorSchemes();
    }

    public static void deleteColorScheme(String name){
        List<ColorScheme> updatedCustomColorSchemes = new LinkedList<>();
        boolean couldNotDelete = true;
        for (ColorScheme cs: customColorSchemes){
            if (!cs.getName().equals(name)){
                updatedCustomColorSchemes.add(cs);
            } else {
                couldNotDelete = false;
            }
        }
        if (couldNotDelete) throw new RuntimeException("Could not find colorscheme: "+ name);
        setColorSchemes(updatedCustomColorSchemes);
    }

    public static List<ColorScheme> getColorSchemes(){
        List<ColorScheme> allColorSchemes = new LinkedList<>();
        allColorSchemes.addAll(colorSchemes);
        allColorSchemes.addAll(customColorSchemes);
        return allColorSchemes;
    }

    public static ColorScheme getColorScheme(int index){
        if (index < colorSchemes.size()) return colorSchemes.get(index);
        else return customColorSchemes.get(index - customColorSchemes.size());
    }

    public static ColorScheme getColorScheme(String name){
        for (ColorScheme cs: getColorSchemes()){
            if (cs.getName().equals(name)) return cs;
        }
        throw new RuntimeException("Could not find color scheme: " + name);
    }

    public static void saveColorSchemes(){
        try {
            Path path = FilePath.getFilePath("colorSchemes");
            FileOutputStream fos = new FileOutputStream(String.valueOf(path));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            List<SerializableColorScheme> serializableColorSchemes = new LinkedList<>();
            for (ColorScheme colorScheme : customColorSchemes){
                serializableColorSchemes.add(new SerializableColorScheme(colorScheme));
            }

            oos.writeObject(serializableColorSchemes);
            oos.close();
            bos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Could Not Create Binary File");
        }
    }

    private static List<ColorScheme> readColorSchemes(){
        try{
            Path path = FilePath.getFilePath("colorSchemes");
            FileInputStream fis = new FileInputStream(String.valueOf(path));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream objis = new ObjectInputStream(bis);
            List<ColorScheme> customColorSchemes = new LinkedList<>();
            for (SerializableColorScheme scs: ((List<SerializableColorScheme>) objis.readObject())){
                customColorSchemes.add(scs.toColorScheme());
            }

            if (customColorSchemes.isEmpty()) throw new IOException("No ColorSchemes");
            objis.close();
            bis.close();
            fis.close();
            return customColorSchemes;

        } catch (IOException | ClassNotFoundException e){
            return new LinkedList<>();
        }
    }

    public static String getNewColorSchemeName(){
        String prefix = "new colorscheme";
        String newColorSchemeName = prefix + " " + 1;
        boolean available = false;
        int count = 1;
        while (!available){
            available = true;
            for (ColorScheme cs: customColorSchemes){
                newColorSchemeName = prefix + " " + count;
                if (cs.getName().equals(newColorSchemeName)) {
                    available = false;
                    break;
                }
            }
            count++;
        }
        return newColorSchemeName;

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

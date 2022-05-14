package Saves.Settings;

import Model.Snake.ColorScheme;
import Model.Snake.SerializableColorScheme;
import Saves.FilePath;
import javafx.scene.paint.Color;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ColorSettings {
    private static final List<ColorScheme> colorSchemes = generateDefaultColorSchemes();
    private static List<ColorScheme> customColorSchemes = readColorSchemes();
    private static ColorScheme activeColorScheme = readActiveColorScheme();

    public static void setActiveColorScheme(ColorScheme colorScheme) {
        ColorSettings.activeColorScheme = colorScheme;
        saveActiveColorScheme();
    }

    public static ColorScheme getActiveColorScheme(){
        return ColorSettings.activeColorScheme;
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
            System.out.println(e.getMessage());
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
            ColorSettings.setActiveColorScheme(activeColorScheme);
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
                new ColorScheme("Miami Vice",
                        Color.hsb(180,1.0,1.0),   //UI
                        Color.rgb(0,0,0),       //Background
                        Color.hsb(180,1.0,1.0),   //Head
                        Color.rgb(255,255,145), //Tail
                        Color.rgb(255,0,255),     //Bits
                        Color.rgb(255,0,255)  //Info
                ),
                new ColorScheme("Pitch",
                        Color.rgb(255,255,255), //UI
                        Color.rgb(0,150,0),     //Background
                        Color.rgb(255,255,255), //Head
                        Color.rgb(200,200,200), //Tail
                        Color.rgb(255,255,0),   //Bits
                        Color.rgb(255, 255,255) //Info
                ),
                new ColorScheme("Black on white",
                        Color.rgb(0,0,0),       //UI
                        Color.rgb(255,255,255), //Background
                        Color.rgb(0,0,0),     //Head
                        Color.rgb(100,100,100),     //Tail
                        Color.rgb(50,50,50),   //Bits
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
        ColorSettings.customColorSchemes = colorSchemes;
        saveColorSchemes();
    }

    public static void updateActiveColorScheme(ColorScheme colorScheme, int index){//TODO: change to index
        customColorSchemes.set(index - colorSchemes.size(), colorScheme);
        ColorSettings.saveColorSchemes();
        ColorSettings.setActiveColorScheme(colorScheme);
    }

    public static void deleteColorScheme(int index){
        customColorSchemes.remove(index - colorSchemes.size());
        setColorSchemes(customColorSchemes);
    }

    public static List<ColorScheme> getColorSchemes(){
        List<ColorScheme> allColorSchemes = new LinkedList<>();
        allColorSchemes.addAll(colorSchemes);
        allColorSchemes.addAll(customColorSchemes);
        return allColorSchemes;
    }

    public static ColorScheme getColorScheme(int index){
        if (index < colorSchemes.size()) return colorSchemes.get(index);
        else return customColorSchemes.get(index - colorSchemes.size());
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
            System.out.println(e.getMessage());
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
        String prefix = "colorscheme";
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
}

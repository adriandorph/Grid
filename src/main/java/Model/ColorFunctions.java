package Model;

import javafx.scene.paint.Color;

public class ColorFunctions {

    public static Color opacityOnBackground(Color subject, Color background, double opacity){
        double red = (subject.getRed() - background.getRed()) * opacity + background.getRed();
        double green = (subject.getGreen() - background.getGreen()) * opacity + background.getGreen();
        double blue = (subject.getBlue() - background.getBlue()) * opacity + background.getBlue();
        return Color.color(red, green, blue);
    }
}

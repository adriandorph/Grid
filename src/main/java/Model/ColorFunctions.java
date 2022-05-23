package Model;

import javafx.scene.paint.Color;

public class ColorFunctions {

    public static Color opacityOnBackground(Color subject, Color background, double opacity){
        double red = monoOpacityOnBackground(subject.getRed(), background.getRed(), opacity);
        double green = monoOpacityOnBackground(subject.getGreen(), background.getGreen(), opacity);
        double blue = monoOpacityOnBackground(subject.getBlue(), background.getBlue(), opacity);
        return Color.color(red, green, blue);
    }

    private static double monoOpacityOnBackground(double subject, double background, double opacity){
        if (subject > 1.0 || subject < 0.0 || background > 1.0 || background < 0.0 || opacity > 1.0 || opacity < 0.0)
            throw new RuntimeException("Bad input in monoOpacityOnBackground. subject: "+ subject + " background: " + background + " opacity: "+ opacity);
        return (subject - background) * opacity + background;
    }
}

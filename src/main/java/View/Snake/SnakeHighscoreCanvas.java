package View.Snake;

import Controller.Controller;
import Saves.Settings.ColorSettings;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class SnakeHighscoreCanvas extends Canvas {
    private final GraphicsContext gc;

    public SnakeHighscoreCanvas(double width, double height, int highscore){
        super(width, height);
        gc = getGraphicsContext2D();
        draw(highscore);
    }

    private void draw(int highscore){
        gc.save();
        gc.setFill(ColorSettings.getActiveColorScheme().getUI());
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Roboto", 100 * Controller.factor));
        gc.fillText("NEW HIGHSCORE", getWidth() / 2,getHeight() / 2 - 100 * Controller.factor);
        gc.fillText(""+highscore, getWidth() / 2, getHeight() / 2 + 75 * Controller.factor);
        gc.restore();
    }
}

package View.Snake;


import Controller.Controller;
import Saves.Settings;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class SnakeGameOverScoreCanvas extends Canvas {
    GraphicsContext gc;

    public SnakeGameOverScoreCanvas(double width, double height){
        super(width, height);
        gc = getGraphicsContext2D();
    }

    public void render(int score){
        gc.save();
        gc.setFill(Settings.getActiveColorScheme().getUI());
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Roboto", 100 * Controller.factor));
        gc.fillText("Score: "+score, getWidth() / 2,getHeight() / 2);
        gc.restore();
    }
}

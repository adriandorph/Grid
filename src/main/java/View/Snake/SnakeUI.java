package View.Snake;

import Controller.Controller;
import Saves.Settings.ColorSettings;
import View.Renderable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class SnakeUI extends Canvas implements Renderable<SnakeUIInfo> {
    private final GraphicsContext gc;

    public SnakeUI(double width, double height){
        super(width, height);
        gc = getGraphicsContext2D();
    }

    @Override
    public void render(SnakeUIInfo info) {
        gc.save();
        gc.clearRect(0,0, getWidth(), getHeight());
        drawScores(info.getScore(), info.getHighscore());
        if(info.shouldDisplayStartHelp()) drawStartHelp();
        gc.restore();
    }

    private void drawScores(int score, int highscore){
        gc.setFill(ColorSettings.getActiveColorScheme().getUI());
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(new Font("Roboto", 30 * Controller.factor));
        gc.fillText(("Score: "+ score), 10 * Controller.factor,25 * Controller.factor);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText(("Highscore: "+ highscore), getWidth() - 10 * Controller.factor,25 * Controller.factor);
    }

    private void drawStartHelp(){
        gc.setFill(ColorSettings.getActiveColorScheme().getInfo());
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Roboto", 60 * Controller.factor));
        gc.fillText("Press an arrow key to start", Controller.windowWidth / 2, Controller.windowHeight / 3);
    }
}

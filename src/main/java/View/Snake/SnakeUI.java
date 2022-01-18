package View.Snake;


import Controller.Controller;
import View.Renderable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class SnakeUI extends Canvas implements Renderable<Integer> {
    private GraphicsContext gc;

    public SnakeUI(double width, double height){
        super(width, height);
        gc = getGraphicsContext2D();
    }

    @Override
    public void render(Integer score) {
        gc.save();
        gc.clearRect(0,0, getWidth(), getHeight());
        gc.setFill(Color.LIME);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(new Font("Roboto", 30 * Controller.factor));
        gc.fillText(("Score: "+ score), 10 * Controller.factor,25 * Controller.factor);
        gc.restore();
    }

    @Override
    public void endScreen() {
        setOpacity(0.3);
    }
}

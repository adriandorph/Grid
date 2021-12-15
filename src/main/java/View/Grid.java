package View;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Grid extends Canvas {
    private GraphicsContext gc;
    public Grid(double width, double height){
        super(width, height);
        gc = getGraphicsContext2D();
    }
}

package View.Snake;

import Saves.Settings;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ArrowCanvas {

    public static Canvas getArrowCanvas(int width, int height, Color color){
        Canvas arrowCanvas = new Canvas(width, height);
        GraphicsContext gc = arrowCanvas.getGraphicsContext2D();
        gc.save();
        gc.setFill(color);

        gc.fillPolygon(
                new double[]{0          ,0.45 * width  , 0.45 * width  , width        , width       , 0.45 * width , 0.45 * width},
                new double[]{height/2.0 , 0           , 0.25 * height , 0.25 * height , 0.75 * height, 0.75 * height, height     },
                7
        );

        gc.restore();
        return arrowCanvas;
    }

}

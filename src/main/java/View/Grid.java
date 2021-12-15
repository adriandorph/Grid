package View;
import Controller.Controller;
import Model.Matrix;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grid extends Canvas implements Renderable<RenderGrid> {
    private GraphicsContext gc;
    public Grid(double width, double height){
        super(width, height);
        gc = getGraphicsContext2D();
    }

    @Override
    public void render(RenderGrid renderGrid) {
        gc.save();
        Matrix squares = renderGrid.getSquares();
        if((double)squares.getLengthX() / (double)squares.getLengthY() != getWidth()/getHeight()) throw new IllegalArgumentException("The squares do not fit in the canvas. Squares: "+(double)squares.getLengthX() / (double)squares.getLengthY()+ " Canvas: "+ getWidth()/getHeight());
        double squareSize = getWidth() / squares.getLengthX(); //* Controller.factor;
        squares.setSquareSize(squareSize);
        for(int x = 0; x < squares.getLengthX(); x++){
            for(int y = 0; y < squares.getLengthY(); y++){
                gc.setFill(squares.get(x,y).getColor());
                gc.fillPolygon(squares.get(x,y).getPointsXWithPosition(squareSize * x), squares.get(x,y).getPointsYWithPosition(squareSize * y), 4);
            }
        }
        gc.restore();
    }
}

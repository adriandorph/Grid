package Model;

import Controller.Updatable;
import View.RenderGrid;
import javafx.scene.paint.Color;

public class GridAnimation implements Updatable<RenderGrid> {
    private double seconds;
    private RenderGrid renderGrid;
    private Matrix squares;
    private int startColorIndex;

    public GridAnimation(double canvasHeight){
        squares = new Matrix(32, 18, canvasHeight);
        renderGrid = tick();
        startColorIndex = 0;
        seconds = 0;
    }

    @Override
    public void update(double seconds) {
        this.seconds += seconds;
        double tickTime = 1.0 / 30;
        if(this.seconds >= tickTime){
            this.seconds -= tickTime;
            renderGrid = tick();
        }
    }

    @Override
    public RenderGrid getRenderObject() {
        return renderGrid;
    }

    @Override
    public boolean stopCondition() {
        return false;
    }

    private RenderGrid tick(){
        Color[] colors = new Color[]{Color.rgb(255, 255, 0), Color.rgb(192,255,0), Color.rgb(128,255,0), Color.rgb(64,255,0), Color.rgb(0, 255, 0), Color.rgb(64,255,0), Color.rgb(128,255,0), Color.rgb(192,255,0)};
        int colorIndex = startColorIndex;
        startColorIndex++;
        for(int y = 0; y < squares.getLengthY(); y++, colorIndex++){
            for(int x = 0; x < squares.getLengthX(); x++, colorIndex++){
                squares.setColor(x, y, colors[colorIndex % colors.length]);
            }
        }
        return new RenderGrid(squares);
    }
}

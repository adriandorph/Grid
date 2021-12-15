package Model;

import Controller.Updatable;
import View.RenderGrid;
import javafx.scene.paint.Color;

public class GridAnimation implements Updatable<RenderGrid> {
    private double seconds;
    private RenderGrid renderGrid;
    private Matrix squares = new Matrix(32, 18);
    private int startColorIndex;

    public GridAnimation(){
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

    private RenderGrid tick(){
        Color[] colors = new Color[]{Color.WHITE, Color.rgb(192,192,192), Color.rgb(128,128,128), Color.rgb(64,64,64), Color.BLACK, Color.rgb(64,64,64), Color.rgb(128,128,128), Color.rgb(192,192,192)};
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

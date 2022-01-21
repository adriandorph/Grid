package View.Snake;

import Model.Snake.SnakeGame;
import View.RenderGrid;

public class SnakeRender {
    private final RenderGrid renderGrid;
    private final SnakeUIInfo snakeUIInfo;

    public SnakeRender(SnakeGame sg){
        this.renderGrid = sg.getRenderGrid();
        this.snakeUIInfo = sg.getSnakeUIInfo();
    }

    public RenderGrid getRenderGrid() {
        return renderGrid;
    }

    public SnakeUIInfo getSnakeUIInfo() {
        return snakeUIInfo;
    }

}

package View.Snake;

import View.Grid;
import View.Renderable;

public class SnakeView implements Renderable<SnakeRender> {
    Grid grid;
    SnakeUI snakeUI;

    public SnakeView(Grid grid, SnakeUI snakeUI){
        this.grid = grid;
        this.snakeUI = snakeUI;
    }

    @Override
    public void render(SnakeRender renderObject) {
        grid.render(renderObject.getRenderGrid());
        snakeUI.render(renderObject.getScore());
    }
}

package Controller.Snake;

import Controller.Engine;
import Model.Snake.SnakeGame;
import View.Grid;
import View.RenderGrid;

public class SnakeEngine extends Engine<RenderGrid> {

    public SnakeEngine(Grid grid, SnakeGame snakeGame, int FPS) {
        super(grid, snakeGame, FPS);
    }

    @Override
    protected void stopped() {

    }
}

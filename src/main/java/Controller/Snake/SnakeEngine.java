package Controller.Snake;

import Controller.Engine;
import Controller.EngineStopHandler;
import Model.Direction;
import Model.Snake.SnakeGame;
import View.Grid;
import View.RenderGrid;

public class SnakeEngine extends Engine<RenderGrid> {

    public SnakeEngine(Grid grid, SnakeGame snakeGame, int FPS, EngineStopHandler stopHandler) {
        super(grid, snakeGame, FPS, stopHandler);
    }

    @Override
    public void start(){
        SnakeInput.activate();
        Thread thread = new Thread(this); // Demands that this is Runnable
        thread.start();
    }

    @Override
    public void pause(){
        SnakeInput.deactivate();
        paused = true;
    }

    @Override
    public void unpause(){
        SnakeInput.activate();
        paused = false;
    }

    @Override
    public void stop(){
        running = false;
        SnakeInput.deactivate();
    }

    @Override
    protected boolean pauseCondition(){
        return SnakeInput.isPaused();
    }

    @Override
    protected void stopped() {
        SnakeInput.reset(Direction.NORTH);
        this.stopHandler.handleStop();
    }
}

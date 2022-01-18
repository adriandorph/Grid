package Controller.Snake;

import Controller.Controller;
import Controller.Engine;
import Model.Direction;
import Model.Snake.SnakeGame;
import View.Snake.SnakeRender;
import View.Snake.SnakeView;

public class SnakeEngine extends Engine<SnakeRender> {

    public SnakeEngine(SnakeView snakeView, SnakeGame snakeGame, int FPS) {
        super(snakeView, snakeGame, FPS);
    }

    @Override
    public void start(){
        SnakeInput.activate();
        Thread thread = new Thread(this); // Demands that this is Runnable
        thread.start();
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
        Controller.viewSnakeGameOver();
    }
}

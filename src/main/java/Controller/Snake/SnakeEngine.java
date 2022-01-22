package Controller.Snake;

import Controller.Controller;
import Controller.Engine;
import Model.Snake.SnakeGame;
import View.Snake.SnakeRender;
import View.Snake.SnakeView;

public class SnakeEngine extends Engine<SnakeRender> {

    public SnakeEngine(SnakeView snakeView, SnakeGame snakeGame, int FPS) {
        super(snakeView, snakeGame, FPS);
    }

    @Override
    protected boolean pauseCondition(){
        return SnakeInput.isPaused();
    }

    @Override
    protected void stopped() {
        SnakeInput.setDirection(null);
        Controller.viewSnakeGameOver();
    }
}

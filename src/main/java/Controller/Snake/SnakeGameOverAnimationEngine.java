package Controller.Snake;

import Controller.Controller;
import Controller.Engine;
import Model.Snake.SnakeGameOverAnimation;
import View.Snake.SnakeGameOverInfo;
import View.Snake.SnakeGameOverView;

public class SnakeGameOverAnimationEngine extends Engine<SnakeGameOverInfo> {
    private final int score;
    private final boolean highscore;

    public SnakeGameOverAnimationEngine(SnakeGameOverView snakeGameOverView, SnakeGameOverAnimation updatable, int FPS) {
        super(snakeGameOverView, updatable, FPS);
        score = updatable.getScore();
        highscore = updatable.isNewHighscore();
    }

    @Override
    protected void stopped() {
        if(highscore) {
            Controller.viewNewHighscore(score);
        } else Controller.viewNewSnakeGame();
    }

    @Override
    protected boolean pauseCondition() {
        return false;
    }
}

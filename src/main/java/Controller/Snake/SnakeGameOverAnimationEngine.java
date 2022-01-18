package Controller.Snake;

import Controller.Controller;
import Controller.Engine;
import Model.Snake.SnakeGameOverAnimation;
import View.Grid;
import View.RenderGrid;

public class SnakeGameOverAnimationEngine extends Engine<RenderGrid> {
    private final int score;
    private final boolean highscore;

    public SnakeGameOverAnimationEngine(Grid renderable, SnakeGameOverAnimation updatable, int FPS) {
        super(renderable, updatable, FPS);
        score = updatable.getScore();
        highscore = updatable.isNewHighscore();
    }

    @Override
    protected void stopped() {
        //if(highscore) {
            Controller.viewNewHighscore(score);
        //} else Controller.viewNewSnakeGame();
    }

    @Override
    protected boolean pauseCondition() {
        return false;
    }
}

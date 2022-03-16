package Model.Snake;

import Controller.Updatable;
import Model.Matrix;
import Model.Position;
import Saves.Settings;
import View.RenderGrid;
import View.Snake.SnakeGameOverInfo;

import java.util.Queue;

public class SnakeGameOverAnimation implements Updatable<SnakeGameOverInfo> {

    private final Queue<Position> snake;
    private final Position head;
    private final Matrix squares;
    private RenderGrid renderGrid;
    private double seconds;
    private final double ticksPerSecond;
    private boolean hasFinished = false;
    private final boolean newHighscore;
    private final int score;

    public SnakeGameOverAnimation(SnakeGame snakeGame){
        this.snake = snakeGame.getSnake();
        this.head = snakeGame.getHeadPosition();
        this.squares = snakeGame.getSquares();
        this.ticksPerSecond = snake.size();
        this.renderGrid = snakeGame.getRenderGrid();
        this.score = snakeGame.getScore();
        this.newHighscore = snakeGame.isNewHighScore();
    }

    @Override
    public void update(double seconds) {
        this.seconds += seconds;
        double tickTime = 1.0 / ticksPerSecond; //Effectively not more than FPS
        if(this.seconds >= tickTime){
            this.seconds -= tickTime;
            renderGrid = tick();
        }
    }

    public RenderGrid tick(){
        Position endOfSnake = snake.poll();
        if(endOfSnake == null){
            hasFinished = true;
            return new RenderGrid(squares);
        }

        squares.setColor(endOfSnake.x, endOfSnake.y, Settings.getActiveColorScheme().getBackground());
        squares.setColor(head.x, head.y, Settings.getActiveColorScheme().getHead());
        return new RenderGrid(squares);
    }

    @Override
    public SnakeGameOverInfo getRenderObject() {
        return new SnakeGameOverInfo(score, renderGrid);
    }

    @Override
    public boolean stopCondition() {
        return hasFinished;
    }

    public int getScore() {
        return score;
    }

    public boolean isNewHighscore(){
        return newHighscore;
    }
}

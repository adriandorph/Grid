package Model.Snake;

import Controller.Updatable;
import Model.Matrix;
import Model.Position;
import View.RenderGrid;
import javafx.scene.paint.Color;

import java.util.Queue;

public class SnakeGameOverAnimation implements Updatable<RenderGrid> {

    private final Queue<Position> snake;
    private final Position head;
    private final Matrix squares;
    private RenderGrid renderGrid;
    private double seconds;
    private final double ticksPerSecond;
    private boolean hasFinished = false;
    private final boolean newHighscore;
    private final int score;

    //Color
    private final Color background = Color.rgb(0,0,0);
    private final Color headColor = Color.rgb(255, 0, 0);

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

        squares.setColor(endOfSnake.x, endOfSnake.y, background);
        squares.setColor(head.x, head.y, headColor);
        return new RenderGrid(squares);
    }

    @Override
    public RenderGrid getRenderObject() {
        return renderGrid;
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

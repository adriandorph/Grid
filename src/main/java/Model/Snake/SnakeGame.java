package Model.Snake;

import Controller.Snake.SnakeInput;
import Controller.Updatable;
import Model.ColorFunctions;
import Model.Direction;
import Model.Matrix;
import Model.Position;
import Saves.Settings.ColorSettings;
import Saves.SnakeHighScoreFile;
import View.RenderGrid;
import View.Snake.SnakeRender;
import View.Snake.SnakeUIInfo;
import java.util.LinkedList;
import java.util.Queue;

public class SnakeGame implements Updatable<SnakeRender> {
    private RenderGrid renderGrid;
    private final Matrix squares;
    private double seconds;
    private double beforeStartSeconds;
    private static final int ticksPerSecond = 6;
    private final Queue<Position> snake;
    private Position headPosition;
    private final RandomBits randomBits;
    private Direction direction;
    private int score;
    private int highscore;
    private final int originalHighscore;

    public SnakeGame(double canvasHeight){
        this(32, 18, canvasHeight);
    }

    public SnakeGame(int width, int height, double canvasHeight){
        if ((double) width / height != 16.0 / 9) {
            Exception e = new RuntimeException("Dimensions not allowed. Has to be 16:9");
            e.printStackTrace();
        }
        seconds = 0;
        beforeStartSeconds = 0;
        score = 0;
        highscore = SnakeHighScoreFile.readHighscore();
        originalHighscore = highscore;
        squares = new Matrix(width, height, canvasHeight);
        squares.setAllColor(ColorSettings.getActiveColorScheme().getBackground());
        direction = null;
        headPosition = new Position(width / 2, height / 2);
        snake = new LinkedList<>();
        randomBits = new RandomBits(width, height, 3, snake.toArray(Position[]::new), headPosition);
        renderGrid = beforeStartTick(beforeStartSeconds);
    }

    public Matrix getSquares() {
        return squares;
    }

    public Position getHeadPosition() {
        return headPosition;
    }

    public Queue<Position> getSnake() {
        return snake;
    }

    @Override
    public void update(double seconds) {
        if(!hasStarted()) renderGrid = beforeStartTick(seconds);
        else {
            this.seconds += seconds;
            double tickTime = 1.0 / ticksPerSecond;
            if(this.seconds >= tickTime){
                this.seconds -= tickTime;
                renderGrid = tick();
            }
        }
    }

    @Override
    public SnakeRender getRenderObject() {
        return new SnakeRender(this);
    }

    @Override
    public boolean stopCondition() {
        //Collision with itself
        for(Position position : snake){
            if(headPosition.x == position.x && headPosition.y == position.y) return true;
        }
        return false;
    }

    private RenderGrid tick() {

        Direction newDirection = SnakeInput.getDirection();
        if(newDirection != null) direction = newDirection;
        Position lastPart = snake.peek();
        if(lastPart == null) lastPart = headPosition;
        snake.add(new Position(headPosition.x, headPosition.y));
        headPosition = squares.getNeighbour(headPosition.x, headPosition.y, direction);
        if(!randomBits.checkAndHandleEating(snake.toArray(Position[]::new), headPosition)) snake.poll();
        else score++;

        //Colors
        //Bits
        for(Position position : randomBits.getBits()){
            squares.setColor(position.x, position.y, ColorSettings.getActiveColorScheme().getBits());
        }
        //Snake
        squares.setColor(lastPart.x, lastPart.y, ColorSettings.getActiveColorScheme().getBackground());
        squares.setColor(headPosition.x, headPosition.y, ColorSettings.getActiveColorScheme().getHead());
        for(Position position : snake)
        {
            squares.setColor(position.x, position.y, ColorSettings.getActiveColorScheme().getTail());
        }

        if(score > highscore) {
            SnakeHighScoreFile.saveNewHighScore(score);
            highscore = score;
        }

        return new RenderGrid(squares);
    }

    private RenderGrid beforeStartTick(double seconds){
        direction = SnakeInput.getDirection();
        beforeStartSeconds += seconds;
        squares.setColor(headPosition.x, headPosition.y, ColorFunctions.opacityOnBackground(ColorSettings.getActiveColorScheme().getHead(), ColorSettings.getActiveColorScheme().getBackground(), getOpacity()));

        for(Position pos: randomBits.getBits()){
            squares.setColor(pos.x, pos.y, shouldDisplayStartHelp()? ColorFunctions.opacityOnBackground(ColorSettings.getActiveColorScheme().getBits(), ColorSettings.getActiveColorScheme().getBackground(), 0.5): ColorSettings.getActiveColorScheme().getBits());
        }
        return new RenderGrid(squares);
    }

    public RenderGrid getRenderGrid(){
        return renderGrid;
    }

    public int getScore(){
        return score;
    }

    public boolean isNewHighScore(){
        return score > originalHighscore;
    }

    public boolean hasStarted(){
        return direction != null;
    }

    public double getOpacity(){
        if(hasStarted()) return 1.0;
        else return (Math.cos(beforeStartSeconds * 10) + 1) / 8 * 3 + 0.25;
    }

    private boolean shouldDisplayStartHelp(){
        return !hasStarted() && beforeStartSeconds > 5;
    }

    public SnakeUIInfo getSnakeUIInfo(){
        return new SnakeUIInfo(score, originalHighscore, shouldDisplayStartHelp());
    }
}

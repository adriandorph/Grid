package Controller.Snake;
import Controller.DirectionController;
import Model.Direction;
import Model.Snake.KeyBinding;
import Model.Snake.SnakeGame;
import Saves.Settings.KeyBindingSettings;
import javafx.scene.input.KeyCode;
import Controller.Controller;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeInput {
    private static Direction finaldirection = null;
    private static Queue<Direction> directions = new LinkedList<>();
    private static boolean isActive = false;
    private static boolean paused = false;

    public static void setDirection(Direction direction){
        directions = new LinkedList<>();
        finaldirection = direction;
    }

    public static Direction getDirection(){
        return directions.poll();
    }

    public static boolean isActive() {
        return isActive;
    }

    public static void activate() {
        isActive = true;
    }

    public static void deactivate() {
        isActive = false;
    }

    public static void pause(){
        paused = true;
    }

    public static void unpause(){
        paused = false;
    }

    public static void togglePause(){
        paused = !paused;
    }

    public static boolean isPaused(){return paused;}

    public static void keyInput(KeyCode key){
        if(isActive()) {
            KeyBinding keyBinding = KeyBindingSettings.getActiveKeyBinding();

            if (key == keyBinding.getLeft()) {
                finaldirection = DirectionController.getLeftDirection(finaldirection, SnakeGame.hasStarted());
                directions.add(finaldirection);

            } else if (key == keyBinding.getRight()) {
                finaldirection = DirectionController.getRightDirection(finaldirection, SnakeGame.hasStarted());
                directions.add(finaldirection);

            } else if (key == keyBinding.getWest()){
                if((DirectionController.getOpposite(Direction.WEST) != finaldirection) || !SnakeGame.hasStarted()){
                    finaldirection = Direction.WEST;
                    directions.add(finaldirection);
                }

            } else if (key == keyBinding.getEast()){
                if ((DirectionController.getOpposite(Direction.EAST) != finaldirection) || !SnakeGame.hasStarted()) {
                    finaldirection = Direction.EAST;
                    directions.add(finaldirection);
                }

            } else if (key == keyBinding.getNorth()){
                if ((DirectionController.getOpposite(Direction.NORTH) != finaldirection) || !SnakeGame.hasStarted()) {
                    finaldirection = Direction.NORTH;
                    directions.add(finaldirection);
                }

            } else if (key == keyBinding.getSouth()) {
                if ((DirectionController.getOpposite(Direction.SOUTH) != finaldirection) || !SnakeGame.hasStarted()) {
                    finaldirection = Direction.SOUTH;
                    directions.add(finaldirection);
                }

            } else if (key == keyBinding.getPause()) {
                togglePause();

            } else if (key == keyBinding.getRestart()) {
                Controller.viewNewSnakeGame();

            } else if (key == KeyCode.ESCAPE) {
                pause();
                Controller.viewEscapeMenu();

            }
        }
    }
}

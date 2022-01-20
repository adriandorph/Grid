package Controller.Snake;
import Controller.DirectionController;
import Model.Direction;
import javafx.scene.input.KeyCode;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeInput {
    private static Direction finaldirection = Direction.NORTH;
    private static Queue<Direction> directions = new LinkedList<>();
    private static boolean isActive = false;
    private static boolean paused = false;

    public static void reset(Direction direction){
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

    public static boolean isPaused(){return paused;}

    public static void keyInput(KeyCode key){
        if(isActive()) {


            switch (key) {
                case A -> {
                    finaldirection = DirectionController.getLeftDirection(finaldirection);
                    directions.add(finaldirection);
                }
                case D -> {
                    finaldirection = DirectionController.getRightDirection(finaldirection);
                    directions.add(finaldirection);
                }
                case LEFT -> {
                    if(DirectionController.getOpposite(Direction.WEST) != finaldirection){
                        finaldirection = Direction.WEST;
                        directions.add(finaldirection);
                    }
                }
                case RIGHT -> {
                    if (DirectionController.getOpposite(Direction.EAST) != finaldirection) {
                        finaldirection = Direction.EAST;
                        directions.add(finaldirection);
                    }
                }
                case UP -> {
                    if (DirectionController.getOpposite(Direction.NORTH) != finaldirection) {
                        finaldirection = Direction.NORTH;
                        directions.add(finaldirection);
                    }
                }
                case DOWN -> {
                    if (DirectionController.getOpposite(Direction.SOUTH) != finaldirection) {
                        finaldirection = Direction.SOUTH;
                        directions.add(finaldirection);
                    }
                }
                case P -> paused = !paused;//Pause toggle
            }
        }
    }

}

package Controller.Snake;
import Controller.DirectionController;
import Controller.InputController;
import Model.Direction;
import javafx.scene.input.KeyCode;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeInput implements InputController {
    private Direction finaldirection;
    private static Queue<Direction> directions;

    public SnakeInput(Direction direction){
        directions = new LinkedList<>();
        finaldirection = direction;
    }

    public Direction getDirection(){
        return directions.poll();
    }

    private static boolean isActive = false;

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void activate() {
        isActive = true;
    }

    @Override
    public void deactivate() {
        isActive = false;
    }

    @Override
    public void keyInput(KeyCode key){
        switch (key){
            case A -> {
                finaldirection = DirectionController.getLeftDirection(finaldirection);
                directions.add(finaldirection);
            }
            case D -> {
                finaldirection = DirectionController.getRightDirection(finaldirection);
                directions.add(finaldirection);
            }
            case LEFT -> {
                finaldirection = Direction.WEST;
                directions.add(finaldirection);
            }
            case RIGHT -> {
                if(DirectionController.getOpposite(Direction.EAST) != finaldirection) {
                    finaldirection = Direction.EAST;
                    directions.add(finaldirection);
                }
            }
            case UP -> {
                if(DirectionController.getOpposite(Direction.NORTH) != finaldirection) {
                    finaldirection = Direction.NORTH;
                    directions.add(finaldirection);
                }
            }
            case DOWN -> {
                if(DirectionController.getOpposite(Direction.SOUTH) != finaldirection) {
                    finaldirection = Direction.SOUTH;
                    directions.add(finaldirection);
                }
            }
        }
    }

}

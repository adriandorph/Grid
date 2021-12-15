package Controller.Snake;

import Model.Snake.Direction;

public class DirectionController {

    public static Direction getLeftDirection(Direction direction){
        switch (direction){
            case NORTH -> {return Direction.WEST;}
            case EAST -> {return Direction.NORTH;}
            case SOUTH -> {return Direction.EAST;}
            case WEST -> {return Direction.SOUTH;}
        }
        return null;
    }

    public static Direction getRightDirection(Direction direction){
        switch (direction){
            case NORTH -> {return Direction.EAST;}
            case EAST -> {return Direction.SOUTH;}
            case SOUTH -> {return Direction.WEST;}
            case WEST -> {return Direction.NORTH;}
        }
        return null;
    }
}

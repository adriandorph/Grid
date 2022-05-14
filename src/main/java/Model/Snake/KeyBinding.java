package Model.Snake;

import javafx.scene.input.KeyCode;

import java.io.Serializable;

public class KeyBinding implements Serializable {
    private static final KeyCode[] illegalKeys = {KeyCode.ESCAPE};

    private String name;
    private KeyCode north;
    private KeyCode east;
    private KeyCode south;
    private KeyCode west;
    private KeyCode left;
    private KeyCode right;
    private KeyCode pause;
    private KeyCode restart;

    public KeyBinding(String name, KeyCode north, KeyCode east, KeyCode south, KeyCode west, KeyCode left, KeyCode right, KeyCode pause, KeyCode restart) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.left = left;
        this.right = right;
        this.pause = pause;
        this.restart = restart;
    }

    public String getName(){
        return name;
    }

    public KeyCode getNorth() {
        return north;
    }

    public KeyCode getEast() {
        return east;
    }

    public KeyCode getSouth() {
        return south;
    }

    public KeyCode getWest() {
        return west;
    }

    public KeyCode getLeft() {
        return left;
    }

    public KeyCode getRight() {
        return right;
    }

    public KeyCode getPause() {
        return pause;
    }

    public KeyCode getRestart() {
        return restart;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNorth(KeyCode north) throws IllegalKeyException {
        validateKey(north, illegalKeys);
        this.north = north;
    }

    public void setEast(KeyCode east) throws IllegalKeyException {
        validateKey(east, illegalKeys);
        this.east = east;
    }

    public void setSouth(KeyCode south) throws IllegalKeyException {
        validateKey(south, illegalKeys);
        this.south = south;
    }

    public void setWest(KeyCode west) throws IllegalKeyException {
        validateKey(west, illegalKeys);
        this.west = west;
    }

    public void setLeft(KeyCode left) throws IllegalKeyException {
        validateKey(left, illegalKeys);
        this.left = left;
    }

    public void setRight(KeyCode right) throws IllegalKeyException {
        validateKey(right, illegalKeys);
        this.right = right;
    }

    public void setPause(KeyCode pause) throws IllegalKeyException {
        validateKey(pause, illegalKeys);
        this.pause = pause;
    }

    public void setRestart(KeyCode restart) throws IllegalKeyException {
        validateKey(restart, illegalKeys);
        this.restart = restart;
    }

    public static void validateKey(KeyCode key, KeyCode[] illegalKeys) throws IllegalKeyException {
        for (KeyCode illegalKey: illegalKeys){
            if (key == illegalKey) throw new IllegalKeyException(key);
        }
    }
}

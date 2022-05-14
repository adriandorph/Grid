package Model.Snake;

import javafx.scene.input.KeyCode;

import java.io.Serializable;

public class KeyBinding implements Serializable {
    private static final KeyCode[] illegalKeys = {KeyCode.ESCAPE};

    private String name;
    private final boolean isCustomizable;
    private KeyCode north;
    private KeyCode east;
    private KeyCode south;
    private KeyCode west;
    private KeyCode left;
    private KeyCode right;
    private KeyCode pause;
    private KeyCode restart;

    public KeyBinding(String name, boolean isCustomizable, KeyCode north, KeyCode east, KeyCode south, KeyCode west, KeyCode left, KeyCode right, KeyCode pause, KeyCode restart) {
        this.name = name;
        this.isCustomizable = isCustomizable;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.left = left;
        this.right = right;
        this.pause = pause;
        this.restart = restart;
    }

    /**
     * Creates a KeyBinding that is not customizable
     */
    public KeyBinding(String name, KeyCode north, KeyCode east, KeyCode south, KeyCode west, KeyCode left, KeyCode right, KeyCode pause, KeyCode restart){
        this(name,
                false,
                north,
                east,
                south,
                west,
                left,
                right,
                pause,
                restart
        );
    }

    /**
     * Creates a new KeyBinding that is customizable
     * @param defaultBinding - the KeyBinding that is copied
     * @param name - the name of the new customizable KeyBinding
     */
    public KeyBinding(KeyBinding defaultBinding, String name) {
        this(name,
                true,
                defaultBinding.getNorth(),
                defaultBinding.getEast(),
                defaultBinding.getSouth(),
                defaultBinding.getWest(),
                defaultBinding.getLeft(),
                defaultBinding.getRight(),
                defaultBinding.getPause(),
                defaultBinding.getRestart()
        );
    }

    public String getName(){
        return name;
    }

    public boolean isCustomizable() {
        return isCustomizable;
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
        if(isCustomizable) this.name = name;
        else throw new RuntimeException("The KeyBinding is not customizable");
    }

    public void setNorth(KeyCode north) throws IllegalKeyException {
        validateKey(north, illegalKeys);
        if (isCustomizable) this.north = north;
        else throw new RuntimeException("The KeyBinding is not customizable");
    }

    public void setEast(KeyCode east) throws IllegalKeyException {
        validateKey(east, illegalKeys);
        if(isCustomizable)this.east = east;
        else throw new RuntimeException("The KeyBinding is not customizable");
    }

    public void setSouth(KeyCode south) throws IllegalKeyException {
        validateKey(south, illegalKeys);
        if(isCustomizable)this.south = south;
        else throw new RuntimeException("The KeyBinding is not customizable");
    }

    public void setWest(KeyCode west) throws IllegalKeyException {
        validateKey(west, illegalKeys);
        if(isCustomizable)this.west = west;
        else throw new RuntimeException("The KeyBinding is not customizable");
    }

    public void setLeft(KeyCode left) throws IllegalKeyException {
        validateKey(left, illegalKeys);
        if(isCustomizable)this.left = left;
        else throw new RuntimeException("The KeyBinding is not customizable");
    }

    public void setRight(KeyCode right) throws IllegalKeyException {
        validateKey(right, illegalKeys);
        if(isCustomizable)this.right = right;
        else throw new RuntimeException("The KeyBinding is not customizable");
    }

    public void setPause(KeyCode pause) throws IllegalKeyException {
        validateKey(pause, illegalKeys);
        if(isCustomizable)this.pause = pause;
        else throw new RuntimeException("The KeyBinding is not customizable");
    }

    public void setRestart(KeyCode restart) throws IllegalKeyException {
        validateKey(restart, illegalKeys);
        if(isCustomizable)this.restart = restart;
        else throw new RuntimeException("The KeyBinding is not customizable");
    }

    public static void validateKey(KeyCode key, KeyCode[] illegalKeys) throws IllegalKeyException {
        for (KeyCode illegalKey: illegalKeys){
            if (key == illegalKey) throw new IllegalKeyException(key);
        }
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof KeyBinding)) return false;
        KeyBinding o = (KeyBinding) other;
        return this.name.equals(o.name)
                && this.isCustomizable == o.isCustomizable
                && this.north == o.north
                && this.east == o.east
                && this.south == o.south
                && this.west == o.west
                && this.left == o.left
                && this.right == o.right
                && this.pause == o.pause
                && this.restart == o.restart;
    }
    @Override
    public String toString(){
        return this.name;
    }
}

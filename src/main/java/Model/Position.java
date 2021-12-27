package Model;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        Position other;
        if(o instanceof Position) other = (Position) o;
        else return false;
        return this.x == other.x && this.y == other.y;
    }
}

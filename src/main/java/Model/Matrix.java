package Model;

import javafx.scene.paint.Color;

public class Matrix {
    private final Square[][] matrix;
    private final int lengthX;
    private final int lengthY;

    public Matrix(int lengthX, int lengthY, double canvasHeight){
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        this.matrix = new Square[lengthX][lengthY];

        double squareSize = canvasHeight / lengthY;
        for(int x = 0; x < lengthX; x++){
            for(int y = 0; y < lengthY; y++){
                matrix[x][y] = new Square(squareSize, null, x * squareSize, y * squareSize);
            }
        }
    }

    public void set(int x, int y, Square square){
        matrix[x][y] = square;
    }

    public void set(int x, Square[] squares){
        matrix[x] = squares;
    }

    public void setColor(int x, int y, Color color){
        matrix[x][y].setColor(color);
    }

    public void setAllColor(Color color){
        for(int x = 0; x < lengthX; x++){
            for(int y = 0; y < lengthY; y++){
                matrix[x][y].setColor(color);
            }
        }
    }

    public Square get(int x, int y){
        return matrix[x][y];
    }

    public Square[] get(int x){
        return matrix[x];
    }

    public int getLengthX(){
        return lengthX;
    }

    public int getLengthY(){
        return lengthY;
    }

    public void setSquareSize(double size){
        for(Square[] row : matrix){
            for(Square square : row){
                square.setSize(size);
            }
        }
    }

    public Position getNeighbour(int x, int y, Direction direction){
        if(x >= lengthX || x < 0) return null;
        if(y >= lengthY || y < 0) return null;

        switch (direction){
            case NORTH -> {
                if(x != 0) return new Position(x-1, y);
                else return new Position(lengthX - 1, y);
            }
            case SOUTH -> {
                if(x != lengthX - 1) return new Position(x+1, y);
                else return new Position(0, y);
            }
            case EAST -> {
                if(y != 0) return new Position(x, y - 1);
                else return new Position(x, lengthY - 1);
            }
            case WEST -> {
                if(y != lengthY -1) return new Position(x, y + 1);
                else return new Position(x, 0);
            }
            default -> {return null;}
        }
    }
}

package Model;

import javafx.scene.paint.Color;

public class Matrix {
    private final Square[][] matrix;
    private final int lengthX;
    private final int lengthY;

    public Matrix(int lengthX, int lengthY){
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        this.matrix = new Square[lengthX][lengthY];
        for(int x = 0; x < lengthX; x++){
            for(int y = 0; y < lengthY; y++){
                matrix[x][y] = new Square(0, null);
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
}

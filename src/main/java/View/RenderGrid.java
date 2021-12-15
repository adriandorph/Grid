package View;

import Model.Matrix;

public class RenderGrid {
    private final Matrix squares;

    public RenderGrid(Matrix squares){
        this.squares = squares;
    }

    public Matrix getSquares(){
        return squares;
    }
}

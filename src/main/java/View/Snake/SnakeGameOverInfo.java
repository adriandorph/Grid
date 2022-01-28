package View.Snake;

import View.RenderGrid;

public class SnakeGameOverInfo {
    private final int score;
    private final RenderGrid renderGrid;

    public SnakeGameOverInfo(int score, RenderGrid renderGrid){
        this.score = score;
        this.renderGrid = renderGrid;
    }

    public int getScore() {
        return score;
    }

    public RenderGrid getRenderGrid() {
        return renderGrid;
    }
}

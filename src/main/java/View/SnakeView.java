package View;


public class SnakeView implements Renderable<SnakeRender> {
    Grid grid;
    SnakeUI snakeUI;

    public SnakeView(Grid grid, SnakeUI snakeUI){
        this.grid = grid;
        this.snakeUI = snakeUI;
    }

    @Override
    public void render(SnakeRender renderObject) {
        grid.render(renderObject.getRenderGrid());
        snakeUI.render(renderObject.getScore());
    }

    @Override
    public void endScreen() {
        grid.endScreen();
        snakeUI.endScreen();
    }
}

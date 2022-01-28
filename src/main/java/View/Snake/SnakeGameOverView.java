package View.Snake;

import View.Grid;
import View.Renderable;
import Controller.Controller;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;

public class SnakeGameOverView extends StackPane implements Renderable<SnakeGameOverInfo> {
    private final Grid grid;
    private final SnakeGameOverScoreCanvas scoreCanvas;

    public SnakeGameOverView(Grid grid){
        this.grid = grid;
        scoreCanvas = new SnakeGameOverScoreCanvas(Controller.windowWidth, Controller.windowHeight);
        Platform.runLater(() -> {
            getChildren().add(grid);
            getChildren().add(scoreCanvas);
        });
    }

    @Override
    public void render(SnakeGameOverInfo info) {
        this.grid.render(info.getRenderGrid());
        this.scoreCanvas.render(info.getScore());
    }
}

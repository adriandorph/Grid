package Controller;
import Controller.Snake.SnakeEngine;
import Controller.Snake.SnakeGameOverAnimationEngine;
import Controller.Snake.SnakeInput;
import Model.Matrix;
import Model.Snake.SnakeGame;
import Model.Snake.SnakeGameOverAnimation;
import Saves.SnakeSaveFileWriter;
import View.*;
import View.Snake.SnakeHighscoreCanvas;
import View.Snake.SnakeHighscoreView;
import View.Snake.SnakeUI;
import View.Snake.SnakeView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Controller extends Application{

    public static double factor = 1.0;   // 1.0 = 720p used for scaling.
    public static Stage stage;
    private static boolean fullScreen;
    public static double windowHeight;
    public static double windowWidth;
    private static GridAnimationEngine gridAnimationEngine;
    private static SnakeEngine snakeEngine;
    private static SnakeGame snakeGame;
    private static SnakeGameOverAnimationEngine snakeGameOverAnimationEngine;

    private static View view;

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        Controller.stage = stage;
        setFullScreen();
        //setSize(720);

        Grid grid = new Grid(windowWidth, windowHeight);

        StackPane gridPane = new StackPane();
        gridPane.getChildren().add(grid);

        view = new View(gridPane);
        view.setFill(Color.BLACK);
        Controller.stage.setScene(view);
        sizingAfterNewScene();
        Controller.stage.show();
        setKeyInput();
        viewNewSnakeGame();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void viewNewSnakeGame(){
        if(snakeEngine != null) snakeEngine.dispose();
        Grid grid = new Grid(windowWidth, windowHeight);
        grid.render(new RenderGrid(new Matrix(16, 9, windowHeight)));
        SnakeUI snakeUI = new SnakeUI(windowWidth, windowHeight, SnakeSaveFileWriter.readHighscore());
        SnakeView snakeView = new SnakeView(grid, snakeUI);
        snakeGame = new SnakeGame(grid.getHeight());
        snakeEngine = new SnakeEngine(snakeView, snakeGame, 30);
        StackPane pane = new StackPane();
        pane.getChildren().add(grid);
        pane.getChildren().add(snakeUI);
        Platform.runLater(new Runnable() {
            @Override public void run() {
                view.setRoot(pane);
                stage.show();
            }
        });
        snakeEngine.start();
    }

    public static void viewSnakeGameOver(){
        if(snakeGameOverAnimationEngine != null) snakeGameOverAnimationEngine.stop();
        Grid grid = new Grid(windowWidth, windowHeight);
        grid.render(new RenderGrid(new Matrix(16,9, 9)));
        SnakeGameOverAnimation snakeGameOverAnimation = new SnakeGameOverAnimation(snakeGame);
        snakeGameOverAnimationEngine = new SnakeGameOverAnimationEngine(grid, snakeGameOverAnimation, 30);
        StackPane pane = new StackPane();
        pane.getChildren().add(grid);
        Platform.runLater(new Runnable() {
            @Override public void run() {
                view.setRoot(pane);
                stage.show();
            }
        });
        snakeGameOverAnimationEngine.start();
    }

    public static void viewNewHighscore(int highscore){
        SnakeHighscoreCanvas hc = new SnakeHighscoreCanvas(windowWidth, windowHeight, highscore);
        SnakeHighscoreView shv = new SnakeHighscoreView(hc);
        StackPane pane = new StackPane();
        pane.getChildren().add(shv);
        Platform.runLater(new Runnable() {
            @Override public void run() {
                view.setRoot(pane);
                stage.show();
            }
        });
    }

    private static void sizingAfterNewScene(){
        if (fullScreen){
            stage.setFullScreen(true);
        } else {
            stage.sizeToScene();
            setSize(windowHeight);
        }
    }

    public static void setSize(double height){
        if (!fullScreen && (height < 720 || height > 2160)) throw new RuntimeException("The window size has to be within 720p - 2160p");
        stage.setFullScreen(false);
        factor = height / 720.0;
        windowWidth = height * 16/9.0;
        windowHeight = height;

        stage.setWidth(windowWidth + 10);
        stage.setHeight(height+38);//+38 because javafx is stupid
    }

    public static void setFullScreen(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        setSize(bounds.getHeight()+40);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreen(true);
        fullScreen = true;
    }

    private void setKeyInput(){
        view.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();

            //Global keys
            switch (event.getCode()){
                case ESCAPE -> {
                    Platform.exit();
                    System.exit(0);
                    snakeEngine.stop();
                    gridAnimationEngine.stop();
                    snakeGameOverAnimationEngine.stop();
                }
                case R -> {
                    viewNewSnakeGame();
                }
            }


            //All controllers
            SnakeInput.keyInput(key);
        });
    }
}


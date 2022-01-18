package Controller;
import Controller.Snake.SnakeEngine;
import Controller.Snake.SnakeInput;
import Model.GridAnimation;
import Model.Snake.SnakeGame;
import View.*;
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

public class Controller extends Application implements EngineStopHandler{

    public static double factor = 1.0;   // 1.0 = 720p used for scaling.
    public static Stage stage;
    private static boolean fullScreen;
    public static double windowHeight;
    public static double windowWidth;
    private static GridAnimationEngine gridAnimationEngine;
    private static SnakeEngine snakeEngine;

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
        GridAnimation gridAnimation = new GridAnimation(windowHeight);
        gridAnimationEngine = new GridAnimationEngine(grid, gridAnimation, 30);
        SnakeGame snakeGame = new SnakeGame(grid.getHeight());


        StackPane gridPane = new StackPane();
        gridPane.getChildren().add(grid);

        view = new View(gridPane);
        view.setFill(Color.BLACK);
        Controller.stage.setScene(view);
        sizingAfterNewScene();
        setKeyInput();
        viewNewSnakeGame();
    }

    public static void main(String[] args) {
        launch();
    }

    private static void viewNewSnakeGame(){
        if(snakeEngine != null) snakeEngine.stop();
        Grid grid = new Grid(windowWidth, windowHeight);
        SnakeUI snakeUI = new SnakeUI(windowWidth, windowHeight);
        SnakeView snakeView = new SnakeView(grid, snakeUI);
        SnakeGame snakeGame = new SnakeGame(grid.getHeight());
        snakeEngine = new SnakeEngine(snakeView, snakeGame, 30);
        StackPane pane = new StackPane();
        pane.getChildren().add(grid);
        pane.getChildren().add(snakeUI);
        view.setRoot(pane);
        stage.show();
        snakeEngine.start();
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

        stage.setWidth(windowWidth);
        stage.setHeight(height);
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
                }
                case R -> {
                    viewNewSnakeGame();
                }
            }


            //All controllers
            SnakeInput.keyInput(key);
        });
    }

    @Override
    public void handleStop() {//Game has stopped

    }
}


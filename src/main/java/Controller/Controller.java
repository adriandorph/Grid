package Controller;

import Controller.Snake.*;
import Model.Matrix;
import Model.Snake.SnakeGame;
import Model.Snake.SnakeGameOverAnimation;
import Saves.Settings;
import View.*;
import View.Snake.*;
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
    private static SnakeEngine snakeEngine;
    private static SnakeGame snakeGame;
    private static SnakeGameOverAnimationEngine snakeGameOverAnimationEngine;
    private static StackPane snakeGamePane;
    private static Grid grid;

    private static View view;

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        Controller.stage = stage;
        if (screenHasFormat16_9()) setFullScreen();
        else setSize(720);

        grid = new Grid(windowWidth, windowHeight);

        StackPane gridPane = new StackPane();
        gridPane.getChildren().add(grid);

        //Color
        Settings.setActiveColorScheme(Settings.getColorScheme(2));

        view = new View(gridPane);
        view.setFill(Settings.getActiveColorScheme().getBackground());
        Controller.stage.setScene(view);
        sizingAfterNewScene();
        setKeyInput();
        if(Settings.getStartUpInGame()) viewNewSnakeGame();
        else viewMainMenu();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void viewMainMenu(){
        InputController.MainMenuInput();
        Platform.runLater(() -> {
            SnakeMainMenuView smmv = new SnakeMainMenuView();
            StackPane pane = new StackPane();
            pane.getChildren().add(smmv);
            view.setRoot(pane);
            stage.show();
        });
    }

    public static void viewSettingsMenu(){
        InputController.SettingsInput();
        Platform.runLater(() -> {
            SettingsMenuView smv = new SettingsMenuView();
            StackPane pane = new StackPane();
            pane.getChildren().add(smv);
            view.setRoot(pane);
            stage.show();
        });
    }

    public static void viewNewSnakeGame(){
        InputController.SnakeInput();
        SnakeInput.unpause();
        if(snakeEngine != null) snakeEngine.dispose();
        grid = new Grid(windowWidth, windowHeight);
        grid.render(new RenderGrid(new Matrix(16, 9, windowHeight)));
        SnakeUI snakeUI = new SnakeUI(windowWidth, windowHeight);
        SnakeView snakeView = new SnakeView(grid, snakeUI);
        snakeGame = new SnakeGame(grid.getHeight());
        snakeEngine = new SnakeEngine(snakeView, snakeGame, 30);
        snakeGamePane = new StackPane();
        snakeGamePane.getChildren().add(grid);
        snakeGamePane.getChildren().add(snakeUI);
        Platform.runLater(() -> {
            view.setRoot(snakeGamePane);
            stage.show();
        });
        snakeEngine.start();
    }

    public static void viewExistingSnakeGame(){
        InputController.SnakeInput();
        Platform.runLater(() -> {
            view.setRoot(snakeGamePane);
            stage.show();
        });
    }

    public static void viewEscapeMenu(){
        InputController.EscapeMenuInput();
        EscapeMenuView emv = new EscapeMenuView();
        StackPane pane = new StackPane();
        pane.getChildren().add(emv);
        Platform.runLater(() -> {
            view.setRoot(pane);
            stage.show();
        });
    }

    public static void viewSnakeGameOver(){
        InputController.deactivateAll();
        if(snakeGameOverAnimationEngine != null) snakeGameOverAnimationEngine.dispose();
        SnakeGameOverAnimation snakeGameOverAnimation = new SnakeGameOverAnimation(snakeGame);
        SnakeGameOverView snakeGameOverView = new SnakeGameOverView(grid);
        snakeGameOverAnimationEngine = new SnakeGameOverAnimationEngine(snakeGameOverView, snakeGameOverAnimation, 30);
        Platform.runLater(() -> {
            view.setRoot(snakeGameOverView);
            stage.show();
        });
        snakeGameOverAnimationEngine.start();
    }

    public static void viewNewHighscore(int highscore){
        SnakeHighscoreCanvas hc = new SnakeHighscoreCanvas(windowWidth, windowHeight, highscore);
        SnakeHighscoreView shv = new SnakeHighscoreView(hc);
        StackPane pane = new StackPane();
        pane.getChildren().add(shv);
        Platform.runLater(() -> {
            view.setRoot(pane);
            stage.show();
        });
    }

    public static void exit(){
        Platform.exit();
        System.exit(0);
        snakeEngine.stop();
        snakeGameOverAnimationEngine.stop();

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
        double maxHeight = maxHeight();
        if(maxHeight < height) height = maxHeight;
        if(height % 9 != 0) height = (int)(height / 9.0) * 9;
        windowWidth = height * 16/9.0;
        windowHeight = height;

        stage.setWidth(windowWidth + 10);
        stage.setHeight(height+38);//+38 because javafx is stupid
    }

    public static void setFullScreen(){
        setSize(maxHeight());
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreen(true);
        fullScreen = true;
    }

    public static boolean screenHasFormat16_9(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        double maxHeight = bounds.getHeight() + 40;
        double maxWidth = bounds.getWidth();
        return maxWidth / maxHeight == 16.0 / 9.0;
    }

    public static double maxHeight(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        double maxHeight = bounds.getHeight() + 40; // +40 because api stupid
        double maxWidth = bounds.getWidth();
        if(maxHeight * 16.0 / 9.0 > maxWidth) return maxWidth * 9.0 / 16.0;
        else return maxHeight;
    }

    private void setKeyInput(){
        view.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();

            //All input controllers
            if(EscapeMenuInput.isActive())EscapeMenuInput.keyInput(key);
            else if(MainMenuInput.isActive()) MainMenuInput.keyInput(key);
            else if(SnakeInput.isActive()) SnakeInput.keyInput(key);
            else if(SettingsInput.isActive()) SettingsInput.keyInput(key);
        });
    }
}


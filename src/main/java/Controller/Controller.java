package Controller;
import Controller.Snake.SnakeInput;
import View.View;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Controller extends Application {

    public static double factor = 1.0;   // 1.0 = 720p bruges til skalering.
    public static Stage stage;
    private static boolean fullScreen;
    public static double windowHeight;
    public static double windowWidth;

    private View view;

    private final InputController[] inputControllers = new InputController[]{new SnakeInput()};

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

        view = new View();
        view.setFill(Color.BLACK);
        view.getStylesheets().add("styling/menuButtons.css");
        Controller.stage.setScene(view);
        sizingAfterNewScene();
        for(InputController inputController : inputControllers){
            inputController.setKeyInput(view);
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
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

    }

}


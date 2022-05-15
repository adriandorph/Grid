package View.Snake.Settings;

import Controller.Controller;
import Controller.InputController;
import Saves.Settings.ColorSettings;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

public class KeyAssignmentPane extends Pane {
    private final String bindingAction;

    public KeyAssignmentPane(String bindingAction){
        this.bindingAction = bindingAction;
        setBackground(
                new Background(
                        new BackgroundFill(
                                ColorSettings.getActiveColorScheme().getBackground(),
                                new CornerRadii(0),
                                new Insets(0))
                )
        );

        setPrefSize(Controller.factor * 400, Controller.factor * 250);
        drawListening();
    }

    public void drawListening(){
        getChildren().clear();
        drawText(bindingAction + "\nPress the key to be assigned");
    }

    public void drawIllegalChoice(){
        getChildren().clear();
        drawText(this.bindingAction + "\nKey not allowed\nPick a different key");
    }

    private void drawText(String text){
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Roboto", 35));
        getChildren().add(label);
    }
}

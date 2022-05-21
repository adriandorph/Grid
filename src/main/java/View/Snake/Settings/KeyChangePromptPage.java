package View.Snake.Settings;

import Controller.Controller;
import Model.Snake.ColorScheme;
import Saves.Settings.ColorSettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class KeyChangePromptPage extends StackPane {
    private final String bindingAction;

    public KeyChangePromptPage(String bindingAction){
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
        drawText(bindingAction, "Press the key to be assigned");
    }

    public void drawIllegalChoice(){
        getChildren().clear();
        drawText(this.bindingAction, "Key not allowed, try a different key");
    }

    private void drawText(String keyBindAction, String text){
        Label keyBindActionLabel = new Label(keyBindAction);
        keyBindActionLabel.setTextAlignment(TextAlignment.CENTER);
        keyBindActionLabel.setStyle(
                "-fx-text-fill: " + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getUI()) + ";"
        );
        keyBindActionLabel.setFont(new Font("Roboto", 80 * Controller.factor));
        keyBindActionLabel.setTranslateY(-100 * Controller.factor);

        Label textLabel = new Label(text);
        textLabel.setTextAlignment(TextAlignment.CENTER);
        textLabel.setStyle(
                "-fx-text-fill: " + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getUI()) + ";"
        );
        textLabel.setFont(new Font("Roboto", 50 * Controller.factor));
        getChildren().add(keyBindActionLabel);
        getChildren().add(textLabel);
    }
}

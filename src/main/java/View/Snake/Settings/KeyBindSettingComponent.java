package View.Snake.Settings;

import Model.Snake.ColorScheme;
import Saves.Settings.ColorSettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static Controller.Controller.factor;
import static Controller.Controller.windowHeight;

public class KeyBindSettingComponent extends StackPane {
    public KeyBindSettingComponent(String text, KeyCode currentBinding, EventHandler<ActionEvent> changeAction, boolean isCustomizable){
        Canvas canvasForText = new Canvas(factor * 700, factor * 40);
        GraphicsContext gc = canvasForText.getGraphicsContext2D();
        gc.setFill(ColorSettings.getActiveColorScheme().getUI());
        gc.setFont(new Font("Roboto", 30 * factor));
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText(text, factor * 0, 30 * factor);

        gc.setFont(new Font("Roboto", 25 * factor));
        gc.setTextAlign(TextAlignment.RIGHT);
        String currentBindingText = currentBinding == null? "Not Assigned" : currentBinding.toString();
        gc.fillText(currentBindingText, isCustomizable? factor * 550 : factor * 660, 30 * factor);
        canvasForText.setTranslateX(factor * 10);

        gc.save();
        gc.restore();
        getChildren().clear();
        getChildren().add(canvasForText);

        if (isCustomizable) {

            Button changeKeyBindingButton = new Button("Change");
            changeKeyBindingButton.setTranslateX(factor * 275);
            changeKeyBindingButton.setPrefWidth(factor * 100);
            changeKeyBindingButton.setPrefHeight(windowHeight * 0.04);
            changeKeyBindingButton.setFont(new Font(factor * 20));
            changeKeyBindingButton.setStyle(
                    "-fx-font-size: " + factor * 15 + "px;" +
                            "-fx-text-fill: " + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getBackground()) + ";" +
                            "-fx-background-color:" + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getUI()) + ";"
            );
            changeKeyBindingButton.setOnAction(changeAction);

            getChildren().add(changeKeyBindingButton);
        }
    }
}

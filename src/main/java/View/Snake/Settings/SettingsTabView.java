package View.Snake.Settings;

import Saves.Settings.ColorSettings;
import View.Snake.ArrowCanvas;
import View.Snake.ImageButton;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static Controller.Controller.*;
import static Controller.Controller.factor;

public class SettingsTabView extends StackPane {

    protected String title;
    protected Canvas background;
    protected GraphicsContext gc;
    protected Button backButton;
    public SettingsTabView(String title){
        this.title = title;
        this.background = new Canvas(windowWidth, windowHeight);
        this.gc = background.getGraphicsContext2D();
        this.backButton = new ImageButton(ArrowCanvas.getArrowCanvas((int) (80 * factor), (int) (50 * factor), ColorSettings.getActiveColorScheme().getUI()));

    }

    public void repaint(){
        this.gc.setFill(ColorSettings.getActiveColorScheme().getBackground());
        this.gc.fillRect(0, 0, windowWidth, windowHeight);

        //Settings text
        this.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
        this.gc.setTextAlign(TextAlignment.CENTER);
        this.gc.setFont(new Font("Roboto", 80 * factor));
        this.gc.fillText(title, background.getWidth() / 2, 90 * factor);

        //BackArrowButton
        this.backButton = new ImageButton(ArrowCanvas.getArrowCanvas((int) (80 * factor), (int) (50 * factor), ColorSettings.getActiveColorScheme().getUI()));
        this.backButton.setPrefWidth(windowWidth * 0.075);
        this.backButton.setPrefHeight(windowHeight * 0.05);
        this.backButton.setTranslateX(factor * -450);
        this.backButton.setTranslateY(factor * -300);
    }
}

package View.Snake.Settings;

import Controller.Controller;
import Model.ColorFunctions;
import Model.Snake.ColorScheme;
import Saves.Settings.ColorSettings;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static Controller.Controller.factor;
import static Controller.Controller.windowHeight;

public class EditColorSchemesView extends SettingsTabView {
    private TextField colorSchemeName;

    public EditColorSchemesView() {
        super("Color Settings");
        repaint();
    }

    @Override
    public void repaint() {
        super.repaint();
        super.backButton.setOnAction(e -> Controller.viewSettingsMenu());

        //Color scheme picker
        super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
        super.gc.setTextAlign(TextAlignment.LEFT);
        super.gc.setFont(new Font("Roboto", 35 * factor));
        super.gc.fillText("Color Scheme", 300 * factor, 175 * factor);

        ComboBox<ColorScheme> colorSchemesDropDown = new ComboBox<>();
        ObservableList<ColorScheme> colorSchemesList = colorSchemesDropDown.getItems();
        colorSchemesList.addAll(ColorSettings.getColorSchemes());
        colorSchemesDropDown.getSelectionModel().select(0);
        for (int i = 0; i < colorSchemesList.size(); i++) {
            if (colorSchemesList.get(i).equals(ColorSettings.getActiveColorScheme())) {
                colorSchemesDropDown.getSelectionModel().select(i);
                break;
            }
        }
        colorSchemesDropDown.setStyle("-fx-font-size: " + 20 * factor + "px;");
        colorSchemesDropDown.setPrefWidth(factor * 300);
        colorSchemesDropDown.setTranslateY(factor * -195);
        colorSchemesDropDown.setTranslateX(factor * 175);
        colorSchemesDropDown.setOnAction(updateColorScheme -> {
            ColorSettings.setActiveColorScheme(ColorSettings.getColorScheme(colorSchemesDropDown.getSelectionModel().getSelectedIndex()));
            repaint();
        });

        //Create new colorscheme
        Button createNewColorSchemeButton = new Button("New color scheme");
        createNewColorSchemeButton.setTranslateY(factor * -135);
        createNewColorSchemeButton.setTranslateX(factor * 200);
        createNewColorSchemeButton.setPrefWidth(factor * 250);
        createNewColorSchemeButton.setPrefHeight(windowHeight * 0.05);
        createNewColorSchemeButton.setFont(new Font(factor * 30));
        createNewColorSchemeButton.setStyle(
                "-fx-font-size: " + factor * 20 + "px;" +
                        "-fx-text-fill: " + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getBackground()) + ";" +
                        "-fx-background-color:" + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getUI()) + ";"
        );
        createNewColorSchemeButton.setOnAction(createNewColoScheme -> {
            ColorScheme newColorScheme = new ColorScheme(ColorSettings.getActiveColorScheme(), ColorSettings.getNewColorSchemeName());
            ColorSettings.addColorScheme(newColorScheme);
            ColorSettings.setActiveColorScheme(newColorScheme);
            repaint();
        });

        //Delete colorscheme
        StackPane customizeColorscheme = new StackPane();
        if (ColorSettings.getActiveColorScheme().isCustomizable()) {
            //DeleteButton
            Button deleteColorScheme = new Button("Delete color scheme");
            deleteColorScheme.setTranslateY(factor * -135);
            deleteColorScheme.setTranslateX(factor * -75);
            deleteColorScheme.setPrefWidth(factor * 250);
            deleteColorScheme.setPrefHeight(windowHeight * 0.05);
            deleteColorScheme.setFont(new Font(factor * 30));
            deleteColorScheme.setStyle(
                    "-fx-font-size: " + factor * 20 + "px;" +
                            "-fx-text-fill: " + ColorScheme.toCssHexCode(ColorSettings.getActiveColorScheme().getBackground()) + ";" +
                            "-fx-background-color:" + ColorScheme.toCssHexCode(ColorFunctions.opacityOnBackground(ColorSettings.getActiveColorScheme().getUI(), ColorSettings.getActiveColorScheme().getBackground(), 0.5)) + ";"
            );
            deleteColorScheme.setOnAction(deleteActiveColorScheme -> {
                ColorSettings.deleteColorScheme(colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                ColorSettings.setActiveColorScheme(ColorSettings.getColorScheme(0));
                repaint();
            });
            //Color scheme name
            super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
            super.gc.setTextAlign(TextAlignment.LEFT);
            super.gc.setFont(new Font("Roboto", 35 * factor));
            super.gc.fillText("Color scheme name", 300 * factor, 300 * factor);

            colorSchemeName = new TextField(ColorSettings.getActiveColorScheme().getName());
            colorSchemeName.setStyle("-fx-font-size: " + factor * 20 + "px;");
            colorSchemeName.setMaxWidth(factor * 200);
            colorSchemeName.setPrefWidth(factor * 200);
            colorSchemeName.setPrefHeight(factor * 30);
            colorSchemeName.setTranslateX(factor * 225);
            colorSchemeName.setTranslateY(factor * -70);
            colorSchemeName.textProperty().addListener((ignored, oldValue, newValue) -> {
                String name = newValue;
                if (name.length() > 18) {
                    name = oldValue;
                    colorSchemeName.setText(name);
                }
                ColorScheme colorScheme = ColorSettings.readActiveColorScheme();
                colorScheme.setName(name);
                ColorSettings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
                colorSchemeName.requestFocus();
                colorSchemeName.end();
            });
            colorSchemeName.setOnMouseClicked(caretAtEnd -> {
                colorSchemeName.end();
            });


            //UI
            super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
            super.gc.setTextAlign(TextAlignment.LEFT);
            super.gc.setFont(new Font("Roboto", 35 * factor));
            super.gc.fillText("UI color", 300 * factor, 350 * factor);

            ColorPicker UIPicker = new ColorPicker(ColorSettings.getActiveColorScheme().getUI());
            UIPicker.setTranslateX(factor * 230);
            UIPicker.setTranslateY(factor * -20);
            UIPicker.setStyle("-fx-font-size: " + factor * 20 + "px; -fx-color-rect-width: " + 20 * factor + "px; -fx-color-rect-height: " + 20 * factor + "px;");
            UIPicker.setPrefWidth(187.5 * factor);
            UIPicker.setPrefHeight(40 * factor);
            UIPicker.setOnAction(updateUIColor -> {
                ColorScheme colorScheme = ColorSettings.getActiveColorScheme();
                colorScheme.setUI(UIPicker.getValue());
                ColorSettings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
            });

            //Background
            super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
            super.gc.setTextAlign(TextAlignment.LEFT);
            super.gc.setFont(new Font("Roboto", 35 * factor));
            super.gc.fillText("Background color", 300 * factor, 400 * factor);

            ColorPicker backgroundPicker = new ColorPicker(ColorSettings.getActiveColorScheme().getBackground());
            backgroundPicker.setTranslateX(factor * 230);
            backgroundPicker.setTranslateY(factor * 30);
            backgroundPicker.setStyle("-fx-font-size: " + factor * 20 + "px; -fx-color-rect-width: " + 20 * factor + "px; -fx-color-rect-height: " + 20 * factor + "px;");
            backgroundPicker.setPrefWidth(187.5 * factor);
            backgroundPicker.setPrefHeight(40 * factor);
            backgroundPicker.setOnAction(updateBackgroundColor -> {
                ColorScheme colorScheme = ColorSettings.getActiveColorScheme();
                colorScheme.setBackground(backgroundPicker.getValue());
                ColorSettings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
            });

            //Head
            super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
            super.gc.setTextAlign(TextAlignment.LEFT);
            super.gc.setFont(new Font("Roboto", 35 * factor));
            super.gc.fillText("Snake head color", 300 * factor, 450 * factor);

            ColorPicker headPicker = new ColorPicker(ColorSettings.getActiveColorScheme().getHead());
            headPicker.setTranslateX(factor * 230);
            headPicker.setTranslateY(factor * 80);
            headPicker.setStyle("-fx-font-size: " + factor * 20 + "px; -fx-color-rect-width: " + 20 * factor + "px; -fx-color-rect-height: " + 20 * factor + "px;");
            headPicker.setPrefWidth(187.5 * factor);
            headPicker.setPrefHeight(40 * factor);
            headPicker.setOnAction(updateHeadColor -> {
                ColorScheme colorScheme = ColorSettings.getActiveColorScheme();
                colorScheme.setHead(headPicker.getValue());
                ColorSettings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
            });

            //Tail
            super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
            super.gc.setTextAlign(TextAlignment.LEFT);
            super.gc.setFont(new Font("Roboto", 35 * factor));
            super.gc.fillText("Snake tail color", 300 * factor, 500 * factor);

            ColorPicker tailPicker = new ColorPicker(ColorSettings.getActiveColorScheme().getTail());
            tailPicker.setTranslateX(factor * 230);
            tailPicker.setTranslateY(factor * 130);
            tailPicker.setStyle("-fx-font-size: " + factor * 20 + "px; -fx-color-rect-width: " + 20 * factor + "px; -fx-color-rect-height: " + 20 * factor + "px;");
            tailPicker.setPrefWidth(187.5 * factor);
            tailPicker.setPrefHeight(40 * factor);
            tailPicker.setOnAction(updateTailColor -> {
                ColorScheme colorScheme = ColorSettings.getActiveColorScheme();
                colorScheme.setTail(tailPicker.getValue());
                ColorSettings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
            });

            //Bits
            super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
            super.gc.setTextAlign(TextAlignment.LEFT);
            super.gc.setFont(new Font("Roboto", 35 * factor));
            super.gc.fillText("Food color", 300 * factor, 550 * factor);

            ColorPicker bitsPicker = new ColorPicker(ColorSettings.getActiveColorScheme().getBits());
            bitsPicker.setTranslateX(factor * 230);
            bitsPicker.setTranslateY(factor * 180);
            bitsPicker.setStyle("-fx-font-size: " + factor * 20 + "px; -fx-color-rect-width: " + 20 * factor + "px; -fx-color-rect-height: " + 20 * factor + "px;");
            bitsPicker.setPrefWidth(187.5 * factor);
            bitsPicker.setPrefHeight(40 * factor);
            bitsPicker.setOnAction(updateBitsColor -> {
                ColorScheme colorScheme = ColorSettings.getActiveColorScheme();
                colorScheme.setBits(bitsPicker.getValue());
                ColorSettings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
            });

            //Info
            super.gc.setFill(ColorSettings.getActiveColorScheme().getUI());
            super.gc.setTextAlign(TextAlignment.LEFT);
            super.gc.setFont(new Font("Roboto", 35 * factor));
            super.gc.fillText("Info text color", 300 * factor, 600 * factor);

            ColorPicker infoPicker = new ColorPicker(ColorSettings.getActiveColorScheme().getInfo());
            infoPicker.setTranslateX(factor * 230);
            infoPicker.setTranslateY(factor * 230);
            infoPicker.setStyle("-fx-font-size: " + factor * 20 + "px; -fx-color-rect-width: " + 20 * factor + "px; -fx-color-rect-height: " + 20 * factor + "px;");
            infoPicker.setPrefWidth(187.5 * factor);
            infoPicker.setPrefHeight(40 * factor);
            infoPicker.setOnAction(updateInfoColor -> {
                ColorScheme colorScheme = ColorSettings.getActiveColorScheme();
                colorScheme.setInfo(infoPicker.getValue());
                ColorSettings.updateActiveColorScheme(colorScheme, colorSchemesDropDown.getSelectionModel().getSelectedIndex());
                repaint();
            });

            customizeColorscheme.getChildren().add(deleteColorScheme);
            customizeColorscheme.getChildren().add(colorSchemeName);
            customizeColorscheme.getChildren().add(UIPicker);
            customizeColorscheme.getChildren().add(backgroundPicker);
            customizeColorscheme.getChildren().add(headPicker);
            customizeColorscheme.getChildren().add(tailPicker);
            customizeColorscheme.getChildren().add(bitsPicker);
            customizeColorscheme.getChildren().add(infoPicker);

        }

        //Insert all
        super.gc.restore();
        getChildren().clear();
        getChildren().add(super.background);
        if (ColorSettings.getActiveColorScheme().isCustomizable()) getChildren().add(customizeColorscheme);
        getChildren().add(super.backButton);
        getChildren().add(colorSchemesDropDown);
        getChildren().add(createNewColorSchemeButton);
    }
}

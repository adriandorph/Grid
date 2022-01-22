package View.Snake;

import Controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ImageButton extends Button {
    public ImageButton(String iconPath){
        super();
        Image image = new Image(Objects.requireNonNull(Controller.class.getClassLoader().getResourceAsStream(iconPath)));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(0.05 * Controller.windowHeight);
        imageView.setFitWidth(0.05 * Controller.windowHeight);
        setGraphic(imageView);
        getStyleClass().add("imagebutton");

    }
}

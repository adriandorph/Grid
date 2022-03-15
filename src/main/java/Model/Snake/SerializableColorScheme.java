package Model.Snake;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class SerializableColorScheme implements Serializable {
    private final String name;
    private final double[] UI;
    private final double[] background;
    private final double[] head;
    private final double[] tail;
    private final double[] bits;
    private final double[] info;

    public SerializableColorScheme(ColorSheme colorSheme) {
        this.name = colorSheme.getName();
        this.UI = new double[] {colorSheme.getUI().getRed(), colorSheme.getUI().getGreen(), colorSheme.getUI().getBlue()};
        this.background = new double[] {colorSheme.getBackground().getRed(), colorSheme.getBackground().getGreen(), colorSheme.getBackground().getBlue()};
        this.head = new double[] {colorSheme.getHead().getRed(), colorSheme.getHead().getGreen(), colorSheme.getHead().getBlue()};
        this.tail = new double[] {colorSheme.getTail().getRed(), colorSheme.getTail().getGreen(), colorSheme.getTail().getBlue()};
        this.bits = new double[] {colorSheme.getBits().getRed(), colorSheme.getBits().getGreen(), colorSheme.getBits().getBlue()};
        this.info = new double[] {colorSheme.getInfo().getRed(), colorSheme.getInfo().getGreen(), colorSheme.getInfo().getBlue()};
    }

    public ColorSheme toColorScheme(){
        return new ColorSheme(name,
                Color.color(UI[0],UI[1],UI[2]),
                Color.color(background[0], background[1], background[2]),
                Color.color(head[0], head[1], head[2]),
                Color.color(tail[0], tail[1], tail[2]),
                Color.color(bits[0], bits[1],bits[2]),
                Color.color(info[0], info[1], info[2])
                );
    }
}

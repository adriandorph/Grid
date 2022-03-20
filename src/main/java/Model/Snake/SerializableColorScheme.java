package Model.Snake;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class SerializableColorScheme implements Serializable {
    private final String name;
    private final boolean customizable;
    private final double[] UI;
    private final double[] background;
    private final double[] head;
    private final double[] tail;
    private final double[] bits;
    private final double[] info;

    public SerializableColorScheme(ColorScheme colorScheme) {
        this.name = colorScheme.getName();
        this.customizable = colorScheme.isCustomizable();
        this.UI = new double[] {colorScheme.getUI().getRed(), colorScheme.getUI().getGreen(), colorScheme.getUI().getBlue()};
        this.background = new double[] {colorScheme.getBackground().getRed(), colorScheme.getBackground().getGreen(), colorScheme.getBackground().getBlue()};
        this.head = new double[] {colorScheme.getHead().getRed(), colorScheme.getHead().getGreen(), colorScheme.getHead().getBlue()};
        this.tail = new double[] {colorScheme.getTail().getRed(), colorScheme.getTail().getGreen(), colorScheme.getTail().getBlue()};
        this.bits = new double[] {colorScheme.getBits().getRed(), colorScheme.getBits().getGreen(), colorScheme.getBits().getBlue()};
        this.info = new double[] {colorScheme.getInfo().getRed(), colorScheme.getInfo().getGreen(), colorScheme.getInfo().getBlue()};
    }

    public ColorScheme toColorScheme(){
        return new ColorScheme(name,
                customizable,
                Color.color(UI[0],UI[1],UI[2]),
                Color.color(background[0], background[1], background[2]),
                Color.color(head[0], head[1], head[2]),
                Color.color(tail[0], tail[1], tail[2]),
                Color.color(bits[0], bits[1],bits[2]),
                Color.color(info[0], info[1], info[2])
                );
    }
}

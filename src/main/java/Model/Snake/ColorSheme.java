package Model.Snake;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class ColorSheme implements Serializable {
    private String name;
    private Color UI;
    private Color background;
    private Color head;
    private Color tail;
    private Color bits;
    private Color info;

    public ColorSheme(String name, Color UI, Color background, Color head, Color tail, Color bits, Color info) {
        this.name = name;
        this.UI = UI;
        this.background = background;
        this.head = head;
        this.tail = tail;
        this.bits = bits;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Color autoInfoColor(){//Possible problem if background is grey
        return background.invert();
    }

    public Color getUI() {
        return UI;
    }

    public void setUI(Color UI) {
        this.UI = UI;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getHead() {
        return head;
    }

    public void setHead(Color head) {
        this.head = head;
    }

    public Color getTail() {
        return tail;
    }

    public void setTail(Color tail) {
        this.tail = tail;
    }

    public Color getBits() {
        return bits;
    }

    public void setBits(Color bits) {
        this.bits = bits;
    }

    public Color getInfo() {
        return info;
    }

    public void setInfo(Color info) {
        this.info = info;
    }
}

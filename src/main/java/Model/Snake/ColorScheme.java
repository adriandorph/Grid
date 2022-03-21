package Model.Snake;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class ColorScheme implements Serializable {
    private String name;
    private final boolean customizable;
    private Color UI;
    private Color background;
    private Color head;
    private Color tail;
    private Color bits;
    private Color info;



    public ColorScheme(String name, boolean customizable, Color UI, Color background, Color head, Color tail, Color bits, Color info){
        this.name = name;
        this.customizable = customizable;
        this.UI = UI;
        this.background = background;
        this.head = head;
        this.tail = tail;
        this.bits = bits;
        this.info = info;
    }

    /**
     * Creates a colorScheme that is not customizable
     * @param name
     * @param UI
     * @param background
     * @param head
     * @param tail
     * @param bits
     * @param info
     */
    public ColorScheme(String name, Color UI, Color background, Color head, Color tail, Color bits, Color info) {
        this(name, false, UI, background, head, tail, bits, info);
    }

    /**
     * Creates a new colorScheme that is customizable
     * @param colorScheme - the colorScheme that is copied
     * @param name - the name of the new customizable colorScheme
     */
    public ColorScheme(ColorScheme colorScheme, String name){
        this(name, true, colorScheme.getUI(),  colorScheme.getBackground(), colorScheme.getHead(), colorScheme.getTail(), colorScheme.getBits(), colorScheme.getInfo());
    }



    public static String toCssHexCode(Color color){
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255),
                (int)( color.getBlue() * 255 )
        );
    }

    public String getName() {
        return name;
    }

    public boolean isCustomizable(){
        return customizable;
    }

    public Color getUI() {
        return UI;
    }

    public Color getBackground() {
        return background;
    }

    public Color getHead() {
        return head;
    }

    public Color getTail() {
        return tail;
    }

    public Color getBits() {
        return bits;
    }

    public Color getInfo() {
        return info;
    }

    public void setName(String name) {
        if (customizable) this.name = name;
        else throw new RuntimeException("The colorscheme is not customizable");
    }

    public void setUI(Color UI) {
        if(customizable) this.UI = UI;
        else throw new RuntimeException("The colorscheme is not customizable");
    }

    public void setBackground(Color background) {
        if(customizable) this.background = background;
        else throw new RuntimeException("The colorscheme is not customizable");
    }

    public void setHead(Color head) {
        if(customizable) this.head = head;
        else throw new RuntimeException("The colorscheme is not customizable");
    }

    public void setTail(Color tail) {
        if(customizable) this.tail = tail;
        else throw new RuntimeException("The colorscheme is not customizable");
    }

    public void setBits(Color bits) {
        if(customizable) this.bits = bits;
        else throw new RuntimeException("The colorscheme is not customizable");
    }

    public void setInfo(Color info) {
        if(customizable) this.info = info;
        else throw new RuntimeException("The colorscheme is not customizable");
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof ColorScheme)) return false;
        ColorScheme other = (ColorScheme) o;
        return this.name.equals(other.name)
                && this.customizable == other.customizable
                && this.UI.equals(other.UI)
                && this.background.equals(other.background)
                && this.head.equals(other.head)
                && this.tail.equals(other.tail)
                && this.bits.equals(other.bits)
                && this.info.equals(other.info);
    }
}

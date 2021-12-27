package Model;

import javafx.scene.paint.Color;

public class Square {
    private double posX;
    private double posY;
    private double size;
    private double[] pointsX;
    private double[] pointsY;
    private Color color;

    public Square(double size, Color color, double posX, double posY){
        this.size = size;
        setPosition(posX, posY);
        this.color = color;
    }

    public void setSize(double size){
        this.size = size;
        setPosition();
    }

    private void setPosition(){
        this.pointsX = new double[]{posX, posX,        posX + size, posX + size};
        this.pointsY = new double[]{posY, posY + size, posY + size, posY       };
    }

    public void setPosition(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
        setPosition();
    }

    public double[] getPointsX(){
        return pointsX;
    }

    public double[] getPointsY(){
        return pointsY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

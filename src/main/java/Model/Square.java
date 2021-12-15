package Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square {
    private double[] pointsX;
    private double[] pointsY;
    private Color color;

    public Square(double size, Color color){
        setSize(size);
        this.color = color;
    }

    public void setSize(double size){
        this.pointsX = new double[]{0,0,size,size};
        this.pointsY = new double[]{0,size,size,0};
    }

    public double[] getPointsXWithPosition(double x){
        return getPointsWithPosition(x, pointsX);
    }

    public double[] getPointsYWithPosition(double y){
        return getPointsWithPosition(y, pointsY);
    }

    private double[] getPointsWithPosition(double pos, double[] points){
        double[] pointsWithPos = new double[points.length];
        for(int i = 0; i<points.length; i++){
            pointsWithPos[i] = points[i] + pos;
        }
        return pointsWithPos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

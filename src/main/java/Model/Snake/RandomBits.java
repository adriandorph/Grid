package Model.Snake;

import Model.Position;

public class RandomBits {
    private Position[] bits;
    private final int height;
    private final int width;

    public RandomBits(int width, int height, int bits, Position[] snake, Position head){
        this.width = width;
        this.height = height;
        this.bits = new Position[bits];
        randomAll(snake, head);
    }

    public void randomAll(Position[] snake, Position head){
        for(int i = 0; i < bits.length; i++){
            bits[i] = randomPosition(bits, snake, head);
        }
    }

    private Position randomPosition(Position[] bits, Position[] snake, Position head){
        Position bit = new Position((int)(Math.random() * width), (int)(Math.random() * height));
        while(!isPositionAvailable(bit, bits, snake, head)){
            bit = new Position((int)(Math.random() * width), (int)(Math.random() * height));
        }
        return bit;
    }

    public boolean isSnakeEating(Position[] snake,Position head){
        boolean yesno = false;
        Position[] updatedBits = new Position[bits.length];
        for(int i = 0; i < bits.length; i++){
            if (!bits[i].equals(head)) updatedBits[i] = bits[i];
            else {
                updatedBits[i] = randomPosition(bits, snake, head);
                yesno = true;
            }
        }
        bits = updatedBits;
        return yesno;
    }

    private boolean isPositionAvailable(Position bitePosition, Position[] bits, Position[] snake, Position head){
        if(head.equals(bitePosition)) return false;
        for(Position position : snake){
            if(position.equals(bitePosition)) return false;
        }
        for(Position position : bits){
            if (position != null && position.equals(bitePosition)) return false;
        }
        return true;
    }

    public Position[] getBits(){
        return bits;
    }
}

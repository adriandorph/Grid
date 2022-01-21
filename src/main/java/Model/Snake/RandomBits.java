package Model.Snake;

import Model.Position;

import java.util.ArrayList;

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

    private Position[] getAvailableSpace(Position[] bits, Position[] snake, Position head){
        Position[] availableSpace = new Position[width * height - snake.length - bits.length - 1]; //-1 because of the snakes head
        int index = 0;
        for(int i = 0; index < availableSpace.length; i++){
            Position curPos = new Position(i % width, i / width);
            boolean available = true;
            if(curPos.equals(head)){
                available = false;
            } else {
                for (Position pos : snake) {
                    if (curPos.equals(pos)) {
                        available = false;
                        break;
                    }
                }
                if(available){
                    for (Position pos : bits) {
                        if (curPos.equals(pos)) {
                            available = false;
                            break;
                        }
                    }
                }

            }
            if(available){
                availableSpace[index] = curPos;
                index++;
            }
        }
        return availableSpace;
    }

    private void randomAll(Position[] snake, Position head){
        for(int i = 0; i < bits.length; i++){
            Position bit = randomPosition(bits, snake, head);
            if(bit != null) bits[i] = bit;
            else {
                var shorterBits = new ArrayList<Position>();
                for(Position pos: bits){
                    if(pos != null) shorterBits.add(pos);
                }
                bits = shorterBits.toArray(Position[]::new);
            }
        }
    }

    private Position randomPosition(Position[] bits, Position[] snake, Position head){
        Position[] availableSpace = getAvailableSpace(bits, snake,head);
        if(availableSpace.length == 0) return null;
        return availableSpace[(int)(Math.random() * (availableSpace.length - 1))];
    }

    public boolean checkAndHandleEating(Position[] snake, Position head){
        boolean eating = false;
        ArrayList<Position> updatedBits = new ArrayList<>();
        for (Position position : bits) {
            if (!position.equals(head)) updatedBits.add(position);
            else {
                Position bit = randomPosition(bits, snake, head);
                if (bit != null) updatedBits.add(bit);
                eating = true;
            }
        }
        bits = updatedBits.toArray(Position[]::new);
        return eating;
    }

    public Position[] getBits(){
        return bits;
    }
}

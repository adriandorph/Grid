package View.Snake;

public class SnakeUIInfo {
    private final int score;
    private final int highscore;

    public SnakeUIInfo(int score, int highscore){
        this.highscore = highscore;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getHighscore() {
        return highscore;
    }
}

package View.Snake;

public class SnakeUIInfo {
    private final int score;
    private final int highscore;
    private final boolean displayStartHelp;

    public SnakeUIInfo(int score, int highscore, boolean displayStartHelp){
        this.highscore = highscore;
        this.score = score;
        this.displayStartHelp = displayStartHelp;
    }

    public boolean shouldDisplayStartHelp() {
        return displayStartHelp;
    }

    public int getScore() {
        return score;
    }

    public int getHighscore() {
        return highscore;
    }
}

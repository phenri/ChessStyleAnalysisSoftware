package ua.edu.vntu.analysis;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 18.05.13
 * Time: 19:08
 */
public class RatingMove {
    private int movingValue;

    public RatingMove(int movingValue) {
        this.movingValue = movingValue;
    }

    public RatingMove() {
    }

    public int getMovingValue() {
        return movingValue;
    }

    public void setMovingValue(int movingValue) {
        this.movingValue = movingValue;
    }

    @Override
    public String toString() {
        return "RatingMove{" +
                "movingValue=" + movingValue +
                '}';
    }
}

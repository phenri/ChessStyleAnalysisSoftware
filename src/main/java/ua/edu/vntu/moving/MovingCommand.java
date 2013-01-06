package ua.edu.vntu.moving;

public interface MovingCommand {
    public void start();
    public void pause();
    public void stop();
    public void next();
    public void back();
}

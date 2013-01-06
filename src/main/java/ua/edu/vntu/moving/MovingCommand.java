package ua.edu.vntu.moving;

public interface MovingCommand {
    public void start(int partyId);
    public void pause(int partyId);
    public void stop(int partyId);
    public void next(int partyId);
    public void back(int partyId);
}

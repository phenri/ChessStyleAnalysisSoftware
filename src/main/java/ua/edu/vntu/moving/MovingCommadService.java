package ua.edu.vntu.moving;

import org.springframework.stereotype.Repository;

@Repository("movingCommand")
public class MovingCommadService implements MovingCommand {

    public MovingCommadService() {
        System.out.println("hello");
    }

    @Override
    public void start(int partyId) {

    }

    @Override
    public void pause(int partyId) {

    }

    @Override
    public void stop(int partyId) {

    }

    @Override
    public void next(int partyId) {

    }

    @Override
    public void back(int partyId) {

    }
}

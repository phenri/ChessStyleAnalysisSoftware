package ua.edu.vntu.handlers;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 12.05.13
 * Time: 12:37
 */
@Service
public class CommandActionsHandler implements CommandActions {
    @Override
    public void toEnd() {
        System.out.println("ToEnd");
    }

    @Override
    public void previous() {
        System.out.println("Previous");
    }

    @Override
    public void play() {
        System.out.println("Play");
    }

    @Override
    public void pause() {
        System.out.println("Pause");
    }

    @Override
    public void next() {
        System.out.println("Next");
    }

    @Override
    public void toBegin() {
        System.out.println("toBegin");
    }
}

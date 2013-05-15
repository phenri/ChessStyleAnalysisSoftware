package ua.edu.vntu.containers;

import ua.edu.vntu.chessboard.Figures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadedParty implements SaverParty, ContainerAllMoves {

    public final static List<Figures> states = Collections.synchronizedList(new ArrayList<Figures>());

    public ReadedParty() {
        System.out.println("Readed party created");
    }

    @Override
    public synchronized void save(Figures figures) {
        try {
            states.add((Figures) figures.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Figures getAction(int index) {
        return states.get(index);
    }

    @Override
    public Figures getNextAction() {
        return states.listIterator().next();
    }

    @Override
    public Figures getPreviosAction() {
        return states.listIterator().previous();
    }

    @Override
    public Figures toBegin() {
        return states.get(0);
    }

    @Override
    public Figures toEnd() {
        return states.get(states.size() - 1);
    }

    @Override
    public void clear() {
        states.clear();
    }
}

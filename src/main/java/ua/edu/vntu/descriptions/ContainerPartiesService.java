package ua.edu.vntu.descriptions;

import ua.edu.vntu.Main;
import ua.edu.vntu.chessboard.Cells;

import java.util.ArrayList;
import java.util.List;

public class ContainerPartiesService implements ContainerParties {

    private final static ContainerParties INSTANCE = new ContainerPartiesService();
    private List<Party> parties = new ArrayList<>();
    private ContainerPartiesService(){}

    Cells cells = (Cells)Main.context.getBean("cells");

    public static ContainerParties getInstance() {
        return INSTANCE;
    }

    @Override
    public synchronized Party getPartyById(int id) {
        if (id > parties.size())
            return null;
        for(Party p: parties){
            if (id == p.getId())
                return p;
        }
        return null;
    }

    @Override
    public synchronized void addParty(Party party) {
        party.setFigures(cells.initFigures());
        parties.add(party);
        System.out.println(parties.size());
    }

}

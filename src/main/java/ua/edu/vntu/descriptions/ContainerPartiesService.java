package ua.edu.vntu.descriptions;

import java.util.ArrayList;
import java.util.List;

public class ContainerPartiesService implements ContainerParties {

    private final static ContainerParties INSTANCE = new ContainerPartiesService();
    private List<Party> parties = new ArrayList<>();
    private ContainerPartiesService(){}

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
        parties.add(party);
        System.out.println(parties.size());
    }

}

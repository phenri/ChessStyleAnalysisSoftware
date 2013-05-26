package ua.edu.vntu.containers;

import ua.edu.vntu.descriptions.Party;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContainerParsedPartiesService implements ContainerParties {

    private final static ContainerParties INSTANCE = new ContainerParsedPartiesService();
    private List<Party> parties = Collections.synchronizedList(new ArrayList<Party>());

    private ContainerParsedPartiesService() {
    }

    public static ContainerParties getInstance() {
        return INSTANCE;
    }

    public List<Party> getParties() {
        return parties;
    }

    @Override
    public synchronized Party getPartyById(int id) {
        if (id > parties.size())
            return null;
        for (Party p : parties) {
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

    @Override
    public void clear() {
        parties.clear();
    }
}

package ua.edu.vntu.containers;


import ua.edu.vntu.descriptions.Party;

public interface ContainerParties {

    Party getPartyById(int id);

    void addParty(Party party);

}

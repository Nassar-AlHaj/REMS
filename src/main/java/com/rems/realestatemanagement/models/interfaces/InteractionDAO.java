package com.rems.realestatemanagement.models.interfaces;

import com.rems.realestatemanagement.models.Interaction;
import java.util.List;

public interface InteractionDAO {

    void createInteraction(Interaction interaction);


    List<Interaction> getAllInteractions();
    public void deleteInteraction(Interaction interaction);


}

package com.rems.realestatemanagement.models.interfaces;

import com.rems.realestatemanagement.models.services.Offers;

import java.util.List;

public interface OffersDAO {
    public void CreateOffer(Offers offer);
    List<Offers> getAllOffers();

}

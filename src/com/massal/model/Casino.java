package com.massal.model;


import java.util.ArrayList;
import java.util.List;

public class Casino {


    private List<Carte> hand;

    public Casino(){
        this.hand = new ArrayList<>();
    }

    public void tirerCard(Carte carte) {
        this.hand.add(carte);
    }

    public List<Carte> getHand(){
        return this.hand;
    }


}

package com.massal.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    //Au BlackJack en france on utilise un deck compose de 6 paquets de 52 carte

    private List<Carte> deck;

    public Deck (){
        deck = new ArrayList<>();
        for(int i=0;i<6;i++) {
            for (Rank rank : Rank.values()) {
                for (Couleur couleur : Couleur.values()) {
                    deck.add(new Carte(rank, couleur));
                }
            }
        }
        Collections.shuffle(deck);
    }




    public void removeFirstCarte(){
        this.deck.remove(0);
    }

    public Carte getFirstCarte(){
        Carte carte = this.deck.get(0);
        removeFirstCarte();
        return carte;
    }

    public void bruleCarte(){
        deck.subList(0,4).clear();
    }

}

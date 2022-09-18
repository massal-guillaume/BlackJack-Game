package com.massal.model;

public class Carte  {
    private Rank rank;
    private Couleur couleur;


    public Carte (Rank rank, Couleur couleur){
        super();
        this.rank=rank;
        this.couleur=couleur;
    }

    public Rank getRank() {
        return this.rank;
    }

    public Couleur getCouleur(){
        return this.couleur;
    }
}

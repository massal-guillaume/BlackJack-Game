package com.massal.model;

public enum Couleur {

    Pique(1),
    Coeur(2),
    Carreau(3),
    Trefle(4);

    final int colorOfTheCard;

    Couleur (int colorOfTheCard){
        this.colorOfTheCard = colorOfTheCard;
    }

}

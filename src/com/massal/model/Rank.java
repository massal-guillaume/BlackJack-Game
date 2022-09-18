package com.massal.model;

public enum Rank {

    As (1),
    Roi(10),
    Dame(10),
    Valet(10),
    dix(10),
    neuf(9),
    huit(8),
    sept(7),
    six(6),
    cinq(5),
    quatre(4),
    trois(3),
    deux(2);

    final int valueOfTheCard;

    private Rank(int value){
        valueOfTheCard=value;
    }

    public int value(){
        return valueOfTheCard;
    }

}

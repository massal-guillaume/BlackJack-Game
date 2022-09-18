package com.massal.model;


import java.util.ArrayList;
import java.util.List;

public class Joueur {

    private List<Carte> hand;
    private List<Carte> handsplited;
    private int jetons;
    private int mise;
    private int misesplited;
    private int assurance;

    public Joueur (int jetons){
        this.hand = new ArrayList<>();
        this.handsplited = new ArrayList<>();
        this.jetons = jetons;
        this.mise = 0;
        this.misesplited = 0;
        this.assurance=0;
    }

    public int getAssurance(){
        return this.assurance;
    }
    public void tirerCardHand(Carte carte) {
    this.hand.add(carte);
    }
    public void tirerCarteHandsplited(Carte carte){
        this.handsplited.add(carte);
    }

    public void split(){
        this.handsplited.add(this.hand.get(1));
        this.hand.remove(1);
        this.misersplit();
    }

    public void doubler(){
        this.jetons = jetons - mise;
        this.mise = mise * 2;
    }

    public void miser(int mise){
    this.mise = mise;
    this.jetons = jetons - mise;
    }
    private void misersplit(){
        this.misesplited=this.mise;
        this.jetons= this.jetons-this.mise;
    }

    public List<Carte> getHand(){
        return this.hand;
    }
    public List<Carte> getHandsplited(){
        return this.handsplited;
    }

    public void assurance(){
        this.assurance = this.mise/2;
        this.jetons = jetons - assurance;
    }

    public int getJetons(){
        return this.jetons;
    }
    public int getMise(){
        return this.mise;
    }
    public int getMisesplited(){
        return this.misesplited;
    }

    public void gain(int mise,float coeff){
        this.jetons= this.jetons + mise + (int) (mise*coeff);
    }
    public void gainAssurance(int assurance){
        this.jetons = this.jetons + assurance*2;
    }

    public void reset(){
        this.mise =0;
        this.misesplited = 0;
        this.assurance=0;
    }

}

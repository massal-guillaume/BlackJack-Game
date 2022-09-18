package com.massal.controller;

import com.massal.model.Carte;
import com.massal.model.Casino;
import com.massal.model.Deck;
import com.massal.model.Joueur;
import com.massal.view.View;

import java.util.List;

public class GameControlleur {

    enum EtatduJeux{
        Findepartie,tourJoueur,initialisation
    }

    private Deck deck;
    private Casino casino;
    private Joueur joueur;
    private View view;
    private EtatduJeux etatduJeux;
    private boolean splited;

    public GameControlleur (View view){

    this.joueur = new Joueur(100);
    this.casino = new Casino();
    this.view=view;

    }

    public void run(){
     while(this.etatduJeux!=EtatduJeux.Findepartie) {
        if (this.etatduJeux == EtatduJeux.initialisation) {
            this.deck = new Deck();
            this.deck.bruleCarte();
            this.joueur.miser(this.view.askMise(this.joueur));
            this.joueur.tirerCardHand(this.deck.getFirstCarte());
            this.joueur.tirerCardHand(this.deck.getFirstCarte());
            this.casino.tirerCard(this.deck.getFirstCarte());
            this.view.affichageCarte();
            this.splited = false;
            checkBlackJack();
        }
        if (this.etatduJeux == EtatduJeux.tourJoueur){
            String actionJoueur = this.view.askjoueur();
            switch (actionJoueur) {
                case "double":
                    if(this.joueur.getMise()*2 > this.joueur.getJetons()){
                        this.view.actionImpossible();
                    }
                    else{
                        if(splited) {
                                this.view.actionImpossible();
                        }else{
                                this.joueur.doubler();
                                this.joueur.tirerCardHand(this.deck.getFirstCarte());
                                this.view.affichageCarte();
                                this.view.affichageJetons();
                                checkBlackJack();
                            }
                        }
                    break;
                case "tirer":
                    if(splited) {
                        this.joueur.tirerCarteHandsplited(this.deck.getFirstCarte());
                    }
                    else{
                        this.joueur.tirerCardHand(this.deck.getFirstCarte());
                    }
                    this.view.affichageCarte();
                    checkBlackJack();
                    break;
                case "split":
                    if(this.joueur.getMise()*2 > this.joueur.getJetons()){
                        this.view.actionImpossible();
                    }else {
                        if(!splited){
                            joueur.split();
                            splited=true;
                        } else {
                            this.view.actionImpossible();
                        }
                    }
                    break;
                case "assurance":
                    if(splited){
                        this.view.actionImpossible();
                    }else{
                        if(this.joueur.getHand().size() != 1){
                            this.view.actionImpossible();
                        }else{
                            if(joueur.getJetons() > (joueur.getMise()/2)){
                                 this.joueur.assurance();
                                }
                            }
                        }
                    break;
                case "hold":
                    checkVictoire();
                    break;
            }

        }
        if (this.etatduJeux == EtatduJeux.Findepartie) {

        }
     }
    }

    public void checkVictoire(){


    }
    public void checkBlackJack(){
        if(splited){
         if(checkScore(joueur.getHandsplited()) == 21){
             etatduJeux = EtatduJeux.tourJoueur;
             splited=false;
         }else{
             etatduJeux= EtatduJeux.tourJoueur;
         }

        }else{

        }
    }

    private int checkScore(List<Carte> hand){
    return 0;
    }

   /*
    Distribution des cartes
    Check blackJack
    Premiere action du joueurs

     */







}

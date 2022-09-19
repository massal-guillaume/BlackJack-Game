package com.massal.view;

import com.massal.model.Casino;
import com.massal.model.Joueur;

import java.util.Scanner;

public class View {


    public int askMise(Joueur joueur){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir votre mise :");
        int mise = sc.nextInt();
        while(mise < 0 && joueur.getJetons()< mise){
            System.out.println("Votre mise est impossible, veuillez saisir une mise possible");
            mise = sc.nextInt();
        }
        return mise;
    }
    public void victoire(){
        System.out.println(" Victoire ! ");
    }
    public void defaite(){
        System.out.println(" defaite ! ");
    }
    public void egalite(){
        System.out.println(" egalite ! ");
    }

    public void affichageCarte(Joueur joueur, Casino casino){
        System.out.println(" ");
        System.out.println("--------------------------------------------");
        System.out.println(" Carte Joueur : ");
        for(int i=0 ;i<joueur.getHand().size();i++){
            System.out.println(" Carte "+i+" "+joueur.getHand().get(i).getRank()+" Valeur : "+joueur.getHand().get(i).getRank().value()+ "\n");
        }
        System.out.println(" Carte Casino : ");
        for(int i=0 ;i<casino.getHand().size();i++){
            System.out.println(" Carte "+i+" "+casino.getHand().get(i).getRank()+" Valeur : "+casino.getHand().get(i).getRank().value()+"\n");
        }
        System.out.println("--------------------------------------------");
        System.out.println(" ");
    }

    public void affichageScore(int scoreJoueur, int scoreCasino){
        System.out.println("Score Joueur : "+scoreJoueur+" Score casino "+scoreCasino+"\n");
    }

    public void affichageJetons(Joueur joueur){
        System.out.println(" ");
        System.out.println("--------------------------------------------");
        System.out.println(" Jetons restant : "+joueur.getJetons()+"\n");
    }
    public void affichageMise(Joueur joueur){
        System.out.println(" ");
        System.out.println(" Mise en cours : "+joueur.getMise()+"\n");
    }
    public void affichageMiseSplit(Joueur joueur){
        System.out.println(" ");
        System.out.println(" Mise en cours : "+joueur.getMisesplited()+"\n");
    }
    public void affichageAssurance(Joueur joueur){
        System.out.println(" ");
        System.out.println(" Assurance en cours : "+joueur.getMise()+"\n");
    }


    public String askjoueur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Que voulez vous faire ?");
        System.out.println("Vous pouvez : tirer,doubler,split,assurance ou hold");
        String action = sc.next();
        while(!action.matches("double|split|assurance|hold|tirer")){
            System.out.println("Veuillez saisir une action possible");
            System.out.println("Vous pouvez : tirer,doubler,split,assurance ou hold");
            action = sc.next();
        }
        return action;
    }

    public void actionImpossible() {
        System.out.println("Votre action est impossible ! Veuillez en selectioner une autre");

    }

    public void split(Joueur joueur, Casino casino) {
        System.out.println("Votre avez split votre main !");
        System.out.println("Vous allez maintenant jouer une main apres l'autre");
        System.out.println(" ");
        System.out.println("--------------------------------------------");
        System.out.println(" Carte Joueur : ");
        System.out.println(" Main 1 : ");
        this.affichagebothHand(joueur, casino);
        System.out.println("--------------------------------------------");
        System.out.println(" ");
    }

    public void findepartie() {
        System.out.println("--------------------------------------------");
        System.out.println(" Fin de partie : Vous n'avez plus de jetons ");
        System.out.println("--------------------------------------------");
    }

    public void affichagebothHand(Joueur joueur, Casino casino) {
        for (int i = 0; i < joueur.getHandsplited().size(); i++) {
            System.out.println(" Carte " + i + " " + joueur.getHandsplited().get(i).getRank() + " Valeur : " + joueur.getHandsplited().get(i).getRank().value() + "\n");
        }
        System.out.println(" Main 2 : ");
        for (int i = 0; i < joueur.getHand().size(); i++) {
            System.out.println(" Carte " + i + " " + joueur.getHand().get(i).getRank() + " Valeur : " + joueur.getHand().get(i).getRank().value() + "\n");
        }
        System.out.println(" Carte Casino: ");
        for (int i = 0; i < casino.getHand().size(); i++) {
            System.out.println(" Carte " + i + " " + casino.getHand().get(i).getRank() + " Valeur : " + casino.getHand().get(i).getRank().value() + "\n");
        }
    }

    public void affigagesplit(Joueur joueur, Casino casino) {
        System.out.println("--------------------------------------------");
        System.out.println(" Carte Joueur : ");
        for (int i = 0; i < joueur.getHandsplited().size(); i++) {
            System.out.println(" Carte " + i + " " + joueur.getHandsplited().get(i).getRank() + " Valeur : " + joueur.getHandsplited().get(i).getRank().value() + "\n");
        }
        System.out.println(" Carte Casino : ");
        for (int i = 0; i < casino.getHand().size(); i++) {
            System.out.println(" Carte " + i + " " + casino.getHand().get(i).getRank() + " Valeur : " + casino.getHand().get(i).getRank().value() + "\n");
        }
        System.out.println("--------------------------------------------");
        System.out.println(" ");
    }

}

package com.massal.view;

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

    public void affichageCarte(){

    }

    public void affichageJetons(){
    //mise , assurance et jetons restant
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

    public void actionImpossible(){
        System.out.println("Votre action est impossible ! Veuillez en selectioner une autre");

    }

}

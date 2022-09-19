package com.massal.controller;

import com.massal.model.*;
import com.massal.view.View;

import java.util.List;

public class GameControlleur {

    public GameControlleur(View view) {

        this.joueur = new Joueur(100);
        this.casino = new Casino();
        this.view = view;
        this.etatduJeux = EtatduJeux.initialisation;

    }

    private Deck deck;
    private Casino casino;
    private Joueur joueur;
    private View view;
    private EtatduJeux etatduJeux;
    private boolean splited;
    private boolean hasbeensplited;

    public void run() {
        while (this.etatduJeux != EtatduJeux.Findepartie) {
            if (this.etatduJeux == EtatduJeux.initialisation) {
                this.deck = new Deck();
                this.deck.bruleCarte();
                this.joueur.miser(this.view.askMise(this.joueur));
                this.joueur.tirerCardHand(this.deck.getFirstCarte());
                this.joueur.tirerCardHand(this.deck.getFirstCarte());
                this.casino.tirerCard(this.deck.getFirstCarte());
                this.view.affichageCarte(joueur, casino);
                this.splited = false;
                this.hasbeensplited = false;
                this.etatduJeux = EtatduJeux.tourJoueur;
                checkBlackJack();
            }
            if (this.etatduJeux == EtatduJeux.tourJoueur) {
                String actionJoueur = this.view.askjoueur();
                switch (actionJoueur) {
                    case "double":
                        if (this.joueur.getMise() * 2 > this.joueur.getJetons()) {
                            System.out.println("1");
                            this.view.actionImpossible();
                        } else {
                            if (splited) {
                                this.view.actionImpossible();
                                System.out.println("2");
                            } else {
                                this.joueur.doubler();
                                this.joueur.tirerCardHand(this.deck.getFirstCarte());
                                this.view.affichageJetons(joueur);
                                this.view.affichageMise(joueur);
                                this.view.affichageCarte(joueur, casino);
                                if (checkScore(joueur.getHand()) >= 21) {
                                    findePartie();
                                }
                            }
                        }
                        break;
                    case "tirer":
                        if (splited) {
                            this.joueur.tirerCarteHandsplited(this.deck.getFirstCarte());
                            this.view.affigagesplit(joueur, casino);
                            if (checkScore(joueur.getHandsplited()) >= 21) {
                                findePartie();
                            }
                        } else {
                            this.joueur.tirerCardHand(this.deck.getFirstCarte());
                            this.view.affichageCarte(joueur, casino);
                            if (checkScore(joueur.getHand()) >= 21) {
                                findePartie();
                            }
                        }
                        break;
                    case "split":
                        if (this.joueur.getMise() * 2 > this.joueur.getJetons()) {
                            this.view.actionImpossible();
                        } else {
                            if (!splited) {
                                if (joueur.getHand().get(0).getRank() == joueur.getHand().get(1).getRank()) {
                                    joueur.split();
                                    splited = true;
                                    hasbeensplited = true;
                                    this.view.split(joueur, casino);
                                } else {
                                    this.view.actionImpossible();
                                }
                            } else {
                                this.view.actionImpossible();
                            }
                        }
                        break;
                    case "assurance":
                        if (splited) {
                            this.view.actionImpossible();
                        } else {
                            if (this.joueur.getHand().size() != 2) {
                                this.view.actionImpossible();
                            } else {
                                if (joueur.getJetons() > (joueur.getMise() / 2)) {
                                    this.joueur.assurance();
                                    this.view.affichageJetons(joueur);
                                    this.view.affichageMise(joueur);
                                    this.view.affichageAssurance(joueur);
                                }
                            }
                        }
                        break;
                    case "hold":
                        findePartie();
                        break;
                }

            }
        }
        this.joueur.reset();
        if (hasbeensplited) {
            this.view.affichagebothHand(joueur, casino);
            this.view.affichageScore(checkScore(joueur.getHandsplited()), checkScore(casino.getHand()));
        } else {
            this.view.affichageCarte(joueur, casino);
        }
        this.view.affichageScore(checkScore(joueur.getHand()), checkScore(casino.getHand()));
        this.view.affichageJetons(joueur);

    }

    public void findePartie() {
        if (splited) {
            splited = false;
        } else {
            while (checkScore(casino.getHand()) < 17) {
                casino.tirerCard(deck.getFirstCarte());
            }
            if (hasbeensplited) {
                gain(joueur, joueur.getHandsplited(), casino.getHand());
            }
            gain(joueur, joueur.getHand(), casino.getHand());
            etatduJeux = EtatduJeux.Findepartie;
        }
    }

    public void gain(Joueur joueur, List<Carte> handJoueur, List<Carte> handcasino) {
        if (checkScore(handJoueur) == 21) {
            if (checkScore(casino.getHand()) == 21) {
                joueur.gain(joueur.getMise(), 0);
                this.view.egalite();
            } else {
                joueur.gain(joueur.getMise(), 1);
                this.view.victoire();
            }
        } else {
            if (checkScore(handJoueur) < 21) {
                if (checkScore(casino.getHand()) > 21) {
                    joueur.gain(joueur.getMise(), 1);
                    this.view.victoire();
                } else {
                    if (checkScore(casino.getHand()) == checkScore(handJoueur)) {
                        joueur.gain(joueur.getMise(), 0);
                        this.view.egalite();
                    }
                    if (checkScore(casino.getHand()) > checkScore(handJoueur)) {
                        this.view.defaite();
                        return;
                    }
                    if (checkScore(casino.getHand()) < checkScore(handJoueur)) {
                        joueur.gain(joueur.getMise(), 1);
                        this.view.victoire();
                    }
                }
            } else {
                this.view.defaite();
            }
        }

        if (checkScore(casino.getHand()) == 21) {
            if (casino.getHand().size() == 2) {
                joueur.gainAssurance(joueur.getAssurance());
            }
        }
    }

    public void checkBlackJack() {
        if (checkScore(joueur.getHand()) == 21) {
            while (checkScore(casino.getHand()) <= 17) {
                casino.tirerCard(deck.getFirstCarte());
            }
            if (checkScore(casino.getHand()) == 21) {
                if (casino.getHand().size() == 2) {
                    joueur.gain(joueur.getMise(), 0);
                    joueur.gainAssurance(joueur.getAssurance());
                    this.view.egalite();
                } else {
                    joueur.gain(joueur.getMise(), 1);
                    this.view.victoire();
                }
            } else {
                joueur.gain(joueur.getMise(), 1);
                this.view.victoire();
            }
            etatduJeux = EtatduJeux.Findepartie;
        }
    }

    private int checkScore(List<Carte> hand) {
        int score = 0;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getRank().equals(Rank.As)) {
                int inter = score + 11;
                if (inter <= 21) {
                    score = inter;
                } else {
                    score = score + hand.get(i).getRank().value();
                }
            } else {
                score = score + hand.get(i).getRank().value();
            }
        }
        return score;
    }

    enum EtatduJeux {
        Findepartie, tourJoueur, initialisation
    }
}




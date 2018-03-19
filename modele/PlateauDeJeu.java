package representationCo.modele;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import representationCo.view.Vue;

public class PlateauDeJeu {

	private int taillePlateau;
	private JoueurOthello[] tableauJoueurs;
	private int tourDuJoueurNum;
	private EtatOthello etat;
    private ArrayList<Vue> vues;
    
    public PlateauDeJeu() {
    		tableauJoueurs = new JoueurOthello[2];
    		tableauJoueurs[0] = new JoueurOthello("Joueur 1", 'N');
    		tableauJoueurs[1] = new JoueurOthello("Joueur 2", 'B');
    		tourDuJoueurNum = 0;
    		vues = new ArrayList<Vue>();
    		nouvellePartie(8);
    		etat.calculJouabilite();
    }
    
    public PlateauDeJeu(int taille) {
    		tableauJoueurs = new JoueurOthello[2];
    		tableauJoueurs[0] = new JoueurOthello("Joueur 1", 'N');
    		tableauJoueurs[1] = new JoueurOthello("Joueur 2", 'B');
    		tourDuJoueurNum = 0;
    		vues = new ArrayList<Vue>();
    		nouvellePartie(taille);
    		etat.calculJouabilite();
    }
    
    
    /*
     * permet l'ajout d'une vue
     */
    public void ajouterVue(Vue vue) {
    		vues.add(vue);
    } 
    
    
    /*
     * ajoute un joueur passé en paramètre à la liste des joueurs
     */
    public void ajouterJoueur() {
		String[] joueurPossible = {tableauJoueurs[0].getNom(), tableauJoueurs[1].getNom()};
		String nomJoueurARemplacer = tableauJoueurs[0].getNom();
		nomJoueurARemplacer = (String)JOptionPane.showInputDialog(null, "Choisissez le joueur qui sera remplacé", "Ajout nouveau joueur", JOptionPane.QUESTION_MESSAGE, null, joueurPossible, joueurPossible[0]);
		if(nomJoueurARemplacer != null) {
			String nom = JOptionPane.showInputDialog("Quel est votre nom", "Ajout nouveau joueur");
			if(nom != null) {
		    		if(tableauJoueurs[0].getNom().equals(nomJoueurARemplacer)) {
		    			tableauJoueurs[0] = new JoueurOthello(nom, tableauJoueurs[0].getCouleur());;
		    		}
		    		else {
		        		tableauJoueurs[1] = new JoueurOthello(nom, tableauJoueurs[1].getCouleur());
		    		}
			}
		}
    }
    
    /*
     * retourne la taille de la grille de jeu
     */
    public int getTaille() {
		return taillePlateau;
    	}
    
    
    /*
     * retorune la couleur du pion(si il y en a un)
     * se trouvant dans la case de coordonnée (i,j)
     */
    public char getCouleur(int i, int j) {
    		return etat.lecture(i, j);
    }
    
    
    /*
     * retourne le nom du joueur numéro index
     */
    public String getNomJoueur(int index) {
    		return tableauJoueurs[index].getNom();
    }
    
    
    /*
     * retourne le nombre de partie gagnee pas le joueur numero index
     */
    public int getScore(int index) {
    		return tableauJoueurs[index].getGagne();
    }
    
    
    
    /*
     * retourne true si le joueur peut jouer à cet endroit
     */
    public boolean estJouable(int i, int j){
    		if(etat.lecture(i,j) == 'J') {
    			return true;
    		}
    		return false;
    }
    
    /*
     * passe la main au joueur suivant
     */
    public void joueurSuivant() {
    		tourDuJoueurNum++;
		tourDuJoueurNum = tourDuJoueurNum % 2;
		if(tourDuJoueurNum == 0) {
			JOptionPane.showMessageDialog(null, "C'est au tour de "+tableauJoueurs[0].getNom());
    			etat.setTourJoueur(tableauJoueurs[0]);
		}
		else if(tourDuJoueurNum == 1 ){
			JOptionPane.showMessageDialog(null, "C'est au tour de "+tableauJoueurs[1].getNom());
			etat.setTourJoueur(tableauJoueurs[1]);
		}
		etat.calculJouabilite();
		/* si le joueur n'a pas de solution pour jouer, on passe son tour */
		if(!etat.estFinal && etat.tourPrecedentPasse) {
			JOptionPane.showMessageDialog(null, etat.joueur.getNom()+" ne peut pas jouer, il passe son tour.");
			joueurSuivant();
		}
    }
    
    
    /*
     *  permet de jouer un coup
     */
    public void jouer(int i, int j) {
    		if(etat.lecture(i,j) == 'J') {
    			etat.ecriture(i, j);
    			colorer(i,j);
    			if(etat.estFinal()) {
    				finDeLaPartie();
    			}
    			else {
    				joueurSuivant();
            		maj();	
    			}
    		}
    }
    
   /*
    * coloration des pions adjacents a celui posé
    * s'ils doivent l'etre
    */
    public void colorer(int x, int y) {
    		int i = 1;
    		char couleur = etat.lecture(x, y);
    		char couleurOpposee;
    		if(couleur == 'N') {
    			couleurOpposee = 'B';
    		}
    		else {
    			couleurOpposee = 'N';
    		}		
		/* ligne en bas */
		if(y < taillePlateau-2 && etat.lecture(x,y+i) == couleurOpposee) {
			while(y+i < taillePlateau-1 && etat.lecture(x, y+i) == couleurOpposee) {
				i++;
			}
			if(etat.lecture(x, y+i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.ecriture(x, y+j, couleur);
				}
			}
			i = 1;
		}
		/* ligne en haut */
		if(y > 1 && etat.lecture(x,y-i) == couleurOpposee) {
			while(y-i > 0 && etat.lecture(x, y-i) == couleurOpposee) {
				i++;
			}
			if(etat.lecture(x, y-i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.ecriture(x, y-j, couleur);
				}
			}
			i = 1;
		}
		/* ligne droite */
		if(x < taillePlateau-2 && etat.lecture(x+i,y) == couleurOpposee) {
			while(x+i < taillePlateau-1 && etat.lecture(x+i, y) == couleurOpposee) {
				i++;
			}
			if(etat.lecture(x+i, y) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.ecriture(x+j, y, couleur);
				}
			}
			i = 1;
		}
		/* ligne gauche */
		if(x > 1 && etat.lecture(x-i,y) == couleurOpposee) {
			while(x-i > 0 && etat.lecture(x-i, y) == couleurOpposee) {
				i++;
			}
			if(etat.lecture(x-i, y) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.ecriture(x-j, y, couleur);
				}
			}
			i = 1;
		}
		/* ligne en diagonale droite-bas*/
		if(y < taillePlateau-2 && x < taillePlateau-2 && etat.lecture(x+i,y+i) == couleurOpposee) {
			while(y+i < taillePlateau-1 && x+i < taillePlateau-1 && etat.lecture(x+i, y+i) == couleurOpposee) {
				i++;
			}
			if(etat.lecture(x+i, y+i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.ecriture(x+j, y+j, couleur);
				}
			}
			i = 1;
		}
		/* ligne en diagonale droite-haut */
		if(y > 1 && x < taillePlateau-2 && etat.lecture(x+i,y-i) == couleurOpposee) {
			while(y-i > 0 && x+i < taillePlateau-1 && etat.lecture(x+i, y-i) == couleurOpposee) {
				i++;
			}
			if(etat.lecture(x+i, y-i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.ecriture(x+j, y-j, couleur);
				}
			}
			i = 1;
		}
		/* ligne gauche-bas */
		if(y < taillePlateau-2 && x > 1 && etat.lecture(x-i,y+i) == couleurOpposee) {
			while(y+i < taillePlateau-1 && x-i > 0 && etat.lecture(x-i, y+i) == couleurOpposee) {
				i++;
			}
			if(etat.lecture(x-i, y+i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.ecriture(x-j, y+j, couleur);
				}
			}
			i = 1;
		}
		/* ligne gauche-haut */
		if(y > 1 && x > 1 && etat.lecture(x-i,y-i) == couleurOpposee) {
			while(y-i > 0 && x-i > 0 && etat.lecture(x-i, y-i) == couleurOpposee) {
				i++;
			}
			if(etat.lecture(x-i, y-i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.ecriture(x-j, y-j, couleur);
				}
			}
			i = 1;
		}
    }
    
    /*
     * création d'une nouvelle partie
     */
    public void nouvellePartie(int taille) {
	    taillePlateau = taille;
	    tourDuJoueurNum = 1;
	    	etat = new EtatOthello(taillePlateau);
	    	joueurSuivant();
    }
    
    
    /*
     * retourne vrai si la case ne contient pas de pion
     */
    public boolean isEmpty(int i, int j){
    		if( etat.lecture(i, j) == 'V'  ) {
    			return true;
    		}
    		return false;
    }
    
    /*
     * retourne le vainqueur de la partie (s'il y en a un)
     */
    public JoueurOthello getVainqueur()
    {
    		int numJoueur = etat.getNumJoueurVainqueur();
    		if(numJoueur == 2) {
    			return null;
    		}
    		else {
    			return tableauJoueurs[numJoueur];
    		}
    }

    /*
     * test si la partie est finie
     */
    public void finDeLaPartie() {
    		 maj();
    		 JoueurOthello vainqueur = getVainqueur();
    		 if(vainqueur != null) {
    			 vainqueur.setGagne();
        		 JOptionPane.showMessageDialog(null, "La partie est terminée. "+vainqueur.getNom()+" gagne la partie");
    		 }
    }
    
    	/*
    	 * Affichage plateau du jeu sur terminal
    	 */
  	public String toString(){
  		StringBuilder spb = new StringBuilder();
  		encadrementJeu(spb);
  		for(int y = 0 ; y < taillePlateau ; y++){
  			spb.append("|");
  			for(int x = 0 ; x < taillePlateau ; x++) {
  				spb.append("|"+ etat.lecture(x, y));
  			}
  			spb.append("||");
  			spb.append("\n");
  		}
  		encadrementJeu(spb);
  		return spb.toString();
  	}
  	
  	public StringBuilder encadrementJeu(StringBuilder spb){
		for(int t = 0 ; t < (taillePlateau*2)+3 ; t++){
			spb.append("|");
		}
		spb.append("\n");
		return spb;
	}

  	/*
  	 * fonction de mis a jour des vues
  	 */
  	public void maj(){
  		for(Vue v : vues){
  			v.maj();
  		}
  	}
    
}

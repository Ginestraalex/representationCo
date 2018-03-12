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
    private boolean tourJoueurPrecedPasse; //variable de détection si le jeu est bloqué
    
    public PlateauDeJeu() {
    		tableauJoueurs = new JoueurOthello[2];
    		tableauJoueurs[0] = new JoueurOthello("Joueur 1");
    		tableauJoueurs[1] = new JoueurOthello("Joueur 2");
    		tourDuJoueurNum = 0;
    		tourJoueurPrecedPasse = false;
    		vues = new ArrayList<Vue>();
    		nouvellePartie(8);
    		this.calculJouabilite();
    }
    
    public PlateauDeJeu(int taille) {
    		tableauJoueurs = new JoueurOthello[2];
    		tableauJoueurs[0] = new JoueurOthello("Joueur 1");
    		tableauJoueurs[1] = new JoueurOthello("Joueur 2");
    		tourDuJoueurNum = 0;
    		tourJoueurPrecedPasse = false;
    		vues = new ArrayList<Vue>();
    		nouvellePartie(taille);
    		calculJouabilite();
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
				JoueurOthello j = new JoueurOthello(nom);
		    		if(tableauJoueurs[0].getNom().equals(nomJoueurARemplacer)) {
		    			tableauJoueurs[0] = j;
		    		}
		    		else {
		        		tableauJoueurs[1] = j;
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
     * fonction remplissant le tableau de jeu en fonction de la jouabilité
     * du joueur courrant
     */
    public void calculJouabilite(){
    		for(int y = 0 ; y < taillePlateau ; y++) {
    			for(int x = 0 ; x < taillePlateau ; x++) {
    				if(etat.lecture(x, y) == 'J') {
    					etat.ecriture(x, y, 'V');
    				}
    			}
    		}
    		boolean estJouable = false;
    		char cJoueur;
    		char cAdversaire;
    		int k = 1;
    		if(tourDuJoueurNum == 0) { //tour joueur noir
    			cJoueur = 'N';
    			cAdversaire = 'B';
    		}
    		else {	//tour joueur blanc
    			cJoueur = 'B';
    			cAdversaire = 'N';
    		}
		for(int y = 0 ; y < taillePlateau ; y++) {
			for(int x = 0 ; x < taillePlateau ; x++) {
				if(etat.lecture(x, y) == cJoueur) {
					/* ligne vers droite */
					if(x < taillePlateau-2) {
						while(x+k < taillePlateau-1 && etat.lecture(x+k, y) == cAdversaire) {
							k++;
						}
						if(etat.lecture(x+k, y) == 'V' && k != 1) {
							etat.ecriture(x+k, y, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* ligne vers gauche */
					if(x > 1) {
						while(x-k > 0 && etat.lecture(x-k, y) == cAdversaire) {
							k++;
						}
						if(etat.lecture(x-k, y) == 'V' && k != 1) {
							etat.ecriture(x-k, y, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* ligne vers bas */
					if(y < taillePlateau-2) {
						while(y+k < taillePlateau-1 && etat.lecture(x, y+k) == cAdversaire) {
							k++;
						}
						if(etat.lecture(x, y+k) == 'V' && k != 1) {
							etat.ecriture(x, y+k, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* ligne vers le haut */
					if(y > 1) {
						while(y-k > 0 && etat.lecture(x, y-k) == cAdversaire) {
							k++;
						}
						if(etat.lecture(x, y-k) == 'V' && k != 1) {
							etat.ecriture(x, y-k, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* diagonale bas-droit */
					if(x < taillePlateau-2 && y < taillePlateau-2 ) {
						while(y+k < taillePlateau-1  &&  x+k < taillePlateau-1 && etat.lecture(x+k, y+k) == cAdversaire) {
							k++;
						}
						if(etat.lecture(x+k, y+k) == 'V' && k != 1) {
							etat.ecriture(x+k, y+k, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* diagonale bas-gauche */
					if(x > 1 && y < taillePlateau-2) {
						while(y+k < taillePlateau-1  &&  x-k > 0 && etat.lecture(x-k, y+k) == cAdversaire) {
							k++;
						}
						if(etat.lecture(x-k, y+k) == 'V' && k != 1) {
							etat.ecriture(x-k, y+k, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* diagonale haut-gauche */
					if(x > 1 && y > 1) {
						while(y-k > 0 && x-k > 0 && etat.lecture(x-k, y-k) == cAdversaire) {
							k++;
						}
						if(etat.lecture(x-k, y-k) == 'V' && k != 1) {
							etat.ecriture(x-k, y-k, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* diagonale haut-droit */
					if(x < taillePlateau-2 && y > 1 ) {
						while(x+k < taillePlateau-1 &&  y-k > 0 && etat.lecture(x+k, y-k) == cAdversaire) {
							k++;
						}
						if(etat.lecture(x+k, y-k) == 'V' && k != 1) {
							etat.ecriture(x+k, y-k, 'J');
							estJouable = true;
						}
						k = 1;
					}
				}
			}
		}
		/* si le joueur ne peut pas jouer, il passe son tour */
		if(!estJouable && tourJoueurPrecedPasse) {
			finDeLaPartie();
		}
		else if(!estJouable) {
			tourJoueurPrecedPasse = true;
			joueurSuivant();
		}
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
		calculJouabilite();
    }
    
    /*
     *  permet de jouer un coup
     */
    public void jouer(int i, int j) {
    		if(etat.lecture(i,j) == 'J') {
    			if(tourDuJoueurNum == 0) {
        			etat.ecriture(i, j, 'N');
    			}
    			else{
        			etat.ecriture(i, j, 'B');
    			}
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

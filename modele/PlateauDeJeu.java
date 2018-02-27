package representationCo.modele;

import java.util.ArrayList;

import representationCo.view.Vue;

public class PlateauDeJeu {

	private int taillePlateau;
	private Joueur[] tableauJoueurs;
	private int tourDuJoueurNum;
	private EtatOthello etat;
    private ArrayList<Vue> vues;
    
    public PlateauDeJeu() {
    		nouvellePartie(8);
    		tableauJoueurs = new Joueur[2];
    		tableauJoueurs[0] = null;
    		tableauJoueurs[1] = null;
    		tourDuJoueurNum = 1;
    		vues = new ArrayList<Vue>();
    		this.nouvellePartie(8);
    		this.calculJouabilite();
    }
    
    public PlateauDeJeu(int taille) {
    		nouvellePartie(taille);
    		tableauJoueurs = new Joueur[2];
    		tableauJoueurs[0] = null;
    		tableauJoueurs[1] = null;
    		tourDuJoueurNum = 1;
    		vues = new ArrayList<Vue>();
    		this.nouvellePartie(taille);
    		this.calculJouabilite();
    }
    
    public void ajouterVue(Vue vue) {
    		vues.add(vue);
    } 
    
    public void ajouterJoueur(String nom) {
    		JoueurOthello j = new JoueurOthello(nom);
    		if(tableauJoueurs[0] == null) {
    			tableauJoueurs[0] = j;
    		}
    		else {
        		tableauJoueurs[1] = j;
    		}
    }
    
    public int getTaille() {
		return taillePlateau;
    	}
    
    public char getCouleur(int i, int j) {
    		return etat.getCouleur(i, j);
    }
    
    
    public void calculJouabilite(){
    		for(int y = 0 ; y < taillePlateau ; y++) {
    			for(int x = 0 ; x < taillePlateau ; x++) {
    				if(etat.getCouleur(x, y) == 'J') {
    					etat.setCouleur(x, y, 'V');
    				}
    			}
    		}
    		char cJoueur;
    		char cAdversaire;
    		int k = 1;
    		if(tourDuJoueurNum == 0) { //tour joueur blanc
    			cJoueur = 'B';
    			cAdversaire = 'N';
    		}
    		else {	//tour joueur noir
    			cJoueur = 'N';
    			cAdversaire = 'B';
    		}
		for(int y = 0 ; y < taillePlateau ; y++) {
			for(int x = 0 ; x < taillePlateau ; x++) {
				if(etat.getCouleur(x, y) == cJoueur) {
					/* ligne vers droite */
					if(x < taillePlateau-2) {
						while(x+k < taillePlateau-1 && etat.getCouleur(x+k, y) == cAdversaire) {
							k++;
						}
						if(etat.getCouleur(x+k, y) == 'V' && k != 1) {
							etat.setCouleur(x+k, y, 'J');
						}
						k = 1;
					}
					/* ligne vers gauche */
					if(x > 1) {
						while(x-k > 0 && etat.getCouleur(x-k, y) == cAdversaire) {
							k++;
						}
						if(etat.getCouleur(x-k, y) == 'V' && k != 1) {
							etat.setCouleur(x-k, y, 'J');
						}
						k = 1;
					}
					/* ligne vers bas */
					if(y < taillePlateau-2) {
						while(y+k < taillePlateau-1 && etat.getCouleur(x, y+k) == cAdversaire) {
							k++;
						}
						if(etat.getCouleur(x, y+k) == 'V' && k != 1) {
							etat.setCouleur(x, y+k, 'J');
						}
						k = 1;
					}
					/* ligne vers le haut */
					if(y > 1) {
						while(y-k > 0 && etat.getCouleur(x, y-k) == cAdversaire) {
							k++;
						}
						if(etat.getCouleur(x, y-k) == 'V' && k != 1) {
							etat.setCouleur(x, y-k, 'J');
						}
						k = 1;
					}
					/* diagonale bas-droit */
					if(x < taillePlateau-2 && y < taillePlateau-2 ) {
						while(y+k < taillePlateau-1  &&  x+k < taillePlateau-1 && etat.getCouleur(x+k, y+k) == cAdversaire) {
							k++;
						}
						if(etat.getCouleur(x+k, y+k) == 'V' && k != 1) {
							etat.setCouleur(x+k, y+k, 'J');
						}
						k = 1;
					}
					/* diagonale bas-gauche */
					if(x > 1 && y < taillePlateau-2) {
						while(y+k < taillePlateau-1  &&  x-k > 0 && etat.getCouleur(x-k, y+k) == cAdversaire) {
							k++;
						}
						if(etat.getCouleur(x-k, y+k) == 'V' && k != 1) {
							etat.setCouleur(x-k, y+k, 'J');
						}
						k = 1;
					}
					/* diagonale haut-gauche */
					if(x > 1 && y > 1) {
						while(y-k > 0 && x-k > 0 && etat.getCouleur(x-k, y-k) == cAdversaire) {
							k++;
						}
						if(etat.getCouleur(x-k, y-k) == 'V' && k != 1) {
							etat.setCouleur(x-k, y-k, 'J');
						}
						k = 1;
					}
					/* diagonale haut-droit */
					if(x < taillePlateau-2 && y > 1 ) {
						while(x+k < taillePlateau-1 &&  y-k > 0 && etat.getCouleur(x+k, y-k) == cAdversaire) {
							k++;
						}
						if(etat.getCouleur(x+k, y-k) == 'V' && k != 1) {
							etat.setCouleur(x+k, y-k, 'J');
						}
						k = 1;
					}
				}
			}
		}
    }
    
    public boolean estJouable(int i, int j){
    		if(etat.getCouleur(i,j) == 'J') {
    			return true;
    		}
    		return false;
    }
    
    /*
     * retourne vrai si le joueur peur jouer
     */
    public void jouer(int i, int j) {
    		if(etat.getCouleur(i,j) == 'J') {
    			if(tourDuJoueurNum == 0) {
        			etat.setCouleur(i, j, 'B');
    			}
    			else{
        			etat.setCouleur(i, j, 'N');
    			}
    			colorer(i,j);
    			tourDuJoueurNum++;
    			tourDuJoueurNum = tourDuJoueurNum % 2;
        		this.calculJouabilite();
        		maj();
    		}
    }
    
   /*
    * coloration des pions adjacents s'ils doivent l'etre
    */
    public void colorer(int x, int y) {
    		int i = 1;
    		char couleur = etat.getCouleur(x, y);
    		char couleurOpposee;
    		if(couleur == 'N') {
    			couleurOpposee = 'B';
    		}
    		else {
    			couleurOpposee = 'N';
    		}		
		/* ligne en bas */
		if(y < taillePlateau-2 && etat.getCouleur(x,y+i) == couleurOpposee) {
			while(y+i < taillePlateau-1 && etat.getCouleur(x, y+i) == couleurOpposee) {
				i++;
			}
			if(etat.getCouleur(x, y+i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.setCouleur(x, y+j, couleur);
				}
			}
			i = 1;
		}
		/* ligne en haut */
		if(y > 1 && etat.getCouleur(x,y-i) == couleurOpposee) {
			while(y-i > 0 && etat.getCouleur(x, y-i) == couleurOpposee) {
				i++;
			}
			if(etat.getCouleur(x, y-i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.setCouleur(x, y-j, couleur);
				}
			}
			i = 1;
		}
		/* ligne droite */
		if(x < taillePlateau-2 && etat.getCouleur(x+i,y) == couleurOpposee) {
			while(x+i < taillePlateau-1 && etat.getCouleur(x+i, y) == couleurOpposee) {
				i++;
			}
			if(etat.getCouleur(x+i, y) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.setCouleur(x+j, y, couleur);
				}
			}
			i = 1;
		}
		/* ligne gauche */
		if(x > 1 && etat.getCouleur(x-i,y) == couleurOpposee) {
			while(x+i < taillePlateau-1 && etat.getCouleur(x-i, y) == couleurOpposee) {
				i++;
			}
			if(etat.getCouleur(x-i, y) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.setCouleur(x-j, y, couleur);
				}
			}
			i = 1;
		}
		/* ligne en diagonale droite-bas*/
		if(y < taillePlateau-2 && x < taillePlateau-2 && etat.getCouleur(x+i,y+i) == couleurOpposee) {
			while(y+i < taillePlateau-1 && x+i < taillePlateau-1 && etat.getCouleur(x+i, y+i) == couleurOpposee) {
				i++;
			}
			if(etat.getCouleur(x+i, y+i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.setCouleur(x+j, y+j, couleur);
				}
			}
			i = 1;
		}
		/* ligne en diagonale droite-haut */
		if(y > 1 && x < taillePlateau-2 && etat.getCouleur(x+i,y-i) == couleurOpposee) {
			while(y-i > 0 && x+i < taillePlateau-2 && etat.getCouleur(x+i, y-i) == couleurOpposee) {
				i++;
			}
			if(etat.getCouleur(x+i, y-i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.setCouleur(x+j, y-j, couleur);
				}
			}
			i = 1;
		}
		/* ligne gauche-bas */
		if(y < taillePlateau-2 && x > 1 && etat.getCouleur(x-i,y+i) == couleurOpposee) {
			while(y+i < taillePlateau-1 && x-i > 0 && etat.getCouleur(x-i, y+i) == couleurOpposee) {
				i++;
			}
			if(etat.getCouleur(x-i, y+i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.setCouleur(x-j, y+j, couleur);
				}
			}
			i = 1;
		}
		/* ligne gauche-haut */
		if(y > 1 && x > 1 && etat.getCouleur(x-i,y-i) == couleurOpposee) {
			while(y-i > 0 && x-i > 0 && etat.getCouleur(x-i, y-i) == couleurOpposee) {
				i++;
			}
			if(etat.getCouleur(x-i, y-i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					etat.setCouleur(x-j, y-j, couleur);
				}
			}
			i = 1;
		}
    }
    
    public void nouvellePartie(int taille) {
	    taillePlateau = taille;
	    tourDuJoueurNum = 1;
	    	etat = new EtatOthello(taillePlateau);
	    	
    }
    
    public boolean isEqual(Pion[][] tableauATester) {
    		int taille = tableauATester.length;
	    	if(taille != taillePlateau){
			return false;
		}
	    	else{
			for(int i = 0 ; i < taille ; i++){
				for(int j = 0 ; j < taille ; j++) {
					if(tableauATester[i][j].getCouleur() != etat.getCouleur(i, j)){
						return false;
					}
				}
			}
		}
	    	return true;
    }
    
    
    public boolean isEmpty(int i, int j){
    		if( etat.getCouleur(i, j) == 'V'  ) {
    			return true;
    		}
    		return false;
    }

    
  //Affichage plateau du jeu sur terminal
  	public String toString(){
  		StringBuilder spb = new StringBuilder();
  		encadrementJeu(spb);
  		for(int y = 0 ; y < taillePlateau ; y++){
  			spb.append("|");
  			for(int x = 0 ; x < taillePlateau ; x++) {
  				spb.append("|"+ etat.getCouleur(x, y));
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

  	
  	public void maj(){
  		for(Vue v : vues){
  			v.maj();
  		}
  	}
    
}

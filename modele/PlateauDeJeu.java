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
    		lancer();
    }
    
    public PlateauDeJeu(int taille) {
    		nouvellePartie(taille);
    		tableauJoueurs = new Joueur[2];
    		tableauJoueurs[0] = null;
    		tableauJoueurs[1] = null;
    		tourDuJoueurNum = 1;
    		vues = new ArrayList<Vue>();
    		lancer();
    }
    
    public void ajouterVue(Vue vue) {
    		vues.add(vue);
    } 
    
    public void ajouterJoueur(Joueur j) {
    		if(tableauJoueurs[0] == null) {
    			tableauJoueurs[0] = j;
    		}
    		else {
        		tableauJoueurs[1] = j;
    		}
    }
    
    
    public char getCouleur(int i, int j) {
    		return etat.getCouleur(i, j);
    }
    
    
    public void lancer(){
    	//Affichage des 4 jetons de départ 
		etat.setCouleur(taillePlateau/2-1, taillePlateau/2-1, 'N');
		etat.setCouleur(taillePlateau/2-1, taillePlateau/2, 'B');
		etat.setCouleur(taillePlateau/2, taillePlateau/2-1, 'B');
		etat.setCouleur(taillePlateau/2, taillePlateau/2, 'N');
		this.calculJouabilite();
    }
    
    
    public void calculJouabilite(){
    		int k;
    		boolean jouable = false;
    		for(int y = 0 ; y < taillePlateau ; y++) {
    			for(int x = 0 ; x < taillePlateau ; x++) {
    				if(etat.getCouleur(x, y) == 'J') {
    					etat.setCouleur(x, y, 'V');
    				}
    			}
    		}
    		if(tourDuJoueurNum == 0) { //tour joueur blanc
    			/* lignes contoures du plateau */
    			for(int i = 1; i < taillePlateau-1; i++){
    				if(etat.getCouleur(i, 0) == 'N' ){
    					if(etat.getCouleur(i-1, 0) == 'B'){
    						k = 1;
    						while(i+k < taillePlateau && !jouable){
    							if(etat.getCouleur(i+k, 0) == 'V'){
									jouable = true;
									etat.setCouleur(i+k, 0, 'J');
    							}	
    							else if(etat.getCouleur(i+k, 0) == 'B'){
    								jouable = true;
    							}
    							k++;
    						}
    						jouable = false;
    					}
    					if(etat.getCouleur(i+1, 0) == 'B'){
    						k = 1;
    					}
    				}
    			}
    			for(int i = 1; i < taillePlateau-1; i++){
    				if(etat.getCouleur(i, taillePlateau-1) == 'N' ){
    					if(etat.getCouleur(i-1, taillePlateau-1) == 'B'){
    						k = 1;
    						while(i+k < taillePlateau && !jouable){
    							if(etat.getCouleur(i+k, taillePlateau-1) == 'V'){
									jouable = true;
									etat.setCouleur(i+k, taillePlateau-1, 'J');
    							}	
    							else if(etat.getCouleur(i+k, taillePlateau-1) == 'B'){
    								jouable = true;
    							}
    							k++;
    						}
    						jouable = false;
    					}
    					if(etat.getCouleur(i+1, taillePlateau-1) == 'B'){
    						k = 1;
    					}
    				}
    			}
    			for(int i = 1; i < taillePlateau-1; i++){
    				if(etat.getCouleur(taillePlateau-1, i) == 'N' ){
    					if(etat.getCouleur(taillePlateau-1, i-1) == 'B'){
    						k = 1;
    						while(i+k < taillePlateau && !jouable){
    							if(etat.getCouleur(taillePlateau-1, i+k) == 'V'){
									jouable = true;
									etat.setCouleur(taillePlateau-1, i+k, 'J');
    							}	
    							else if(etat.getCouleur(taillePlateau-1, i+k) == 'B'){
    								jouable = true;
    							}
    							k++;
    						}
    						jouable = false;
    					}
    					if(etat.getCouleur(taillePlateau-1, i+1) == 'B'){
    						k = 1;
    					}
    				}
    			}
    			for(int i = 1; i < taillePlateau-1; i++){
    				if(etat.getCouleur(0, i) == 'N' ){
    					if(etat.getCouleur(0, i-1) == 'B'){
    						k = 1;
    						while(i+k < taillePlateau && !jouable){
    							if(etat.getCouleur(0, i+k) == 'V'){
									jouable = true;
									etat.setCouleur(0, i+k, 'J');
    							}	
    							else if(etat.getCouleur(0, i+k) == 'B'){
    								jouable = true;
    							}
    							k++;
    						}
    						jouable = false;
    					}
    					if(etat.getCouleur(0, i+1) == 'B'){
    						k = 1;
    					}
    				}
    			}
    			/* lignes intérieures */
    			for(int i = 1 ; i < taillePlateau-1 ; i++) {
    				for(int j = 1 ; j < taillePlateau-1 ; j++) {
    					if(etat.getCouleur(i, j) == 'N') {
    						if(etat.getCouleur(i-1, j-1) == 'B') {
    							k = 1;
    							while(i+k < taillePlateau && j+k < taillePlateau && !jouable) {
    								if(etat.getCouleur(i+k, j+k) == 'V') {
    									jouable = true;
    									etat.setCouleur(i+k, j+k, 'J');
    								}
    								else if(etat.getCouleur(i+k, j+k) == 'B'){
    									jouable = true;
    								}
								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i, j-1) == 'B') {
    							k = 1;
    							while(j+k < taillePlateau && !jouable) {
    								if(etat.getCouleur(i, j+k) == 'V') {
    									jouable = true;
    									etat.setCouleur(i, j+k, 'J');
    								}
    								else if(etat.getCouleur(i, j+k) == 'B'){
    									jouable = true;
    								}
								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i+1, j-1) == 'B') {
    							k = 1;
    							while(i-k >= 0 && j+k < taillePlateau && !jouable) {
    								if(etat.getCouleur(i-k, j+k) == 'V') {
    									jouable = true;
    									etat.setCouleur(i-k, j+k, 'J');
    								}
    								else if(etat.getCouleur(i-k, j+k) == 'B'){
    									jouable = true;
    								}
    								k++;	
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i+1, j) == 'B') {
    							k = 1;
    							while(i-k >= 0 && !jouable) {
    								if(etat.getCouleur(i-k, j) == 'V') {
    									jouable = true;
    									etat.setCouleur(i-k, j, 'J');
    								}
    								else if(etat.getCouleur(i-k, j) == 'B'){
    									jouable = true;
    								}
    								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i+1, j+1) == 'B') {
    							k = 1;
    							while(i-k >= 0 && j-k >= 0 && !jouable) {
    								if(etat.getCouleur(i-k, j-k) == 'V') {
    									jouable = true;
    									etat.setCouleur(i-k, j-k, 'J');
    								}
    								else if(etat.getCouleur(i-k, j-k) == 'B'){
    									jouable = true;
    								}
								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i, j+1) == 'B') {
    							k = 1;
    							while(j-k >= 0 && !jouable) {
    								if(etat.getCouleur(i, j-k) == 'V') {
    									jouable = true;
    									etat.setCouleur(i, j-k, 'J');
    								}
    								else if(etat.getCouleur(i, j-k) == 'B'){
    									jouable = true;
    								}
    								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i-1, j+1) == 'B') {
    							k = 1;
    							while(i+k < taillePlateau && j-k >= 0 && !jouable) {
    								if(etat.getCouleur(i+k, j-k) == 'V') {
    									jouable = true;
    									etat.setCouleur(i+k, j-k, 'J');
    								}
    								else if(etat.getCouleur(i+k, j-k) == 'B'){
    									jouable = true;
    								}
    								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i-1, j) == 'B') {
    							k = 1;
    							while(i+k < taillePlateau && !jouable) {
    								if(etat.getCouleur(i+k, j) == 'V') {
    									jouable = true;
    									etat.setCouleur(i+k, j, 'J');
    								}
    								else if(etat.getCouleur(i+k, j) == 'B'){
    									jouable = true;
    								}
    								k++;
    							}
    							jouable = false;
    						}
    					}
    				}
    			}
    		}
    		else { // tour joueur noir
    			/* lignes contoures du plateau */
    			for(int i = 1; i < taillePlateau-1; i++){
    				if(etat.getCouleur(i, 0) == 'B' ){
    					if(etat.getCouleur(i-1, 0) == 'N'){
    						k = 1;
    						while(i+k < taillePlateau && !jouable){
    							if(etat.getCouleur(i+k, 0) == 'V'){
									jouable = true;
									etat.setCouleur(i+k, 0, 'J');
    							}	
    							else if(etat.getCouleur(i+k, 0) == 'N'){
    								jouable = true;
    							}
    							k++;
    						}
    						jouable = false;
    					}
    					if(etat.getCouleur(i+1, 0) == 'N'){
    						k = 1;
    					}
    				}
    			}
    			for(int i = 1; i < taillePlateau-1; i++){
    				if(etat.getCouleur(i, taillePlateau-1) == 'B' ){
    					if(etat.getCouleur(i-1, taillePlateau-1) == 'N'){
    						k = 1;
    						while(i+k < taillePlateau && !jouable){
    							if(etat.getCouleur(i+k, taillePlateau-1) == 'V'){
									jouable = true;
									etat.setCouleur(i+k, taillePlateau-1, 'J');
    							}	
    							else if(etat.getCouleur(i+k, taillePlateau-1) == 'N'){
    								jouable = true;
    							}
    							k++;
    						}
    						jouable = false;
    					}
    					if(etat.getCouleur(i+1, taillePlateau-1) == 'N'){
    						k = 1;
    					}
    				}
    			}
    			for(int i = 1; i < taillePlateau-1; i++){
    				if(etat.getCouleur(taillePlateau-1, i) == 'B' ){
    					if(etat.getCouleur(taillePlateau-1, i-1) == 'N'){
    						k = 1;
    						while(i+k < taillePlateau && !jouable){
    							if(etat.getCouleur(taillePlateau-1, i+k) == 'V'){
									jouable = true;
									etat.setCouleur(taillePlateau-1, i+k, 'J');
    							}	
    							else if(etat.getCouleur(taillePlateau-1, i+k) == 'N'){
    								jouable = true;
    							}
    							k++;
    						}
    						jouable = false;
    					}
    					if(etat.getCouleur(taillePlateau-1, i+1) == 'N'){
    						k = 1;
    					}
    				}
    			}
    			for(int i = 1; i < taillePlateau-1; i++){
    				if(etat.getCouleur(0, i) == 'B' ){
    					if(etat.getCouleur(0, i-1) == 'N'){
    						k = 1;
    						while(i+k < taillePlateau && !jouable){
    							if(etat.getCouleur(0, i+k) == 'V'){
									jouable = true;
									etat.setCouleur(0, i+k, 'J');
    							}	
    							else if(etat.getCouleur(0, i+k) == 'N'){
    								jouable = true;
    							}
    							k++;
    						}
    						jouable = false;
    					}
    					if(etat.getCouleur(0, i+1) == 'N'){
    						k = 1;
    					}
    				}
    			}
    			/* lignes intérieures du plateau */
    			for(int i = 1 ; i < taillePlateau-1 ; i++) {
    				for(int j = 1 ; j < taillePlateau-1 ; j++) {
    					if(etat.getCouleur(i, j) == 'B') {
    						
    						if(etat.getCouleur(i-1, j-1) == 'N') {
    							k = 1;
    							while(i+k < taillePlateau && j+k < taillePlateau && !jouable) {
    								if(etat.getCouleur(i+k, j+k) == 'V') {
    									jouable = true;
    									etat.setCouleur(i+k, j+k, 'J');
    								}
    								else if(etat.getCouleur(i+k, j+k) == 'N') {
    									jouable = true;
    								}
    								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i, j-1) == 'N') {
    							k = 1;
    							while(j+k < taillePlateau && !jouable) {
    								if(etat.getCouleur(i, j+k) == 'V') {
    									jouable = true;
    									etat.setCouleur(i, j+k, 'J');
    								}
    								else if(etat.getCouleur(i, j+k) == 'N') {
    									jouable = true;
    								}
    								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i+1, j-1) == 'N') {
    							k = 1;
    							while(i-k >= 0 && j+k < taillePlateau && !jouable) {
    								if(etat.getCouleur(i-k, j+k) == 'V') {
    									jouable = true;
    									etat.setCouleur(i-k, j+k, 'J');
    								}
    								else if(etat.getCouleur(i-k, j+k) == 'N') {
    									jouable = true;
    								}
    								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i+1, j) == 'N') {
    							k = 1;
    							while(i-k >= 0 && !jouable) {
    								if(etat.getCouleur(i-k, j) == 'V') {
    									jouable = true;
    									etat.setCouleur(i-k, j, 'J');
    								}
    								else if(etat.getCouleur(i-k, j) == 'N') {
    									jouable = true;
    								}
    								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i+1, j+1) == 'N') {
    							k = 1;
    							while(i-k >= 0 && j-k >= 0 && !jouable) {
    								if(etat.getCouleur(i-k, j-k) == 'V') {
    									jouable = true;
    									etat.setCouleur(i-k, j-k, 'J');
    								}
    								else if(etat.getCouleur(i-k, j-k) == 'N') {
    									jouable = true;
    								}
    								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i, j+1) == 'N') {
    							k = 1;
    							while(j-k >= 0 && !jouable) {
    								if(etat.getCouleur(i, j-k) == 'V') {
    									jouable = true;
    									etat.setCouleur(i, j-k, 'J');
    								}
    								else if(etat.getCouleur(i, j-k) == 'N') {
    									jouable = true;
    								}
    								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i-1, j+1) == 'N') {
    							k = 1;
    							while(i+k < taillePlateau && j-k >= 0 && !jouable) {
    								if(etat.getCouleur(i+k, j-k) == 'V') {
    									jouable = true;
    									etat.setCouleur(i+k, j-k, 'J');
    								}
    								else if(etat.getCouleur(i+k, j-k) == 'N') {
    									jouable = true;
    								}
    								k++;
    							}
    							jouable = false;
    						}
    						if(etat.getCouleur(i-1, j) == 'N') {
    							k = 1;
    							while(i+k < taillePlateau && !jouable) {
    								if(etat.getCouleur(i+k, j) == 'V') {
    									jouable = true;
    									etat.setCouleur(i+k, j, 'J');
    								}
    								else if(etat.getCouleur(i+k, j) == 'N') {
    									jouable = true;
    								}
    								k++;
    							}
    							jouable = false;
    						}
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
	    	if(taille%2 == 1){
	    		taillePlateau = taille+1;
	    	}
	    	else{
		    	taillePlateau = taille;
	
	    	}
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
    
    
    public int getTaille() {
    		return taillePlateau;
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

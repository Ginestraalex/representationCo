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
    		etat = new EtatOthello(8);
    		taillePlateau = 8;
    		tableauJoueurs = new Joueur[2];
    		tableauJoueurs[0] = null;
    		tableauJoueurs[1] = null;
    		tourDuJoueurNum = 1;
    		vues = new ArrayList<Vue>();
    		//Affichage des 4 jetons de départ 
    		jouer(3, 3);
    		jouer(3, 4);
    		jouer(4, 4);
    		jouer(4, 3);
    }
    
    public PlateauDeJeu(int taille) {
    		taillePlateau = taille;
    		etat = new EtatOthello(taille);
    		tableauJoueurs = new Joueur[2];
    		tableauJoueurs[0] = null;
    		tableauJoueurs[1] = null;
    		tourDuJoueurNum = 1;
    		vues = new ArrayList<Vue>();
    		//Affichage des 4 jetons de départ 
    		jouer(3, 3);
    		jouer(3, 4);
    		jouer(4, 4);
    		jouer(4, 3);
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
    			for(int i = 0 ; i < taillePlateau ; i++) {
    				for(int j = 0 ; j < taillePlateau ; j++) {
    					if(etat.getCouleur(i, j) == 'N') {
    						if(etat.getCouleur(i-1, j-1) == 'B') {
    							k = 1;
    							jouable = false;
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
    						else if(etat.getCouleur(i, j-1) == 'B') {
    							k = 1;
    							jouable = false;
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
    						else if(etat.getCouleur(i+1, j-1) == 'B') {
    							k = 1;
    							jouable = false;
    							while(i-k > 0 && j+k < taillePlateau && !jouable) {
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
    						else if(etat.getCouleur(i+1, j) == 'B') {
    							k = 1;
    							jouable = false;
    							while(i-k > 0 && !jouable) {
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
    						else if(etat.getCouleur(i+1, j+1) == 'B') {
    							k = 1;
    							jouable = false;
    							while(i-k > 0 && j-k > 0 && !jouable) {
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
    						else if(etat.getCouleur(i, j+1) == 'B') {
    							k = 1;
    							jouable = false;
    							while(j-k > 0 && !jouable) {
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
    						else if(etat.getCouleur(i-1, j+1) == 'B') {
    							k = 1;
    							jouable = false;
    							while(i+k < taillePlateau && j-k > 0 && !jouable) {
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
    						else if(etat.getCouleur(i-1, j) == 'B') {
    							k = 1;
    							jouable = false;
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
    			for(int i = 0 ; i < taillePlateau ; i++) {
    				for(int j = 0 ; j < taillePlateau ; j++) {
    					if(etat.getCouleur(i, j) == 'B') {
    						if(etat.getCouleur(i-1, j-1) == 'N') {
    							k = 1;
    							jouable = false;
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
    						else if(etat.getCouleur(i, j-1) == 'N') {
    							k = 1;
    							jouable = false;
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
    						else if(etat.getCouleur(i+1, j-1) == 'N') {
    							k = 1;
    							jouable = false;
    							while(i-k > 0 && j+k < taillePlateau && !jouable) {
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
    						else if(etat.getCouleur(i+1, j) == 'N') {
    							k = 1;
    							jouable = false;

    							while(i-k > 0 && !jouable) {
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
    						else if(etat.getCouleur(i+1, j+1) == 'N') {
    							k = 1;
    							jouable = false;
    							while(i-k > 0 && j-k > 0 && !jouable) {
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
    						else if(etat.getCouleur(i, j+1) == 'N') {
    							k = 1;
    							jouable = false;
    							while(j-k > 0 && !jouable) {
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
    						else if(etat.getCouleur(i-1, j+1) == 'N') {
    							k = 1;
    							jouable = false;
    							while(i+k < taillePlateau && j-k > 0 && !jouable) {
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
    						else if(etat.getCouleur(i-1, j) == 'N') {
    							k = 1;
    							jouable = false;
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
    		if(etat.getCouleur(i, j) == 'V' || etat.getCouleur(i,j) == 'J') {
    			if(tourDuJoueurNum == 0) {
        			etat.setCouleur(i, j, 'B');
    			}
    			else{
        			etat.setCouleur(i, j, 'N');
    			}
    			tourDuJoueurNum++;
    			tourDuJoueurNum = tourDuJoueurNum % 2;
        		this.calculJouabilite();

			maj();
    		}
    }
    
    public void nouvellePartie(int taille) {
	    	taillePlateau = taille;
	    	etat = new EtatOthello(taille);
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

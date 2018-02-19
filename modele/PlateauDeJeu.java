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
    		tourDuJoueurNum = 0;
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
    		tourDuJoueurNum = 0;
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
    
    /*
     * retourne vrai si le joueur peur jouer
     */
    public void jouer(int i, int j) {
    		if(etat.getCouleur(i, j) == 'V') {
    			if(tourDuJoueurNum == 0) {
        			etat.setCouleur(i, j, 'B');
    			}
    			else{
        			etat.setCouleur(i, j, 'N');
    			}
    			tourDuJoueurNum++;
    			tourDuJoueurNum = tourDuJoueurNum % 2;
			maj();
			System.out.println(this.toString());
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
    		if( etat.getCouleur(i, j) == 'V' ) {
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
  		for(int x = 0 ; x < taillePlateau ; x++){
  			spb.append("|");
  			for(int y = 0 ; y < taillePlateau ; y++) {
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

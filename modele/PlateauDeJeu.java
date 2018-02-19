package representationCo.modele;

import java.util.ArrayList;

import representationCo.view.Vue;

public class PlateauDeJeu {

	private int taillePlateau;
	private Pion[][] tableauJeu;
    private ArrayList<Vue> vues;
    
    public PlateauDeJeu() {
    		taillePlateau = 8;
    		for(int i = 0 ; i < taillePlateau ; i++) {
    			for(int j = 0 ; j < taillePlateau ; j++) {
    				tableauJeu[i][j] = new Pion();
    			}
    		}
    		vues = new ArrayList<Vue>();
    }
    
    public PlateauDeJeu(int taille) {
    	taillePlateau = taille;
    		for(int i = 0 ; i < taille ; i++) {
    			for(int j = 0 ; j < taille ; j++) {
    				tableauJeu[i][j] = new Pion();
    			}
    		}
    		vues = new ArrayList<Vue>();
    }
    
    public void ajouterVue(Vue vue) {
    		vues.add(vue);
    }
    
    
    /*
     * retourne vrai si le joueur peur jouer
     */
    public boolean jouer(int i, int j, char couleur) {
    		if(tableauJeu[i][j].getCouleur() == 'V') {
    			tableauJeu[i][j].setCouleur(couleur);
    			return true;
    		}
    		return false;
    }
    
    public void nouvellePartie(int taille) {
	    	taillePlateau = taille;
	    	for(int i = 0 ; i < taillePlateau ; i++) {
				for(int j = 0 ; j < taillePlateau ; j++) {
					tableauJeu[i][j] = new Pion();
				}
			}
    }
    
    public boolean isEqual(Pion[][] tableauATester) {
    		int taille = tableauATester.length;
	    	if(taille != taillePlateau){
			return false;
		}
	    	else{
			for(int i = 0 ; i < taille ; i++){
				for(int j = 0 ; j < taille ; j++) {
					if(tableauATester[i][j] != tableauJeu[i][j]){
						return false;
					}
				}
			}
		}
	    	return true;
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
  				spb.append("|"+ tableauJeu[x][y].getCouleur());
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

    
}

package representationCo.modele;

import java.util.ArrayList;

import representationCo.view.Vue;

public class PlateauDeJeu {

	private int taillePlateau;
	private EtatOthello etat;
    private ArrayList<Vue> vues;
    
    public PlateauDeJeu() {
    		etat = new EtatOthello(8);
    		taillePlateau = 8;
    		vues = new ArrayList<Vue>();
    }
    
    public PlateauDeJeu(int taille) {
    		taillePlateau = taille;
    		etat = new EtatOthello(taille);
    		vues = new ArrayList<Vue>();
    }
    
    public void ajouterVue(Vue vue) {
    		vues.add(vue);
    }
    
    
    /*
     * retourne vrai si le joueur peur jouer
     */
    public boolean jouer(int i, int j, char couleur) {
    		if(etat.getCouleur(i, j) == 'V') {
    			etat.setCouleur(i, j, couleur);
    			return true;
    		}
    		return false;
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

    
}

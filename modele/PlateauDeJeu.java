package representationCo.modele;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import representationCo.view.Vue;

public class PlateauDeJeu {

	private int taillePlateau;
	private JoueurOthello[] tableauJoueurs;
	private EtatOthello etat;
    private ArrayList<Vue> vues;
    
    public PlateauDeJeu() {
    		tableauJoueurs = new JoueurOthello[2];
    		tableauJoueurs[0] = new JoueurOthello("Joueur 1", 'N');
    		tableauJoueurs[1] = new JoueurOthello("Joueur 2", 'B');
    		vues = new ArrayList<Vue>();
    		nouvellePartie(8);
    		etat.calculJouabilite();
    }
    
    public PlateauDeJeu(int taille) {
    		tableauJoueurs = new JoueurOthello[2];
    		tableauJoueurs[0] = new JoueurOthello("Joueur 1", 'N');
    		tableauJoueurs[1] = new JoueurOthello("Joueur 2", 'B');
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
				if(nom.equals("Ordinateur")){
    				JOptionPane.showMessageDialog(null, "Impossible d'ajouter le joueur (Ordinateur est reserve pour la machine)");
				}
				else{
		    		if(tableauJoueurs[0].getNom().equals(nomJoueurARemplacer)) {
		    			if(tableauJoueurs[1].getNom().equals(nom))
		    			{
		    				JOptionPane.showMessageDialog(null, "Impossible d'ajouter le joueur, le nom existe deja");
		    				ajouterJoueur();
		    			}
		    			else{
		    				tableauJoueurs[0] = new JoueurOthello(nom, tableauJoueurs[0].getCouleur());
		    			}
		    		}
		    		else {
		    			if(tableauJoueurs[0].getNom().equals(nom)){
		    				JOptionPane.showMessageDialog(null, "Impossible d'ajouter le joueur, le nom existe deja");
		    				ajouterJoueur();
		    			}
		    			else{
		    				tableauJoueurs[1] = new JoueurOthello(nom, tableauJoueurs[1].getCouleur());
	    				}
		    		}
				}
			}
		}
    }
    
    public void remplacerJoeurParOrdinateur(){
    		String[] joueurPossible = {tableauJoueurs[0].getNom(), tableauJoueurs[1].getNom()};
		String nomJoueurARemplacer = tableauJoueurs[0].getNom();
		nomJoueurARemplacer = (String)JOptionPane.showInputDialog(null, "Choisissez le joueur qui sera remplacé", "Ajout nouveau joueur", JOptionPane.QUESTION_MESSAGE, null, joueurPossible, joueurPossible[0]);
		if(nomJoueurARemplacer != null) {
    		if(tableauJoueurs[0].getNom().equals(nomJoueurARemplacer)) {
    			tableauJoueurs[0].setOrdinateur();
    		}
    		else {
    			tableauJoueurs[1].setOrdinateur();
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
     * passe la main au joueur suivant
     */
    public void joueurSuivant() {
		etat.tourSuivant();
		etat.calculJouabilite();

		/* si la partie est finie */
		if(etat.estFinal) {
			finDeLaPartie();
		}
		/* si le joueur n'a pas de solution pour jouer, on passe son tour */
		else if(!etat.estFinal && etat.tourPrecedentPasse) {
			JOptionPane.showMessageDialog(null, etat.joueurCourant.getNom()+" ne peut pas jouer, il passe son tour.");
			joueurSuivant();
		}
		else {
			if(etat.joueurCourant.isOrdinateur()){
				etat.affichage();
				etat = etat.minimax(2);
				System.out.println("--- res ---");
				etat.affichage();
				System.out.println();
				System.out.println("===========================");
				System.out.println();
				etat.tourSuivant();
				joueurSuivant();
			}
			else {
				JOptionPane.showMessageDialog(null, "C'est au tour de "+etat.joueurCourant.getNom());
			}
		}
    }
    
    
    /*
     *  permet de jouer un coup
     */
    public void jouer(int i, int j) {
    		if(etat.lecture(i,j) == 'J') {
    			etat.ecriture(i, j);
    			etat.setDernierCoupJoue(i, j);
    			colorer(i,j);
    			joueurSuivant();
    			maj();	
    		}
    }
    
    public static void jouer(EtatOthello e, int i, int j) {
	    	if(e.lecture(i,j) == 'J') {
				e.ecriture(i, j);
				e.setDernierCoupJoue(i, j);
				PlateauDeJeu.colorer(e, i,j);
				e.tourSuivant();
				e.calculJouabilite();
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
    
    
    public static void colorer(EtatOthello e, int x, int y) {
		int i = 1;
		char couleur = e.lecture(x, y);
		char couleurOpposee;
		if(couleur == 'N') {
			couleurOpposee = 'B';
		}
		else {
			couleurOpposee = 'N';
		}	
		int taillePlateau = e.getSize();
		/* ligne en bas */
		if(y < taillePlateau-2 && e.lecture(x,y+i) == couleurOpposee) {
			while(y+i < taillePlateau-1 && e.lecture(x, y+i) == couleurOpposee) {
				i++;
			}
			if(e.lecture(x, y+i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					e.ecriture(x, y+j, couleur);
				}
			}
			i = 1;
		}
		/* ligne en haut */
		if(y > 1 && e.lecture(x,y-i) == couleurOpposee) {
			while(y-i > 0 && e.lecture(x, y-i) == couleurOpposee) {
				i++;
			}
			if(e.lecture(x, y-i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					e.ecriture(x, y-j, couleur);
				}
			}
			i = 1;
		}
		/* ligne droite */
		if(x < taillePlateau-2 && e.lecture(x+i,y) == couleurOpposee) {
			while(x+i < taillePlateau-1 && e.lecture(x+i, y) == couleurOpposee) {
				i++;
			}
			if(e.lecture(x+i, y) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					e.ecriture(x+j, y, couleur);
				}
			}
			i = 1;
		}
		/* ligne gauche */
		if(x > 1 && e.lecture(x-i,y) == couleurOpposee) {
			while(x-i > 0 && e.lecture(x-i, y) == couleurOpposee) {
				i++;
			}
			if(e.lecture(x-i, y) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					e.ecriture(x-j, y, couleur);
				}
			}
			i = 1;
		}
		/* ligne en diagonale droite-bas*/
		if(y < taillePlateau-2 && x < taillePlateau-2 && e.lecture(x+i,y+i) == couleurOpposee) {
			while(y+i < taillePlateau-1 && x+i < taillePlateau-1 && e.lecture(x+i, y+i) == couleurOpposee) {
				i++;
			}
			if(e.lecture(x+i, y+i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					e.ecriture(x+j, y+j, couleur);
				}
			}
			i = 1;
		}
		/* ligne en diagonale droite-haut */
		if(y > 1 && x < taillePlateau-2 && e.lecture(x+i,y-i) == couleurOpposee) {
			while(y-i > 0 && x+i < taillePlateau-1 && e.lecture(x+i, y-i) == couleurOpposee) {
				i++;
			}
			if(e.lecture(x+i, y-i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					e.ecriture(x+j, y-j, couleur);
				}
			}
			i = 1;
		}
		/* ligne gauche-bas */
		if(y < taillePlateau-2 && x > 1 && e.lecture(x-i,y+i) == couleurOpposee) {
			while(y+i < taillePlateau-1 && x-i > 0 && e.lecture(x-i, y+i) == couleurOpposee) {
				i++;
			}
			if(e.lecture(x-i, y+i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					e.ecriture(x-j, y+j, couleur);
				}
			}
			i = 1;
		}
		/* ligne gauche-haut */
		if(y > 1 && x > 1 && e.lecture(x-i,y-i) == couleurOpposee) {
			while(y-i > 0 && x-i > 0 && e.lecture(x-i, y-i) == couleurOpposee) {
				i++;
			}
			if(e.lecture(x-i, y-i) == couleur) {
				for(int j = 1 ; j < i ; j++) {
					e.ecriture(x-j, y-j, couleur);
				}
			}
			i = 1;
		}
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
     * création d'une nouvelle partie
     */
    public void nouvellePartie(int taille) {
	    taillePlateau = taille;
	    	etat = new EtatOthello(taillePlateau);
	    	etat.setJoueurCourant(tableauJoueurs[0]);
	    	etat.setJoueurSuivant(tableauJoueurs[1]);

	    	etat.calculJouabilite();	    	
		JOptionPane.showMessageDialog(null, "C'est au tour de "+etat.joueurCourant.getNom());
	    	
	    	if(etat.joueurCourant.isOrdinateur()) {
	    		etat = etat.minimax(0);
	    		etat.tourSuivant();
	    		joueurSuivant();
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

package representationCo.modele;

import java.util.ArrayList;



public class EtatOthello extends Etat{
	
	public Pion[][] plateauJeu;
	int nbPionsNoirs;
	int nbPionsBlancs;
	public JoueurOthello joueur;
	
	public EtatOthello() {
		
	}
	
	public EtatOthello(int taille) {
		plateauJeu = new Pion[taille][taille];
		for(int i = 0 ; i < taille ; i++) {
			for(int j = 0 ; j < taille ; j++) {
				plateauJeu[i][j] = new Pion();
			}
		}
		this.nbPionsBlancs = 0;
		this.nbPionsNoirs = 0;
		ecriture(taille/2-1, taille/2-1, 'N');
		ecriture(taille/2-1, taille/2, 'B');
		ecriture(taille/2, taille/2-1, 'B');
		ecriture(taille/2, taille/2, 'N');
	}
	
	
	/*
	 * retourne le numéro du joueur vaiqueur:
	 * 1 : pion blanc
	 * 0 : pion noir
	 * 2 : match nul
	 */
	public int getNumJoueurVainqueur() {
		if(nbPionsNoirs < nbPionsBlancs) { 
			return 1;
		}
		else if(nbPionsNoirs > nbPionsBlancs) {
			return 0;
		}
		return 2;
	}
	
	
	/*
	 * defini le joueur qui doit jouer
	 */
	public void setTourJoueur(JoueurOthello j) {
		joueur = j;
	}
	
	/*
	 * test si l'etat est final (si le plateau est rempli)
	 */
	public boolean estFinal() {
		System.out.println("taille plateau" + (plateauJeu.length*plateauJeu.length));
		System.out.println(""+(nbPionsBlancs+nbPionsNoirs));
		if( (nbPionsBlancs + nbPionsNoirs) == plateauJeu.length*plateauJeu.length ) {
			return true;
		}
		return false;
	}
	
	@Override
	public ArrayList<Etat> successeurs(Joueur j) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public char lecture() {
		return 0;
	}
	
	/*
	 * retourne la couleur de la case
	 */
	public char lecture(int x, int y) {
		return plateauJeu[x][y].getCouleur();
	}

	@Override
	public void ecriture() {
		
	}
	
	/*
	 * defini le caractere laCouleur dans la case du plateau de jeu
	 */
	public void ecriture(int x, int y, char laCouleur) {
		if(laCouleur == 'N') {
			if(this.plateauJeu[x][y].getCouleur() == 'B') {
				nbPionsBlancs--;
			}
			this.nbPionsNoirs++;
		}
		else if(laCouleur == 'B') {
			if(this.plateauJeu[x][y].getCouleur() == 'N') {
				nbPionsNoirs--;
			}
			this.nbPionsBlancs++;
		}
		plateauJeu[x][y].setCouleur(laCouleur);
	}
	
}

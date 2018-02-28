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
		setCouleur(taille/2-1, taille/2-1, 'N');
		setCouleur(taille/2-1, taille/2, 'B');
		setCouleur(taille/2, taille/2-1, 'B');
		setCouleur(taille/2, taille/2, 'N');
	}
	
	/*
	 * retourne la couleur de la case
	 */
	public char getCouleur(int x, int y) {
		return plateauJeu[x][y].getCouleur();
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
	 * defini le caractere laCouleur dans la case du plateau de jeu
	 */
	public void setCouleur(int x, int y, char laCouleur) {
		if(laCouleur == 'N') {
			this.nbPionsNoirs++;
		}
		else if(laCouleur == 'B') {
			this.nbPionsBlancs++;
		}
		plateauJeu[x][y].setCouleur(laCouleur);
	}
	
	/*
	 * defini le joueur qui doit jouer
	 */
	public void setTourJoueur(JoueurOthello j) {
		joueur = j;
	}
	
	/*
	 * test si l'etat est final
	 */
	public boolean estFinal() {
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
	
}

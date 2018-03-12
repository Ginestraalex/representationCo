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
	
	public EtatOthello(EtatOthello e){
		plateauJeu = new Pion[e.plateauJeu.length][e.plateauJeu.length];
		for(int i = 0 ; i < plateauJeu.length; i++) {
			for(int j = 0 ; j < plateauJeu.length ; j++) {
				plateauJeu[i][j] = new Pion(e.plateauJeu[i][j].getCouleur());
			}
		}
		this.nbPionsBlancs = e.nbPionsBlancs;
		this.nbPionsNoirs = e.nbPionsNoirs;
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
		if( (nbPionsBlancs + nbPionsNoirs) == plateauJeu.length*plateauJeu.length ) {
			return true;
		}
		return false;
	}
	
	public ArrayList<EtatOthello> successeurs() {
		ArrayList<EtatOthello> listeEtats = new ArrayList<EtatOthello>();
		for(int i = 0 ; i < plateauJeu.length ; i++){
			for(int j = 0 ; j < plateauJeu.length ; j++){
				if(plateauJeu[i][j].getCouleur() == 'J'){
					EtatOthello e = new EtatOthello(this);
					int k = listeEtats.size();
					int l = 0 ;
					boolean ajoute = false;
					while(l < k && !ajoute){
						if(!listeEtats.get(l).estEgal(e)){
							ajoute = true;
							listeEtats.add(e);
						}
					}
				}
			}
		}
		return listeEtats;
	}


	
	/*
	 * retourne la couleur de la case
	 */
	public char lecture(int x, int y) {
		return plateauJeu[x][y].getCouleur();
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

	public boolean estEgal(EtatOthello e) {
		/* symetrie axiale et rotative a faire */
		if( this.nbPionsBlancs == e.nbPionsBlancs && this.nbPionsNoirs == e.nbPionsNoirs && joueur == e.joueur) {
			int i = 0;
			int j = 0 ;
			int taille = plateauJeu.length;
			boolean symCentrale = true;
			boolean egal = true;
			while((symCentrale || egal) && i < taille){
				while ((symCentrale || egal) && j < taille){
					/* egalite simple */
					if(plateauJeu[i][j].getCouleur() != e.plateauJeu[i][j].getCouleur()){
						egal = false;
					}
					/* symétrie centrale */
					if(plateauJeu[i][j].getCouleur() != e.plateauJeu[taille-1-i][taille-1-j].getCouleur())
					{
						symCentrale = false;
					}
					j++;
				}
				i++;
				j = 0;
			}
			if(egal || symCentrale){
				return true;
			}
		}
		return false;
	}

	@Override
	public void affichage() {
		for(int i = 0 ; i < plateauJeu.length ; i++) {
			for(int j = 0 ; j < plateauJeu[0].length ; j++) {
				System.out.print(" "+lecture(j,i)+ " ");
			}
			System.out.println("");
		}
	}
	
}

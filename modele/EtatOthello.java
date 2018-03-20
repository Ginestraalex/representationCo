package representationCo.modele;

import java.util.ArrayList;



public class EtatOthello extends Etat{
	
	public Pion[][] plateauJeu;
	int nbPionsNoirs;
	int nbPionsBlancs;
	public JoueurOthello joueur;
	public boolean tourPrecedentPasse;
	public boolean estFinal; 
	
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
		tourPrecedentPasse = false;
		estFinal = false;
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
		this.tourPrecedentPasse = e.tourPrecedentPasse;
		this.estFinal = e.estFinal;
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
     * fonction remplissant le tableau de jeu en fonction de la jouabilité
     * du joueur courrant
     */
    public void calculJouabilite(){
    		int taillePlateau = plateauJeu.length;
    		for(int y = 0 ; y < taillePlateau ; y++) {
    			for(int x = 0 ; x < taillePlateau ; x++) {
    				if(lecture(x, y) == 'J') {
    					ecriture(x, y, 'V');
    				}
    			}
    		}
    		boolean estJouable = false;
    		char cJoueur;
    		char cAdversaire;
    		int k = 1;
    		if(joueur.getCouleur() == 'N') { //tour joueur noir
    			cJoueur = 'N';
    			cAdversaire = 'B';
    		}
    		else {	//tour joueur blanc
    			cJoueur = 'B';
    			cAdversaire = 'N';
    		}
		for(int y = 0 ; y < taillePlateau ; y++) {
			for(int x = 0 ; x < taillePlateau ; x++) {
				if(lecture(x, y) == cJoueur) {
					/* ligne vers droite */
					if(x < taillePlateau-2) {
						while(x+k < taillePlateau-1 && lecture(x+k, y) == cAdversaire) {
							k++;
						}
						if(lecture(x+k, y) == 'V' && k != 1) {
							ecriture(x+k, y, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* ligne vers gauche */
					if(x > 1) {
						while(x-k > 0 && lecture(x-k, y) == cAdversaire) {
							k++;
						}
						if(lecture(x-k, y) == 'V' && k != 1) {
							ecriture(x-k, y, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* ligne vers bas */
					if(y < taillePlateau-2) {
						while(y+k < taillePlateau-1 && lecture(x, y+k) == cAdversaire) {
							k++;
						}
						if(lecture(x, y+k) == 'V' && k != 1) {
							ecriture(x, y+k, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* ligne vers le haut */
					if(y > 1) {
						while(y-k > 0 && lecture(x, y-k) == cAdversaire) {
							k++;
						}
						if(lecture(x, y-k) == 'V' && k != 1) {
							ecriture(x, y-k, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* diagonale bas-droit */
					if(x < taillePlateau-2 && y < taillePlateau-2 ) {
						while(y+k < taillePlateau-1  &&  x+k < taillePlateau-1 && lecture(x+k, y+k) == cAdversaire) {
							k++;
						}
						if(lecture(x+k, y+k) == 'V' && k != 1) {
							ecriture(x+k, y+k, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* diagonale bas-gauche */
					if(x > 1 && y < taillePlateau-2) {
						while(y+k < taillePlateau-1  &&  x-k > 0 && lecture(x-k, y+k) == cAdversaire) {
							k++;
						}
						if(lecture(x-k, y+k) == 'V' && k != 1) {
							ecriture(x-k, y+k, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* diagonale haut-gauche */
					if(x > 1 && y > 1) {
						while(y-k > 0 && x-k > 0 && lecture(x-k, y-k) == cAdversaire) {
							k++;
						}
						if(lecture(x-k, y-k) == 'V' && k != 1) {
							ecriture(x-k, y-k, 'J');
							estJouable = true;
						}
						k = 1;
					}
					/* diagonale haut-droit */
					if(x < taillePlateau-2 && y > 1 ) {
						while(x+k < taillePlateau-1 &&  y-k > 0 && lecture(x+k, y-k) == cAdversaire) {
							k++;
						}
						if(lecture(x+k, y-k) == 'V' && k != 1) {
							ecriture(x+k, y-k, 'J');
							estJouable = true;
						}
						k = 1;
					}
				}
			}
		}
		/* si le joueur ne peut pas jouer, il passe son tour */
		if(!estJouable && tourPrecedentPasse) {
			estFinal = true;
		}
		else if(!estJouable) {
			tourPrecedentPasse = true;
		}else {
			tourPrecedentPasse = false;
		}
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


	public EtatOthello minimax(EtatOthello e, int c) {
		ArrayList<EtatOthello> listeEtat = this.successeurs();
		EtatOthello e_sortie = null;
		int score;
		int score_max = Integer.MIN_VALUE;
		for(EtatOthello eTemp : listeEtat) {
			score = evaluation(c, eTemp);
			if(score >= score_max) {
				e_sortie = eTemp;
				score_max = score;
			}
		}
		return e_sortie;
	}
	
	
	
	private int evaluation(int c, EtatOthello e) {
		if(e.estFinal()) {
			if(nbPionsNoirs == nbPionsBlancs) {
				return 0;
			}
			else if(nbPionsNoirs < nbPionsBlancs) {
				if(joueur.getCouleur() == 'N') {
					return Integer.MIN_VALUE;
				}
				else {
					return Integer.MAX_VALUE;
				}
			}
			else {
				if(joueur.getCouleur() == 'N') {
					return Integer.MAX_VALUE;
				}
				else {
					return Integer.MIN_VALUE;
				}
			}
		}
		ArrayList<EtatOthello> listeEtat = new ArrayList<EtatOthello>();
		if(c == 0) {
			return 0;
		}
		int score;
		if(joueur.getNom().equals("Ordinateur")) {
			int scoreMax = Integer.MIN_VALUE;
			for(EtatOthello eTemp : listeEtat) {
				score = evaluation(c-1, eTemp);
				if(score > scoreMax) {
					scoreMax = score;
				}
			}
			return scoreMax;
		}
		else {
			int scoreMin = Integer.MAX_VALUE;
			for(EtatOthello eTemp : listeEtat) {
				score = evaluation(c-1, eTemp);
				if(score < scoreMin) {
					scoreMin = score;
				}
			}
			return scoreMin;
		}
	}

	
	/*
	 * retourne la couleur de la case
	 */
	public char lecture(int x, int y) {
		return plateauJeu[x][y].getCouleur();
	}
	
	
	
	/*
	 * defini le caractere de la couelur du joueur dans la case du plateau de jeu
	 */
	public void ecriture(int x, int y) {
		char laCouleur = joueur.getCouleur();
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
	
	
	/*
	 * test si l'etat est final (si le plateau est rempli)
	 */
	public boolean estFinal() {
		if( (nbPionsBlancs + nbPionsNoirs) == plateauJeu.length*plateauJeu.length  || estFinal) {
			return true;
		}
		return false;
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

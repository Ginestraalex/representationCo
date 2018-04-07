package representationCo.modele;

import java.util.ArrayList;

import representationCo.eval0.Eval0;
import representationCo.eval0.Eval0num1;



public class EtatOthello extends Etat{
	
	public Pion[][] plateauJeu;
	public int nbPionsNoirs;
	public int nbPionsBlancs;
	public JoueurOthello joueurCourant;
	public JoueurOthello joueurSuivant;
	public boolean tourPrecedentPasse;
	public boolean estFinal; 
	public int[] dernierCoupJoue; //tableau de taille 2 : tab[0] = x , tab[1] = y
	public Eval0 fonctionDEvaluation;

	
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
		dernierCoupJoue = new int[2];
		fonctionDEvaluation = new Eval0num1();

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
		this.joueurCourant = e.joueurCourant;
		this.joueurSuivant = e.joueurSuivant;
		dernierCoupJoue = new int[2];
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
	
	public int getSize() {
		return plateauJeu.length;
	}
	
	/* 
	 * retourne le X du dernier coup joue
	 */
	public int getXDernierCoup(){
		return dernierCoupJoue[0];
	}
	
	/* 
	 * retourne le Y du dernier coup joue
	 */
	public int getYDernierCoup(){
		return dernierCoupJoue[1];
	}
	
	
	/*
	 * stocke le dernier coup joue
	 */
	public void setDernierCoupJoue(int x, int y){
		this.dernierCoupJoue[0] = x;
		this.dernierCoupJoue[1] = y;
	}
	
	
	/*
	 * defini le joueur qui doit jouer
	 */
	public void setJoueurCourant(JoueurOthello j) {
		joueurCourant = j;
	}
	
	/*
	 * defini le joueur qui jouera le prochain tour
	 */
	public void setJoueurSuivant(JoueurOthello j) {
		joueurSuivant = j;
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
    		if(joueurCourant.getCouleur() == 'N') { //tour joueur noir
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
	
	
	/*
	 * retourne la liste des successeurs possibles d'un état
	 */
	public ArrayList<EtatOthello> successeurs() {
		ArrayList<EtatOthello> listeEtats = new ArrayList<EtatOthello>();				
		for(int i = 0 ; i < plateauJeu.length ; i++){
			for(int j = 0 ; j < plateauJeu.length ; j++){
				if(plateauJeu[i][j].getCouleur() == 'J'){
					EtatOthello e = new EtatOthello(this);
					PlateauDeJeu.jouer(e, i, j);
					boolean inexistant = true;
					for(EtatOthello eTemp : listeEtats) {
						if(eTemp.estEgal(e)) {
							inexistant = false;
						}
					}
					if(inexistant) {
						if(e.estFinal()) {
							e.estFinal = true;
						}
						listeEtats.add(e);
					}
				}
			}
		}
		return listeEtats;
	}


	/*
	 * fonction minimax du cours
	 */
	public EtatOthello minimax(int c) {
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
	
	
	
	/*
	 * fonction minmax avec elagage du cours
	 */
	public EtatOthello minmax_avec_elagage(int c) {
		ArrayList<EtatOthello> listeEtat = this.successeurs();
		EtatOthello e_sortie = null;
		int score;
		int score_max = Integer.MIN_VALUE;
		for(EtatOthello eTemp : listeEtat) {
			score = evaluation_alpha_beta(c, eTemp, Integer.MIN_VALUE, Integer.MAX_VALUE);
			if(score >= score_max) {
				e_sortie = eTemp;
				score_max = score;
			}
		}
		return e_sortie;
	}
	
	
	
	/*
	 * fonction evaluation du cours
	 */
	public int evaluation(int c, EtatOthello e) {
		if(e.estFinal()) {
			if(e.nbPionsNoirs == e.nbPionsBlancs) {
				return 0;
			}
			else if(e.nbPionsNoirs < e.nbPionsBlancs) {
				if(e.joueurCourant.getCouleur() == 'N') {
					return Integer.MIN_VALUE;
				}
				else {
					return Integer.MAX_VALUE;
				}
			}
			else {
				if(e.joueurCourant.getCouleur() == 'N') {
					return Integer.MAX_VALUE;
				}
				else {
					return Integer.MIN_VALUE;
				}
			}
		}
		if(c == 0) {
			return fonctionDEvaluation.eval0fonction(e);
		}
		ArrayList<EtatOthello> S = e.successeurs();
		int score;
		if(!e.joueurCourant.isOrdinateur()) {
			int scoreMax = Integer.MIN_VALUE;
			for(EtatOthello eTemp : S) {
				score = evaluation(c-1, eTemp);
				if(score > scoreMax) {
					scoreMax = score;
				}
			}
			return scoreMax;
		}
		else {
			int scoreMin = Integer.MAX_VALUE;
			for(EtatOthello eTemp : S) {
				score = evaluation(c-1, eTemp);
				if(score < scoreMin) {
					scoreMin = score;
				}
			}
			return scoreMin;
		}
	}
	
	
	
	/*
	 * fonction evalutation avec elagage
	 */
	public int evaluation_alpha_beta(int c, EtatOthello e, int alpha, int beta) {
		if(e.estFinal()) {
			if(e.nbPionsNoirs == e.nbPionsBlancs) {
				return 0;
			}
			else if(e.nbPionsNoirs < e.nbPionsBlancs) {
				if(e.joueurCourant.getCouleur() == 'N') {
					return Integer.MIN_VALUE;
				}
				else {
					return Integer.MAX_VALUE;
				}
			}
			else {
				if(e.joueurCourant.getCouleur() == 'N') {
					return Integer.MAX_VALUE;
				}
				else {
					return Integer.MIN_VALUE;
				}
			}
		}
		if(c == 0) {
			return fonctionDEvaluation.eval0fonction(e);
		}
		ArrayList<EtatOthello> S = e.successeurs();
		int score;
		if(!e.joueurCourant.isOrdinateur()) {
			int a = alpha;
			int scoreMax = Integer.MIN_VALUE;
			for(EtatOthello eTemp : S) {
				scoreMax = Integer.MIN_VALUE;
				score = evaluation_alpha_beta(c-1, eTemp, a, beta);
				if(score > scoreMax) {
					scoreMax = score;
				}
				if(scoreMax >= beta) {
					return scoreMax;
				}
				if(a < scoreMax) {
					a = scoreMax;
				}
			}
			return scoreMax;
		}
		else {
			int b = beta;
			int scoreMin = Integer.MAX_VALUE;
			for(EtatOthello eTemp : S) {
				scoreMin = Integer.MAX_VALUE;
				score = evaluation_alpha_beta(c-1, eTemp, alpha, b);
				if(score < scoreMin) {
					scoreMin = score;
				}
				if(scoreMin <= beta) {
					return scoreMin;
				}
				if(b > scoreMin) {
					b = scoreMin;
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
		char laCouleur = joueurCourant.getCouleur();
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

	
	/*
	 * teste si deux etats sont egaux, teste la symetrie centrale
	 */
	public boolean estEgal(EtatOthello e) {
		/* symetrie axiale et rotative a faire */
		if( this.nbPionsBlancs == e.nbPionsBlancs && this.nbPionsNoirs == e.nbPionsNoirs && joueurCourant == e.joueurCourant) {
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
				if(lecture(j,i)=='V') {
					System.out.print(" . ");

				}
				else
				{
					System.out.print(" "+lecture(j,i)+ " ");

				}
			}
			System.out.println();
		}
		System.out.println("dernier coup joue: x="+ this.getXDernierCoup() + " y=" +this.getYDernierCoup());
	}

	/*
	 * donne la main au joueur qui suit
	 */
	public void tourSuivant() {
		JoueurOthello jTemp = joueurCourant;
		joueurCourant = joueurSuivant;
		joueurSuivant = jTemp;
		fonctionDEvaluation = joueurCourant.fonctionDEvaluation;
	}
}

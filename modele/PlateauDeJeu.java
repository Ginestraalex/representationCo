package representationCo.modele;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import representationCo.eval0.Eval0;
import representationCo.eval0.Eval0Lambda;
import representationCo.eval0.Eval0num1;
import representationCo.eval0.Eval0num2;
import representationCo.eval0.Eval0num3;
import representationCo.view.Vue;

public class PlateauDeJeu {

	public boolean activerMessagesTour;
	private int taillePlateau;
	private JoueurOthello[] tableauJoueurs;
	private EtatOthello etat;
    private ArrayList<Vue> vues;
    
    public PlateauDeJeu() {
    		activerMessagesTour = true;
    		tableauJoueurs = new JoueurOthello[2];
    		tableauJoueurs[0] = new JoueurOthello("Joueur 1", 'N');
    		tableauJoueurs[1] = new JoueurOthello("Joueur 2", 'B');
    		vues = new ArrayList<Vue>();
    		nouvellePartie(8);
    		etat.calculJouabilite();
    }
    
    public PlateauDeJeu(int taille) {
		activerMessagesTour = true;
    		tableauJoueurs = new JoueurOthello[2];
    		tableauJoueurs[0] = new JoueurOthello("Joueur 1", 'N');
    		tableauJoueurs[1] = new JoueurOthello("Joueur 2", 'B');
    		vues = new ArrayList<Vue>();
    		nouvellePartie(taille);
    		etat.calculJouabilite();
    }
    
    
    public void activerDesactiverMessage() {
    		if(activerMessagesTour) {
    			activerMessagesTour = false;
    		}
    		else {
    			activerMessagesTour = true;
    		}
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
				if(nom.contains("Ordinateur")){
    					JOptionPane.showMessageDialog(null, "Impossible d'ajouter le joueur (le mot: Ordinateur est reserve pour la machine)");
				}
				else{
			    		if(tableauJoueurs[0].getNom().equals(nomJoueurARemplacer)) {
			    			if(tableauJoueurs[1].getNom().equals(nom))
			    			{
			    				JOptionPane.showMessageDialog(null, "Impossible d'ajouter le joueur, le nom existe deja");
			    				ajouterJoueur();
			    			}
			    			else{
			    				tableauJoueurs[0].setNom(nom);
			    				tableauJoueurs[0].setOrdinateur(false);
			    			}
			    		}
			    		else {
			    			if(tableauJoueurs[0].getNom().equals(nom)){
			    				JOptionPane.showMessageDialog(null, "Impossible d'ajouter le joueur, le nom existe deja");
			    				ajouterJoueur();
			    			}
			    			else{
			    				tableauJoueurs[1].setNom(nom);
			    				tableauJoueurs[1].setOrdinateur(false);
		    				}
			    		}
				}
			}
		}
    }
    
    
    
    
    /*
     * fonction de définition de eval0
     */
    public void setEval0() {
		String[] joueurPossible = {tableauJoueurs[0].getNom(), tableauJoueurs[1].getNom()};
		String nomJoueurARemplacer = tableauJoueurs[0].getNom();
		nomJoueurARemplacer = (String)JOptionPane.showInputDialog(null, "Choisissez le joueur qui changera sa fonction eval0", "selection algo eval0", JOptionPane.QUESTION_MESSAGE, null, joueurPossible, joueurPossible[0]);
		if(nomJoueurARemplacer != null) {
			String[] eval0possible = {"Eval0num1","Eval0num2","Eval0num3","Eval0Lambda"};
			String eval0Choisie = "Eval0num1";
			eval0Choisie  = (String)JOptionPane.showInputDialog(null, "Choisissez la fonction eval0", "selection algo eval0", JOptionPane.QUESTION_MESSAGE, null, eval0possible, eval0possible[0]);
			if(tableauJoueurs[0].getNom().equals(nomJoueurARemplacer) && tableauJoueurs[0].isOrdinateur()) {
				if(eval0Choisie.equals("Eval0num1")) {
					tableauJoueurs[0].fonctionDEvaluation = new Eval0num1();
				}
				else if(eval0Choisie.equals("Eval0num2")) {
					tableauJoueurs[0].fonctionDEvaluation = new Eval0num2();
				}
				else if(eval0Choisie.equals("Eval0num3")){
					tableauJoueurs[0].fonctionDEvaluation = new Eval0num3();
				}
				else {
					tableauJoueurs[0].fonctionDEvaluation = new Eval0Lambda();
				}
			}
	    		else if(tableauJoueurs[1].isOrdinateur()){
	    			if(eval0Choisie.equals("Eval0num1")) {
					tableauJoueurs[1].fonctionDEvaluation = new Eval0num1();
				}
				else if(eval0Choisie.equals("Eval0num2")) {
					tableauJoueurs[1].fonctionDEvaluation = new Eval0num2();
				}
				else if(eval0Choisie.equals("Eval0num3")){
					tableauJoueurs[1].fonctionDEvaluation = new Eval0num3();
				}
				else {
					tableauJoueurs[1].fonctionDEvaluation = new Eval0Lambda();
				}
	    		}
		}
    }
    
    
    /*
     * fonction de definition de la fonction
     * eval0 sur un joueur J
     */
    public void setEval0(JoueurOthello j) {
    		String[] eval0possible = {"Eval0num1","Eval0num2","Eval0num3","Eval0Lambda"};
		String eval0Choisie = "Eval0num1";
		eval0Choisie  = (String)JOptionPane.showInputDialog(null, "Choisissez la fonction eval0 pour "+j.getNom(), "selection algo eval0", JOptionPane.QUESTION_MESSAGE, null, eval0possible, eval0possible[0]);
		if(eval0Choisie != null) {
			if(eval0Choisie.equals("Eval0num1")) {
				j.fonctionDEvaluation = new Eval0num1();
			}
			else if(eval0Choisie.equals("Eval0num2")) {
				j.fonctionDEvaluation = new Eval0num2();
			}
			else if(eval0Choisie.equals("Eval0num3")){
				j.fonctionDEvaluation = new Eval0num3();
			}
			else {
				j.fonctionDEvaluation = new Eval0Lambda();
			}
		}
    }
    
    
    public void setProfondeurRecherche(JoueurOthello j) {
    		if(j.isOrdinateur()) {
    			String[] profondeurPossible = {"0","1","2","3","4","5","6","7","8","9"};
        		String profondeur = "2";
        		profondeur = (String) JOptionPane.showInputDialog(null, "Choisissez la profondeur de recherche", "selection profondeur", JOptionPane.QUESTION_MESSAGE, null, profondeurPossible, profondeurPossible[2]);
        		if(profondeur != null) {
        			j.setProfondeurRecherche(Integer.parseInt(profondeur));
        		}
    		}
    		else {
    			JOptionPane.showMessageDialog(null, "Le joueur doit etre un ordinateur");
    		}
    }
    
    
    public void remplacerJoeurParOrdinateur(){
    		String[] joueurPossible = {tableauJoueurs[0].getNom(), tableauJoueurs[1].getNom()};
		String nomJoueurARemplacer = tableauJoueurs[0].getNom();
		nomJoueurARemplacer = (String)JOptionPane.showInputDialog(null, "Choisissez le joueur qui sera remplacé", "Ajout nouveau joueur", JOptionPane.QUESTION_MESSAGE, null, joueurPossible, joueurPossible[0]);
		if(nomJoueurARemplacer != null) {
	    		if(tableauJoueurs[0].getNom().equals(nomJoueurARemplacer)) {
	    			tableauJoueurs[0].setOrdinateur(true);
	    			tableauJoueurs[0].setNom("Ordinateur");
	        		tableauJoueurs[0].fonctionDEvaluation = new Eval0num1();
	        		tableauJoueurs[0].setProfondeurRecherche(2);
	        		this.setProfondeurRecherche(tableauJoueurs[0]);
	        		this.setEval0(tableauJoueurs[0]);
	    		}
	    		else {
	    			tableauJoueurs[1].setOrdinateur(true);
	    			tableauJoueurs[1].setNom("Ordinateur");
	        		tableauJoueurs[1].fonctionDEvaluation = new Eval0num1();
	        		tableauJoueurs[1].setProfondeurRecherche(2);
	        		this.setProfondeurRecherche(tableauJoueurs[1]);
	        		this.setEval0(tableauJoueurs[1]);
	    		}
		}
    }
    
    /*
     * instancie les deux joueurs du jeux comme 
     * étant des ordinateurs
     */
    public void ordinateurVsOrdinateur() {
    		tableauJoueurs[0].setNom("Ordinateur1");
    		tableauJoueurs[0].setOrdinateur(true);
    		tableauJoueurs[0].fonctionDEvaluation = new Eval0num1();
    		this.setEval0(tableauJoueurs[0]);
    		this.setProfondeurRecherche(tableauJoueurs[0]);
    		tableauJoueurs[1].setNom("Ordinateur2");
    		tableauJoueurs[1].setOrdinateur(true);
    		tableauJoueurs[1].fonctionDEvaluation = new Eval0num1();
    		this.setEval0(tableauJoueurs[1]);
    		this.setProfondeurRecherche(tableauJoueurs[1]);
    		activerMessagesTour = false;
    }
    
    
    /*
     * fonction de comparaison 
     * de deux fonction eval0
     */
    public void comparaisonEval0() {
    		Eval0 e1 = null;
		Eval0 e2 = null;
    		String[] eval0possible = {"Eval0num1","Eval0num2","Eval0num3","Eval0Lambda"};
		String eval0Choisie = "Eval0num1";
		eval0Choisie  = (String)JOptionPane.showInputDialog(null, "Choisissez la premiere fonction eval0", "selection algo eval0", JOptionPane.QUESTION_MESSAGE, null, eval0possible, eval0possible[0]);
		if(eval0Choisie != null) {
			if(eval0Choisie.equals("Eval0num1")) {
				e1 = new Eval0num1();
			}
			else if(eval0Choisie.equals("Eval0num2")) {
				e1 = new Eval0num2();
			}
			else if(eval0Choisie.equals("Eval0num3")){
				e1 = new Eval0num3();
			}
			else {
				e1 = new Eval0Lambda();
			}
		}
		eval0Choisie  = (String)JOptionPane.showInputDialog(null, "Choisissez la deuxieme fonction eval0", "selection algo eval0", JOptionPane.QUESTION_MESSAGE, null, eval0possible, eval0possible[0]);
		if(eval0Choisie != null) {
			if(eval0Choisie.equals("Eval0num1")) {
				e2 = new Eval0num1();
			}
			else if(eval0Choisie.equals("Eval0num2")) {
				e2 = new Eval0num2();
			}
			else if(eval0Choisie.equals("Eval0num3")){
				e2 = new Eval0num3();
			}
			else {
				e2 = new Eval0Lambda();
			}
		}
		String[] profondeurPossible = {"0","1","2","3","4","5","6","7","8","9"};
		String profondeur = "2";
		profondeur = (String) JOptionPane.showInputDialog(null, "Choisissez la profondeur de recherche", "selection profondeur", JOptionPane.QUESTION_MESSAGE, null, profondeurPossible, profondeurPossible[2]);
		
		if(e1 != null && e2 != null && profondeur != null) {
			int res = comparaisonEval0(e1,e2, Integer.parseInt(profondeur));
			if(res == 1) {
				JOptionPane.showMessageDialog(null, e1.getNom() + " est meilleure que " + e2.getNom());
			}
			else if(res == -1) {
				JOptionPane.showMessageDialog(null, e2.getNom() + " est meilleure que " + e1.getNom());
			}
			else {
				JOptionPane.showMessageDialog(null, e1.getNom() + " est aussi efficace que " + e2.getNom());

			}
		}
    }
    
    
    /*
     * fonction de comparaison entre deux eval0
     * retourne:
     * 1 si eval1 est plus performante
     * -1 si eval2 est plus performante
     * et 0 si nul
     */
    public int comparaisonEval0(Eval0 eval1, Eval0 eval2, int p) {
    		int score = 0;
    		int resPartie = 0;
    		/* premiere partie eval1 commence*/
    		tableauJoueurs[0].setNom("Ordinateur1");
		tableauJoueurs[0].setOrdinateur(true);
		tableauJoueurs[0].fonctionDEvaluation = eval1;
		tableauJoueurs[0].setProfondeurRecherche(p);
		tableauJoueurs[1].setNom("Ordinateur2");
		tableauJoueurs[1].setOrdinateur(true);
		tableauJoueurs[1].fonctionDEvaluation = eval2;
		tableauJoueurs[1].setProfondeurRecherche(p);
		activerMessagesTour = false;
		nouvellePartie(taillePlateau);
		resPartie = etat.getNumJoueurVainqueur();
		if(resPartie == 0) {
			score  = 1;
		}
		else if(resPartie == 1) {
			score = -1;
		}
		
		/* deuxieme partie eval2 commence */
		tableauJoueurs[1].setNom("Ordinateur1");
		tableauJoueurs[1].setOrdinateur(true);
		tableauJoueurs[1].fonctionDEvaluation = eval1;
		tableauJoueurs[1].setProfondeurRecherche(p);
		tableauJoueurs[0].setNom("Ordinateur2");
		tableauJoueurs[0].setOrdinateur(true);
		tableauJoueurs[0].fonctionDEvaluation = eval2;
		tableauJoueurs[0].setProfondeurRecherche(p);
		activerMessagesTour = false;
		nouvellePartie(taillePlateau);
		resPartie = etat.getNumJoueurVainqueur();
		if(resPartie == 0) {
			score -= 1;
		}
		else if(resPartie == 1) {
			score += 1;
		}
		
		if(score < 0 ) {
			score = -1;
		}
		else if(score > 0) {
			score = 1;
		}
		return score;
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
		if(activerMessagesTour) {
			JOptionPane.showMessageDialog(null, "C'est au tour de "+etat.joueurCourant.getNom());
		}
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
				//long debut = System.currentTimeMillis();	 
				etat = etat.minmax_avec_elagage(etat.getProfondeurRecherche());
				//System.out.println("temps de calcul :"+(System.currentTimeMillis()-debut));
				
				etat.tourSuivant();
				joueurSuivant();
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
     * création d'une nouvelle partie
     */
    public void nouvellePartie(int taille) {
	    taillePlateau = taille;
	    	etat = new EtatOthello(taillePlateau);
	    	etat.setJoueurCourant(tableauJoueurs[0]);
	    	etat.setJoueurSuivant(tableauJoueurs[1]);

	    	etat.calculJouabilite();	    	
		JOptionPane.showMessageDialog(null, "C'est au tour de "+etat.joueurCourant.getNom());
		maj();
	    	
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
  		for(int y = 0 ; y < taillePlateau ; y++){
  			spb.append("|");
  			for(int x = 0 ; x < taillePlateau ; x++) {
  				spb.append("|"+ etat.lecture(x, y));
  			}
  			spb.append("||");
  			spb.append("\n");
  		}
  		return spb.toString();
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

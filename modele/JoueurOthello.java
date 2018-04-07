package representationCo.modele;

import representationCo.eval0.Eval0;

public class JoueurOthello extends Joueur{
	
	public char couleur;
	public Eval0 fonctionDEvaluation;
	public int profondeurRecherche;

	
	public JoueurOthello(String nom, char c){
		super(nom);
		couleur = c;
	}
	
	public char getCouleur() {
		return couleur;
	}
	
	public int getProfondeurRecherche() {
		return profondeurRecherche;
	}
	
	public void setCouleur(char c) {
		couleur = c;
	}
	
	public void setProfondeurRecherche(int p) {
		profondeurRecherche = p;
	}
}

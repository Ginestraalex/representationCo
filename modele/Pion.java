package representationCo.modele;

public class Pion {
	private char couleur;
	
	public Pion() {
		couleur = 'V'; //vide
	}
	public Pion(char c){
		couleur = c;
	}
	
	/*
	 * retourne la valeur contenue dans la case
	 */
	public char getCouleur() {
		return couleur;
	}
	
	/*
	 * definit la couleur de la case
	 */
	public void setCouleur(char laCouleur) {
		couleur = laCouleur;
	}
}

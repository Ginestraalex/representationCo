package representationCo.modele;

public class Pion {
	private char couleur;
	
	public Pion() {
		couleur = 'V'; //vide
	}
	public Pion(char c){
		couleur = c;
	}
	
	public char getCouleur() {
		return couleur;
	}
	
	public void setCouleur(char laCouleur) {
		couleur = laCouleur;
	}
}

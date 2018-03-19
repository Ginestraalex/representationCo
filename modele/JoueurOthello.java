package representationCo.modele;


public class JoueurOthello extends Joueur{
	
	public char couleur;
	
	public JoueurOthello(String nom, char c){
		super(nom);
		couleur = c;
	}
	
	public char getCouleur() {
		return couleur;
	}
	
	public void setCouleur(char c) {
		couleur = c;
	}
}

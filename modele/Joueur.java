package representationCo.modele;

public abstract class Joueur {
	
	private String nomJoueur;
	private int nbGagnees;
	
	public Joueur(){
		nbGagnees = 0;
		nomJoueur = "No Name";
	}
	
	public Joueur(String nom) {
		nbGagnees = 0;
		nomJoueur = nom;
	}
	
	/*
	 * fonction appelée quand un joueur remporte une partie
	 */
	public void setGagne(){
		nbGagnees++;
	}
	
	/*
	 * retourne le nombre de partie gagnée
	 */
	public int getGagne() {
		return nbGagnees;
	}
	
	/*
	 * retroune le nom du Joueur
	 */
	public String getNom() {
		return nomJoueur;
	}
}

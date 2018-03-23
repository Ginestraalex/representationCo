package representationCo.modele;

public abstract class Joueur {
	
	private String nomJoueur;
	private int nbGagnees;
	private boolean estOrdinateur;

	
	public Joueur(){
		nbGagnees = 0;
		nomJoueur = "No Name";
		estOrdinateur = false;
	}
	
	public Joueur(String nom) {
		nbGagnees = 0;
		nomJoueur = nom;
		estOrdinateur = false;
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
	
	public void setOrdinateur(){
		estOrdinateur = true;
		nomJoueur = "Ordinateur";
		nbGagnees = 0;
	}
	
	/*
	 * retroune le nom du Joueur
	 */
	public String getNom() {
		return nomJoueur;
	}
	
	public boolean isOrdinateur(){
		return estOrdinateur;
	}
}

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
	
	public void setGagne(){
		nbGagnees++;
	}
	
	public int getGagne() {
		return nbGagnees;
	}
	
	public String getNom() {
		return nomJoueur;
	}
}

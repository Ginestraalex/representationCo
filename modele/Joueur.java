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
	 *  definit le nom du joueur par str
	 */
	public void setNom(String str) {
		nomJoueur = str;
		nbGagnees = 0;
	}
	
	/*
	 * definit si le joueur est un ordinateur
	 */
	public void setOrdinateur(boolean isComputer){
		estOrdinateur = isComputer;
		nbGagnees = 0;
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
	
	/*
	 * retourne true si le joueur est un ordianteur
	 * 
	 */
	public boolean isOrdinateur(){
		return estOrdinateur;
	}
}

package modele;

public abstract class Joueur {
	private int nbGagnés;
	
	public Joueur(){
		nbGagnés = 0;
	}
	
	public void setGagne(){
		nbGagnés++;
	}
}

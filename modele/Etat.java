package modele;

import java.util.ArrayList;

public abstract class Etat {
	
	public Etat(){
	}
	
	public abstract ArrayList<Etat> successeurs(Joueur j);
	
}

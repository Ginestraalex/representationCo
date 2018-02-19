package representationCo.modele;

import java.util.ArrayList;



public class EtatOthello extends Etat{
	
	public Pion[][] plateauJeu;
	
	public EtatOthello() {
		
	}
	
	public EtatOthello(int taille) {
		plateauJeu = new Pion[taille][taille];
		for(int i = 0 ; i < taille ; i++) {
			for(int j = 0 ; j < taille ; j++) {
				plateauJeu[i][j] = new Pion();
			}
		}
	}
	
	public char getCouleur(int i, int j) {
		return plateauJeu[i][j].getCouleur();
	}
	
	public void setCouleur(int i, int j, char laCouleur) {
		plateauJeu[i][j].setCouleur(laCouleur);
	}
	
	
	@Override
	public ArrayList<Etat> successeurs(Joueur j) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

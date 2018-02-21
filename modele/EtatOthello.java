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
	
	public char getCouleur(int x, int y) {
		return plateauJeu[x][y].getCouleur();
	}
	
	public void setCouleur(int x, int y, char laCouleur) {
		plateauJeu[x][y].setCouleur(laCouleur);
	}
	
	
	@Override
	public ArrayList<Etat> successeurs(Joueur j) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

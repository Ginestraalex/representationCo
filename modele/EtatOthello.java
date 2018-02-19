package representationCo.modele;

import java.util.ArrayList;

import representationCo.view.Vue;


public class EtatOthello extends Etat{
	private int taille;
	private Pion[][] tab;
    private ArrayList<Vue> vues;
	
	public EtatOthello() {
	    this.vues = new ArrayList<>(1);
		taille = 8;
		tab = new Pion[taille][taille];
		
		//initialisation
		for(int i = 0 ; i < taille ; i++){
			for(int j = 0 ; j < taille ; j++)
			tab[i][j] = new Pion(" ");
		}
		debutPartie();
	}
	
	// Deuxieme constructeur si on veut une taille précise pour le plateau
	public EtatOthello(int t){
		if((t % 2) == 0) {
			taille = t;
			tab = new Pion[taille][taille];
			
			//initialisation
			for(int i = 0 ; i < taille ; i++){
				for(int j = 0 ; j < taille ; j++) {
					tab[i][j] = new Pion(" ");
				}
			}
			debutPartie();
		} else {
			System.out.println("la taille doit être un chiffre pair");	
		}
	}
	
	public void debutPartie() {
		int l = taille / 2;
		int c = (taille / 2) - 1;
		tab[l][l] = new Pion("B");
		tab[c][c] = new Pion("B");
		tab[l][c] = new Pion("N");
		tab[c][l] = new Pion("N");
	}
	
	public void setPion(int l, int c, Pion val){
		tab[l][c] = val;
	}
	
	public Pion[][] getPlateau(){
		Pion[][] res = new Pion[taille][taille];
		
		// passage des valeurs du tableau au tableau qui va etre renvoyer
		for(int i = 0 ; i < taille ; i++){
			for(int j = 0 ; j < taille ; j++) {
				res[i][j] = tab[i][j];
			}
		}
		return res;
	}
	
	public void setPlateau(Pion[][] test){
			for(int i = 0 ; i < 2 ; i++){
				for(int j = 0 ; j < taille ; j++) {
					tab[i][j] = test[i][j];
				}
			}
	}
	
	public boolean isEgal(Pion[][] test){
		if(test.length != tab.length){
			return false;
		}else{
			for(int i = 0 ; i < 2 ; i++){
				for(int j = 0 ; j < taille ; j++) {
					if(test[i][j] != tab[i][j]){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean isEgal(Pion[][] test,Pion[][] res){
		if(test.length != res.length){
			return false;
		}else{
			int t = test.length;
			for(int i = 0 ; i < 2 ; i++){
				for(int j = 0 ; j < t ; j++) {
					if(test[i][j] != res[i][j]){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public int getTaille(){
		return taille;
	}
	
	//Affichage plateau du jeu sur terminal
	public String toString(){
		StringBuilder spb = new StringBuilder();
		encadrementJeu(spb);
		for(int x = 0 ; x < taille ; x++){
			spb.append("|");
			for(int y = 0 ; y < taille ; y++) {
				spb.append("|"+ tab[x][y].getCouleur());
			}
			spb.append("||");
			spb.append("\n");
		}
		encadrementJeu(spb);
		return spb.toString();
	}
	
	public StringBuilder encadrementJeu(StringBuilder spb){
		for(int t = 0 ; t < (taille*2)+3 ; t++){
			spb.append("|");
		}
		spb.append("\n");
		return spb;
	}

	@Override
	public ArrayList<Etat> successeurs(Joueur j) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void ajouterVue(Vue v){
		vues.add(v);
	}
}

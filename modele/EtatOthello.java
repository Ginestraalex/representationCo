package modele;


public class EtatOthello extends Etat{
	private int taille;
	private Pion[][] tab;
	
	public EtatOthello() {
		taille = 8;
		tab = new Pion[taille][taille];
		int total = taille * taille;
		
		//initialisation
		for(int i = 0 ; i < total ; i++){
			int ligne = i / taille;
			int colonne = i % taille;
			tab[ligne][colonne] = new Pion(" ");
		}
		debutPartie();
	}
	
	// Deuxieme constructeur si on veut une taille précise pour le plateau
	public EtatOthello(int t){
		if((t % 2) == 0) {
			taille = t;
			tab = new Pion[taille][taille];
			int total = taille * taille;
			
			//initialisation
			for(int i = 0 ; i < total ; i++){
				int ligne = i / taille;
				int colonne = i % taille;
				tab[ligne][colonne] = new Pion(" ");
			}
			debutPartie();
		} else {
			System.out.println("la taille doit être un chiffre pair");	
		}
	}
	
	public void debutPartie() {
		int l = taille / 2;
		int c = (taille / 2) - 1;
		tab[l][l]=new Pion("B");
		tab[c][c]=new Pion("B");
		tab[l][c]=new Pion("N");
		tab[c][l]=new Pion("N");
	}
	
	public void setPion(int l, int c, Pion val){
		tab[l][c]=val;
	}
	
	public Pion[][] getPlateau(){
		Pion[][] res = new Pion[taille][taille];
		int total = taille * taille;
		
		// passage des valeurs du tableau au tableau qui va etre renvoyer
		for(int i = 0 ; i < total ; i++){
			int ligne = i / taille;
			int colonne = i % taille;
			res[ligne][colonne] = tab[ligne][colonne];
		}
		
		return res;
	}
	
	public void setPlateau(Pion[][] test){
			for(int i = 0 ; i < (taille * 2) ; i++){
				int ligne = i / taille;
				int colonne = i % taille;
				tab[ligne][colonne] = test[ligne][colonne];
				}
	}
	
	public boolean isEgal(Pion[][] test){
		if(test.length != tab.length){
			return false;
		}else{
			for(int i = 0 ; i < (taille * 2) ; i++){
				int ligne = i / taille;
				int colonne = i % taille;
				if(test[ligne][colonne] != tab[ligne][colonne]){
					return false;
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
			for(int i = 0 ; i < (t * 2) ; i++){
				int ligne = i / t;
				int colonne = i % t;
				if(test[ligne][colonne] != res[ligne][colonne]){
					return false;
				}
			}
		}
		return true;
	}
	
	//Affichage plateau du jeu sur terminal
	public String toString(){
		StringBuilder spb = new StringBuilder();
		for(int x = 0 ; x < taille ; x++){
			spb.append("|");
			for(int y = 0 ; y < taille ; y++) {
				spb.append("|"+ tab[x][y].getCouleur());
			}
			spb.append("||");
			spb.append("\n");
		}
		return spb.toString();
	}
}

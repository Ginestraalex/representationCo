package representationCo.eval0;

import representationCo.modele.EtatOthello;
import representationCo.modele.PlateauDeJeu;
import representationCo.view.Vue;

public class Lambda implements Vue{

	public static int[][] tab = null;
	public static int[][] tabEvolue = null;
	public PlateauDeJeu modele;
	
	public Lambda(PlateauDeJeu mod) {
		modele = mod;
	}
	
	public static void initTab(EtatOthello e) {
		int taille = e.getSize();
		tab = new int[taille][taille];
		for(int i = 0 ; i < taille/2 ; i++) {
			for(int j = 0 ; j < taille/2 ; j++){
				tab[taille/2+i][taille/2+j] = i+j;
				tab[taille/2-i-1][taille/2-j-1] = i+j;
				tab[taille/2-i-1][taille/2+j] = i+j;
				tab[taille/2+i][taille/2 -j-1] = i+j;
				tabEvolue[taille/2+i][taille/2+j] = i+j;
				tabEvolue[taille/2-i-1][taille/2-j-1] = i+j;
				tabEvolue[taille/2-i-1][taille/2+j] = i+j;
				tabEvolue[taille/2+i][taille/2 -j-1] = i+j;
			}
		}
		affichage();
	}
	
	public static void initTab(int taille) {
		tab = new int[taille][taille];
		for(int i = 0 ; i < taille/2 ; i++) {
			for(int j = 0 ; j < taille/2 ; j++){
				tab[taille/2+i][taille/2+j] = i+j;
				tab[taille/2-i-1][taille/2-j-1] = i+j;
				tab[taille/2-i-1][taille/2+j] = i+j;
				tab[taille/2+i][taille/2 -j-1] = i+j;
				tabEvolue[taille/2+i][taille/2+j] = i+j;
				tabEvolue[taille/2-i-1][taille/2-j-1] = i+j;
				tabEvolue[taille/2-i-1][taille/2+j] = i+j;
				tabEvolue[taille/2+i][taille/2 -j-1] = i+j;
			}
		}
		affichage();
	}
	
	public static void modifAlea(EtatOthello e) {
		int x = (int)Math.random()%e.getSize();
		int y = (int)Math.random()%e.getSize();
		tab[x][y]++;
		affichage();
	}
	
	public static int getValeurBasique(int x, int y) {
		return tab[x][y];
	}
	
	
	public static int getValeurEvolue(int x, int y) {
		return tabEvolue[x][y];
	}
	
	public static void affichage() {
		if(tab != null) {
			for(int i = 0 ; i < tab.length ; i++) {
				for(int j = 0 ; j < tab.length ; j++) {
					System.out.print(tab[j][i]);
				}
				System.out.println();
			}
		}
	}

	@Override
	public void maj() {
		if(tab == null || modele.getTaille() != tab.length) {
			initTab(modele.getTaille());
		}
	}
}

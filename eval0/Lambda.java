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
	
	/*
	 * initialise les tableaux de valeur des positions
	 */
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
		affichageEvolue();
		affichage();
	}
	
	/*
	 * initialise les tableaux de valeur des positions
	 */
	public static void initTab(int taille) {
		tab = new int[taille][taille];
		tabEvolue = new int[taille][taille];
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
		affichageEvolue();
	}
	
	/*
	 * genere la modification aleatoire de LambdaEvolue
	 */
	public static void modifAlea(EtatOthello e) {
		if(tabEvolue != null) {
			int x = (int)Math.random()*e.getSize();
			int y = (int)Math.random()*e.getSize();
			tabEvolue[x][y]++;
			affichageEvolue();
		}
	}
	
	/*
	 * genere la modification aleatoire de LambdaEvolue
	 */
	public static void modifAlea() {
		if(tabEvolue != null) {
			int x = (int)Math.random()*tabEvolue.length;
			int y = (int)Math.random()*tabEvolue.length;
			tabEvolue[x][y]++;
			affichageEvolue();
		}
	}
	
	/*
	 * retourne la valeur de la position sur lambda basique
	 */
	public static int getValeurBasique(int x, int y) {
		return tab[x][y];
	}
	
	
	/*
	 * retourne la valeur de la position sur lambda evolue
	 */
	public static int getValeurEvolue(int x, int y) {
		return tabEvolue[x][y];
	}
	
	
	/*
	 * remplace lambda basique par lambda evolue
	 */
	public static void remplacerLamdbaBasique() {
		for(int i = 0 ; i < tab.length ; i++) {
			for(int j = 0 ; j < tab.length ; j++) {
				tab[i][j] = tabEvolue[i][j];
			}
		}
	}
	
	/*
	 * restaure lambda evolue au stade lambda basique
	 */
	public static void restaurerLambdaEvolutive() {
		
	}

	/*
	 * fonction d'affichage de lambda basique
	 */
	public static void affichage() {
		if(tab != null) {
			for(int i = 0 ; i < tab.length ; i++) {
				for(int j = 0 ; j < tab.length ; j++) {
					System.out.print(tab[j][i]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	/*
	 * affichage de lambda evolue
	 */
	public static void affichageEvolue() {
		if(tab != null) {
			for(int i = 0 ; i < tabEvolue.length ; i++) {
				for(int j = 0 ; j < tabEvolue.length ; j++) {
					System.out.print(tabEvolue[j][i]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	
	@Override
	public void maj() {
		if(tab == null || modele.getTaille() != tab.length) {
			initTab(modele.getTaille());
		}
	}
}

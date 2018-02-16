import modele.EtatOthello;
import modele.Pion;


public class Jeu {
	private EtatOthello modele;
	
	public Jeu(){
		modele = new EtatOthello();
	}
	
	public String toString(){
		return modele.toString();
	}
	
	public static void main(String[] args) {
		Jeu reversi = new Jeu();
		System.out.println(reversi.toString());
	}
}

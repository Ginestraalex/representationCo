package representationCo.modele;


public class JoueurOthello extends Joueur{
	private String couleur;
	
	public JoueurOthello(String c){
		couleur = c;
	}
	
	public String getCouleur(){
		return couleur;
	}
}

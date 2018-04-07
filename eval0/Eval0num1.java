package representationCo.eval0;

import representationCo.modele.EtatOthello;

public class Eval0num1 implements Eval0{

	
	public Eval0num1() {
		
	}
	
	/*
	 * fonction eval 0
	 * qui cherche a prendre les lignes exterieurs et les coins
	 * et le maximum de pions
	 */
	public int eval0fonction(EtatOthello e) {
		int bonus = 0;
		/* ajoute un bonus si l'ordinateur prend une des lignes exterieures (+4) ou un coin (+12) */
		if(e.getXDernierCoup() == 0 || e.getXDernierCoup() == e.plateauJeu.length-1){
			bonus += 4;
			if(e.getYDernierCoup() == 0 || e.getYDernierCoup() == e.plateauJeu.length -1){
				bonus += 8;
			}
		}
		else if(e.getYDernierCoup() == 0 || e.getYDernierCoup() == e.plateauJeu.length-1){
			bonus += 4;
			if(e.getXDernierCoup() == 0 || e.getXDernierCoup() == e.plateauJeu.length-1){
				bonus += 8;
			}
		}
		if(e.joueurCourant.couleur == 'N'){
			return e.nbPionsBlancs + bonus;
		}
		else{
			return e.nbPionsNoirs + bonus;
		}
	}
}

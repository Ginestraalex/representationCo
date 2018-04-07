package representationCo.eval0;

import representationCo.modele.EtatOthello;

public class Eval0num3 implements Eval0{

	
	public Eval0num3() {
		
	}
	
	/*
	 * cherche a prendre les coins si possible,
	 * sinon essaye de prendre le maximum de pions
	 */
	public int eval0fonction(EtatOthello e) {
		int res = 0;
		if(e.getXDernierCoup() == 0 || e.getXDernierCoup() == e.plateauJeu.length-1){
			if(e.getYDernierCoup() == 0 || e.getYDernierCoup() == e.plateauJeu.length -1){
				res = Integer.MAX_VALUE;
			}
		}
		else if(e.getYDernierCoup() == 0 || e.getYDernierCoup() == e.plateauJeu.length-1){
			if(e.getXDernierCoup() == 0 || e.getXDernierCoup() == e.plateauJeu.length-1){
				res = Integer.MAX_VALUE;
			}
		}
		else {
			if(e.joueurCourant.couleur == 'N'){
				return e.nbPionsBlancs;
			}
			else{
				return e.nbPionsNoirs;
			}
		}
		return res;
	}

	@Override
	public String getNom() {
		return "Eval0num3";
	}

}

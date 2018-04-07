package representationCo.eval0;

import representationCo.modele.EtatOthello;

public class Eval0num2 implements Eval0{
	
	public Eval0num2() {
		
	}

	/*
	 * fonction eval 0
	 * qui cherche a avoir le plus de pions
	 */
	@Override
	public int eval0fonction(EtatOthello e) {
		if(e.joueurCourant.couleur == 'N'){
			return e.nbPionsBlancs;
		}
		else{
			return e.nbPionsNoirs;
		}
	}

	@Override
	public String getNom() {
		return "Eval0num2";
	}

}

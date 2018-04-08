package representationCo.eval0;

import representationCo.modele.EtatOthello;
import representationCo.eval0.Lambda;;


public class Eval0Lambda implements Eval0{
	
	public Eval0Lambda() {
		
	}

	/*
	 * Utilise la fonction Lambda pour evaluer la situation
	 */
	@Override
	public int eval0fonction(EtatOthello e) {
		return Lambda.getValeurBasique(e.getXDernierCoup(), e.getYDernierCoup());
	}

	@Override
	public String getNom() {
		return "Eval0Lambda";
	}

}

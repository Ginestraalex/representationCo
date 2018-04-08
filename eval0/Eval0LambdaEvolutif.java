package representationCo.eval0;

import representationCo.modele.EtatOthello;

public class Eval0LambdaEvolutif  implements Eval0{
	
	public Eval0LambdaEvolutif() {
		
	}

	/*
	 * Utilise la fonction Lambda pour evaluer la situation
	 */
	@Override
	public int eval0fonction(EtatOthello e) {
		return Lambda.getValeurEvolue(e.getXDernierCoup(), e.getYDernierCoup());
	}

	@Override
	public String getNom() {
		return "Eval0LambdaEvolutive";
	}

}

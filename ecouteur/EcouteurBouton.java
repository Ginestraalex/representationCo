package representationCo.ecouteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import representationCo.modele.PlateauDeJeu;

public class EcouteurBouton {
		
	public PlateauDeJeu modele;
	public ActionListener act;
	public int i;
	public int j;
	
	public EcouteurBouton(int x, int y, PlateauDeJeu mod){
		modele = mod;
		i = x;
		j = y;
		act = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modele.jouer(i, j);
			}
		};
	}
	
	public ActionListener getActionListener() {
		return act;
	}
}

package representationCo.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import representationCo.modele.EtatOthello;
import representationCo.modele.PlateauDeJeu;
import representationCo.view.Vue;

public class VueJeu extends JPanel implements Vue {
	private PlateauDeJeu modele;
	public JButton[][] buttons;
	
	public VueJeu(PlateauDeJeu mod) {
		super();
		modele = mod;
		this.buttons = new JButton[modele.getTaille()][modele.getTaille()];
		GridLayout grille = new GridLayout(mod.getTaille(),mod.getTaille());
		this.setLayout(grille);
	    init();
	    for (int x = 0 ; x < mod.getTaille() ; x++) {
	        for (int y = 0 ; y < mod.getTaille() ; y++) {
	        	this.add(buttons[x][y]);
	        }
	    }
		//mod.ajouterVue(this); probleme avec cette ligne
	}

	public void init() {
	    for (int x = 0 ; x < modele.getTaille() ; x++) {
	        for (int y = 0 ; y < modele.getTaille(); y++) {
	            buttons[x][y] = new JButton();
	            buttons[x][y].setPreferredSize(new Dimension(30, 30));
	        }
	    }
	}
	
	@Override
	public void maj() {
		// TODO Auto-generated method stub
	}

}

package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modele.EtatOthello;


import view.Vue;

public class VueJeu extends JPanel implements Vue {
	private EtatOthello mod;
	public JButton[][] buttons;
	
	public VueJeu(EtatOthello modele) {
		super();
		this.mod = modele;
		this.buttons = new JButton[mod.getTaille()][mod.getTaille()];
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
	    for (int x = 0 ; x < mod.getTaille() ; x++) {
	        for (int y = 0 ; y < mod.getTaille(); y++) {
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

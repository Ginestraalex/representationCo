package representationCo.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import representationCo.ecouteur.EcouteurBouton;
import representationCo.image.PictureFactory;
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
	    initialisation();
	    for (int x = 0 ; x < mod.getTaille() ; x++) {
	        for (int y = 0 ; y < mod.getTaille() ; y++) {
        			this.add(buttons[x][y]);
        			this.buttons[x][y].addActionListener(new EcouteurBouton(x, y, mod).getActionListener());
	        }
	    }
	    maj();
		//mod.ajouterVue(this); probleme avec cette ligne
	}

	public void initialisation() {
	    for (int x = 0 ; x < modele.getTaille() ; x++) {
	        for (int y = 0 ; y < modele.getTaille(); y++) {
	            buttons[x][y] = new JButton();
	            buttons[x][y].setPreferredSize(new Dimension(90, 90));
	        }
	    }
	}
	
	
	@Override
	public void maj() {
		for(int i = 0 ; i < buttons.length ; i++){
			for(int j = 0 ; j < buttons.length ; j++) {
				if(!modele.isEmpty(i, j)) {
					if(modele.getCouleur(i, j) == 'B'){
						buttons[i][j].setIcon(PictureFactory.pionBlanc);
					}
					else if(modele.getCouleur(i, j) == 'N'){
						buttons[i][j].setIcon(PictureFactory.pionNoir);
					}
				}
			}
		}
	}

}

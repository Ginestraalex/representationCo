package representationCo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import representationCo.ecouteur.EcouteurBouton;
import representationCo.image.PictureFactory;
import representationCo.modele.PlateauDeJeu;
import representationCo.view.Vue;

public class VueJeu extends JPanel implements Vue {
	private PlateauDeJeu modele;
	private GridLayout grille;
	public JButton[][] grilleBouton;
	
	public VueJeu(PlateauDeJeu mod) {
		super();
		modele = mod;
		grilleBouton = new JButton[modele.getTaille()][modele.getTaille()];
		grille = new GridLayout(mod.getTaille(),mod.getTaille());
		setLayout(grille);
	    initialisation();
	    for (int y = 0 ; y < mod.getTaille() ; y++) {
	        for (int x = 0 ; x < mod.getTaille() ; x++) {
        			add(grilleBouton[x][y]);
        			grilleBouton[x][y].addActionListener(new EcouteurBouton(x, y, mod).getActionListener());
	        }
	    }
	    maj();
		mod.ajouterVue(this);
	}

	/*
	 * initialise une nouvelle grille de jeu
	 */
	public void initialisation() {
	    for (int x = 0 ; x < modele.getTaille() ; x++) {
	        for (int y = 0 ; y < modele.getTaille(); y++) {
	            grilleBouton[x][y] = new JButton();
	            grilleBouton[x][y].setBackground(Color.BLUE);
	        }
	    }
	}
	
	
	@Override
	public void maj() {
		int taille = modele.getTaille();
		/* MAJ des dimensions du tableau */
		if(grilleBouton.length != taille) {
			removeAll();
			grilleBouton = new JButton[taille][taille];
			grille = new GridLayout(taille, taille);
			setLayout(grille);
		    initialisation();
		    for (int y = 0 ; y < taille ; y++) {
		        for (int x = 0 ; x < taille ; x++) {
	        			add(grilleBouton[x][y]);
	        			grilleBouton[x][y].addActionListener(new EcouteurBouton(x, y, modele).getActionListener());
		        }
			}
		}
		else {
			/* MAJ des valeur des cases */
			for(int i = 0 ; i < taille ; i++){
				for(int j = 0 ; j < taille ; j++) {
					grilleBouton[i][j].setText(" ");
					if(!modele.isEmpty(i, j)) {
						if(modele.getCouleur(i, j) == 'B'){
							grilleBouton[i][j].setIcon(PictureFactory.pionBlanc);
						}
						else if(modele.getCouleur(i, j) == 'N'){
							grilleBouton[i][j].setIcon(PictureFactory.pionNoir);
						}
						else if(modele.getCouleur(i, j) == 'J'){
							grilleBouton[i][j].setText("J");
						}
					}
				}
			}
		}
	}

}

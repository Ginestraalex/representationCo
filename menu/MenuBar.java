package representationCo.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import representationCo.modele.PlateauDeJeu;
import representationCo.view.Vue;

public class MenuBar extends JMenuBar implements Vue{
	
	public PlateauDeJeu modele;
	public JMenu paramPartie;
	public JMenuItem ajouterJoueur;
	public JMenuItem nouvellePartie;
	
	public MenuBar(PlateauDeJeu mod) {
		modele = mod;
		paramPartie = new JMenu("Parametres");
		ajouterJoueur = new JMenuItem("Ajouter Joueur");
		nouvellePartie = new JMenuItem("Nouvelle Partie");

		ajouterJoueur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.ajouterJoueur();
				modele.maj();
			}
		});
		
		nouvellePartie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] taillePossible = {"6", "8","10"};
				String taille = "8";
				taille = (String)JOptionPane.showInputDialog(null, "Choisissez la taille de la grille:", "Nouvelle partie", JOptionPane.QUESTION_MESSAGE, null, taillePossible, taillePossible[1]);
				if(taille != null) {
					modele.nouvellePartie(Integer.parseInt(taille));
					modele.maj();
				}
			}
		});
		
		paramPartie.add(ajouterJoueur);
		paramPartie.add(nouvellePartie);
		add(paramPartie);	
	}

	@Override
	public void maj() {
		// TODO Auto-generated method stub
		
	}
	
	
}

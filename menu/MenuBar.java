package representationCo.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import representationCo.eval0.Lambda;
import representationCo.modele.PlateauDeJeu;
import representationCo.view.Vue;

public class MenuBar extends JMenuBar implements Vue{
	
	public PlateauDeJeu modele;
	public JMenu paramPartie;
	public JMenu joueurs;
	public JMenu eval0;
	public JMenuItem ajouterJoueur;
	public JMenuItem nouvellePartie;
	public JMenuItem affichageScore;
	public JMenuItem jouerContreOrdi;
	public JMenuItem ordiContreOrdi;
	public JMenuItem activationMessage;
	public JMenuItem choixEval0;
	public JMenuItem affrontementEval0;
	public JMenuItem faireEvoluerEval0;
	
	
	public MenuBar(PlateauDeJeu mod) {
		modele = mod;
		paramPartie = new JMenu("Parametres");
		joueurs = new JMenu("Joueurs");
		eval0 = new JMenu("Eval0");
		
		ajouterJoueur = new JMenuItem("Ajouter Joueur");
		nouvellePartie = new JMenuItem("Nouvelle Partie");
		affichageScore = new JMenuItem("Afficher Score");
		jouerContreOrdi = new JMenuItem("Jouer contre l'ordinateur");
		ordiContreOrdi = new JMenuItem("Observer Ordinateur vs Ordinateur");
		activationMessage = new JMenuItem("(des)activer messages tours");
		activationMessage.setToolTipText("Permet d'activer/desactiver l'affichage des messages concernant le tour du joueur");
		choixEval0 = new JMenuItem("Choix eval0");
		affrontementEval0 = new JMenuItem("Evalutaion meilleure eval0");
		faireEvoluerEval0 = new JMenuItem("Faire evoluer eval0");

		ajouterJoueur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.ajouterJoueur();
				modele.nouvellePartie(modele.getTaille());
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
		
		affichageScore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Les scores sont:\n"
							+modele.getNomJoueur(0)+" score: "+modele.getScore(0)+" victoires\n"
							+modele.getNomJoueur(1)+" score: "+modele.getScore(1)+" victoires");
			}
		});
		
		jouerContreOrdi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modele.remplacerJoeurParOrdinateur();
				modele.nouvellePartie(modele.getTaille());
				modele.maj();
			}
		});
		
		ordiContreOrdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.ordinateurVsOrdinateur();
				modele.nouvellePartie(modele.getTaille());
				modele.maj();
			}
		});
		
		activationMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.activerDesactiverMessage();
			}
		});
		
		choixEval0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.setEval0();
			}
		});
		
		affrontementEval0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modele.comparaisonEval0();
			}
		});
		
		faireEvoluerEval0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lambda.modifAlea();
				modele.evolutionEval0();
			}	
		});
		
		joueurs.add(ajouterJoueur);
		paramPartie.add(nouvellePartie);
		joueurs.add(jouerContreOrdi);
		paramPartie.add(affichageScore);
		joueurs.add(ordiContreOrdi);
		paramPartie.add(activationMessage);
		paramPartie.add(choixEval0);
		eval0.add(affrontementEval0);
		eval0.add(faireEvoluerEval0);
		add(paramPartie);	
		add(joueurs);
		add(eval0);
	}

	@Override
	public void maj() {
		// TODO Auto-generated method stub
		
	}
	
	
}

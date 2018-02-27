package representationCo;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import representationCo.view.Vue;
import representationCo.view.VueJeu;
import representationCo.menu.MenuBar;
import representationCo.modele.PlateauDeJeu;


public class Jeu extends JFrame{
	
	private PlateauDeJeu modele;
	
	public Jeu(){
		/* frame settings */
		super("Reversi");
		setPreferredSize(new Dimension(1000,400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		modele = new PlateauDeJeu();
		VueJeu vuePlateau = new VueJeu(modele);
		MenuBar menu = new MenuBar(modele);
		this.setJMenuBar(menu);

		modele.ajouterVue(vuePlateau);
		modele.ajouterVue(menu);
		
		this.getContentPane().add(vuePlateau);
		this.add(vuePlateau,BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}
	
	public String toString(){
		return modele.toString();
	}
	
	public static void main(String[] args) {
		Jeu reversi = new Jeu();
		System.out.println(reversi.toString());
	}
}

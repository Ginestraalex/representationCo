package representationCo;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import representationCo.view.VueJeu;
import representationCo.menu.MenuBar;
import representationCo.modele.PlateauDeJeu;


public class Jeu extends JFrame{
	
	private PlateauDeJeu modele;
	
	public Jeu() throws UnsupportedLookAndFeelException{
		/* frame settings */
		super("Reversi");
		setPreferredSize(new Dimension(1000,400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		/* resoud le probleme d'affichage sous mac */
		UIManager.setLookAndFeel(new MetalLookAndFeel());

		
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
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		Jeu reversi = new Jeu();
		System.out.println(reversi.toString());
	}
}

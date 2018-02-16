import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import view.VueJeu;
import modele.EtatOthello;
import modele.Pion;


public class Jeu extends JFrame{
	private EtatOthello modele;
	
	public Jeu(){
		super("Reversi");
		setPreferredSize(new Dimension(1000,400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		modele = new EtatOthello();
		VueJeu lanceur = new VueJeu(modele);
		this.getContentPane().add(lanceur);
		this.add(lanceur,BorderLayout.CENTER);
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

package Juego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JLabel;

public class Cohete extends JLabel{
	int nivel = 1;
	String level = "1";
	boolean mov = false;
	public Cohete(){
		setText(level);
	}
	
	public void subirNivel(){
		
		nivel = nivel + 1;
		level = Integer.toString(nivel);
		setText(level);
	}
	
	protected void paintComponent(Graphics g) {	
		Graphics2D g2 = (Graphics2D) g;  
		 g2.drawRect(0, 0, 50,50);
		 g2.drawString(level, 25, 30);
	}	
}

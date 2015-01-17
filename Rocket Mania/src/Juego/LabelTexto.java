package Juego;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class LabelTexto extends JLabel {
	String str ="HOOOLA";
	int tam = 20;
	public LabelTexto(String str){
		this.str = str;
	}
	public void setTexto(String s){
		str = s;
	}
	public void setFont(int t){
		tam = t;
	}
	protected void paintComponent(Graphics g) {
		/*Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.setFont(new Font("Arial", Font.BOLD, 14));
		g2.drawString(str, 0, 0);
		System.out.println("hoooola");*/
		Graphics2D g2 = (Graphics2D) g;  
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Arial", Font.BOLD, tam));
		g2.drawString(str, 25, 30);
		
	}	
}

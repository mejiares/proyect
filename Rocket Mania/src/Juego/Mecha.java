package Juego;

import java.awt.Component;

import javax.swing.JButton;

public abstract class Mecha {
	int giro = 90;
	int pos = 0;
	boolean izquierda, derecha, arriba, abajo;
	
	/*
	public Mecha() {
			
	}
	
	public Component getBoton() {
		JButton vacio = new JButton();
		return vacio;
	}
	*/
	
	public abstract JButton getBoton();
	public abstract void giro();
	public abstract void vuelta();
	public abstract boolean iz();
	public abstract void setConecta(boolean b);
	public abstract boolean getConecta();
}

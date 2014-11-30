package Juego;

import java.awt.Component;

import javax.swing.JButton;

public abstract class Mecha {
	int giro = 90;
	int pos = 0;
	boolean izquierda, derecha, arriba, abajo;
	boolean uIzquierda, uDerecha, uArriba, uAbajo;
	boolean conectado;
	
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
	public abstract boolean der();
	public abstract boolean up();
	public abstract boolean down();
	public abstract void setUnidoIz(boolean b);
	public abstract void setUnidoDer(boolean b);
	public abstract void setUnidoUp(boolean b);
	public abstract void setUnidoDown(boolean b);
	public abstract boolean getUnidoIz();
	public abstract boolean getUnidoDer();
	public abstract boolean getUnidoUp();
	public abstract boolean getUnidoDown();
	public abstract void setConectaDer(boolean b);
	public abstract boolean getConectaDer();
	public abstract void setConectaIzq(boolean b);
	public abstract boolean getConectaIzq();
}
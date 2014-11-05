package Juego;

import java.awt.Component;

import javax.swing.JButton;

public class MechaCruz extends Mecha {
	BotonMechaCruz b = new BotonMechaCruz();
	boolean conecta;
	public MechaCruz(){
		conecta = false;
		izquierda = true;
		derecha = true;
		arriba = false;
		abajo = false;
	}
	
	public void vuelta (){
		
	}
	
	public boolean iz(){
		return izquierda;
	}
	
	public JButton getBoton() {
		return b;
	}
	
	public void giro(){
		b.gira();
		//b.repaint();
	}

	@Override
	public void setConecta(boolean b) {
		this.conecta = b;
		
	}

	@Override
	public boolean getConecta() {
		
		return this.conecta;
	}
	
}

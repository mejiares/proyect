package Juego;

import java.awt.Component;

public class MechaCruz extends Mecha {
	BotonMechaCruz b = new BotonMechaCruz();
	public MechaCruz(){
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
	
	public Component getBoton(){
		return b;
	}
	
	public void giro(){
		b.gira();
		//b.repaint();
	}
	
}

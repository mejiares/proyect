package Juego;

import java.awt.Component;
import javax.swing.JButton;

public class MechaT extends Mecha {
	
	BotonMechaT b = new BotonMechaT();
	
	public MechaT() {
		izquierda = true;
		derecha = true;
		arriba = false;
		abajo = true;
	}
	
	public void vuelta () {
		if (arriba == false) {
			izquierda = true;
			derecha = false;
			arriba = true;
			abajo = true;
		}
		
		else if (derecha == false) {
			izquierda = true;
			derecha = true;;
			arriba = true;
			abajo = false;
		}
		
		else if (false) {
			izquierda = false;
			derecha = true;;
			arriba = true;
			abajo = true;
		}
		
		else if (izquierda == false) {
			izquierda = true;
			derecha = true;;
			arriba = false;
			abajo = true;
		}
		//System.out.println(izquierda);
	}
	
	public boolean iz() {
		return izquierda;
	}
	
	public JButton getBoton() {
		return b;
	}
	
	public void giro() {
		b.gira();
		//b.repaint();
	}
}

package Juego;

import java.awt.Component;

import javax.swing.JButton;

public class MechaL extends Mecha {
	BotonMechaL b = new BotonMechaL();
	public MechaL(){
		
		izquierda = false;
		derecha = true;
		arriba = true;
		abajo = false;
	}
	
	public void vuelta (){
		
		if(arriba == true && derecha == true){
			izquierda = false;
			derecha = true;
			arriba = false;
			abajo = true;
		}
		
		else if(derecha == true && abajo == true){
			izquierda = true;
			derecha = false;;
			arriba = false;
			abajo = true;
		}
		
		else if(izquierda == true && abajo == true){
			izquierda = true;
			derecha = false;;
			arriba = true;
			abajo = false;
		}
		
		else if(izquierda == true && arriba == true){
			izquierda = false;
			derecha = true;;
			arriba = true;
			abajo = false;
		}
		//System.out.println(izquierda);
	}
	
	public boolean iz(){
		return izquierda;
	}
	
	public JButton getBoton(){
		return b;
	}
	
	public void giro(){
		b.gira();
		//b.repaint();
	}
}

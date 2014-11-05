package Juego;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MechaBarra extends Mecha {
	BotonMechaBarra b = new BotonMechaBarra();
	boolean conecta;
	public MechaBarra(){
		conecta = false;
		izquierda = true;
		derecha = true;
		arriba = false;
		abajo = false;
	}
	
	public void vuelta (){
		
		if(derecha == true){
			izquierda = false;
			derecha = false;
			arriba = true;
			abajo = true;
		}
		
		else if(arriba == true){
			izquierda = true;
			derecha = true;
			arriba = false;
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
	@Override
	public void setConecta(boolean b) {
		this.conecta = b;
		System.out.println("Paso 3");
		if(conecta == true){
			System.out.println("Paso 4");
			this.b.cambioAmarillo();
		}
		else
			this.b.cambioGris();
		
	}

	@Override
	public boolean getConecta() {
		
		return this.conecta;
	}
	
}

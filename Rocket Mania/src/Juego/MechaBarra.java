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
	boolean conectaDer;
	boolean conectaIzq;
	
	public MechaBarra(){
		conectaDer = false;
		conectaIzq = false;
		
		izquierda = true;
		derecha = true;
		arriba = false;
		abajo = false;
		
		uIzquierda = false;
		uDerecha = false;
		uArriba = false;
		uAbajo = false;
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
	}
	
	@Override
	public void setConectaDer(boolean b) {
		this.conectaDer = b;
		if(conectaDer == true){
			this.b.cambioAmarillo();
			if(conectaIzq==true){
				this.b.cambioNaranja();
			}
		}
		else if (conectaIzq == false){
			this.b.cambioGris();
		}
	}

	@Override
	public boolean getConectaDer() {
		
		return this.conectaDer;
	}
	
	public void setConectaIzq(boolean b) {
		this.conectaIzq = b;
		
		if(conectaIzq == true){
			System.out.println("pasa!!");
			this.b.cambioRojo();
			if(conectaDer==true){
				this.b.cambioNaranja();
			}
		}
		else if (conectaDer == false){
			this.b.cambioGris();
		}
					
	}
	
	@Override
	public boolean getConectaIzq() {

		return this.conectaIzq;
	}
	
	@Override
	public boolean der() {
		// TODO Auto-generated method stub
		return this.derecha;
	}

	@Override
	public boolean up() {
		// TODO Auto-generated method stub
		return this.arriba;
	}

	@Override
	public boolean down() {
		// TODO Auto-generated method stub
		return this.abajo;
	}

	@Override
	public void setUnidoIz(boolean b) {
		this.uIzquierda = b;
		
	}

	@Override
	public void setUnidoDer(boolean b) {
		this.uDerecha = b;
		
	}

	@Override
	public void setUnidoUp(boolean b) {
		this.uArriba = b;
		
	}

	@Override
	public void setUnidoDown(boolean b) {
		this.uAbajo = b;
		
	}

	@Override
	public boolean getUnidoIz() {
		return this.uIzquierda;
	}

	@Override
	public boolean getUnidoDer() {
		return this.uDerecha;
	}

	@Override
	public boolean getUnidoUp() {
		return this.uArriba;
	}

	@Override
	public boolean getUnidoDown() {
		return this.uAbajo;
	}


	
}
package Juego;

import java.awt.Component;

import javax.swing.JButton;

public class MechaCruz extends Mecha {
	BotonMechaCruz b = new BotonMechaCruz();
	boolean conectaDer;
	boolean conectaIzq;
	
	public MechaCruz(){
		conectaDer = false;
		conectaIzq = false;
		
		izquierda = true;
		derecha = true;
		arriba = true;
		abajo = true;
		
		uIzquierda = false;
		uDerecha = false;
		uArriba = false;
		uAbajo = false;
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
	}

	@Override
	public void setConectaDer(boolean b) {
		this.conectaDer = b;
		if(conectaDer == true){
			this.b.cambioAmarillo();
		}
		else
			this.b.cambioGris();		
	}

	@Override
	public boolean getConectaDer() {	
		return this.conectaDer;
	}
	
	@Override
	public void setConectaIzq(boolean b) {
		this.conectaIzq = b;
		if(conectaIzq == true){
			this.b.cambioNaranja();
		}
		else
			this.b.cambioGris();		
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
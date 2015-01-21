package Juego;

import java.awt.Component;

import javax.swing.JButton;

public class MechaT extends Mecha {
	
	BotonMechaT b = new BotonMechaT();
	boolean conectaDer;
	boolean conectaIzq;
	
	public MechaT() {
		conectaDer = false;
		conectaIzq = false;
		
		izquierda = true;
		derecha = true;
		arriba = false;
		abajo = true;
		
		uIzquierda = false;
		uDerecha = false;
		uArriba = false;
		uAbajo = false;
		
		conectado = false;
		revisado = false;
		anulado = false;
		
		usado = false;
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
		
		else if (abajo == false) {
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
	}

	@Override
	public void setConectaDer(boolean b) {
		this.conectaDer = b;
		if(conectaDer == true){
			this.b.cambioAmarillo();
			if(conectaIzq==true){
				this.b.cambioNaranja();
				conectado = true;
			}
			else{
				conectado = false;
			}
		}
		else if (conectaIzq == false){
			this.b.cambioGris();
			conectado = false;
		}
	}

	@Override
	public boolean getConectaDer() {
		return this.conectaDer;
	}
	
	@Override
	public void setConectaIzq(boolean b) {
this.conectaIzq = b;
		
		if(conectaIzq == true){
			//System.out.println("pasa!!");
			this.b.cambioRojo();
			if(conectaDer==true){
				this.b.cambioNaranja();
				conectado = true;
			}
			else{
				conectado = false;
			}
		}
		else if (conectaDer == false){
			this.b.cambioGris();
			conectado = false;
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

	@Override
	public void anular() {
		// TODO Auto-generated method stub
		b.setVacio();
		anulado = true;
	}

	@Override
	public void moneda(int val) {
		// TODO Auto-generated method stub
		coin = val;
		b.setMoneda(coin);
		usado = true;	
	}

	@Override
	public int cogida() {
		// TODO Auto-generated method stub
		usado = false;
		b.agarrada();
		int dev = coin;
		coin = 0;
		return dev;
	}
}
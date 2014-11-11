package Juego;

import java.awt.Component;

import javax.swing.JButton;

public class MechaL extends Mecha {
	BotonMechaL b = new BotonMechaL();
	boolean conecta;
	public MechaL(){
		conecta = false;
		
		izquierda = false;
		derecha = true;
		arriba = true;
		abajo = false;
		
		uIzquierda = false;
		uDerecha = false;
		uArriba = false;
		uAbajo = false;
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

	@Override
	public void setConecta(boolean b) {
		this.conecta = b;
		//System.out.println("Paso 3");
		if(conecta == true){
		//	System.out.println("Paso 4");
			this.b.cambioAmarillo();
		}
		else
			this.b.cambioGris();
		
	}

	@Override
	public boolean getConecta() {
		
		return this.conecta;
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

package Juego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BotonMechaBarra extends JButton {
	double miGiro = 0;
	int alto = 50;
	int ancho = 50;
	int valor = 0;
	int i = 0;
	boolean girar = false;
	boolean amarillo = false;
	boolean naranja = false;
	boolean rojo = false;
	boolean vacio= false;
	private String monedas = "";
	ImageIcon icon = new ImageIcon("src/Imagenes/barra.png");
	ImageIcon icon2 = new ImageIcon("src/Imagenes/barraA.png");
	ImageIcon icon3 = new ImageIcon("src/Imagenes/barraR.png");
	ImageIcon icon4 = new ImageIcon("src/Imagenes/barraN.png");
	
	ImageIcon icon5 = new ImageIcon("src/Imagenes/bronce.png");
	ImageIcon icon6 = new ImageIcon("src/Imagenes/plata.png");
	ImageIcon icon7 = new ImageIcon("src/Imagenes/oro.png");
	ImageIcon icon8 = new ImageIcon("src/Imagenes/platino.png");
	public BotonMechaBarra(){
		
		setIcon(icon);
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
	}
	
	public void setVacio(){
		amarillo = false;
		naranja = false;
		rojo = false;
		vacio = true;
	}
	public void cambioAmarillo(){
		amarillo = true;
		naranja = false;
		rojo = false;
	}
	
	public void cambioGris(){
		amarillo = false;
		naranja = false;
		rojo = false;
	}
	
	public void cambioNaranja(){
		amarillo = false;
		naranja = true;
		rojo = false;
	}
	
	public void cambioRojo(){
		amarillo = false;
		naranja = false;
		rojo = true;
	}
	
	public void setMoneda(int val){
		valor = val;
	}
	public void agarrada(){
		valor = 0;
	}
	
	public void gira() {
		//miGiro = 90/180*Math.PI;
		
		//miGiro = -miGiro;  
		
		miGiro = miGiro + Math.PI/(2*45); 
		
		//repaint();
		System.out.println("Boton barra gira:  " + miGiro);
	}
	
	protected void paintComponent(Graphics g) {	
		Image img = icon.getImage() ; 
		Image img2 = icon2.getImage() ;
		Image img3 = icon3.getImage();
		Image img4 = icon4.getImage();
		Image img5 = icon5.getImage() ; 
		Image img6 = icon6.getImage() ;
		Image img7 = icon7.getImage();
		Image img8 = icon8.getImage();
		Graphics2D g2 = (Graphics2D) g;  
        g2.rotate(miGiro, 25, 25);
        // Dibujado de la imagen
      if(vacio == true){
    	  
      						}
      else if(amarillo == true){
        g2.drawImage( img2, 0, 0, 50, 50, null );
        }
      else if(rojo ==true){
        g2.drawImage( img3, 0, 0, 50, 50, null );
  
        }
      else if(naranja == true){
    	  g2.drawImage( img4, 0, 0, 50, 50, null );
      }
       else{
        g2.drawImage( img, 0, 0, 50, 50, null );
      
       }
     if(valor == 1){
    	  g2.drawImage( img5, 0, 0, 50, 50, null );
		}
		else if(valor == 2){
			 g2.drawImage( img6, 0, 0, 50, 50, null );
		}
		else if(valor == 5){
			 g2.drawImage( img7, 0, 0, 50, 50, null );
		}
		else if(valor == 10){
			 g2.drawImage( img8, 0, 0, 50, 50, null );
		}
     
        i = i +1;
   
	}	
}
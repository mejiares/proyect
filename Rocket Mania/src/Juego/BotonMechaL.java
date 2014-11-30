package Juego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BotonMechaL extends JButton {
	double miGiro = 0;
	int alto = 50;
	int ancho = 50;
	int i = 0;
	boolean girar = false;
	boolean amarillo = false;
	boolean naranja = false;
	boolean rojo = false;
	ImageIcon icon = new ImageIcon("src/Imagenes/L.png");
	ImageIcon icon2 = new ImageIcon("src/Imagenes/LA.png");
	ImageIcon icon3 = new ImageIcon("src/Imagenes/LR.png");
	ImageIcon icon4 = new ImageIcon("src/Imagenes/LN.png");
	public BotonMechaL(){
		
		setIcon(icon);
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
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

	
	public void gira() {
		//miGiro = 90/180*Math.PI;
		//miGiro = -miGiro;  
		
		miGiro = miGiro + Math.PI/2; 
		
		//repaint();
		System.out.println("Boton L gira:  " + miGiro);
	}
	
	protected void paintComponent(Graphics g) {	
		Image img = icon.getImage() ; 
		Image img2 = icon2.getImage() ;
		Image img3 = icon3.getImage();
		Image img4 = icon4.getImage();
		Graphics2D g2 = (Graphics2D) g;  
        g2.rotate(miGiro, 25, 25);
        // Dibujado de la imagen
      if(amarillo == true){
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
        g2.drawRect(0, 0, 50,50);
        i = i +1;
        // System.out.println("Pasa2  " + i);
               
	}
}
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
	int i = 0;
	boolean girar = false;
	ImageIcon icon = new ImageIcon("Imagenes/barra.png");
	public BotonMechaBarra(){
		
		setIcon(icon);
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
	}
	
	public void gira() {
		//miGiro = 90/180*Math.PI;
		
		//miGiro = -miGiro;  
		
		miGiro = miGiro + Math.PI/2; 
		
		//repaint();
		System.out.println("Boton barra gira:  " + miGiro);
	}
	
	protected void paintComponent(Graphics g) {	
		Image img = icon.getImage() ; 
		Graphics2D g2 = (Graphics2D) g;  
        g2.rotate(miGiro, 25, 25);
        // Dibujado de la imagen
        
        g2.drawImage( img, 0, 0, 50, 50, null );
        
        //este solo se usa con la imagen que solo es una barra, sin fondo
        //g2.drawImage( img, 0, 20, 50, 10, null );
        g2.drawRect(0, 0, 50,50);
        i = i +1;
      //  System.out.println("Pasa2  " + i);
   
	}	
}

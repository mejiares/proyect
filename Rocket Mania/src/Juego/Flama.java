package Juego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Flama extends JLabel {
	int numero =1;
	ImageIcon icon = new ImageIcon("src/Imagenes/flama1.png");
	ImageIcon icon2 = new ImageIcon("src/Imagenes/flama2.png");
	ImageIcon icon3 = new ImageIcon("src/Imagenes/flama3.png");
	ImageIcon icon4 = new ImageIcon("src/Imagenes/flama4.png");
	ImageIcon icon5 = new ImageIcon("src/Imagenes/flama5.png");
	ImageIcon icon6 = new ImageIcon("src/Imagenes/flama6.png");
	ImageIcon icon7 = new ImageIcon("src/Imagenes/flama7.png");
	ImageIcon icon8 = new ImageIcon("src/Imagenes/flama8.png");
	ImageIcon icon9 = new ImageIcon("src/Imagenes/flama9.png");
	ImageIcon icon10 = new ImageIcon("src/Imagenes/flama10.png");
	public Flama(){
		
	}
	public void cambio(){
		numero = numero +1;
		if(numero > 10){
			numero = 1;
		}
		
	}
	protected void paintComponent(Graphics g) {	
		
		Image img = icon.getImage() ; 
		Image img2 = icon2.getImage() ;
		Image img3 = icon3.getImage() ;
		Image img4 = icon4.getImage() ;
		Image img5 = icon5.getImage() ;
		Image img6 = icon6.getImage() ;
		Image img7 = icon7.getImage() ;
		Image img8 = icon8.getImage() ;
		Image img9 = icon9.getImage() ;
		Image img10 = icon10.getImage() ;
		Graphics2D g2 = (Graphics2D) g;
		if(numero == 1){
			g2.drawImage( img, 0, 0, 50, 50, null );
		}
		else if (numero == 2){
			g2.drawImage( img2, 0, 0, 50, 50, null );
		}
		else if (numero == 3){
			g2.drawImage( img3, 0, 0, 50, 50, null );
				}
		else if (numero == 4){
			g2.drawImage( img4, 0, 0, 50, 50, null );
		}
		else if (numero == 5){
			g2.drawImage( img5, 0, 0, 50, 50, null );
		}
		else if (numero == 6){
			g2.drawImage( img6, 0, 0, 50, 50, null );
		}
		else if (numero == 7){
			g2.drawImage( img7, 0, 0, 50, 50, null );
		}
		else if (numero == 8){
			g2.drawImage( img8, 0, 0, 50, 50, null );
		}
		else if (numero == 9){
			g2.drawImage( img9, 0, 0, 50, 50, null );
		}
		else if (numero == 10){
			g2.drawImage( img10, 0, 0, 50, 50, null );
		}
		
		 

	}	
}

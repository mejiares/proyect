package Juego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Explosion extends JLabel {
	int numero =1;
	int x = 0;
	int y = 0;
	ImageIcon icon = new ImageIcon("src/Imagenes/exp1.png");
	ImageIcon icon2 = new ImageIcon("src/Imagenes/exp2.png");
	ImageIcon icon3 = new ImageIcon("src/Imagenes/exp3.png");
	ImageIcon icon4 = new ImageIcon("src/Imagenes/exp4.png");
	ImageIcon icon5 = new ImageIcon("src/Imagenes/exp5.png");
	ImageIcon icon6 = new ImageIcon("src/Imagenes/exp6.png");
	ImageIcon icon7 = new ImageIcon("src/Imagenes/exp7.png");
	ImageIcon icon8 = new ImageIcon("src/Imagenes/exp8.png");
	ImageIcon icon9 = new ImageIcon("src/Imagenes/exp9.png");
	ImageIcon icon10 = new ImageIcon("src/Imagenes/exp10.png");
	ImageIcon icon11 = new ImageIcon("src/Imagenes/exp11.png");
	ImageIcon icon12 = new ImageIcon("src/Imagenes/exp12.png");
	ImageIcon icon13 = new ImageIcon("src/Imagenes/exp13.png");
	ImageIcon icon14 = new ImageIcon("src/Imagenes/exp14.png");
	ImageIcon icon15 = new ImageIcon("src/Imagenes/exp15.png");
	public Explosion(int x, int y){
		this.x = x*50;
		this.y = y*50;
	}
	public void cambio(){
		numero = numero +1;
		if(numero > 17){
			numero = 1;
		}
		
	}
	
	public int getEx(){
		return x;
	}
	public int getYe(){
		return y;
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
		Image img11 = icon11.getImage() ;
		Image img12 = icon12.getImage() ;
		Image img13 = icon13.getImage() ;
		Image img14 = icon14.getImage() ;
		Image img15 = icon15.getImage() ;
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
		else if (numero == 11){
			g2.drawImage( img11, 0, 0, 50, 50, null );
		}
		else if (numero == 12){
			g2.drawImage( img12, 0, 0, 50, 50, null );
		}
		else if (numero == 13){
			g2.drawImage( img13, 0, 0, 50, 50, null );
		}
		else if (numero == 14){
			g2.drawImage( img14, 0, 0, 50, 50, null );
		}
		else if (numero == 15){
			g2.drawImage( img15, 0, 0, 50, 50, null );
		}
		
		 

	}	
}

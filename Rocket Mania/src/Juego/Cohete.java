package Juego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cohete extends JLabel{
	int nivel = 1;
	ImageIcon icon = new ImageIcon("src/Imagenes/rocket01.png");
	ImageIcon icon2 = new ImageIcon("src/Imagenes/rocket02.png");
	ImageIcon icon3 = new ImageIcon("src/Imagenes/rocket03.png");
	ImageIcon icon4 = new ImageIcon("src/Imagenes/rocket04.png");
	ImageIcon icon5 = new ImageIcon("src/Imagenes/rocket05.png");
	ImageIcon icon6 = new ImageIcon("src/Imagenes/rocket06.png");
	ImageIcon icon7 = new ImageIcon("src/Imagenes/rocket07.png");
	ImageIcon icon8 = new ImageIcon("src/Imagenes/rocket08.png");
	ImageIcon icon9 = new ImageIcon("src/Imagenes/rocket09.png");
	ImageIcon icon10 = new ImageIcon("src/Imagenes/rocket10.png");
	ImageIcon icon11 = new ImageIcon("src/Imagenes/exp2.png");
	String level = "1";
	boolean mov = false;
	public Cohete(){
		setText(level);
	}
	
	public void subirNivel(){
		
		nivel = nivel + 1;
		level = Integer.toString(nivel);
		setText(level);
	}
	
	protected void paintComponent(Graphics g) {	
		
		// g2.drawRect(0, 0, 50,50);
		// g2.drawString(level, 25, 30);
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
		
		Graphics2D g2 = (Graphics2D) g;  
		
		if(nivel == 1){
			g2.drawImage( img, 0, 0, 50, 50, null );
		}
		else if (nivel == 2){
			g2.drawImage( img2, 0, 0, 50, 50, null );
		}
		else if (nivel == 3){
			g2.drawImage( img3, 0, 0, 50, 50, null );
				}
		else if (nivel == 4){
			g2.drawImage( img4, 0, 0, 50, 50, null );
		}
		else if (nivel == 5){
			g2.drawImage( img5, 0, 0, 50, 50, null );
		}
		else if (nivel == 6){
			g2.drawImage( img6, 0, 0, 50, 50, null );
		}
		else if (nivel == 7){
			g2.drawImage( img7, 0, 0, 50, 50, null );
		}
		else if (nivel == 8){
			g2.drawImage( img8, 0, 0, 50, 50, null );
		}
		else if (nivel == 9){
			g2.drawImage( img9, 0, 0, 50, 0, null );
		}
		else if (nivel == 10){
			g2.drawImage( img10, 0, 0, 50, 50, null );
		}
		if(mov == true){
			g2.drawImage( img11, -5, 33, 20, 20, null );
		}
	}	
}

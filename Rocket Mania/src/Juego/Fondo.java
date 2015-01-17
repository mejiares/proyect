package Juego;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Fondo extends JLabel {
	ImageIcon icon = new ImageIcon("src/Imagenes/daysky.jpg");
	ImageIcon icon2 = new ImageIcon("src/Imagenes/nightsky.jpg");
	ImageIcon icon3 = new ImageIcon("src/Imagenes/city.png");
	ImageIcon icon4 = new ImageIcon("src/Imagenes/city2.png");
	int tipo = 0;
	float op = 0f;
	public Fondo(int tipo, float op){
		this.tipo = tipo;
		this.op = op;
	}
	public void setOpacity(float f){
		op = f;
	}
	protected void paintComponent(Graphics g) {
		Image img = icon.getImage() ;
		Image img2 = icon2.getImage() ;
		Image img3 = icon3.getImage() ;
		Image img4 = icon4.getImage() ;
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, op));
		if(tipo == 0){
		g2.drawImage( img, 0, 0, 536, 450, null);
		}
		else if(tipo == 1){
			g2.drawImage( img2, 0, 0, 536, 450, null);
		}
		else if(tipo == 2){
			g2.drawImage( img3, 0, 0, 536, 100, null);
		}
		else if(tipo == 3){
			g2.drawImage( img4, 0, 0, 536, 100, null);
		}
	}	
}

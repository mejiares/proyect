package Juego;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

public class Ventana extends JFrame implements  MouseListener {
	JPanel w = new JPanel();
	int dimension = 10;
	int y = 0;
	int x = 0;
	private JButton[][] matrizBotones;
	private Mecha[][] matrizMechas;
	public Ventana(){
		setSize(50*dimension,50*dimension);
		//w.setLayout(new GridLayout(dimension, dimension));
		w.setLayout(null);
		add(w);
		//MechaBarra b = new MechaBarra();
		//w.add(b.getBoton());
		this.matrizMechas = new Mecha[dimension][dimension];
		this.matrizBotones = new JButton[dimension][dimension];
		//this.matrizBotones = new BotonMechaBarra[dimension][dimension];
		for (int i = 0; i < this.dimension; i++){
			for (int j = 0; j < this.dimension; j++){
				//if(random == 0)
				Random r = new Random();
				int ran = r.nextInt(4) ;
				if (ran == 0) {
					MechaBarra m = new MechaBarra();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					//this.matrizMechas[i][j] = new MechaBarra();
					//this.matrizBotones[i][j] = ((MechaBarra) matrizMechas[i][j]).getBoton();
					matrizBotones[i][j].setBounds(i*50, j*50, 50, 50);
			
				}
				else if (ran == 1) {
					//this.matrizMechas[i][j] = new MechaCruz();
					//this.matrizBotones[i][j] = matrizMechas[i][j].getBoton();
					//matrizBotones[i][j].setBounds(i*50, j*50, 50, 50);
					MechaL m = new MechaL();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i*50, j*50, 50, 50);
			
				}
				else if (ran == 2) {
					//this.matrizMechas[i][j] = new MechaCruz();
					//this.matrizBotones[i][j] = ((MechaCruz) matrizMechas[i][j]).getBoton();
					//matrizBotones[i][j].setBounds(i*50, j*50, 50, 50);
					MechaCruz m = new MechaCruz();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i*50, j*50, 50, 50);
				}
				else if (ran == 3) {
					MechaT m = new MechaT();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i*50, j*50, 50, 50);
				}
				
				w.add(matrizBotones[i][j]);
				matrizBotones[i][j].addMouseListener(this);
				System.out.println(ran);
				//this.matrizBotones[i][j] = new BotonMechaBarra();
				//Creamos un panel en el que metemos todos los botones que se han creado
				//le agregamos el listener a cada uno de lo botones	
			}
		} 
	}
	
	
	public static void main(String[] args) {
		Ventana v = new Ventana();
		v.setVisible(true);
	}
		
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		
		for (int i = 0; i < this.dimension; i++){
			for (int j = 0; j < this.dimension; j++){
				if(e.getSource()== matrizBotones[i][j])
				{
					try {
					((MechaBarra) matrizMechas[i][j]).giro();
					((MechaBarra) matrizMechas[i][j]).vuelta();
					System.out.println(((MechaBarra) matrizMechas[i][j]).iz());
					w.repaint();
					//((BotonMechaBarra) matrizBotones[i][j]).gira();
					System.out.println("HOLA Barra");
					}
					catch(Exception ex) {
						try {
						((MechaL) matrizMechas[i][j]).giro();
						((MechaL) matrizMechas[i][j]).vuelta();
						System.out.println(((MechaL) matrizMechas[i][j]).iz());
						w.repaint();
						//((BotonMechaBarra) matrizBotones[i][j]).gira();
						System.out.println("HOLA L");
						}
						catch(Exception exe) {
							try {
								((MechaCruz) matrizMechas[i][j]).giro();
								((MechaCruz) matrizMechas[i][j]).vuelta();
								System.out.println(((MechaCruz) matrizMechas[i][j]).iz());
								w.repaint();
								//((BotonMechaBarra) matrizBotones[i][j]).gira();
								System.out.println("HOLA Cruz");
							}
							catch(Exception exec) {
								((MechaT) matrizMechas[i][j]).giro();
								((MechaT) matrizMechas[i][j]).vuelta();
								System.out.println(((MechaT) matrizMechas[i][j]).iz());
								w.repaint();
								//((BotonMechaBarra) matrizBotones[i][j]).gira();
								System.out.println("HOLA T");
							}
						}
					}
				}
			}
		}	
	}
	
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
	}
}

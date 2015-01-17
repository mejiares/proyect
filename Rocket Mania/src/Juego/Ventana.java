package Juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.events.MouseEvent;


/** Clase principal del juego RocketMania - Prog III
 * Ventana principal del juego.
 * @author David Mejía, Aitor Delgado, uncal Fdez. de Casadevante
 * @version 1.4
 * Facultad de Ingeniería - Universidad de Deusto (2014)
 */

public class Ventana extends JFrame implements  MouseListener, Runnable {
	
	private static final long serialVersionUID = -1728805850944908776L;
	static String nombre = "Jugador 1";
	//private static JTextField txtJugador;
	JPanel w = new JPanel();
	JPanel f = new JPanel();
	JLayeredPane pane = new JLayeredPane();
	JLabel palo = new JLabel();
	JLabel barra = new JLabel();
	LabelTexto obj = new LabelTexto("");
	LabelTexto niv = new LabelTexto("Nivel: 1");
	LabelTexto punt = new LabelTexto("0");
	LabelTexto mon = new LabelTexto("Monedas: 0");
	Fondo fondo = new Fondo(1, 1f);
	Fondo fondo2 = new Fondo(0, 0f);
	JLabel suelo = new JLabel();
	Fondo ciudad = new 	Fondo(3, 1f);
	Fondo ciudad2 = new Fondo(2, 0f);
	JFrame hasPerdido = new JFrame();
	JFrame sigNivel = new JFrame();
	JLabel mensaje = new JLabel();
	JButton continuar = new JButton("Aceptar");
	ImageIcon ama = new ImageIcon("src/Imagenes/barraA.png");
	float opa = 0f;
	int dimension = 10;
	int y = 0;
	int x = 0;
	int cont =0;
	int cohetes=0;
	int monedas = 0;
	int objetivo = 10;
	static int puntuacion = 0;
	static int nivel = 1;
	int puntero = 0;
	double time = 10000;
	//double time = 130000;
	boolean estado = false;
	boolean reset = false;
	boolean pasa = false;
	boolean listo = true;
	boolean aceptar = false;
	boolean cronometro = true;
	JLabel temporizador;
    Thread hilo;
    boolean temporizadorActivo;
	private JButton[][] matrizBotones;
	private Mecha[][] matrizMechas;
	private Cohete[] matrizCohetes;
	private JLabel[] matrizFlamas;
	private ArrayList<Explosion> listaExp;

	public Ventana(){
		setLayout(null);
		setSize(750,700);
		setResizable(false);
		setVisible(false);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		
		pane.setLayout(null);
		pane.setSize(1000, 1000);
		pane.setBounds(0, 0, 1000, 1000);
		//w.setLayout(new GridLayout(dimension, dimension));
		palo.setIcon(new ImageIcon("src/Imagenes/palo1.jpg"));
		w.setLayout(null);
		w.setBounds(200, 200, 500, 400);
		//w.setBackground(Color.black);
		w.setOpaque(false);
		//w.setVisible(false);
		palo.setBounds(205,195,56,410);
		f.setLayout(null);
		f.setBounds(160, 200, 50, 400);
		fondo.setBounds(160, 0, 536, 450);
		fondo2.setBounds(160, 0, 536, 450);
		suelo.setIcon(new ImageIcon("src/Imagenes/ground.jpg"));
		suelo.setBounds(172, 500, 536, 100);
		barra.setIcon(new ImageIcon("src/Imagenes/barra.jpg"));
		barra.setBounds(0, 0, 161, 600);
		ciudad.setBounds(160, 400, 536, 150);
		ciudad2.setBounds(160, 400, 536, 150);
		obj.setTexto(String.valueOf(objetivo));
		obj.setBounds(-5, 240, 50, 30);
		mon.setBounds(-5, 180, 200, 50);
		mon.setFont(12);
		punt.setBounds(-5, 120, 200, 50);
		punt.setFont(12);
		niv.setBounds(-5, 70, 200, 50);
		niv.setFont(12);
		add(pane);
		
		//Temporizador
		temporizador = new JLabel( "  00:00:000" );
	    temporizador.setFont( new Font( Font.SERIF, Font.BOLD, 50 ) );
	    temporizador.setHorizontalAlignment( JLabel.RIGHT );
	    temporizador.setForeground( Color.WHITE );
	    temporizador.setBounds(50, 50, 500, 100);
	//  temporizador.setBackground( Color.WHITE );
	//  temporizador.setOpaque( true );
	    pane.add(temporizador, 3, 0);
	//TODO: preguntar david panel pane	
		
		pane.add(barra, 1, 0);
		pane.add(fondo, 1, 0);
		pane.add(fondo2, 1, 0);
		pane.add(suelo, 1, 0);
		pane.add(ciudad, 1, 0);
		pane.add(ciudad2, 1, 0);
		pane.add(obj, 2, 0);
		pane.add(punt, 2, 0);
		pane.add(niv, 2, 0);
		pane.add(mon, 2, 0);
		pane.add(w, 2, 0);
		pane.add(palo, 3, 0);
		pane.add(f, 3, 0);
		
		
		
		//(f);
		//add(palo);
		//add(w);
		this.matrizMechas = new Mecha[dimension][dimension];
		this.matrizBotones = new JButton[dimension][dimension];
		this.matrizCohetes = new Cohete[dimension];
		this.matrizFlamas = new JLabel[dimension];
		this.listaExp = new ArrayList<Explosion>();
		for (int i = 1; i < this.dimension-1; i++){
			for (int j = 0; j < this.dimension; j++){
				//if(random == 0)
				Random r = new Random();
				int ran = r.nextInt(10) ;
			/*	if (j<3 && j>0) {
					MechaBarra m = new MechaBarra();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i*50, j*50, 50, 50);

				}
				else*/ if (ran  < 4) {
					MechaBarra m = new MechaBarra();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i*50, j*50-50, 50, 50);

				}
				else if (ran < 7) {
					MechaL m = new MechaL();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i*50, j*50-50, 50, 50);

				}
				else if (ran <8) {
					MechaCruz m = new MechaCruz();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i*50, j*50-50, 50, 50);
				}
				else if (ran < 10) {
					MechaT m = new MechaT();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i*50, j*50-50, 50, 50);
				}
				else if (ran < 11) {
					MechaVacio m = new MechaVacio();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i*50, j*50-50, 50, 50);
				}
				 		
				w.add(matrizBotones[i][j]);
				matrizBotones[i][j].addMouseListener(this);
				//System.out.println(ran);
				//this.matrizBotones[i][j] = new BotonMechaBarra();
				//Creamos un panel en el que metemos todos los botones que se han creado
				//le agregamos el listener a cada uno de lo botones	
				//rev Conecta derecha es la propagación amarilla
				if(i==1 && j>0 && j<dimension-1){
					//System.out.println("Paso 1");
					if(matrizMechas[i][j].iz()==true){
						//System.out.println("Paso 2");
						matrizMechas[i][j].setConectaDer(true);
					}
					else {
						matrizMechas[i][j].setConectaDer(false);
					}
				}
				if(i==dimension-2 && j>0 && j<dimension-1){
					if(matrizMechas[i][j].der()==true){
						//System.out.println("Paso 2");
						matrizMechas[i][j].setConectaIzq(true);
					}
					else {
						matrizMechas[i][j].setConectaIzq(false);
					}
				}
				if(j == 9){
					matrizBotones[i][j].setVisible(false);
					
				}
			}
		}
		
		
		
		for (int i = 0; i<8; i++ ){
			Cohete c = new Cohete();
			this.matrizCohetes[i]= c;
			this.matrizCohetes[i].setBounds(450, i*50, 50, 50);
			w.add(this.matrizCohetes[i]);
		}
		for (int i = 0; i<8; i++ ){
			Flama fu = new Flama();
			this.matrizFlamas[i]= fu;
			this.matrizFlamas[i].setBounds(0, i*50, 50, 50);
			f.add(this.matrizFlamas[i]);
			f.repaint();
			
			
		}
		
		hasPerdido.add(mensaje, BorderLayout.NORTH);
		hasPerdido.add(continuar, BorderLayout.SOUTH);
		hasPerdido.setVisible(false);
		continuar.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(estado == false){
					System.out.println("Puntuación: ");
					System.exit(ABORT);
				}
				else {
					double temp = monedas/5;
					int temp2 = (int)Math.floor(temp);
					monedas= monedas - temp2*5;
					for(int i=0; i<temp2;i++){
						((Cohete) matrizCohetes[puntero]).subirNivel();
						puntero = puntero + 1;
						if(puntero > 7){
							puntero = 0;
						}
					}
					
					//w.repaint();
					objetivo = 0;
					nivel = nivel + 1;
					objetivo = 9 + nivel;
					if (time <30001) {
						ciudad.setOpacity(1f);
						ciudad2.setOpacity(0f);
						fondo.setOpacity(1f);
						fondo2.setOpacity(0f);
					}
					
					time = 130000;
					mon.setTexto("Monedas: "+String.valueOf(monedas));
					mon.repaint();
					obj.setTexto(String.valueOf(objetivo));
					obj.repaint();
					niv.setTexto("Nivel: "+String.valueOf(nivel));
					niv.repaint();
					cronometro = true;
								
					//TODO: estos metodos de que sirven si tambn estan en el main?

					hasPerdido.setVisible(false);
					setVisible(true);
					animaFlamas();
					tiempo();
					iniciarTemporizador();
					System.out.println(monedas);
				}	
			}	 
		});
	}
	
	public static void main(String[] args) throws InterruptedException {
		Ventana v = new Ventana();
		portada(v);
		empezar(v);
		
			
		while (v.aceptar == false) {
			
			if (v.aceptar == true) {
				
					v.tiempo();
					v.iniciarTemporizador();
					v.animaFlamas();
					v.ciclo();
					
			}
		}
	}
	
	public static void portada(Ventana v){
		
		JFrame por = new JFrame();
		
		por.setLayout(null);
		
		JPanel Panel0 = new JPanel();		
		por.getContentPane().setLayout(null);
		Panel0.setBorder(new EmptyBorder(5, 5, 5, 5));
		por.add(Panel0);	
		//por.getContentPane().setBackground(Color.YELLOW);
		
		//por.setVisible(true);
		por.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		por.setSize(750, 562);
	//	por.setResizable(false);
		por.setLocationRelativeTo(null);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 750, 562);
		//lblNewLabel.setIcon(new ImageIcon("src/Imagenes/portada.jpg"));
		lblFondo.setIcon(new ImageIcon("src/Imagenes/fondoPortada.jpg"));
		
		JLabel lblPortada = new JLabel("");
		lblPortada.setBounds(125, 0, 500, 234);
		//lblPortada.setIcon(new ImageIcon("src/Imagenes/portada.jpg"));
		lblPortada.setIcon(new ImageIcon("src/Imagenes/Portada.png"));
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(235, 290, 140, 52);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Cambria", Font.BOLD, 26));
		por.getContentPane().add(lblNombre);
		
		//Texto
		JTextField txtJugador = new JTextField();
		txtJugador.setForeground(Color.WHITE);
		//txtJugador.setBackground(Color.WHITE);		
		txtJugador.setText(getNombre());
		txtJugador.setFont(new Font("Cambria", Font.BOLD, 20));
		txtJugador.setColumns(10);	
		txtJugador.setBounds(422, 289, 120, 52);
		txtJugador.setOpaque(false); 		// Fondo Transparente (los gráficos son png transparentes)
		txtJugador.setBorder(null);           	// No considerar el borde
		txtJugador.setHorizontalAlignment(JTextField.CENTER);
		por.getContentPane().add(txtJugador);
		
		
		//JLabel texto
		JLabel lblText = new JLabel("");
		lblText.setBounds(410, 290, 140, 52);
		lblText.setIcon(new ImageIcon("src/Imagenes/box.png"));
		por.getContentPane().add(lblText);
		
		//Botón Jugar
		JButton btnJugar = new JButton("");
		btnJugar.setIcon(new ImageIcon("src/Imagenes/aceptar1.png"));
		btnJugar.setBounds(200, 370, 140, 52);
		btnJugar.setOpaque(false); 		// Fondo Transparente (los gráficos son png transparentes)
		btnJugar.setContentAreaFilled(false);// No rellenar el área
		btnJugar.setBorderPainted(false);  	// No pintar el borde
    	btnJugar.setBorder(null);           	// No considerar el borde (el botón se hace sólo del tamaño del gráfico)
    	btnJugar.setRolloverIcon(new ImageIcon("src/Imagenes/aceptar2.png")); // Pone imagen de rollover
    	btnJugar.setPressedIcon(new ImageIcon("src/Imagenes/aceptar3.png")); // Pone imagen de click		
		btnJugar.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				por.setVisible(false);
				v.setVisible(true);
				v.aceptar = true;			
			}
		});
		por.getContentPane().add(btnJugar);	
		
		//Botón Borrar
		JButton btnBorrar = new JButton("");
		btnBorrar.setIcon(new ImageIcon("src/Imagenes/cancelar1.png"));
		btnBorrar.setBounds(410, 370, 140, 52);
		btnBorrar.setOpaque(false); 		// Fondo Transparente (los gráficos son png transparentes)
		btnBorrar.setContentAreaFilled(false);// No rellenar el área
		btnBorrar.setBorderPainted(false);  	// No pintar el borde
		btnBorrar.setBorder(null);           	// No considerar el borde (el botón se hace sólo del tamaño del gráfico)         	
		btnBorrar.setRolloverIcon(new ImageIcon("src/Imagenes/cancelar2.png")); // Pone imagen de rollover
		btnBorrar.setPressedIcon(new ImageIcon("src/Imagenes/cancelar3.png")); // Pone imagen de click
   		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtJugador.setText("");
			}
		});
		por.getContentPane().add(btnBorrar);
		
		//botón Ranking puntuaciones
		JButton btnRanking = new JButton("");
		btnRanking.setIcon(new ImageIcon("src/Imagenes/ranking1.png"));
		btnRanking.setBounds(200, 450, 140, 52);
		btnRanking.setOpaque(false); 		// Fondo Transparente (los gráficos son png transparentes)
		btnRanking.setContentAreaFilled(false);// No rellenar el área
		btnRanking.setBorderPainted(false);  	// No pintar el borde
		btnRanking.setBorder(null);           	// No considerar el borde (el botón se hace sólo del tamaño del gráfico)
		btnRanking.setRolloverIcon(new ImageIcon("src/Imagenes/ranking2.png")); // Pone imagen de rollover
		btnRanking.setPressedIcon(new ImageIcon("src/Imagenes/ranking3.png")); // Pone imagen de click		
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
							
			}
		});
		por.getContentPane().add(btnRanking);
		
		//botón Salir
		JButton btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon("src/Imagenes/salir1.png"));
		btnExit.setBounds(410, 450, 140, 52);
		btnExit.setOpaque(false); 		// Fondo Transparente (los gráficos son png transparentes)
		btnExit.setContentAreaFilled(false);// No rellenar el área
		btnExit.setBorderPainted(false);  	// No pintar el borde
		btnExit.setBorder(null);           	// No considerar el borde (el botón se hace sólo del tamaño del gráfico)
		btnExit.setRolloverIcon(new ImageIcon("src/Imagenes/salir2.png")); // Pone imagen de rollover
		btnExit.setPressedIcon(new ImageIcon("src/Imagenes/salir3.png")); // Pone imagen de click		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(ABORT);			
			}
		});
		por.getContentPane().add(btnExit);
		
		
		por.getContentPane().add(lblPortada);
		por.getContentPane().add(lblFondo);	
		por.repaint();
		por.setVisible(true);
	}

	//Devuelve el valor de nombre
	public static String getNombre () {
		return nombre;
	}
	
	public static void empezar(Ventana v){
		boolean comienzo = false;
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(comienzo == false){
			v.revIzquierda();
			v.revArriba();
			v.revAbajo();
			v.revDerecha();
			v.revIzquierda();
			int has = 0;
			do {
			v.revConectaDer();
			v.revConectaIzq();
			//v.revConectaIzq();
			has = has + 1;
			//System.out.println(has);
			}
			while(has < 16);
			comienzo = true;
		}
	}
	
	public void animaFlamas(){
		(new Thread() {
			@Override
			public void run() {
				while(cronometro==true){
					try {
						Thread.sleep(85);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					for(int i=0; i<8; i++){
						((Flama) matrizFlamas[i]).cambio();
					}
					
					
				}
			}
		}).start();

	}
	
	public void tiempo(){
		(new Thread() {
			@Override
			public void run() {
				while(cronometro==true){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					f.repaint();
					palo.repaint();
					time = time - 1;
//					System.out.println(time);
					if(time<30001){
					opa = opa + 1/30000f;
					fondo.setOpacity(1-opa);
					fondo2.setOpacity(opa);
					ciudad.setOpacity(1-opa);
					ciudad2.setOpacity(opa);
					fondo.repaint();
					fondo2.repaint();
					ciudad.repaint();
					ciudad2.repaint();
					}
					if(time == 0){
						
						cronometro = false;
						pararTemporizador();
						estado = false;
						
						FinPartida ventana = new FinPartida (); 
						ventana.setVisible(true);
						
						//setVisible(false);
						
						
						mensaje.setText("Ha perdido");
						//setVisible(false);
						hasPerdido.setVisible(true);
						
					}
					if(objetivo <= 0){
						cronometro = false;
						pararTemporizador();
						estado = true;
						mensaje.setText("Ha pasado de nivel");
						setVisible(false);
						hasPerdido.setVisible(true);
					}
				}
			}
		}).start();

	}
	
	public void revArriba(){
		
		for (int i = 1; i < this.dimension-1; i++){
			for (int j = 0; j < this.dimension; j++){

				if(j>0&&j<dimension-1 && i>0&&i<dimension-1){

					if(matrizMechas[i][j-1].down() == true && matrizMechas[i][j].up()==true ){

						matrizMechas[i][j].setUnidoUp(true);
						//System.out.println(matrizMechas[i][j-1].down()+"pasa bool de "+j);
					}

					else {
						matrizMechas[i][j].setUnidoUp(false);
						//System.out.println(matrizMechas[i][j-1].down()+"NO pasa bool de "+j);
					}
				}	
			}
		}	
	}

	public void revAbajo() {

		for (int i = 1; i < this.dimension-1; i++){
			for (int j = 0; j < this.dimension; j++){

				if(j>0&&j<dimension-1 && i>0&&i<dimension-1){

					if(matrizMechas[i][j+1].up() == true && matrizMechas[i][j].down()==true ){

						matrizMechas[i][j].setUnidoDown(true);
						//System.out.println(matrizMechas[i][j-1].down()+"pasa bool de "+j);
					}

					else {
						matrizMechas[i][j].setUnidoDown(false);
						//System.out.println(matrizMechas[i][j-1].down()+"NO pasa bool de "+j);
					}
				}	
			}
		}
	}

	public void revDerecha(){
		
		for (int i = 1; i < this.dimension-1; i++){
			
			for (int j = 0; j < this.dimension; j++){
				
				if(j>0&&j<dimension-1 && i>0&&i<dimension-1){
					
					if(i<dimension-2 && matrizMechas[i+1][j].iz() == true && matrizMechas[i][j].der()==true ){

						matrizMechas[i][j].setUnidoDer(true);
						//System.out.println(matrizMechas[i][j-1].down()+"pasa bool de "+j);
					}
				
					else {
						matrizMechas[i][j].setUnidoDer(false);
						//System.out.println(matrizMechas[i][j-1].down()+"NO pasa bool de "+j);
					}
				}	
			}
		}
	}
	
	public void revIzquierda(){
		
		for (int i = 1; i < this.dimension-1; i++){
			
			for (int j = 0; j < this.dimension; j++){
				
				if(j>0&&j<dimension-1 && i>0&&i<dimension-1){
					
					
					if(i>1 && matrizMechas[i-1][j].der() == true && matrizMechas[i][j].iz()==true ){

						matrizMechas[i][j].setUnidoIz(true);
						//System.out.println(matrizMechas[i][j-1].down()+"pasa bool de "+j);
					}
					
					else {
						matrizMechas[i][j].setUnidoIz(false);
						//System.out.println(matrizMechas[i][j-1].down()+"NO pasa bool de "+j);
					}
				}	
			}
		}
	}
		
	public void revConectaDer(){
		
		for (int i = 1; i < this.dimension-1; i++){
			
			for (int j = 0; j < this.dimension; j++){
				
				if (j>0 &&j<dimension-1 && i>0 && i<dimension-1){
//					System.out.println("Entra conecta");
//					if (i==1 && matrizMechas[i][j].iz()==false){
//						matrizMechas[i][j].setConecta(false);
//					}				
					
					if(i>1 && matrizMechas[i-1][j].getConectaDer() == true && matrizMechas[i][j].getUnidoIz() == true) {

						matrizMechas[i][j].setConectaDer(true);
					}
					
					else if(matrizMechas[i][j-1].getConectaDer() == true && matrizMechas[i][j].getUnidoUp() == true) {
						matrizMechas[i][j].setConectaDer(true);
						
					}

					else if (matrizMechas[i][j+1].getConectaDer() == true && matrizMechas[i][j].getUnidoDown() == true) {

						matrizMechas[i][j].setConectaDer(true);
						//System.out.println("Pasa abajo");
						//System.out.println("i "+i+" j "+j);
						
					}

					else if (i< dimension-2 && matrizMechas[i+1][j].getConectaDer() == true && matrizMechas[i][j].getUnidoDer() == true) {

						matrizMechas[i][j].setConectaDer(true);
						//System.out.println("Pasa der");
						//System.out.println("i "+i+" j "+j);
					}

					else if(i>1 && matrizMechas[i-1][j].getConectaDer() == true && matrizMechas[i][j].getUnidoIz() == true) {

						matrizMechas[i][j].setConectaDer(true);
					}


					else{
						if(i==1){
							if(matrizMechas[i][j].iz()==false && matrizMechas[i][j].getUnidoUp()==false){
								if(matrizMechas[i][j].getUnidoUp()==false){
									if(matrizMechas[i][j].getUnidoIz()==false){
										if(matrizMechas[i][j].getUnidoDer()==false){
											matrizMechas[i][j].setConectaDer(false);
										}
									}
								}
							}																
						}

						else{
							matrizMechas[i][j].setConectaDer(false);
						}
					}
				}	
			}
		}
	}
	
	public void revConectaIzq(){
		
		for (int i = 1; i < this.dimension-1; i++){
			for (int j = 0; j < this.dimension; j++){	
						
				if (j>0 &&j<dimension-1 && i>0 && i<dimension-1) {				
					
					if(i>1 && matrizMechas[i-1][j].getConectaIzq() == true && matrizMechas[i][j].getUnidoIz() == true) {
						matrizMechas[i][j].setConectaIzq(true);
					}
					
					else if (matrizMechas[i][j-1].getConectaIzq() == true && matrizMechas[i][j].getUnidoUp() == true) {
						matrizMechas[i][j].setConectaIzq(true);						
					}

					else if (matrizMechas[i][j+1].getConectaIzq() == true && matrizMechas[i][j].getUnidoDown() == true) {
						matrizMechas[i][j].setConectaIzq(true);
						//System.out.println("Pasa abajo");
						//System.out.println("i "+i+" j "+j);					
					}

					else if (i<dimension -2 && matrizMechas[i+1][j].getConectaIzq() == true && matrizMechas[i][j].getUnidoDer() == true) {
						matrizMechas[i][j].setConectaIzq(true);
						//System.out.println("Pasa izq");
						//System.out.println("i "+i+" j "+j);
					}

					else if(i>1 && matrizMechas[i-1][j].getConectaIzq() == true && matrizMechas[i][j].getUnidoIz() == true) {
						matrizMechas[i][j].setConectaIzq(true);
					}

					else{
						if(i==dimension-2){
							if(matrizMechas[i][j].der()==false && matrizMechas[i][j].getUnidoUp()==false){
								if(matrizMechas[i][j].getUnidoUp()==false){
									if(matrizMechas[i][j].getUnidoIz()==false){
										if(matrizMechas[i][j].getUnidoDer()==false){
											matrizMechas[i][j].setConectaIzq(false);
										}
									}
								}
							}																
						}

						else{
							matrizMechas[i][j].setConectaIzq(false);
						}
					}
				}	
			}
		}
	} 
		
	//Pone en false los booleanos de conexión que antes estaban en true
	public void borrarConecta(){
		for (int i = 1; i < this.dimension-1; i++){
			for (int j = 0; j < this.dimension; j++){
				if (j>0 &&j<dimension-1 && i>0 && i<dimension-1){					
					matrizMechas[i][j].setConectaDer(false);
					matrizMechas[i][j].setConectaIzq(false);
				}
			}
		}
	}
	
	public void fuente(){
		for(int j= 0; j<dimension; j++){
			if(j>0 && j<dimension-1){
				//System.out.println("Paso 1");
				if(matrizMechas[1][j].iz()==true){
					//	System.out.println("Paso 2");
					matrizMechas[1][j].setConectaDer(true);
				}
				else{
					matrizMechas[1][j].setConectaDer(false);
				}
			}
		}	
	}
	public void fuenteRoja(){
		for(int j= 0; j<dimension; j++){
			if(j>0 && j<dimension-1){
				
				if(matrizMechas[8][j].der()==true){
					//System.out.println("Paso "+j);
					matrizMechas[8][j].setConectaIzq(true);
					
				}
				else{
					matrizMechas[8][j].setConectaIzq(false);
				}
			}
		}
	}
	
	public void borrarMechas() throws InterruptedException{
		Thread.sleep( 700 );
		cohetes = 0;
		for (int j = 0; j < this.dimension; j++){
			if(j>0 &&j<dimension-1){
				
				if(matrizMechas[8][j].conectado==true && matrizMechas[8][j].der()==true){
					//if(cont>50){cont = 0;}
					//if(cont<51){cont = cont + 1;}
					if(matrizMechas[8][j].revisado==false){
						cohetes = cohetes +1;
						matrizCohetes[j-1].mov = true;
						puntuacion = puntuacion + matrizCohetes[j-1].nivel*100;
						objetivo = objetivo -1;
						listo = false;
						matrizMechas[8][j].revisado=true;
						System.out.println("Cohetes: "+cohetes);
					}
				}
			}
		}
		
		obj.setTexto(String.valueOf(objetivo));
		punt.setTexto("Puntuación: "+ String.valueOf(puntuacion));
		obj.repaint();
		punt.repaint();
		
		//System.out.println(cont);
		//Si el boton esta naranja le pone su boolean anulado a true y esto borra la imagen y lo prepara para su eliminación
			//System.out.println(cont);
			for (int i = 1; i < this.dimension-1; i++){
				for (int j = 0; j < this.dimension; j++){
					if (j>0 &&j<dimension-1 && i>0 && i<dimension-1){					
						if(matrizMechas[i][j].conectado==true){
							monedas = monedas + matrizMechas[i][j].cogida();
							matrizMechas[i][j].anular();
							Explosion e= new Explosion(i, j-1);
							
							listaExp.add(e);
							//w.repaint();
							
						}
					}
				}
			}
			mon.setTexto("Monedas: "+String.valueOf(monedas));
			mon.repaint();
			
			(new Thread() {
				@Override
				public void run() {
					
					for(int z = 0; z<101; z++){
						for(int i=0; i<8; i++){
							if(matrizCohetes[i].mov==true){
								matrizCohetes[i].setBounds(450+z, (i*50)-z, 50, 50);
								if(z == 100){
									matrizCohetes[i].setBounds(450, i*50, 50, 50);
									matrizCohetes[i].mov=false;
								}
							}
							//w.repaint();
						}
						try {
							Thread.sleep(3);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
						
				}
			}).start();
		
		// un while con el número de imagenes que consiste la explosión he irlos cambiando en aquellos anulados, en el boton 
	}
	
	public void crearBoton(int i, int j){
		Random r = new Random();
		int ran = r.nextInt(10) ;
		Mecha m = null;
		if (ran  < 4) {
			m = new MechaBarra();
		}else if (ran < 7) {
			m = new MechaL();
		} else if (ran <8) {
			m = new MechaCruz();
		} else { //if (ran < 10) {
			m = new MechaT();
		}
		this.matrizMechas[i][j] = m;
		this.matrizBotones[i][j] = m.getBoton();
		matrizBotones[i][j].setBounds(i*50, j*50-50, 50, 50);
		matrizBotones[i][j].addMouseListener(this);
		w.add(matrizBotones[i][j]);
		//System.out.println(ran);
		
	}
	
	public void rellenar() throws InterruptedException{
		int cont2 = 0;
		//Aqui busca todas aquellas mechas que tienen el boolean anulado en true y pone la mecha y el botón de esa casilla en null
		for (int i = 1; i < this.dimension-1; i++){
			for (int j = 0; j < this.dimension; j++){
				if(j<dimension-1){
					if(matrizMechas[i][j].anulado==true){
						matrizMechas[i][j] = null;
						
					}
					
				}
			}
		}

		for(int i=0; i<listaExp.size(); i++){
			listaExp.get(i).setBounds(((Explosion) listaExp.get(i)).getEx(), ((Explosion) listaExp.get(i)).getYe(), 50, 50);
			w.add(listaExp.get(i));
		}
		
		for(int i=0; i<16; i++){
			for(int z=0; z<listaExp.size(); z++){
				 listaExp.get(z).cambio();
				 
				w.repaint();
				
			}
			Thread.sleep(30);
		}
		
		listaExp.clear();
		int p =0;
		boolean hay = true;
		while(p<9){
			
			p=p+1;
			for(int z=0; z<5; z++){
				for(int i=1; i<9; i++){
					boolean caida = false;
					hay = false;
					for(int j=8; j>-1; j--){
						if(j<8 && matrizMechas[i][j+1]==null){
							pasa=true;
							caida = true;
							hay = true;
						}
						if(matrizMechas[i][j]!=null &&caida==true){
							int pixels = matrizBotones[i][j].getY();
							
							matrizBotones[i][j].setBounds(i*50, pixels+10, 50, 50);
							Thread.sleep(5);
							//System.out.println(matrizBotones[i][j].getX()+"  "+matrizBotones[i][j].getY());
							//w.repaint();
						}
						
					}
				}
				
			}
			
			//System.out.println("pasa "+ p);
			for(int i=1; i<9; i++){
				boolean caida = false;
				for(int j=8; j>-1; j--){
					if(j<8 && matrizMechas[i][j+1]==null){
						caida = true;
					}
					if(matrizMechas[i][j]!=null &&caida==true){
						matrizMechas[i][j+1] = matrizMechas[i][j];
						matrizBotones[i][j+1] = matrizMechas[i][j].getBoton();
						matrizMechas[i][j] = null;
						
					}
					if(j==0&& caida==true){
						crearBoton(i,0);
					}
					
				}
			}
		
		}
		if(pasa==true){
			w.removeAll(); 
			for(int i = 1; i<dimension-1; i++){
				for(int j=0; j<dimension; j++){
					w.add(matrizBotones[i][j]);
				}
			}
			for(int i = 0; i<8; i++){
				w.add(matrizCohetes[i]);
			}
			borrarConecta();
			fuente();
			fuenteRoja();
			revIzquierda();
			revArriba();
			revAbajo();
			revDerecha();
			int has = 0;
			do {
				revConectaDer();
				revConectaIzq();
				
				has = has + 1;
				//System.out.println(has);
			} while(has < 16);
			has = 0;
			insertarMonedas(cohetes);
			if(cohetes>0){
				System.out.println("monedas: "+monedas);
			}
			w.repaint();
			
			pasa=false;
			listo = true;
		}
	
		
	}
	
	public void ciclo() throws InterruptedException{
		
		
		while(true){
			borrarMechas();
			rellenar();
			
		}
	}
	
	public void insertarMonedas(int c){
		Random ran = new Random();
		int i = 0;
		int j = 0;
		boolean u = true;
		i = ran.nextInt(8) +1 ;
		j = ran.nextInt(8) +1 ;
		System.out.println(i+" "+j+" "+c);
		if(cohetes == 3){
			while(u ==true){
				i = ran.nextInt(8) +1 ;
				j = ran.nextInt(8) +1 ;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(1);
			u = true;
			while(u ==true){
				i = ran.nextInt(8) +1 ;
				j = ran.nextInt(8) +1 ;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(2);
		}
		else if(cohetes == 4){
			while(u ==true){
				i = ran.nextInt(8) +1 ;
				j = ran.nextInt(8) +1 ;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(5);
			
		}
		
		else if(cohetes == 5){
			while(u ==true){
				i = ran.nextInt(8) +1 ;
				j = ran.nextInt(8) +1 ;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			
		}
		
		else if(cohetes == 6){
			while(u ==true){
				i = ran.nextInt(8) +1 ;
				j = ran.nextInt(8) +1 ;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(5);
			u = true;
			while(u ==true){
				i = ran.nextInt(8) +1 ;
				j = ran.nextInt(8) +1 ;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			
		}
		
		else if(cohetes == 7){
			while(u ==true){
				i = ran.nextInt(8) +1 ;
				j = ran.nextInt(8) +1 ;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			u =true;
			while(u ==true){
				i = ran.nextInt(8) +1 ;
				j = ran.nextInt(8) +1 ;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			
		}
		
		else if(cohetes == 8){
			while(u ==true){
				i = ran.nextInt(8) +1 ;
				j = ran.nextInt(8) +1 ;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			u= true;
			while(u ==true){
				
				i = ran.nextInt(8) +1 ;
				j = ran.nextInt(8) +1 ;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			u =true;
			while(u ==true){
				i = ran.nextInt(8) +1 ;
				j = ran.nextInt(8) +1 ;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			
		}
		
	}
	
	public void vueltas(int i, int j) throws InterruptedException{
		int pass = 0;
		listo = false;
		while(pass<45){
			
			matrizMechas[i][j].giro();
			matrizBotones[i][j].repaint();
			pass = pass +1;
			Thread.sleep(4);
		}
		System.out.println("Aquí");
		matrizMechas[i][j].vuelta();
		if(i==1 && j>0 && j<dimension-1){
			//System.out.println("Paso 1");
			if(matrizMechas[i][j].iz()==true){
				//	System.out.println("Paso 2");
				matrizMechas[i][j].setConectaDer(true);
			}
			else{
				matrizMechas[i][j].setConectaDer(false);
			}
		}
		
		if(i==dimension-2  && j<dimension-1){
			//System.out.println("Paso 1");
			if(matrizMechas[i][j].der()==true){
				//	System.out.println("Paso 2");
				matrizMechas[i][j].setConectaIzq(true);
			}
			else{
				matrizMechas[i][j].setConectaIzq(false);
			}
		}
		revIzquierda();
		revArriba();
		revAbajo();
		revDerecha();
		int has = 0;
		do {
			revConectaDer();
			revConectaIzq();
			
			has = has + 1;
			//System.out.println(has);
		} while(has < 16);
		has = 0;
		listo = true;
		w.repaint();
	}

		private int ti, tj;
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {

		for (int i = 1; i < this.dimension-1; i++){
			for (int j = 0; j < this.dimension; j++){
				if(matrizBotones[i][j]!=null){
					if(e.getSource()== matrizBotones[i][j]&&listo==true) {
						ti = i;
						tj = j;
						System.out.println("LISTENER "+i+"  "+j);
						borrarConecta();
						fuente();
						fuenteRoja();
						//matrizMechas[i][j].giro();
						//listo = false;
							
						//System.out.println(i+"  "+j);
						System.out.println(listo);
						//System.out.println( matrizMechas[i][j].iz());
						(new Thread() {
							@Override
							public void run() {
								try {
									System.out.println(ti+"  "+tj);
									vueltas(ti,tj);
									//listo = false;
									//System.out.println(i+"  "+j);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}).start();
	
						
						
						//System.out.println(matrizMechas[i][j].down());
						//System.out.println("Empieza");
						//System.out.println("iz "+matrizMechas[i][j].getUnidoIz());
						//System.out.println("der "+matrizMechas[i][j].getUnidoDer());
						//System.out.println("ab "+matrizMechas[i][j].getUnidoDown());
						//System.out.println("ar "+matrizMechas[i][j].getUnidoUp());
						//w.repaint();					
					}
				}
			}
		}	
	}
	
	//Sonido bomba
		public void musicaLoose () {
			
			Clip music = null;
			
			try {
				music = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			File a = new File("src/Resources/blowup.wav");
			
			try {
				music.open(AudioSystem.getAudioInputStream(a));
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			music.start();
			
		}

		//Sonido victoria
		public void musicaWin () {
			
			Clip music = null;
			
			try {
				music = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			File a = new File("src/Resources/TADA.wav");
			
			try {
				music.open(AudioSystem.getAudioInputStream(a));
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			music.start();
			
		}
		
	    //Iniciar el temporizador poniendo temporizadorActivo en verdadero para que entre en el while
//	    public void iniciarTemporizador() {
//	        
//	       // hilo = new Thread( this );
//	       // hilo.start();
//	    }
	   
		//Esto es para parar el temporizador
	    public void pararTemporizador(){
	        temporizadorActivo = false;
	    }
	    
	    //Temporizador
	    public void iniciarTemporizador(){
	    	(new Thread() {
	    		@Override
	    		public void run() {
	    			temporizadorActivo = true;
	    			Integer minutos = 2 , segundos = 10, milesimas = 100;
	    			//min es minutos, seg es segundos y mil es milesimas de segundo
	    			String min="", seg="", mil="";
	    			try {
	    				//Mientras temporizadorActivo sea verdadero entonces seguira aumentando el tiempo
	    				while (temporizadorActivo) {
	    					Thread.sleep( 10 );
	    					//Incrementamos 4 milesimas de segundo
	    					milesimas -= 10;

	    					//Cuando llega a 1000 osea 1 segundo aumenta 1 segundo y las milesimas de segundo de nuevo a 0
	    					if( milesimas == 0 )
	    					{
	    						milesimas = 1000;
	    						segundos -= 1;
	    						//Si los segundos llegan a 60 entonces aumenta 1 los minutos
	    						//y los segundos vuelven a 0
	    						if( segundos == -1 )
	    						{
	    							segundos = 59;
	    							minutos--;
	    						}
	    					}

	    					//Esto solamente es estetica para que siempre este en formato 00:00:000
	    					if( minutos < 10 ) min = "0" + minutos;
	    					else min = minutos.toString();
	    					if( segundos < 10 ) seg = "0" + segundos;
	    					else seg = segundos.toString();

	    					if( milesimas < 10 ) mil = "00" + milesimas;
	    					else if( milesimas < 100 ) mil = "0" + milesimas;
	    					else mil = milesimas.toString();
	    					System.out.println(minutos + " : " + segundos + " : " + milesimas);
	    					//Colocamos en la etiqueta la informacion
	    					temporizador.setText( "  " + min + ":" + seg + ":" + mil );                

	    					if (minutos<=0 && segundos<=0 && milesimas<=10) {
	    						temporizadorActivo = false;
	    					}
	    				}

	    			} catch(Exception e){}
	    			//Cuando se reincie se coloca nuevamente en 00:00:000
	    			temporizador.setText( "  00:00:000" );

	    		}
	    	}).start();
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public static int getPuntuacion() {
		return puntuacion;
	}

	public static int getNivel() {
		return nivel;
	}
}
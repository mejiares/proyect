package Juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
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

/**
 * Clase principal del juego RocketMania - Prog III Ventana principal del juego.
 * 
 * @author David Mejía, Aitor Delgado, Juncal Fdez. de Casadevante
 * @version 2.0 Facultad de Ingeniería - Universidad de Deusto (2014)
 */

public class Ventana extends JFrame implements MouseListener, Runnable {

	private static final long serialVersionUID = -1728805850944908776L;
	static String nombre = "Jugador 1";
	boolean primero = false;
	static Puntuaciones ventanaRanking = new Puntuaciones();
	JPanel w = new JPanel();
	JPanel f = new JPanel();
	JLayeredPane pane = new JLayeredPane();
	JLabel palo = new JLabel();
	JLabel barra = new JLabel();
	JLabel suelo = new JLabel();
	LabelTexto obj = new LabelTexto("");
	LabelTexto niv = new LabelTexto("Nivel: 1");
	LabelTexto punt = new LabelTexto("0");
	LabelTexto mon = new LabelTexto("Monedas: 0");
	Fondo fondo = new Fondo(1, 1f);
	Fondo fondo2 = new Fondo(0, 1f);
	Fondo ciudad = new Fondo(3, 1f);
	Fondo ciudad2 = new Fondo(2, 1f);
	//Si pierdes o pasas de nivel
	JFrame VentanaFin = new JFrame();
	JLabel lbl1 = new JLabel();
	JLabel lbl2 = new JLabel();
	JLabel lbl3 = new JLabel();
	JLabel lbl4;
	JLabel lbl5;
	JLabel lbl6;
	JButton btnNewPartida = new JButton();
	JButton btnSalir = new JButton();
	JButton continuar = new JButton("Aceptar");
	JButton btnPause = new JButton();
	ImageIcon ama = new ImageIcon("src/Imagenes/barraA.png");
	float opa = 0f;
	int dimension = 10;
	int y = 0;
	int x = 0;
	int cont = 0;
	int cohetes = 0;
	int monedas = 10;
	int objetivo = 10;
	static int puntuacion = 0;
	int nivel = 1;
	int puntero = 0;
	double time = 130000;
	double k = 1;	
	boolean estado = false;
	boolean reset = false;
	boolean pasa = false;
	boolean listo = true;
	boolean aceptar = false;
	boolean pausa = false;
	boolean gallo = false;
	static boolean cronometro = true;
	JLabel temporizador;
	Thread hilo;
	boolean temporizadorActivo;
	private JButton[][] matrizBotones;
	private Mecha[][] matrizMechas;
	private Cohete[] matrizCohetes;
	private JLabel[] matrizFlamas;
	private ArrayList<Explosion> listaExp;

	public Ventana() {
		//Layout null en todos los paneles y frames para poder colocar los objetos donde queramos
		setLayout(null);
		setSize(700, 630);
		setResizable(false);
		setVisible(false);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		//Panel de capas para poder superponer cosas sobre otras y que al repintarse no se solapen
		pane.setLayout(null);
		pane.setSize(1000, 1000);
		//Lo metemos a la ventana principal
		pane.setBounds(0, 0, 1000, 1000);
		//Vamos creando y poniendo valores en los distintos objetos de la ventana principal
		palo.setIcon(new ImageIcon("src/Imagenes/palo1.jpg"));
		//Panel principal donde esta el tablero de juego
		w.setLayout(null);
		w.setBounds(200, 200, 500, 400);
		//Fondos transparentes
		w.setOpaque(false);
		palo.setBounds(205, 195, 56, 410);
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
		obj.setFont(new Font("Cambria", Font.BOLD, 12));
		mon.setBounds(-5, 180, 200, 50);
		mon.setFont(new Font("Cambria", Font.BOLD, 12));
		punt.setBounds(-5, 120, 200, 50);
		punt.setFont(12);
		niv.setBounds(-5, 75, 200, 50);
		niv.setFont(new Font("Cambria", Font.BOLD, 12));
		add(pane);

		// Temporizador
		temporizador = new JLabel("  00:00:000");
		temporizador.setFont(new Font(Font.SERIF, Font.BOLD, 50));
		temporizador.setHorizontalAlignment(JLabel.RIGHT);
		temporizador.setForeground(Color.WHITE);
		temporizador.setBounds(50, 50, 500, 100);
		
		// Botón Pausa
		btnPause.setIcon(new ImageIcon("src/Imagenes/pausa1.png"));
		btnPause.setBounds(10, 290, 140, 52);
		btnPause.setOpaque(false); // Fondo Transparente (los gráficos son png transparentes)
		btnPause.setContentAreaFilled(false);// No rellenar el área
		btnPause.setBorderPainted(false); // No pintar el borde
		btnPause.setBorder(null); // No considerar el borde (el botón se hace solo del tamaño del gráfico)
		btnPause.setRolloverIcon(new ImageIcon("src/Imagenes/pausa2.png")); // Pone imagen de  rollover
		btnPause.setPressedIcon(new ImageIcon("src/Imagenes/pausa3.png")); // Pone imagen de click
		btnPause.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("click!!!");
				if (pausa == false) {
					pausa = true;
				}
				else if (pausa == true)
				{
					pausa = false;			
				}
			}	 
		});
		
		pane.add(temporizador, 3, 0);
		//Como es un layered pane hay que decir que va sobre que
		//Agregamos todos los objetos al layered pane
		pane.add(barra, 1, 0);
		pane.add(fondo2, 1, 0);
		pane.add(fondo, 1, 0);
		pane.add(suelo, 1, 0);
		pane.add(ciudad2, 1, 0);
		pane.add(ciudad, 1, 0);
		pane.add(btnPause, 2, 0);
		pane.add(obj, 2, 0);
		pane.add(punt, 2, 0);
		pane.add(niv, 2, 0);
		pane.add(mon, 2, 0);
		pane.add(w, 2, 0);
		pane.add(palo, 3, 0);
		pane.add(f, 3, 0);
		
		// Inicializamos todas la matrices que hacen funcionar al juego
		//W:
		this.matrizMechas = new Mecha[dimension][dimension];
		this.matrizBotones = new JButton[dimension][dimension];
		this.matrizCohetes = new Cohete[dimension];
		this.listaExp = new ArrayList<Explosion>();
		
		this.matrizFlamas = new JLabel[dimension];
		
		//Con estos fors metemos todos los botones y mechas al panel w 
		for (int i = 1; i < this.dimension - 1; i++) {
			for (int j = 0; j < this.dimension; j++) {
				//Es en base a la probabilidad deque salga un cierto número en random que agregamos botones y mechas
				Random r = new Random();
				int ran = r.nextInt(10);
				
				 if (ran < 3) {
					MechaBarra m = new MechaBarra();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i * 50, j * 50 - 50, 50, 50);

				} else if (ran < 7) {
					MechaL m = new MechaL();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i * 50, j * 50 - 50, 50, 50);

				} else if (ran < 8) {
					MechaCruz m = new MechaCruz();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i * 50, j * 50 - 50, 50, 50);
				} else if (ran < 10) {
					MechaT m = new MechaT();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i * 50, j * 50 - 50, 50, 50);
				} else if (ran < 11) {
					MechaVacio m = new MechaVacio();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i * 50, j * 50 - 50, 50, 50);
				}
				 // despues de calcular que tipo de mecha y de boton se agregará añadimos al panel y creamos el listener correspondiente
				w.add(matrizBotones[i][j]);
				matrizBotones[i][j].addMouseListener(this);
				// Si es borde y esta conectada al límite izquierdo se le pone un valor conectado true al conecta correspondiente
				if (i == 1 && j > 0 && j < dimension - 1) {
					// System.out.println("Paso 1");
					if (matrizMechas[i][j].iz() == true) {
						// System.out.println("Paso 2");
						matrizMechas[i][j].setConectaDer(true);
					} else {
						matrizMechas[i][j].setConectaDer(false);
					}
				}
				// Lo mismo con el borde derecho
				if (i == dimension - 2 && j > 0 && j < dimension - 1) {
					if (matrizMechas[i][j].der() == true) {
						// System.out.println("Paso 2");
						matrizMechas[i][j].setConectaIzq(true);
					} else {
						matrizMechas[i][j].setConectaIzq(false);
					}
				}
				//Hacemos invisible la fila 9 ya que con esta no se jugará
				if (j == 9) {
					matrizBotones[i][j].setVisible(false);

				}
			}
		}
		//Aqui creamos y añadimos todos los cohetes
		for (int i = 0; i < 8; i++) {
			Cohete c = new Cohete();
			this.matrizCohetes[i] = c;
			this.matrizCohetes[i].setBounds(450, (i * 50)-25, 50, 50);
			w.add(this.matrizCohetes[i]);
		}
		//Aqui creamos y añadimos todas las flamas
		for (int i = 0; i < 8; i++) {
			Flama fu = new Flama();
			this.matrizFlamas[i] = fu;
			this.matrizFlamas[i].setBounds(0, i * 50, 50, 50);
			f.add(this.matrizFlamas[i]);
			f.repaint();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Ventana v = new Ventana();
		portada(v);
		empezar(v);
		v.sonido("src/Sounds/Welcome.wav");
		
//while que espera que el juego comience 
		while (v.aceptar == false) {

			if (v.aceptar == true) {

				v.control();
				v.iniciarTemporizador();
				v.animaFlamas();
				v.ciclo();
			}
		}
	}

	public static void portada(Ventana v) {

		JFrame por = new JFrame();
		por.setResizable(false);
		por.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		por.setSize(750, 562);
		por.setLocationRelativeTo(null);

		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 750, 562);
		lblFondo.setIcon(new ImageIcon("src/Imagenes/fondoPortada.jpg"));

		JLabel lblPortada = new JLabel("");
		lblPortada.setBounds(125, 0, 500, 234);
		lblPortada.setIcon(new ImageIcon("src/Imagenes/Portada.png"));

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(235, 290, 140, 52);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Cambria", Font.BOLD, 26));
		por.getContentPane().add(lblNombre);

		// Texto
		JTextField txtJugador = new JTextField();
		txtJugador.setForeground(Color.WHITE);
		txtJugador.setText(v.getNombre());
		txtJugador.setFont(new Font("Cambria", Font.BOLD, 20));
		txtJugador.setColumns(10);
		txtJugador.setBounds(422, 289, 120, 52);
		txtJugador.setOpaque(false); // Fondo Transparente (los gráficos son png transparentes)
		txtJugador.setBorder(null); // No considerar el borde
		txtJugador.setHorizontalAlignment(JTextField.CENTER);
		por.getContentPane().add(txtJugador);

		// JLabel texto
		JLabel lblText = new JLabel("");
		lblText.setBounds(410, 290, 140, 52);
		lblText.setIcon(new ImageIcon("src/Imagenes/box.png"));
		por.getContentPane().add(lblText);

		// Botón Jugar
		JButton btnJugar = new JButton("");
		btnJugar.setIcon(new ImageIcon("src/Imagenes/aceptar1.png"));
		btnJugar.setBounds(200, 370, 140, 52);
		btnJugar.setOpaque(false); // Fondo Transparente (los gráficos son png transparentes)
		btnJugar.setContentAreaFilled(false);// No rellenar el área
		btnJugar.setBorderPainted(false); // No pintar el borde
		btnJugar.setBorder(null); // No considerar el borde (el botón se hace solo del tamaño del gráfico)
		btnJugar.setRolloverIcon(new ImageIcon("src/Imagenes/aceptar2.png")); // Pone imagen de  rollover
		btnJugar.setPressedIcon(new ImageIcon("src/Imagenes/aceptar3.png")); // Pone imagen de click
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//nombre = txtJugador.getText();
				setNombre(txtJugador.getText());
				por.setVisible(false);
				v.setVisible(true);
				v.aceptar = true;
				v.sonido("src/Sounds/Ready.wav");
			}
		});
		por.getContentPane().add(btnJugar);

		// Botón Borrar
		JButton btnBorrar = new JButton("");
		btnBorrar.setIcon(new ImageIcon("src/Imagenes/borrar1.png"));
		btnBorrar.setBounds(410, 370, 140, 52);
		btnBorrar.setOpaque(false); // Fondo Transparente (los gráficos son png transparentes)
		btnBorrar.setContentAreaFilled(false);// No rellenar el área
		btnBorrar.setBorderPainted(false); // No pintar el borde
		btnBorrar.setBorder(null); // No considerar el borde (el botón se hace solo del tamaño del gráfico)
		btnBorrar.setRolloverIcon(new ImageIcon("src/Imagenes/borrar2.png")); // Pone  imagen de rollover
		btnBorrar.setPressedIcon(new ImageIcon("src/Imagenes/borrar3.png")); // Pone imagen de click
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtJugador.setText("");
			}
		});
		por.getContentPane().add(btnBorrar);

		// botón Ranking puntuaciones
		JButton btnRanking = new JButton("");
		btnRanking.setIcon(new ImageIcon("src/Imagenes/ranking1.png"));
		btnRanking.setBounds(200, 450, 140, 52);
		btnRanking.setOpaque(false); // Fondo Transparente (los gráficos son png transparentes)
		btnRanking.setContentAreaFilled(false);// No rellenar el área
		btnRanking.setBorderPainted(false); // No pintar el borde
		btnRanking.setBorder(null); // No considerar el borde (el botón se hace sólo del tamaño del gráfico)
		btnRanking.setRolloverIcon(new ImageIcon("src/Imagenes/ranking2.png")); // Pone imagen de rollover
		btnRanking.setPressedIcon(new ImageIcon("src/Imagenes/ranking3.png")); // Pone imagen de click
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//Actualizamos y mostramos el ranking de mejores jugadores
					ventanaRanking.actualizar();
					//v.setVisible(false);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		por.getContentPane().add(btnRanking);

		// botón Salir
		JButton btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon("src/Imagenes/salir1.png"));
		btnExit.setBounds(410, 450, 140, 52);
		btnExit.setOpaque(false); // Fondo Transparente (los gráficos son png transparentes)
		btnExit.setContentAreaFilled(false);// No rellenar el área
		btnExit.setBorderPainted(false); // No pintar el borde
		btnExit.setBorder(null); // No considerar el borde (el botón se hace sólo del tamaño del gráfico)
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

	public void finPartida() {
		
		JPanel Pfin = new JPanel();
		VentanaFin.getContentPane().setLayout(null);
		Pfin.setBorder(new EmptyBorder(5, 5, 5, 5));
		VentanaFin.add(Pfin);
		VentanaFin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		VentanaFin.setSize(750, 562);
		VentanaFin.setResizable(false);
		VentanaFin.setLocationRelativeTo(null);
		JLabel lblFondo = new JLabel();
		lblFondo.setBounds(0, 0, 750, 562);
		lblFondo.setIcon(new ImageIcon("src/Imagenes/final.jpg"));
		lbl1 = new JLabel("", JLabel.CENTER);
		lbl1.setBounds(130, 90, 490, 50);
		lbl1.setForeground(Color.YELLOW);
		lbl1.setFont(new Font("Cambria", Font.BOLD, 26));
		VentanaFin.getContentPane().add(lbl1);

		lbl2 = new JLabel("", JLabel.CENTER);
		lbl2.setBounds(140, 130, 490, 50);
		lbl2.setForeground(Color.WHITE);
		lbl2.setFont(new Font("Cambria", Font.BOLD, 20));
		VentanaFin.getContentPane().add(lbl2);

		lbl4 = new JLabel("", JLabel.CENTER);
		lbl4.setBounds(140, 170, 490, 50);
		lbl4.setForeground(Color.WHITE);
		lbl4.setFont(new Font("Cambria", Font.BOLD, 20));
		VentanaFin.getContentPane().add(lbl4);

		lbl3 = new JLabel("", JLabel.CENTER);
		lbl3.setBounds(190, 215, 490, 50);
		lbl3.setForeground(Color.YELLOW);
		lbl3.setFont(new Font("Cambria", Font.BOLD, 26));
		VentanaFin.getContentPane().add(lbl3);
		
		// Botón Jugar
		JButton btnNewPartida = new JButton("");
		btnNewPartida.setIcon(new ImageIcon("src/Imagenes/aceptar1.png"));
		btnNewPartida.setBounds(190, 400, 140, 52);
		btnNewPartida.setOpaque(false); // Fondo Transparente (los gráficos son png transparentes)
		btnNewPartida.setContentAreaFilled(false);// No rellenar el área
		btnNewPartida.setBorderPainted(false); // No pintar el borde
		btnNewPartida.setBorder(null); // No considerar el borde (el botón se hace solo del tamaño del gráfico)
		btnNewPartida.setRolloverIcon(new ImageIcon("src/Imagenes/aceptar2.png")); // Pone imagen de  rollover
		btnNewPartida.setPressedIcon(new ImageIcon("src/Imagenes/aceptar3.png")); // Pone imagen de click	
		btnNewPartida.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//si pierde y decide seguir jugando se resetea el juego y de recalculan todos lo valores
			if(estado == false){
					resetGame();
					time = 130000;
					gallo = false;
					mon.setTexto("Monedas: "+String.valueOf(monedas));
					mon.repaint();
					obj.setTexto(String.valueOf(objetivo));
					obj.repaint();
					niv.setTexto("Nivel: "+String.valueOf(nivel));
					niv.repaint();
					cronometro = true;
					//Opacidades de fondo a uno de nuevo
					ciudad.setOpacity(1f);
					fondo.setOpacity(1f);
					//Empezar los hilos de nuevo
					animaFlamas();
					control();
					iniciarTemporizador();
					VentanaFin.setVisible(false);
					setVisible(true);
					sonido("src/Sounds/Ready.wav");
				}
			//si pasa de nivel 
				else {
					int cont = 0;
					for(int i=0; i<8;i++){
						cont = cont + matrizCohetes[i].nivel;
					}
					//si los cohetes no estan todos a nivel 10 las monedas actualizarán lo cohetes
					if(cont<80){
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
					}
					// si no las monedas van directo a la puntuación
					else{
						puntuacion = puntuacion + monedas*100;
						monedas = 0;
					}
					//opacidades a 1 y valores reinicializados
					ciudad.setOpacity(1f);
					fondo.setOpacity(1f);
					pausa = false;
					time = 130000;
					nivel++;
					objetivo = 9 + nivel;
					gallo = false;
					mon.setTexto("Monedas: "+String.valueOf(monedas));
					mon.repaint();
					obj.setTexto(String.valueOf(objetivo));
					obj.repaint();
					niv.setTexto("Nivel: "+String.valueOf(nivel));
					niv.repaint();
					cronometro = true;
					VentanaFin.setVisible(false);
					setVisible(true);
					sonido("src/Sounds/Ready.wav");
					//se reinician los ciclos
					animaFlamas();
					control();
					iniciarTemporizador();
					System.out.println(monedas);
				}
			}
		});
		VentanaFin.getContentPane().add(btnNewPartida);

		// Botón Salir
		JButton btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon("src/Imagenes/salir1.png"));
		btnSalir.setBounds(420, 400, 140, 52);
		btnSalir.setOpaque(false); // Fondo Transparente (los gráficos son png transparentes)
		btnSalir.setContentAreaFilled(false);// No rellenar el área
		btnSalir.setBorderPainted(false); // No pintar el borde
		btnSalir.setBorder(null); // No considerar el borde (el botón se hace solo del tamaño del gráfico)
		btnSalir.setRolloverIcon(new ImageIcon("src/Imagenes/salir2.png")); // Pone  imagen de rollover
		btnSalir.setPressedIcon(new ImageIcon("src/Imagenes/salir3.png")); // Pone imagen de click
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});			
		VentanaFin.getContentPane().add(btnSalir);

		VentanaFin.getContentPane().add(lblFondo);
		VentanaFin.repaint();
		VentanaFin.setVisible(true);
	}
	//con este método  se comienza el juego revisando todo el tablero y las conexiones que existen entre las distintas mechas
	public static void empezar(Ventana v) {
		boolean comienzo = false;
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (comienzo == false) {
			v.revIzquierda();
			v.revArriba();
			v.revAbajo();
			v.revDerecha();
			v.revIzquierda();
			int has = 0;
			do {
				v.revConectaDer();
				v.revConectaIzq();
				has = has + 1;
				//TODO: has?
			} while (has < 16);
			comienzo = true;
		}
	}
	//Este hilo da el efecto de flamas móviles
	public void animaFlamas() {
		(new Thread() {
			@Override
			public void run() {
				while (cronometro == true) {
					try {
						Thread.sleep(85);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					for (int i = 0; i < 8; i++) {
						((Flama) matrizFlamas[i]).cambio();
					}
					f.repaint();

				}
			}
		}).start();
	}
//Este método realiza un control constante para saber si el jugador a perdido o pasado de nivel
	public void control() {
		(new Thread() {
			@Override
			public void run() {
				while (cronometro == true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//si es la primera vez que se empieza crea la ventana de fin de partida solo una vez y la deja en visible falso
					if(primero == false){
						finPartida();
						VentanaFin.setVisible(false);
						primero = true;
					}
					 System.out.println(time);
					 //cuado solo quedan 23 segundos suena un gallo y sonidos alarmantes
					 if (time < 23000 && gallo == false) {
						 sonido("src/Sounds/Gallo.wav");
						 gallo = true;
						 (new Thread() {
								@Override
								public void run() {
									while(time>10){
										sonido("src/Sounds/Limit.wav");
										try {
											Thread.sleep(650);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}	
								}
							}).start();
					 }
					 // cuado quedan 29 segundos se comieza a hacer el efecto de cambio dia noche
					if (time < 29000 && pausa == false) 
					{
						opa = opa + (1 / 30000f);
						if(opa>1){
							opa = 1f;
						}
						fondo.setOpacity(1f - opa);
						ciudad.setOpacity(1f - opa);
						fondo.repaint();
						ciudad.repaint();
						w.repaint();
					}
					// Si el tiempo se acaba sin que cumpla su objetivo se acaba el juego
					if (time == 9) {
						//paramos todo y mostramos la ventana del fin de juego con los valores que correspondan
						cronometro = false;
						pararTemporizador();
						pausa = true;
						estado = false;
						lbl1.setText("¡Mas suerte la proxima vez " + getNombre() + "!");
						lbl2.setText("Has conseguido " + getPuntuacion() + " puntos en el nivel " + getNivel() + ".");
						lbl2.setBounds(160, 150, 490, 50);
						lbl3.setText("¿Te atreves a probar de nuevo?");
						lbl4.setText("");
						sonido("src/Sounds/Out.wav");
						VentanaFin.repaint();
						VentanaFin.setVisible(true);
						setVisible(false);
						//revisamos si su puntuación ha sido lo suficientemente alta para entrar al ranking
						// si es así le mostrará la ventana para que lo mire
						try {
							//ventanaRanking.leer();
							System.out.println(nombre + "  "+ puntuacion);
							ventanaRanking.actualizar();
							ventanaRanking.leer();
							ventanaRanking.Escribir(nombre, puntuacion);
							ventanaRanking.leer();
							ventanaRanking.actualizar();
							//si no ha conseguido un high score no le muestra nada
							if(ventanaRanking.pasa==false){
								ventanaRanking.setVisible(false);
							}
							else{
								try {
									Thread.sleep(600);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								sonido("src/Sounds/High.wav");
							}
							//ventanaRanking.repaint();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					//si ha passa de nivel tambien le muestra la pantalla fin de partida con lo valores que correspondan
					if (objetivo <= 0) {
						
						cronometro = false;
						pararTemporizador();
						pausa = true;
						estado = true;
						lbl1.setText("¡" + getNombre() + ", has superado el nivel "+ getNivel() +"!");
						lbl2.setText("Has conseguido " + getPuntuacion() + " puntos");
						lbl4.setText("y "+ monedas +" monedas para mejorar tus cohetes.");
						lbl3.setText("Pulsa Jugar para pasar al nivel " + (getNivel()+1));
						VentanaFin.repaint();
						sonido("src/Sounds/Up.wav");
						VentanaFin.setVisible(true);
						setVisible(false);
					}
				}
			}
		}).start();

	}
	//Este método resetea el tablero de juego, vuelve ha insertar mechas y botones, como si fueran nuevos
	public void resetGame(){
		w.removeAll();
		opa = 0;
		puntuacion = 0;
		nivel = 1;
		objetivo = 10;
		monedas = 0;
		puntero = 0;
		pausa = false;
		for (int i = 1; i < this.dimension - 1; i++) {
			for (int j = 0; j < this.dimension; j++) {

				this.matrizBotones[i][j].removeMouseListener(this);
			}
		}
		
		for (int i = 1; i < this.dimension - 1; i++) {
			for (int j = 0; j < this.dimension; j++) {
				Random r = new Random();
				int ran = r.nextInt(10);
		
				 if (ran < 3) {
					MechaBarra m = new MechaBarra();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i * 50, j * 50 - 50, 50, 50);

				} else if (ran < 7) {
					MechaL m = new MechaL();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i * 50, j * 50 - 50, 50, 50);

				} else if (ran < 8) {
					MechaCruz m = new MechaCruz();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i * 50, j * 50 - 50, 50, 50);
				} else if (ran < 10) {
					MechaT m = new MechaT();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i * 50, j * 50 - 50, 50, 50);
				} else if (ran < 11) {
					MechaVacio m = new MechaVacio();
					this.matrizMechas[i][j] = m;
					this.matrizBotones[i][j] = m.getBoton();
					matrizBotones[i][j].setBounds(i * 50, j * 50 - 50, 50, 50);
				}

				w.add(matrizBotones[i][j]);
				matrizBotones[i][j].addMouseListener(this);
				
				// rev Conecta derecha es la propagación amarilla
				if (i == 1 && j > 0 && j < dimension - 1) {
					// System.out.println("Paso 1");
					if (matrizMechas[i][j].iz() == true) {
						// System.out.println("Paso 2");
						matrizMechas[i][j].setConectaDer(true);
					} else {
						matrizMechas[i][j].setConectaDer(false);
					}
				}
				if (i == dimension - 2 && j > 0 && j < dimension - 1) {
					if (matrizMechas[i][j].der() == true) {
						// System.out.println("Paso 2");
						matrizMechas[i][j].setConectaIzq(true);
					} else {
						matrizMechas[i][j].setConectaIzq(false);
					}
				}
				if (j == 9) {
					matrizBotones[i][j].setVisible(false);

				}
			}
		}

		for (int i = 0; i < 8; i++) {
			Cohete c = new Cohete();
			this.matrizCohetes[i] = c;
			this.matrizCohetes[i].setBounds(450, (i * 50)-25, 50, 50);
			w.add(this.matrizCohetes[i]);
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
		} while (has < 16);
	}
	//Este método revisa si una mecha esta conectada por arriba
	public void revArriba() {

		for (int i = 1; i < this.dimension - 1; i++) {
			for (int j = 0; j < this.dimension; j++) {

				if (j > 0 && j < dimension - 1 && i > 0 && i < dimension - 1) {

					if (matrizMechas[i][j - 1].down() == true
							&& matrizMechas[i][j].up() == true) {

						matrizMechas[i][j].setUnidoUp(true);
					}

					else {
						matrizMechas[i][j].setUnidoUp(false);
					}
				}
			}
		}
	}
	//Lo mismo pero con abajo
	public void revAbajo() {

		for (int i = 1; i < this.dimension - 1; i++) {
			for (int j = 0; j < this.dimension; j++) {

				if (j > 0 && j < dimension - 1 && i > 0 && i < dimension - 1) {

					if (matrizMechas[i][j + 1].up() == true
							&& matrizMechas[i][j].down() == true) {

						matrizMechas[i][j].setUnidoDown(true);
					}

					else {
						matrizMechas[i][j].setUnidoDown(false);
					}
				}
			}
		}
	}
	// lo mismo pero con la derecha
	public void revDerecha() {

		for (int i = 1; i < this.dimension - 1; i++) {

			for (int j = 0; j < this.dimension; j++) {

				if (j > 0 && j < dimension - 1 && i > 0 && i < dimension - 1) {

					if (i < dimension - 2
							&& matrizMechas[i + 1][j].iz() == true
							&& matrizMechas[i][j].der() == true) {

						matrizMechas[i][j].setUnidoDer(true);
					}

					else {
						matrizMechas[i][j].setUnidoDer(false);
					}
				}
			}
		}
	}
	//lo mismo pero con la izquierda
	public void revIzquierda() {

		for (int i = 1; i < this.dimension - 1; i++) {

			for (int j = 0; j < this.dimension; j++) {

				if (j > 0 && j < dimension - 1 && i > 0 && i < dimension - 1) {

					if (i > 1 && matrizMechas[i - 1][j].der() == true
							&& matrizMechas[i][j].iz() == true) {

						matrizMechas[i][j].setUnidoIz(true);
					}

					else {
						matrizMechas[i][j].setUnidoIz(false);
					}
				}
			}
		}
	}
	//Progagación de la conexión desde la fuente izquierda pasando conexiones por la derecha
	//Se revisa cada mecha una a una para ver si alguno de sus vecinos con los que esta conectados
	//tienen conexión con la fuente izquierda y si es así esta mecha tambien la tendrá
	public void revConectaDer() {

		for (int i = 1; i < this.dimension - 1; i++) {

			for (int j = 0; j < this.dimension; j++) {

				if (j > 0 && j < dimension - 1 && i > 0 && i < dimension - 1) {

					if (i > 1 && matrizMechas[i - 1][j].getConectaDer() == true
							&& matrizMechas[i][j].getUnidoIz() == true) {

						matrizMechas[i][j].setConectaDer(true);
					}

					else if (matrizMechas[i][j - 1].getConectaDer() == true
							&& matrizMechas[i][j].getUnidoUp() == true) {
						matrizMechas[i][j].setConectaDer(true);

					}

					else if (matrizMechas[i][j + 1].getConectaDer() == true
							&& matrizMechas[i][j].getUnidoDown() == true) {

						matrizMechas[i][j].setConectaDer(true);

					}

					else if (i < dimension - 2
							&& matrizMechas[i + 1][j].getConectaDer() == true
							&& matrizMechas[i][j].getUnidoDer() == true) {

						matrizMechas[i][j].setConectaDer(true);
					}

					else if (i > 1
							&& matrizMechas[i - 1][j].getConectaDer() == true
							&& matrizMechas[i][j].getUnidoIz() == true) {

						matrizMechas[i][j].setConectaDer(true);
					}

					else {
						if (i == 1) {
							if (matrizMechas[i][j].iz() == false
									&& matrizMechas[i][j].getUnidoUp() == false) {
								if (matrizMechas[i][j].getUnidoUp() == false) {
									if (matrizMechas[i][j].getUnidoIz() == false) {
										if (matrizMechas[i][j].getUnidoDer() == false) {
											matrizMechas[i][j]
													.setConectaDer(false);
										}
									}
								}
							}
						}

						else {
							matrizMechas[i][j].setConectaDer(false);
						}
					}
				}
			}
		}
	}
	//Progagación de la conexión desde la fuente derecha pasando conexiones por la izquierda
	//Se revisa cada mecha una a una para ver si alguno de sus vecinos con los que esta conectados
	//tienen conexión con la fuente derecha y si es así esta mecha tambien la tendrá
	public void revConectaIzq() {

		for (int i = 1; i < this.dimension - 1; i++) {
			for (int j = 0; j < this.dimension; j++) {

				if (j > 0 && j < dimension - 1 && i > 0 && i < dimension - 1) {

					if (i > 1 && matrizMechas[i - 1][j].getConectaIzq() == true
							&& matrizMechas[i][j].getUnidoIz() == true) {
						matrizMechas[i][j].setConectaIzq(true);
					}

					else if (matrizMechas[i][j - 1].getConectaIzq() == true
							&& matrizMechas[i][j].getUnidoUp() == true) {
						matrizMechas[i][j].setConectaIzq(true);
					}

					else if (matrizMechas[i][j + 1].getConectaIzq() == true
							&& matrizMechas[i][j].getUnidoDown() == true) {
						matrizMechas[i][j].setConectaIzq(true);
					}

					else if (i < dimension - 2
							&& matrizMechas[i + 1][j].getConectaIzq() == true
							&& matrizMechas[i][j].getUnidoDer() == true) {
						matrizMechas[i][j].setConectaIzq(true);
					}

					else if (i > 1
							&& matrizMechas[i - 1][j].getConectaIzq() == true
							&& matrizMechas[i][j].getUnidoIz() == true) {
						matrizMechas[i][j].setConectaIzq(true);
					}

					else {
						if (i == dimension - 2) {
							if (matrizMechas[i][j].der() == false
									&& matrizMechas[i][j].getUnidoUp() == false) {
								if (matrizMechas[i][j].getUnidoUp() == false) {
									if (matrizMechas[i][j].getUnidoIz() == false) {
										if (matrizMechas[i][j].getUnidoDer() == false) {
											matrizMechas[i][j].setConectaIzq(false);
										}
									}
								}
							}
						}

						else {
							matrizMechas[i][j].setConectaIzq(false);
						}
					}
				}
			}
		}
	}

	// Pone en false los booleanos de conexión que antes estaban en true
	public void borrarConecta() {
		for (int i = 1; i < this.dimension - 1; i++) {
			for (int j = 0; j < this.dimension; j++) {
				if (j > 0 && j < dimension - 1 && i > 0 && i < dimension - 1) {
					matrizMechas[i][j].setConectaDer(false);
					matrizMechas[i][j].setConectaIzq(false);
				}
			}
		}
	}
	//Inicialización de la fuente de conexiones amarillas
	public void fuente() {
		for (int j = 0; j < dimension; j++) {
			if (j > 0 && j < dimension - 1) {
				// System.out.println("Paso 1");
				if (matrizMechas[1][j].iz() == true) {
					// System.out.println("Paso 2");
					matrizMechas[1][j].setConectaDer(true);
				} else {
					matrizMechas[1][j].setConectaDer(false);
				}
			}
		}
	}
	//Inicialización de la fuente de conexiones azules
	public void fuenteAzul() {
		for (int j = 0; j < dimension; j++) {
			if (j > 0 && j < dimension - 1) {

				if (matrizMechas[8][j].der() == true) {
					// System.out.println("Paso "+j);
					matrizMechas[8][j].setConectaIzq(true);

				} else {
					matrizMechas[8][j].setConectaIzq(false);
				}
			}
		}
	}

	public void borrarMechas() throws InterruptedException {
		//Este sleep deja un pequeño momento para que el jugador se arrepienta de las conexiones hechas y cambie  
		Thread.sleep(700);
		cohetes = 0;
		//Este for vern cuantos cohetes disparo en una misma jugada
		for (int j = 0; j < this.dimension; j++) {
			if (j > 0 && j < dimension - 1) {

				if (matrizMechas[8][j].conectado == true
						&& matrizMechas[8][j].der() == true) {
					if (matrizMechas[8][j].revisado == false) {
						cohetes = cohetes + 1;
						//preparamos los cohetes de la matriz de cohetes para moverse
						matrizCohetes[j - 1].mov = true;
						//por cada cohete se suma nivel del cohete por 100 a puntuación
						puntuacion = puntuacion + matrizCohetes[j - 1].nivel * 100;
						//disminuimos el objetivo del nivel enun cohete
						objetivo = objetivo - 1;
						listo = false;
						matrizMechas[8][j].revisado = true;
					}
				}
			}
		}
		
		if(cohetes>0){
			sonido("src/Sounds/Disparo.wav");
		}
		

		obj.setTexto(String.valueOf(objetivo));
		punt.setTexto("Puntuación: " + String.valueOf(puntuacion));
		obj.repaint();
		punt.repaint();
		// todas aquellas mechas que esten conectadas en verde se prepararan para ser eliminadas
		for (int i = 1; i < this.dimension - 1; i++) {
			for (int j = 0; j < this.dimension; j++) {
				if (j > 0 && j < dimension - 1 && i > 0 && i < dimension - 1) {
					if (matrizMechas[i][j].conectado == true) {
						//Antes de marcar su eliminacion si tiene una moneda la recogemos
						monedas = monedas + matrizMechas[i][j].cogida();
						//marcamos su eliminación
						matrizMechas[i][j].anular();
						// por cada mecha que se elimina habrá un efecto de explosión
						Explosion e = new Explosion(i, j - 1);
						listaExp.add(e);

					}
				}
			}
		}
		mon.setTexto("Monedas: " + String.valueOf(monedas));
		mon.repaint();

		(new Thread() {
			@Override
			public void run() {

				for (int z = 0; z < 101; z++) {
					for (int i = 0; i < 8; i++) {
						if (matrizCohetes[i].mov == true) {
							matrizCohetes[i].setBounds(450 + z, (i * 50) -25 - z ,50, 50);
							if (z == 100) {
								matrizCohetes[i].setBounds(450, i * 50-25, 50, 50);
								matrizCohetes[i].mov = false;
								sonido("src/Sounds/Explosion.wav");
							}
						}
					}
					try {
						Thread.sleep(9);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}).start();

		
	}
	//Devuelve una nueva mecha con su boton añadido al panel
	public void crearBoton(int i, int j) {
		Random r = new Random();
		int ran = r.nextInt(10);
		Mecha m = null;
		if (ran < 3) {
			m = new MechaBarra();
		} else if (ran < 7) {
			m = new MechaL();
		} else if (ran < 8) {
			m = new MechaCruz();
		} else { // if (ran < 10) {
			m = new MechaT();
		}
		this.matrizMechas[i][j] = m;
		this.matrizBotones[i][j] = m.getBoton();
		matrizBotones[i][j].setBounds(i * 50, j * 50 - 50, 50, 50);
		matrizBotones[i][j].addMouseListener(this);
		w.add(matrizBotones[i][j]);

	}

	public void rellenar() throws InterruptedException {
		// Aqui busca todas aquellas mechas que tienen el boolean anulado en
		// true y pone la mecha y el botón de esa casilla en null
		for (int i = 1; i < this.dimension - 1; i++) {
			for (int j = 0; j < this.dimension; j++) {
				if (j < dimension - 1) {
					if (matrizMechas[i][j].anulado == true) {
						matrizMechas[i][j] = null;

					}

				}
			}
		}
		//Efecto explosion de mecha
		for (int i = 0; i < listaExp.size(); i++) {
			listaExp.get(i).setBounds(((Explosion) listaExp.get(i)).getEx(),((Explosion) listaExp.get(i)).getYe(), 50, 50);
			w.add(listaExp.get(i));
		}

		for (int i = 0; i < 16; i++) {
			for (int z = 0; z < listaExp.size(); z++) {
				listaExp.get(z).cambio();

				w.repaint();

			}
			Thread.sleep(30);
		}

		listaExp.clear();
		//codigo de caida lenta
		int p = 0;
		boolean hay = true;
		while (p < 9) {

			p = p + 1;
			for (int z = 0; z < 5; z++) {
				for (int i = 1; i < 9; i++) {
					boolean caida = false;
					hay = false;
					for (int j = 8; j > -1; j--) {
						if (j < 8 && matrizMechas[i][j + 1] == null) {
							pasa = true;
							caida = true;
							hay = true;
						}
						if (matrizMechas[i][j] != null && caida == true) {
							int pixels = matrizBotones[i][j].getY();

							matrizBotones[i][j].setBounds(i * 50, pixels + 10, 50, 50);
							Thread.sleep(5);
						}

					}
				}

			}

			for (int i = 1; i < 9; i++) {
				boolean caida = false;
				for (int j = 8; j > -1; j--) {
					if (j < 8 && matrizMechas[i][j + 1] == null) {
						caida = true;
					}
					if (matrizMechas[i][j] != null && caida == true) {
						matrizMechas[i][j + 1] = matrizMechas[i][j];
						matrizBotones[i][j + 1] = matrizMechas[i][j].getBoton();
						matrizMechas[i][j] = null;

					}
					if (j == 0 && caida == true) {
						crearBoton(i, 0);
					}

				}
			}

		}
		if (pasa == true) {
			w.removeAll();
			for (int i = 1; i < dimension - 1; i++) {
				for (int j = 0; j < dimension; j++) {
					w.add(matrizBotones[i][j]);
				}
			}
			for (int i = 0; i < 8; i++) {
				w.add(matrizCohetes[i]);
			}
			borrarConecta();
			fuente();
			fuenteAzul();
			revIzquierda();
			revArriba();
			revAbajo();
			revDerecha();
			int has = 0;
			do {
				revConectaDer();
				revConectaIzq();
				has = has + 1;
			} while (has < 16);
			has = 0;
			insertarMonedas(cohetes);
			if (cohetes > 0) {
				System.out.println("monedas: " + monedas);
			}
			w.repaint();

			pasa = false;
			listo = true;
		}

	}

	public void ciclo() throws InterruptedException {

		while (true){
			if(pausa == false)
			{
				borrarMechas();
				rellenar();
			}
			else{
				System.out.println(time);
			}


		}
	}

	public void insertarMonedas(int c) {
		Random ran = new Random();
		int i = 0;
		int j = 0;
		boolean u = true;
		i = ran.nextInt(8) + 1;
		j = ran.nextInt(8) + 1;
		System.out.println(i + " " + j + " " + c);
		if (cohetes == 3) {
			while (u == true) {
				i = ran.nextInt(8) + 1;
				j = ran.nextInt(8) + 1;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(1);
			u = true;
			while (u == true) {
				i = ran.nextInt(8) + 1;
				j = ran.nextInt(8) + 1;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(2);
			sonido("src/Sounds/Aplauso1.wav");
		}
		
		else if (cohetes == 4) {
			while (u == true) {
				i = ran.nextInt(8) + 1;
				j = ran.nextInt(8) + 1;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(5);
			sonido("src/Sounds/Aplauso2.wav");

		}

		else if (cohetes == 5) {
			while (u == true) {
				i = ran.nextInt(8) + 1;
				j = ran.nextInt(8) + 1;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			sonido("src/Sounds/Aplauso2.wav");

		}

		else if (cohetes == 6) {
			while (u == true) {
				i = ran.nextInt(8) + 1;
				j = ran.nextInt(8) + 1;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(5);
			u = true;
			while (u == true) {
				i = ran.nextInt(8) + 1;
				j = ran.nextInt(8) + 1;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			sonido("src/Sounds/Aplauso3.wav");

		}

		else if (cohetes == 7) {
			while (u == true) {
				i = ran.nextInt(8) + 1;
				j = ran.nextInt(8) + 1;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			u = true;
			while (u == true) {
				i = ran.nextInt(8) + 1;
				j = ran.nextInt(8) + 1;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			sonido("src/Sounds/Aplauso3.wav");
			sonido("src/Sounds/Oooh.wav");

		}

		else if (cohetes == 8) {
			while (u == true) {
				i = ran.nextInt(8) + 1;
				j = ran.nextInt(8) + 1;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			u = true;
			while (u == true) {

				i = ran.nextInt(8) + 1;
				j = ran.nextInt(8) + 1;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			u = true;
			while (u == true) {
				i = ran.nextInt(8) + 1;
				j = ran.nextInt(8) + 1;
				u = matrizMechas[i][j].usado;
			}
			matrizMechas[i][j].moneda(10);
			sonido("src/Sounds/Aplauso3.wav");
			sonido("src/Sounds/Oooh.wav");

		}

	}

	public void vueltas(int i, int j) throws InterruptedException {
		sonido("src/Sounds/Rotacion.wav");
		int pass = 0;
		listo = false;
		while (pass < 45) {

			matrizMechas[i][j].giro();
			matrizBotones[i][j].repaint();
			pass = pass + 1;
			Thread.sleep(4);
		}
		System.out.println("Aquí");
		matrizMechas[i][j].vuelta();
		if (i == 1 && j > 0 && j < dimension - 1) {
			// System.out.println("Paso 1");
			if (matrizMechas[i][j].iz() == true) {
				// System.out.println("Paso 2");
				matrizMechas[i][j].setConectaDer(true);
			} else {
				matrizMechas[i][j].setConectaDer(false);
			}
		}

		if (i == dimension - 2 && j < dimension - 1) {
			// System.out.println("Paso 1");
			if (matrizMechas[i][j].der() == true) {
				// System.out.println("Paso 2");
				matrizMechas[i][j].setConectaIzq(true);
			} else {
				matrizMechas[i][j].setConectaIzq(false);
			}
		}
		borrarConecta();
		fuente();
		fuenteAzul();
		revIzquierda();
		revArriba();
		revAbajo();
		revDerecha();
		int has = 0;
		do {
			revConectaDer();
			revConectaIzq();

			has = has + 1;
			// System.out.println(has);
		} while (has < 16);
		has = 0;
		listo = true;
		
		w.repaint();
		
	}

	private int ti, tj;

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {

		for (int i = 1; i < this.dimension - 1; i++) {
			for (int j = 0; j < this.dimension; j++) {
				if (matrizBotones[i][j] != null) {
					if (e.getSource() == matrizBotones[i][j] && listo == true && pausa==false) {
						ti = i;
						tj = j;
						System.out.println("LISTENER " + i + "  " + j);
						borrarConecta();
						fuente();
						fuenteAzul();
						revIzquierda();
						revArriba();
						revAbajo();
						revDerecha();
						int has = 0;
						do {
							revConectaDer();
							revConectaIzq();

							has = has + 1;
						} while (has < 16);
						has = 0;
						System.out.println(listo);
						// System.out.println( matrizMechas[i][j].iz());
						(new Thread() {
							@Override
							public void run() {
								try {
									System.out.println(ti + "  " + tj);
									vueltas(ti, tj);
									// listo = false;
									// System.out.println(i+"  "+j);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}).start();

					}
				}
			}
		}
	}

	public void sonido(String nombre) {
		Clip music = null;

		try {
			music = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		File a = new File(nombre);

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

	// Esto es para parar el temporizador
	public void pararTemporizador() {
		temporizadorActivo = false;
	}

	// Temporizador
	public void iniciarTemporizador() {
		(new Thread() {
			@Override
			public void run() {
				temporizadorActivo = true;
				Integer minutos = 2, segundos = 10, milesimas = 1;
				// min es minutos, seg es segundos y mil es milesimas de segundo
				String min = "", seg = "", mil = "";
				try {
					// Mientras temporizadorActivo sea verdadero entonces
					// seguira aumentando el tiempo
					while (temporizadorActivo) {
						if(pausa == false){
							//System.out.println("pasaaa  "+paus);
							Thread.sleep(1);
							// Incrementamos 4 milesimas de segundo
							time = time - 1;
							milesimas -= 1;
	
							// Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
							// y las milesimas de segundo de nuevo a 0
							if (milesimas == 0) {
								milesimas = 1000;
								segundos -= 1;
								// Si los segundos llegan a 60 entonces aumenta 1
								// los minutos
								// y los segundos vuelven a 0
								if (segundos == -1) {
									segundos = 59;
									minutos--;
								}
							}
	
							// Esto solamente es estetica para que siempre este en
							// formato 00:00:000
							if (minutos < 10)
								min = "0" + minutos;
							else
								min = minutos.toString();
							if (segundos < 10)
								seg = "0" + segundos;
							else
								seg = segundos.toString();
	
							if (milesimas < 10)
								mil = "00" + milesimas;
							else if (milesimas < 100)
								mil = "0" + milesimas;
							else
								mil = milesimas.toString();
							// Colocamos en la etiqueta la informacion
							temporizador.setText("  " + min + ":" + seg + ":" + mil);
	
							if (minutos <= 0 && segundos <= 0 && milesimas <= 10) {
								temporizadorActivo = false;
							}
						}
					}

				} catch (Exception e) {
				}
				// Cuando se reincie se coloca nuevamente en 00:00:000
				temporizador.setText("  00:00:000");

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

	//Getters y Setters
	// Devuelve el nombre de usuario
	public String getNombre() {
		return nombre;
	}
	
	// Sobreescribe el nombre de usuario
	public static void setNombre(String nombre) {
		Ventana.nombre = nombre;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	// Devuelve el valor de puntuacion
	public static  int getPuntuacion() {
		return puntuacion;
	}

	// Devuelve el nivel
	public  int getNivel() {
		return nivel;
	}
}
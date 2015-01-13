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
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class Ventana extends JFrame implements  MouseListener {
	
	private static final long serialVersionUID = -1728805850944908776L;
	static String nombre = "Jugador 1";
	//private static JTextField txtJugador;
	
	JPanel w = new JPanel();
	JFrame ni = new JFrame();
	JLabel mensaje = new JLabel();
	JButton continuar = new JButton("Aceptar");
	ImageIcon ama = new ImageIcon("src/Imagenes/barraA.png");
	int dimension = 10;
	int y = 0;
	int x = 0;
	int cont =0;
	int cohetes=0;
	int monedas = 0;
	int objetivo = 10;
	int nivel = 1;
	double time = 130000;
	boolean estado = false;
	boolean reset = false;
	boolean pasa = false;
	boolean listo = true;
	boolean aceptar = false;
	boolean cronometro = true;
	private JButton[][] matrizBotones;
	private Mecha[][] matrizMechas;

	public Ventana(){
		setSize(51*dimension,60*dimension);
		setVisible(false);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		//w.setLayout(new GridLayout(dimension, dimension));
		w.setLayout(null);
		add(w);
		this.matrizMechas = new Mecha[dimension][dimension];
		this.matrizBotones = new JButton[dimension][dimension];
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
		ni.add(mensaje, BorderLayout.NORTH);
		ni.add(continuar, BorderLayout.SOUTH);
		ni.setVisible(false);
		continuar.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(estado == false){
					System.out.println("Puntuación: ");
					System.exit(ABORT);
				}
				else{
					time = 130000;
					objetivo = 0;
					nivel = nivel + 1;
					objetivo = 9 + nivel;
					cronometro = true;
					tiempo();
					ni.setVisible(false);
					setVisible(true);
				}
				
			}
			 
		});
	}
	
	public static void main(String[] args) throws InterruptedException {
		Ventana v = new Ventana();
		portada(v);
		empezar(v);
		do{
			
		}while(v.aceptar == false);
		if(v.aceptar == true){
				v.tiempo();
				v.ciclo();
				
		}
	}
	
	public static void portada(Ventana v){
		
		JFrame por = new JFrame();
		
		por.setLayout(null);
		
		JPanel Panel0 = new JPanel();		
		por.getContentPane().setLayout(null);
		Panel0.setBorder(new EmptyBorder(5, 5, 5, 5));
		por.add(Panel0);	
		por.getContentPane().setBackground(Color.YELLOW);
		
		por.setVisible(true);
		por.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		por.setSize(460, 500);
	//	por.setResizable(false);
		por.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 460, 215);
		lblNewLabel.setIcon(new ImageIcon("src/Imagenes/portada.jpg"));
		por.getContentPane().add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(84, 250, 89, 34);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Cambria", Font.BOLD, 18));
		por.getContentPane().add(lblNombre);
		
		JTextField txtJugador = new JTextField();
		txtJugador.setForeground(Color.BLACK);
		txtJugador.setBackground(Color.WHITE);
		
		txtJugador.setText(getNombre());
		txtJugador.setBounds(270, 250, 142, 34);
		por.getContentPane().add(txtJugador);
		txtJugador.setFont(new Font("Cambria", Font.BOLD, 18));
		txtJugador.setColumns(10);
						
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBounds(90, 330, 113, 39);
		btnAceptar.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				por.setVisible(false);
				v.setVisible(true);
				v.aceptar = true;			
			}
		});
		por.getContentPane().add(btnAceptar);	
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtJugador.setText("");
			}
		});
		btnCancelar.setBounds(270, 330, 113, 39);
		por.getContentPane().add(btnCancelar);
		
		JButton btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(ABORT);			
			}
		});
		btnExit.setBounds(370, 390, 70, 70);
		btnExit.setIcon(new ImageIcon("src/Imagenes/exit.png"));
		por.getContentPane().add(btnExit);
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
					
					time = time - 1;
					if(time == 0){
						
						cronometro = false;
						estado = false;
						mensaje.setText("Ha perdido");
						setVisible(false);
						ni.setVisible(true);
					}
					if(objetivo <= 0){
						cronometro = false;
						estado = true;
						mensaje.setText("Ha pasado de nivel");
						setVisible(false);
						ni.setVisible(true);
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
						objetivo = objetivo -1;
						listo = false;
						matrizMechas[8][j].revisado=true;
						System.out.println("Cohetes: "+cohetes);
					}
				}
			}
		}
		//System.out.println(cont);
		//Si el boton esta naranja le pone su boolean anulado a true y esto borra la imagen y lo prepara para su eliminación
			//System.out.println(cont);
			for (int i = 1; i < this.dimension-1; i++){
				for (int j = 0; j < this.dimension; j++){
					if (j>0 &&j<dimension-1 && i>0 && i<dimension-1){					
						if(matrizMechas[i][j].conectado==true){
							monedas = monedas + matrizMechas[i][j].cogida();
							matrizMechas[i][j].anular();
							w.repaint();
							
						}
					}
				}
			}
		
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
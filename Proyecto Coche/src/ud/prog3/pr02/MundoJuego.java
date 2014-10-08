package ud.prog3.pr02;

import java.awt.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JPanel;

/** "Mundo" del juego del coche.
 * Incluye las físicas para el movimiento y los choques de objetos.
 * Representa un espacio 2D en el que se mueven el coche y los objetos de puntuación.
 * @author Andoni Eguíluz Morán
 * Facultad de Ingeniería - Universidad de Deusto
 */
public class MundoJuego {
	private JPanel panel;  // panel visual del juego
	CocheJuego miCoche;    // Coche del juego
	public ArrayList<JLabelEstrella> listaEstrellas = new ArrayList<JLabelEstrella>();
	int a = 0;
	int dir = 0;
	/** Construye un mundo de juego
	 * @param panel	Panel visual del juego
	 */
	public MundoJuego( JPanel panel ) {
		this.panel = panel;
	}

	/** Crea un coche nuevo y lo añade al mundo y al panel visual
	 * @param posX	Posición X de pixel del nuevo coche
	 * @param posY	Posición Y de píxel del nuevo coche
	 */
	public void creaCoche( int posX, int posY ) {
		// Crear y añadir el coche a la ventana
		miCoche = new CocheJuego();
		miCoche.setPosicion( posX, posY );
		panel.add( miCoche.getGrafico() );  // Añade al panel visual
		miCoche.getGrafico().repaint();  // Refresca el dibujado del coche
	}
	
	/** Devuelve el coche del mundo
	 * @return	Coche en el mundo. Si no lo hay, devuelve null
	 */
	public CocheJuego getCoche() {
		return miCoche;
	}
	
	public static double calcFuerzaRozamiento( double masa, double coefRozSuelo, 
			 double coefRozAire, double vel ) { 
			 double fuerzaRozamientoAire = coefRozAire * (-vel); // En contra del movimiento 
			 double fuerzaRozamientoSuelo = masa * coefRozSuelo * ((vel>0)?(-1):1); // Contra mvto 
			 return fuerzaRozamientoAire + fuerzaRozamientoSuelo; 
			 } 
	
	public static double calcAceleracionConFuerza( double fuerza, double masa ) { 
		 // 2ª ley de Newton: F = m*a ---> a = F/m 
		 return fuerza/masa; 
		 } 
	
	public static void aplicarFuerza( double fuerza, Coche coche ) { 
		 double fuerzaRozamiento = calcFuerzaRozamiento( Coche.masa , 
		 Coche.CoeRoSuelo, Coche.CoeRoAire, coche.getVelocidad() ); 
		 double aceleracion = calcAceleracionConFuerza( fuerza+fuerzaRozamiento, Coche.masa ); 
		 if (fuerza==0) { 
			 // No hay fuerza, solo se aplica el rozamiento 
			 double velAntigua = coche.getVelocidad(); 
			 coche.acelera( aceleracion, 0.04 ); 
			 if (velAntigua>=0 && coche.getVelocidad()<0 
			 || velAntigua<=0 && coche.getVelocidad()>0) { 
			 coche.setVelocidad(0); // Si se está frenando, se para (no anda al revés) 
			 } 
		 } 
		 
		 else { 
		 coche.acelera( aceleracion, 0.04 ); 
		 }
		 }
	

	/** Calcula si hay choque en horizontal con los límites del mundo
	 * @param coche	Coche cuyo choque se comprueba con su posición actual
	 * @return	true si hay choque horizontal, false si no lo hay
	 */
	public boolean hayChoqueHorizontal( CocheJuego coche ) {
		return (coche.getPosX() < JLabelCoche.RADIO_ESFERA_COCHE+-JLabelCoche.TAMANYO_COCHE/2 
				|| coche.getPosX()>panel.getWidth()-JLabelCoche.TAMANYO_COCHE/2-JLabelCoche.RADIO_ESFERA_COCHE );
	}
	//Como evitar el problema del rebote erroneo!!
	/** Calcula si hay choque en vertical con los límites del mundo
	 * @param coche	Coche cuyo choque se comprueba con su posición actual
	 * @return	true si hay choque vertical, false si no lo hay
	 */
	public boolean hayChoqueVertical( CocheJuego coche ) {
		return (coche.getPosY() < JLabelCoche.RADIO_ESFERA_COCHE-JLabelCoche.TAMANYO_COCHE/2 
				|| coche.getPosY()>panel.getHeight()-JLabelCoche.TAMANYO_COCHE/2-JLabelCoche.RADIO_ESFERA_COCHE );
	}

	/** Realiza un rebote en horizontal del objeto de juego indicado
	 * @param coche	Objeto que rebota en horizontal
	 */
	public void rebotaHorizontal( CocheJuego coche ) {
		// System.out.println( "Choca X");
		double dir = coche.getDireccionActual();
		dir = 180-dir;   // Rebote espejo sobre OY (complementario de 180)
		if (dir < 0) dir = 360+dir;  // Corrección para mantenerlo en [0,360)
		coche.setDireccionActual( dir );
	}
	
	/** Realiza un rebote en vertical del objeto de juego indicado
	 * @param coche	Objeto que rebota en vertical
	 */
	public void rebotaVertical( CocheJuego coche ) {
		// System.out.println( "Choca Y");
		double dir = coche.getDireccionActual();
		dir = 360 - dir;  // Rebote espejo sobre OX (complementario de 360)
		coche.setDireccionActual( dir );
	}
	
	/** Calcula y devuelve la posición X de un movimiento
	 * @param vel    	Velocidad del movimiento (en píxels por segundo)
	 * @param dir    	Dirección del movimiento en grados (0º = eje OX positivo. Sentido antihorario)
	 * @param tiempo	Tiempo del movimiento (en segundos)
	 * @return
	 */
	public static double calcMovtoX( double vel, double dir, double tiempo ) {
		return vel * Math.cos(dir/180.0*Math.PI) * tiempo;
	}
	
	/** Calcula y devuelve la posición X de un movimiento
	 * @param vel    	Velocidad del movimiento (en píxels por segundo)
	 * @param dir    	Dirección del movimiento en grados (0º = eje OX positivo. Sentido antihorario)
	 * @param tiempo	Tiempo del movimiento (en segundos)
	 * @return
	 */
	public static double calcMovtoY( double vel, double dir, double tiempo ) {
		return vel * -Math.sin(dir/180.0*Math.PI) * tiempo;
		// el negativo es porque en pantalla la Y crece hacia abajo y no hacia arriba
	}
	
	/** Calcula el cambio de velocidad en función de la aceleración
	 * @param vel		Velocidad original
	 * @param acel		Aceleración aplicada (puede ser negativa) en pixels/sg2
	 * @param tiempo	Tiempo transcurrido en segundos
	 * @return	Nueva velocidad
	 */
	public static double calcVelocidadConAceleracion( double vel, double acel, double tiempo ) {
		return vel + (acel*tiempo);
	}
	
	public void creacion(double mili){
	
		Random r = new Random();
		 int x = r.nextInt(900);
		 int y = r.nextInt(600);
		 
		 
		 this.listaEstrellas.add(new JLabelEstrella(x,y));
		 panel.add( listaEstrellas.get(a));  // Añade al panel visual
		 listaEstrellas.get(a).setCreado(mili);
		 listaEstrellas.get(a).repaint();
		 a = a+1;
		 // Refresca el dibujado del coche
		
	}
	public void giroEstrellas(){
		
			if (dir > 360) dir = dir - 360;
			if(listaEstrellas.size()>0){
				
				for(int i=0; i<a; i++){
					 listaEstrellas.get(i).setGiro(dir);
					 listaEstrellas.get(i).repaint();
				 }
				dir = dir + 10;
			}
		
		}
	public int Sacar(double mili){
		
		for(int i=0; i<a; i++){
			 if((mili-listaEstrellas.get(i).getCreado())>=150){
				 panel.remove(listaEstrellas.get(i));
				listaEstrellas.remove(i);
				panel.repaint();
				a= a-1;
				return 1;
				 
			 }
			 
		}
		
		return 0;
		
	}
	
	public int Choque(){
		
		double x1 = miCoche.getPosX() + 50;
		double y1 = miCoche.getPosY() + miCoche.getRadio();
		
		for(int i=0; i<a; i++){
			 
			double x2 = listaEstrellas.get(i).getX() + 20;
			double y2 = listaEstrellas.get(i).getY() + listaEstrellas.get(i).getRadio();
			
			double primero = (x2-x1)*(x2-x1);
			double segundo = (y2-y1)*(y2-y1);
			double tercero = primero + segundo;
			double resultado = Math.sqrt(tercero);
			if(resultado<0){
				resultado = resultado*-1;
			}
			if(resultado<52){
				 panel.remove(listaEstrellas.get(i));
					listaEstrellas.remove(i);
					panel.repaint();
					a= a-1;
					return  5;
			}
				 
			 }
		
		return 0;
		
	}
	
	
}

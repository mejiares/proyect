package ud.prog3.pr02;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Calendar;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelEstrella extends JLabel {
	
	public static final int RADIO_ESFERA_ESTRELLA = 17;  
	private static final boolean DIBUJAR_ESFERA_ESTRELLA = true;
	private double creado= 0;
	int x = 0;
	int y = 0;
	
	public JLabelEstrella(int posX, int posY) {
		this.x = posX;
		this.y = posY;
		try {
			setIcon( new ImageIcon( JLabelCoche.class.getResource( "img/estrella.png" ).toURI().toURL() ) );
		} catch (Exception e) {
			System.err.println( "Error en carga de recurso: coche.png no encontrado" );
			e.printStackTrace();
		}
		setBounds( x, y, 40, 40 );
		
		
		// Esto sería útil cuando hay algún problema con el gráfico: borde de color del JLabel
		// setBorder( BorderFactory.createLineBorder( Color.yellow, 4 ));
	}
	
	public void setCreado(double crea){
		this.creado = crea;
	}
	
	public double getCreado(){
		return this.creado;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	  
	
	private double miGiro = Math.PI/2;
	/** Cambia el giro del JLabel
	 * @param gradosGiro	Grados a los que tiene que "apuntar" la estrella,
	 * 						considerados con el 0 en el eje OX positivo,
	 * 						positivo en sentido antihorario, negativo horario.
	 */
	public void setGiro( double gradosGiro ) {
		// De grados a radianes...
		miGiro = gradosGiro/180*Math.PI;
		// El giro en la pantalla es en sentido horario (inverso):
		miGiro = -miGiro;  // Cambio el sentido del giro
		
		miGiro = miGiro + Math.PI/2; // Sumo 90º para que corresponda al origen OX
	}
	
	public int getRadio(){
		return JLabelEstrella.RADIO_ESFERA_ESTRELLA;
	}
	
	

	// Redefinición del paintComponent para que se escale y se rote el gráfico
	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);   // En este caso no nos sirve el pintado normal de un JLabel
		Image img = ((ImageIcon)getIcon()).getImage();
		Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
		// Escalado más fino con estos 3 parámetros:
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
		// Prepara rotación (siguientes operaciones se rotarán)
        g2.rotate( miGiro, 40/2, 40/2 ); // getIcon().getIconWidth()/2, getIcon().getIconHeight()/2 );
        // Dibujado de la imagen
        g2.drawImage( img, 0, 0, 40, 40, null );
        if (DIBUJAR_ESFERA_ESTRELLA) g2.drawOval( 40/2-RADIO_ESFERA_ESTRELLA, 40/2-RADIO_ESFERA_ESTRELLA,
        		RADIO_ESFERA_ESTRELLA*2, RADIO_ESFERA_ESTRELLA*2 );
	}

}

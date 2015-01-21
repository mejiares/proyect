package Juego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;



import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Puntuaciones extends JFrame implements ActionListener {//Esta ventana nos mostrará las mejores puntuaciones almacenadas en el archivo
	
	private static final long serialVersionUID = 1L;
	ArrayList<String> nombres = new ArrayList<String>();
	ArrayList<Integer> puntuaciones = new ArrayList<Integer>();
	boolean primero = false;
	JLabel lbl0 = new JLabel();
	JLabel lbl1 = new JLabel();
	JLabel lbl2 = new JLabel();
	JLabel lbl3 = new JLabel();
	JLabel lbl4 = new JLabel();
	JLabel lbl5 = new JLabel();
	JLabel lbl6 = new JLabel();
	JLabel lbl7 = new JLabel();
	JLabel lbl8 = new JLabel();
	JLabel lbl9 = new JLabel();
	JLabel lbl10 = new JLabel();
	boolean pasa = false;
	
	
	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public Puntuaciones() {

		
	}
	
	public void actualizar() throws FileNotFoundException{
		
		setVisible(true);
		if(primero == false){
		
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(750, 562);
			setLocationRelativeTo(null);
	
			JLabel lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 750, 562);
			lblFondo.setIcon(new ImageIcon("src/Imagenes/halloffame.jpg"));
	
			lbl0.setBounds(290, 100, 200, 52);
			lbl0.setForeground(Color.BLACK);
			lbl0.setFont(new Font("Cambria", Font.BOLD, 26));
			getContentPane().add(lbl0);
	
			lbl1.setBounds(290, 135, 200, 52);
			lbl1.setForeground(Color.BLACK);
			lbl1.setFont(new Font("Cambria", Font.BOLD, 26));
			getContentPane().add(lbl1);
	
			lbl2.setBounds(290, 170, 200, 52);
			lbl2.setForeground(Color.BLACK);
			lbl2.setFont(new Font("Cambria", Font.BOLD, 26));
			getContentPane().add(lbl2);
	
			lbl3.setBounds(290, 205, 200, 52);
			lbl3.setForeground(Color.BLACK);
			lbl3.setFont(new Font("Cambria", Font.BOLD, 26));
			getContentPane().add(lbl3);
	
			lbl4.setBounds(290, 240, 200, 52);
			lbl4.setForeground(Color.BLACK);
			lbl4.setFont(new Font("Cambria", Font.BOLD, 26));
			getContentPane().add(lbl4);
	
			lbl5.setBounds(290, 275, 200, 52);
			lbl5.setForeground(Color.BLACK);
			lbl5.setFont(new Font("Cambria", Font.BOLD, 26));
			getContentPane().add(lbl5);
	
			lbl6.setBounds(290, 310, 200, 52);
			lbl6.setForeground(Color.BLACK);
			lbl6.setFont(new Font("Cambria", Font.BOLD, 26));
			getContentPane().add(lbl6);
	
			lbl7.setBounds(290, 345, 200, 52);
			lbl7.setForeground(Color.BLACK);
			lbl7.setFont(new Font("Cambria", Font.BOLD, 26));
			getContentPane().add(lbl7);
	
			lbl8.setBounds(290, 380, 200, 52);
			lbl8.setForeground(Color.BLACK);
			lbl8.setFont(new Font("Cambria", Font.BOLD, 26));
			getContentPane().add(lbl8);
	
			lbl9.setBounds(290, 415, 200, 52);
			lbl9.setForeground(Color.BLACK);
			lbl9.setFont(new Font("Cambria", Font.BOLD, 26));
			getContentPane().add(lbl9);
	
			lbl10.setBounds(290, 450, 200, 52);
			lbl10.setForeground(Color.BLACK);
			lbl10.setFont(new Font("Cambria", Font.BOLD, 26));
			getContentPane().add(lbl10);
	
			// Botón Volver
			JButton btnVolver = new JButton("");
			btnVolver.setIcon(new ImageIcon("src/Imagenes/volver1.png"));
			btnVolver.setBounds(70, 400, 140, 52);
			btnVolver.setOpaque(false); // Fondo Transparente (los gráficos son png transparentes)
			btnVolver.setContentAreaFilled(false);// No rellenar el área
			btnVolver.setBorderPainted(false); // No pintar el borde
			btnVolver.setBorder(null); // No considerar el borde (el botón se hace solo del tamaño del gráfico)
			btnVolver.setRolloverIcon(new ImageIcon("src/Imagenes/volver2.png")); // Pone  imagen de rollover
			btnVolver.setPressedIcon(new ImageIcon("src/Imagenes/volver3.png")); // Pone imagen de click
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					//TODO: Hacemos visible la ventana de la portada
				}
			});			
			getContentPane().add(btnVolver);
			
			// Botón Volver
			JButton btnVolver2 = new JButton("");
			btnVolver2.setIcon(new ImageIcon("src/Imagenes/volver1.png"));
			btnVolver2.setBounds(540, 400, 140, 52);
			btnVolver2.setOpaque(false); // Fondo Transparente (los gráficos son png transparentes)
			btnVolver2.setContentAreaFilled(false);// No rellenar el área
			btnVolver2.setBorderPainted(false); // No pintar el borde
			btnVolver2.setBorder(null); // No considerar el borde (el botón se hace solo del tamaño del gráfico)
			btnVolver2.setRolloverIcon(new ImageIcon("src/Imagenes/volver2.png")); // Pone  imagen de rollover
			btnVolver2.setPressedIcon(new ImageIcon("src/Imagenes/volver3.png")); // Pone imagen de click
			btnVolver2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					//TODO: Hacemos visible la ventana de la portada
				}
			});			
			getContentPane().add(btnVolver2);
			
			getContentPane().add(lblFondo);	
			primero = true;
		}
		
		File archivoRanking = new File("src/Resources/puntuaciones.txt");//Archivo en el que están las puntuaciones
		File archivoNombres = new File("src/Resources/nombres.txt");//Archivo en el que están los nombres
		FileReader frr = null;
		FileReader frn = null;
		BufferedReader brr = null;
		BufferedReader brn = null;
		int numLines=0;
		Scanner iterateR = new Scanner(archivoRanking);
		Scanner iterateN = new Scanner(archivoNombres);		
		while(iterateR.hasNextLine()) {
			String currLine=iterateR.nextLine(); numLines++; 
		}
		try{

			frr = new FileReader (archivoRanking);
			brr = new BufferedReader(frr);
			frn = new FileReader (archivoNombres);
			brn = new BufferedReader(frn);
			String lineaR;
			String lineaN;

			for(int i=0;i<numLines;i++){
				switch (i) {
				case 0: lineaR=brr.readLine();
				lineaN=brn.readLine();
				lbl0.setText("1.  "+ lineaN + ":  " + lineaR); break;
				case 1: lineaR=brr.readLine();
				lineaN=brn.readLine();
				lbl1.setText("2.  "+ lineaN + ":  " + lineaR); break;
				case 2: lineaR=brr.readLine();
				lineaN=brn.readLine();
				lbl2.setText("3.  "+ lineaN + ":  " + lineaR); break;
				case 3: lineaR=brr.readLine();
				lineaN=brn.readLine();
				lbl3.setText("4.  "+ lineaN + ":  " + lineaR); break;
				case 4: lineaR=brr.readLine();
				lineaN=brn.readLine();
				lbl4.setText("5.  "+ lineaN + ":  " + lineaR); break;
				case 5: lineaR=brr.readLine();
				lineaN=brn.readLine();
				lbl5.setText("6.  "+ lineaN + ":  " + lineaR); break;
				case 6: lineaR=brr.readLine();
				lineaN=brn.readLine();
				lbl6.setText("7.  "+ lineaN + ":  " + lineaR); break;
				case 7: lineaR=brr.readLine();
				lineaN=brn.readLine();
				lbl7.setText("8.  "+ lineaN + ":  " + lineaR); break;
				case 8: lineaR=brr.readLine();
				lineaN=brn.readLine();
				lbl8.setText("9.  "+ lineaN + ":  " + lineaR); break;
				case 9: lineaR=brr.readLine();
				lineaN=brn.readLine();
				lbl9.setText("10.  "+ lineaN + ":  " + lineaR); break;
				}
			}    
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta 
			// una excepcion.
			try{                    
				if( null != frr ){   
					frr.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
	}
	
	public void leer () throws FileNotFoundException{
		File archivoRanking = new File("src/Resources/puntuaciones.txt");//Archivo en el que están las puntuaciones
		File archivoNombres = new File("src/Resources/nombres.txt");//Archivo en el que están los nombres
		FileReader frr = null;
		FileReader frn = null;
		BufferedReader brr = null;
		BufferedReader brn = null;
		
		nombres.clear();
		puntuaciones.clear();
		System.out.println(nombres);
		System.out.println(puntuaciones);
		int numLines=0;
		Scanner iterateR = new Scanner(archivoRanking);
		Scanner iterateN = new Scanner(archivoNombres);		
		while(iterateR.hasNextLine()) {
			String currLine=iterateR.nextLine(); numLines++; 
		}
		try{

			frr = new FileReader (archivoRanking);
			brr = new BufferedReader(frr);
			frn = new FileReader (archivoNombres);
			brn = new BufferedReader(frn);
			String lineaR;
			String lineaN;
			int punt;
			for(int i=0;i<numLines;i++){
				switch (i) {
				case 0: lineaR=brr.readLine();
				lineaN=brn.readLine();
				punt = Integer.parseInt(lineaR);
				nombres.add(lineaN);
				puntuaciones.add(punt);
				case 1: lineaR=brr.readLine();
				lineaN=brn.readLine();
				punt = Integer.parseInt(lineaR);
				nombres.add(lineaN);
				puntuaciones.add(punt);
				case 2: lineaR=brr.readLine();
				lineaN=brn.readLine();
				punt = Integer.parseInt(lineaR);
				nombres.add(lineaN);
				puntuaciones.add(punt);
				case 3: lineaR=brr.readLine();
				lineaN=brn.readLine();
				punt = Integer.parseInt(lineaR);
				nombres.add(lineaN);
				puntuaciones.add(punt);
				case 4: lineaR=brr.readLine();
				lineaN=brn.readLine();
				punt = Integer.parseInt(lineaR);
				nombres.add(lineaN);
				puntuaciones.add(punt);
				case 5: lineaR=brr.readLine();
				lineaN=brn.readLine();
				punt = Integer.parseInt(lineaR);
				nombres.add(lineaN);
				puntuaciones.add(punt);
				case 6: lineaR=brr.readLine();
				lineaN=brn.readLine();
				punt = Integer.parseInt(lineaR);
				nombres.add(lineaN);
				puntuaciones.add(punt);
				case 7: lineaR=brr.readLine();
				lineaN=brn.readLine();
				punt = Integer.parseInt(lineaR);
				nombres.add(lineaN);
				puntuaciones.add(punt);
				case 8: lineaR=brr.readLine();
				lineaN=brn.readLine();
				punt = Integer.parseInt(lineaR);
				nombres.add(lineaN);
				puntuaciones.add(punt);
				case 9: lineaR=brr.readLine();
				lineaN=brn.readLine();
				punt = Integer.parseInt(lineaR);
				nombres.add(lineaN);
				puntuaciones.add(punt);
				}
			}    
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta 
			// una excepcion.
			try{                    
				if( null != frr ){   
					frr.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
		
	}

	public void Escribir (String na, int puntu) throws FileNotFoundException {
		
		pasa = false;
		for (int j=0; j<11; j++) {
			if(puntu>puntuaciones.get(j)&&pasa == false){
				puntuaciones.add(j, puntu);
				nombres.add(j, na);
				pasa = true;
				System.out.println("pasa");
			}
		}
		try {
			//FileNotFoundExceptio
			 FileOutputStream fos = new FileOutputStream("src/Resources/nombres.txt");
	         OutputStreamWriter oos;
	         oos = new OutputStreamWriter(fos);
	         String separator = System.getProperty("line.separator");
		
			for (int j=0; j<11; j++) {
				String n = nombres.get(j);
				if(n!=null){
					//System.out.println(n);
					oos.write(n);
					oos.append(separator);
				}
			}
		
		oos.close();
       fos.close();
       
       FileOutputStream fos2= new FileOutputStream("src/Resources/puntuaciones.txt");
       OutputStreamWriter oos2;
       oos2 = new OutputStreamWriter(fos2);
	
		for (int j=0; j<11; j++) {
			int p = puntuaciones.get(j);
			if(p>=0){
				oos2.write(p+"");
				oos2.append(separator);
			}
		}
	
	oos2.close();
 fos2.close();
		} 
       catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
	}
}

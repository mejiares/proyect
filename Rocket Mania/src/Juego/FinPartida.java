package Juego;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FinPartida extends JFrame implements ActionListener {

	public void Pierdes() {
		JFrame lost = new JFrame();
		
		lost.setLayout(null);
		
		JPanel Panel0 = new JPanel();		
		lost.getContentPane().setLayout(null);
		Panel0.setBorder(new EmptyBorder(5, 5, 5, 5));
		lost.add(Panel0);	

		
		//por.setVisible(true);
		lost.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lost.setSize(750, 562);
	//	por.setResizable(false);
		lost.setLocationRelativeTo(null);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 750, 562);
		//lblNewLabel.setIcon(new ImageIcon("src/Imagenes/portada.jpg"));
		lblFondo.setIcon(new ImageIcon("src/Imagenes/fondoPortada.jpg"));
	
	
	}

	private void initialize() {
		
		JPanel contentPane = new JPanel();
		JPanel panel1 = new JPanel();
		setSize(650, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Partida Finalizada");
		//setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);


		contentPane.add(panel1, BorderLayout.NORTH);
		contentPane.setBackground(Color.black);
		panel1.setBackground(Color.black);
		panel1.setLayout(new GridLayout(4, 1, 0, 0));

		JLabel lblPartidaFinalizada = new JLabel("Mas suerte la proxima vez " + Ventana.getNombre(), null, JLabel.CENTER);	
		lblPartidaFinalizada.setForeground(Color.YELLOW);
		lblPartidaFinalizada.setFont(new Font("Splash", Font.PLAIN, 18));
		panel1.add(lblPartidaFinalizada);
		
		
		JLabel lbl2 = new JLabel("Has conseguido " + Ventana.getPuntuacion() + " puntos en el nivel " + Ventana.getNivel(), null, JLabel.CENTER);	
		lbl2.setForeground(Color.YELLOW);
		lbl2.setFont(new Font("Splash", Font.PLAIN, 18));
		panel1.add(lbl2);	
		


		
		JLabel lbl4 = new JLabel("¿Te atreves a probar de nuevo?", null, JLabel.CENTER);	
		lbl4.setForeground(Color.YELLOW);
		lbl4.setFont(new Font("Splash", Font.PLAIN, 18));
		panel1.add(lbl4);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.BLACK);
		contentPane.add(panel2, BorderLayout.CENTER);
		
		JLabel label = new JLabel(" ");

		label.setIcon(new ImageIcon("src/Resources/explode.jpg"));
		panel2.add(label);

		JPanel panel3 = new JPanel();
		contentPane.add(panel3, BorderLayout.SOUTH);
		panel3.setLayout(new GridLayout(0, 2, 0, 0));
		panel3.setBackground(Color.BLACK);
		
		JButton btnNewButton = new JButton("Volver a jugar");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel3.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JButton btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel3.add(btnNewButton_1);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(ABORT);
			}
		});
		

		
	}
		
	//Click izquierdo en el boton volver a jugar 	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
		
	}

}

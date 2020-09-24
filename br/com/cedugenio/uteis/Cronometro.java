package br.com.cedugenio.uteis;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cronometro {
	
	private JLabel contadorTempo;
	private Timer timer;
	private int contador = 0;
	private boolean rodando = false;
	
	
	public void init() {
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame janela = new JFrame("Cronômetro"); 
		
		janela.setSize(350, 250);
		janela.setAlwaysOnTop(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLayout(new BorderLayout());
		
		contadorTempo =  new JLabel("00:00:00");
		contadorTempo.setFont(new Font(contadorTempo.getName(),Font.PLAIN, 70));
		janela.add(contadorTempo,(BorderLayout.CENTER));
		
		
		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(2,1));
		
		JButton botaoIniciar = new JButton("Iniciar");
		
		botaoIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!rodando) {	
				timer = new Timer();
				rodando = true;
				
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						contador ++;
						int segundo = contador % 60;
						int minuto = contador / 60; 
						int hora = minuto / 60;
						minuto %= 60;
						
						contadorTempo.setText(String.format("%02d:%02d:%02d", hora,minuto,segundo));
					}
				}, 1000, 1000);
			  }	
			}
		});
		
		JButton botaoParar = new JButton("Parar");
		botaoParar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				if(rodando) {
					timer.cancel();
					rodando = false;
					contador = 0; 
				}
				
			}
		});
		
		painel.add(botaoIniciar);
		painel.add(botaoParar);
		
		janela.add(painel, BorderLayout.EAST);
		janela.pack();
		janela.setVisible(true);
		
	}
	
	public static void main(String[] args) {
	
		Cronometro cron = new Cronometro();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				cron.init();
			}
		});
		
		
		
	}
	
	

}

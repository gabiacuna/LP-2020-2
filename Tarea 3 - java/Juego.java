import java.awt.*;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

import Cartas.*;
import Cartas.Carta.Area;
import Cartas.Evento.tipoEv;

import Juego.*;

public class Juego extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int DEF_ANCHO = 1200;
	private static final int DEF_ALT = 750;


	//Atributos para la GUI:
	JFrame fr;
	Container con;
	JPanel titlePnl, strtButPnl, ramosPnl, mazosPnl, manoPnl, opPnl, finpnl;	//Paneles que usare para la GUI
	JLabel titleLb, backG, backU, hdisp, aprobadosLb, reprobadosLb;
	Font titFont = new Font("Monospaced", Font.PLAIN, 80);		//Algunas fuentes que usare
	Font strBtFont = new Font("Monospaced", Font.PLAIN, 40);
	Font tabFont = new Font("Monospaced", Font.PLAIN, 30);

	JButton strtBut;	//Boton para empezar el juego

	boolean permisoRobar = true;	//Boolean para saber si ya empezo el turno

	//Atributos que se pidieron:

	Mazo mazoUniversitario, mazoCarrera;
	Tablero tablero;
	Mano mano;
	
	int aprobados = 0, reprobados = 0;

	public static void main(String[] args) {
		new Juego();
	}

	public Juego() {
		super();
		fr = new JFrame("Jueguito :) - Tarea 3 Gabi Acuña");
		fr.setSize(DEF_ANCHO, DEF_ALT);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.getContentPane().setBackground(new Color(201, 44, 41));

		//Para el fondo:
		try {
			BufferedImage imgF = ImageIO.read(new File("Fondito.jpg"));
			backG = new JLabel(new ImageIcon(imgF));

		} catch (IOException exp) {
			exp.printStackTrace();
		}

		fr.setContentPane(backG);
		fr.setLayout(null);
		fr.setVisible(true);
		con = fr.getContentPane();

		//Para el titulo "Jurguito"
		titlePnl = new JPanel();
		titlePnl.setBounds(250, 150, 700, 150);
		titlePnl.setBackground(new Color(201, 44, 41));

		titleLb = new JLabel("Jueguito");
		titleLb.setForeground(Color.white);
		titleLb.setFont(titFont);

		//Para el Boton "Empezar":
		strtButPnl = new JPanel();
		strtButPnl.setBounds(500, 500, 200, 100);
		strtButPnl.setBackground(new Color(201, 44, 41));

		strtBut = new JButton("empezar");
		strtBut.setBackground(new Color(201, 44, 41));
		strtBut.setForeground(Color.white);
		strtBut.setFont(strBtFont);
		strtBut.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				crearTablero();
			}
		});
		strtBut.setFocusPainted(false);

		titlePnl.add(titleLb);
		strtButPnl.add(strtBut);
		con.add(titlePnl);
		con.add(strtButPnl);
	}

	/**
	* crearMazoU:
	* Crea las cartas de etudios y eventos para agregarlas al Mazo universitario.
	*
	* @return void:solo realiza la accion.
	*/
	public void crearMazoU(){
		// Humanista: 3 Comunes, 2 Raras y 1 Epica.
		// Informatica: 4 Comunes, 3 Raras y 1 Epica.
		// Matematica: 3 Comunes, 2 Raras y 1 Epica.
		// +5 Evento
		
		Estudio e1 = new Estudio("Biblioteca [H]", "pedir libros", 2, Area.Hum); mazoUniversitario.putBack(e1);
		Estudio e2 = new Estudio("Cerrito matutino [H]", "Es primavera, puedes leer algun texto o libro para repasar, afuera para cambiar el ambiente <3 #saludMentalxd", 2, Area.Hum); mazoUniversitario.putBack(e2);
		Estudio e3 = new Estudio("Don Ferde [H]", "Imprimir un texto", 2, Area.Hum); mazoUniversitario.putBack(e3);
		Estudio e4 = new Estudio("Mail respuesta de Tapia", "Le mandaste un mail pidiendole ayuda para un ensayo y se entusiasmo respondiendote, ahora estas estudiando su respuesta", 3, Area.Hum); mazoUniversitario.putBack(e4);
		Estudio e5 = new Estudio("1 shot de cafe [H]", "Te tomaste un shot de cafe, tienes mucha energia para avanzar con el nsayo que tienes pendiente", 3, Area.Hum); mazoUniversitario.putBack(e5);
		Estudio e6 = new Estudio("Ciac [H]", "Pediste una sala de estudio con tus compañerxs, estas listo para debatir hasta tener los suficientes argumentos para termianr un ensayo :D.", 4, Area.Hum); mazoUniversitario.putBack(e6);
		Estudio e7 = new Estudio("Biblioteca [M]", "Pediste una sala en grupo para estudiar con amigxs", 2, Area.Mat); mazoUniversitario.putBack(e7);
		Estudio e8 = new Estudio("Ciac [M]", "Fuiste a una sala de estudio, listx para hacer mil ejercicios en las pizarras", 2, Area.Mat); mazoUniversitario.putBack(e8);
		Estudio e9 = new Estudio("Ayudantia [M]", "Le pesidte ayuda a un ayudante, es genix y te ilumino la vida :)", 3, Area.Mat); mazoUniversitario.putBack(e9);
		Estudio e10 = new Estudio("Ciac [M]", "Intensivo 024", 2, Area.Mat); mazoUniversitario.putBack(e10);
		Estudio e11 = new Estudio("Desesperacion [M]", "Mañana tienes certamen, son las 10pm, estas lisx para estudiar hartas horas, tienes cafecito y unas papitas <3.", 3, Area.Mat); mazoUniversitario.putBack(e11);
		Estudio e12 = new Estudio("Videos de Juanito", "Ver todos los videos de juanito decorridos con descansos cada dos videos :'c ", 4, Area.Mat); mazoUniversitario.putBack(e12);
		Estudio e13 = new Estudio("Desesperacion [I]", "Son las 3 am, te acabas de comer un arrocito y te tomaste una energetica, listx y motivadx para salvar :D", 4, Area.Mat); mazoUniversitario.putBack(e13);
		Estudio e14 = new Estudio("Lds", "Usando un computador", 2, Area.Inf);mazoUniversitario.putBack(e14);
		Estudio e15 = new Estudio("Biblioteca [I]", "Usando una sala de estudio con tele y pizarras + 4 compañerxs", 3, Area.Inf); mazoUniversitario.putBack(e15);
		Estudio e16 = new Estudio("Ayudantia [I]", "Ver los videos que han subido de las ayudantias", 2, Area.Inf); mazoUniversitario.putBack(e16);
		Estudio e17 = new Estudio("Googlear", "Puedes leer algo que no sepas de GeeksforGeeks, tutorialspoint o StackOverflow :)", 2, Area.Inf); mazoUniversitario.putBack(e17);
		Estudio e18 = new Estudio("Lds", "Usando las pizarras", 2, Area.Inf); mazoUniversitario.putBack(e18);
		Estudio e19 = new Estudio("Leer", "Pudes leer el libro recomendado del ramo.", 3, Area.Inf); mazoUniversitario.putBack(e19);
		Estudio e20 = new Estudio("Youtube", "Listx para ver una playlist de 200 videos y asi estar ready para el certamen de mañana? ", 4, Area.Inf); mazoUniversitario.putBack(e20);
		
		Evento ev1 = new Evento("RAV", "Puedes elegir un ramo y mezclarlo con el Mazo Carrera", tipoEv.RAV); mazoUniversitario.putBack(ev1);
		Evento ev2 = new Evento("Cambio Coord", "Puedes elegir un ramo, hay 50% prob que sus creditos aumenten en 3 o disminuyan en 3. Suerte!.", tipoEv.cCoord); mazoUniversitario.putBack(ev2);
		Evento ev3 = new Evento("Buff Mate", "Aumenta en un 25% el puntaje maximo que pueden entregar todas las cartas de estudio a un ramo de Mate durante este turno", tipoEv.Buff); mazoUniversitario.putBack(ev3);
		Evento ev4 = new Evento("Buff Hum", "Aumenta en un 25% el puntaje maximo que pueden entregar todas las cartas de estudio a un ramo Humanista durante este turno", tipoEv.Buff); mazoUniversitario.putBack(ev4);
		Evento ev5 = new Evento("Buff Info", "Aumenta en un 25% el puntaje maximo que pueden entregar todas las cartas de estudio a un ramo de Imfo durante este turno", tipoEv.Buff); mazoUniversitario.putBack(ev5);
	}

	/**
	* crearMazoC:
	* Crea las cartas de ramos para agregarlas al Mazo de Carrera.
	*
	* @return void:solo realiza la accion.
	*/
	public void crearMazoC(){
		//10, 3 3 y 4
		Ramo r1 = new Ramo("Lp", "Sebasta, sebasta, sebasta :)", Area.Inf); mazoCarrera.putBack(r1);
		Ramo r2 = new Ramo("EDD", "Practicando se entiende todooo unu y preocupate de hacer buen grupo para las tareas", Area.Inf); mazoCarrera.putBack(r2);
		Ramo r3 = new Ramo("Talf", "Disfrutable :D, no requiere tantas horas de estudio y son entretenidos los automatas (cuando se entienden jajaja)", Area.Inf); mazoCarrera.putBack(r3);
		Ramo r4 = new Ramo("Discretas", "No confundir con Mat021! (es parecido pero se le agregan grafos y algunos algoritmos) <3", Area.Inf); mazoCarrera.putBack(r4);
		Ramo r5 = new Ramo("Mate 2", "Graficar es lejos lo mas entretenidouuu", Area.Mat); mazoCarrera.putBack(r5);
		Ramo r6 = new Ramo("Mate 3", "Online se pasa solito (Geogebra, wolfram y symbolab son tus mejores amigos", Area.Mat); mazoCarrera.putBack(r6);
		Ramo r7 = new Ramo("Mate 4", "Mortal! Evitar a toda costa", Area.Mat); mazoCarrera.putBack(r7);
		Ramo r8 = new Ramo("Historia", "Puros facts <3", Area.Hum); mazoCarrera.putBack(r8);
		Ramo r9 = new Ramo("Politica", "A debatir se ha dicho unu", Area.Hum); mazoCarrera.putBack(r9);
		Ramo r10 = new Ramo("Etica", "Sras y Sres, para pensar.", Area.Hum); mazoCarrera.putBack(r10);
	}

	/**
	* crearTablero:
	* Inicializa el tablero, todos los botones con sus actionListeners, imagenes etc.
	*
	* @return void:solo realiza la accion.
	*/
	public void crearTablero(){
		//Se crean los atributos
		mazoUniversitario = new Mazo();
		mazoCarrera = new Mazo();
		mano = new Mano();
		tablero = new Tablero();

		aprobados = 0;
		reprobados = 0;

		//Se agregan las cartas a los mazos
		crearMazoU();
		mazoUniversitario.shuffle();
		crearMazoC();
		mazoCarrera.shuffle();
		
		//Se agrgan las 6 cartas iniciales a la mano:
		IntStream.range(0, 6).forEach(i -> mano.anadirCarta(mazoUniversitario.draw()));

		titlePnl.setVisible(false);
		strtButPnl.setVisible(false);
		
		//Se inicia el panel que muestra los mazos
		mazosPnl = new JPanel();
		mazosPnl.setBounds(50, 10, 500, 200);
		mazosPnl.setOpaque(false);
		mazosPnl.setVisible(true);


		//Se crea el boton que muestra el mazo universitario
		JButton mazoUBut = new JButton();
		mazoUBut.setIcon(new ImageIcon("cartaUback.png"));
		mazoUBut.setOpaque(false);
		mazoUBut.setContentAreaFilled(false);
		mazoUBut.setBorderPainted(false);
		mazoUBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(!permisoRobar){
					showMessageDialog(null, "Solo puedes robar al comenzo del turno");
					return;
				}
				mano.anadirCarta(mazoUniversitario.draw());

				mano.mostrarMano(manoPnl);
			};
		});

		//Se crea el boton que muestra el mazo de carrera
		JButton mazoCBut = new JButton();
		mazoCBut.setIcon(new ImageIcon("cartaCback.png"));
		mazoCBut.setOpaque(false);
		mazoCBut.setContentAreaFilled(false);
		mazoCBut.setBorderPainted(false);
		mazoCBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMessageDialog(null, "No tienes control sobre tus ramos. :c");
			};
		});

		//Se agregan los botones de mazos a su panel
		mazosPnl.add(mazoUBut);
		mazosPnl.add(mazoCBut);

		//Se crea el panel que muestra los ramos en el tablero
		ramosPnl = new JPanel();

		tablero.mostrarRamos(ramosPnl);

		//Se crea el panel que muestra la mano
		manoPnl = new JPanel();
		manoPnl.setOpaque(false);
		mano.mostrarMano(manoPnl);

		//Se crea el panel que muestra las opciones e info del juego (Menu)
		opPnl = new JPanel();
		opPnl.setBounds(770, 10, 280, 200);
		opPnl.setBackground(Color.white);
		opPnl.setVisible(true);


		//Se crea el boton para jugar una carta de la mano
		JButton jugCarBut = new JButton("Jugar Carta");
		jugCarBut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				permisoRobar = false;

				JPopupMenu popup = new JPopupMenu();
				
				mano.seleccionarCarta(popup, opPnl, tablero, mazoCarrera, manoPnl, ramosPnl, hdisp);

				tablero.mostrarRamos(ramosPnl);
				
			};
		});

		//Se crea el boton que termina con el turno
		JButton termTurno = new JButton("Terminar turno");
		termTurno.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				terminarTurno();
			};
		});


		//Se agregan los botones de jugar Carta y terminar turno a su panel (Menu)
		opPnl.add(jugCarBut);
		opPnl.add(termTurno);

		//Se limpia el contenedor de la opcion inicial
		con.removeAll();

		con.add(mazosPnl);
		con.add(ramosPnl);
		con.add(manoPnl);
		con.add(opPnl);


		//Valida el objeto juego para poder mostrar las imagenes en los botones
		validate();

		//Inicializa las labels que muestran info en el Menu
		aprobadosLb = new JLabel();
		reprobadosLb = new JLabel();
		hdisp = new JLabel();

		//Empieza el primer turno, los otros se inician luego de apretar terminar turno
		inicioTurno();
	}

	/**
	* inicioTurno:
	* Reinicia todas los atributos y variables necesarias para empezar un nuevo turno
	*
	* @return void:solo realiza la accion.
	*/
	public void inicioTurno() {
		
		aprobadosLb.setText("Ramos Aprobados: " + aprobados);
		
		reprobadosLb.setText("Ramos Reprobados: " + reprobados);

		permisoRobar = true;
		
		tablero.resetTab();
		Ramo r1, r2;

		r1 = (Ramo)mazoCarrera.draw();
		r2 = (Ramo)mazoCarrera.draw();

		if(r1 == null || r2 == null){
			terminarJuego();
		}


		tablero.agregarRamo(r1, mazoCarrera);
		tablero.agregarRamo(r2, mazoCarrera);

		
		tablero.mostrarRamos(ramosPnl);

		hdisp.setText("Horas Disponibles: " + tablero.getHoras());
		
		opPnl.add(aprobadosLb);
		opPnl.add(reprobadosLb);
		opPnl.add(hdisp);

	}
	
	/**
	* terminarTurno:
	* Calcula las notas de los ramos, y revisa si se aprobaron o reprobaron ramos y si se gano no.
	*
	* @return void:solo realiza la accion.
	*/
	public void terminarTurno(){
		for(Ramo r : tablero.semestre){
			int nf = r.caluclarNota();
			if(nf >= 55){
				aprobados += 1;
				showMessageDialog(null, "Felicitaciones!!! <3 Aprobaste " + r.nombre + "\n con nota " + nf);
				aprobadosLb.setText("Ramos Aprobados: " + aprobados);
			} else {
				reprobados += 1;
				showMessageDialog(null, "Ounouu reprobaste " + r.nombre + "\n con nota " + nf +" animo que un puedes salvar el semestre <3");
				reprobadosLb.setText("Ramos Reprobados: " + reprobados);
			}
			
			tablero.mostrarRamos(ramosPnl);
		}

		if(aprobados < 4 && reprobados < 2){
			//Empieza un nuevo turno
			inicioTurno();
		}else if (aprobados >= 4){
			showMessageDialog(null, "Felicitaciones Ganaste!!!!!!!!");
			terminarJuego();
		}else if (reprobados >= 2){
			showMessageDialog(null, "Pucha oh perdiste D:");
			terminarJuego();
		}
	}

	/**
	* terminarJuego:
	* Termina el juego, se pregunta si se quiere jugar de nuevo o cerrar la gui.
	*
	* @return void:solo realiza la accion.
	*/
	public void terminarJuego(){
		//Se acabo el hjuego

		ramosPnl.setVisible(false);
		opPnl.setVisible(false);
		manoPnl.setVisible(false);
		mazosPnl.setVisible(false);
		con.removeAll();

		finpnl = new JPanel();
		finpnl.setBounds(150, 275, 900, 200);
		finpnl.setBackground(new Color(201, 44, 41));
		finpnl.setVisible(true);

		JLabel fin = new JLabel("Se acabo el juego!");
		fin.setFont(titFont);
		fin.setBackground(new Color(201, 44, 41));
		fin.setForeground(Color.white);
		
		JLabel preg = new JLabel("Quieres jugar de nuevo?");
		preg.setFont(strBtFont);
		preg.setBackground(new Color(201, 44, 41));
		preg.setForeground(Color.white);


		JButton si = new JButton("Si");
		si.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				finpnl.setVisible(false);
				crearTablero();
				
			}
		});

		JButton no = new JButton("No");
		no.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		finpnl.add(fin);
		finpnl.add(preg);
		finpnl.add(si);
		finpnl.add(no);
		con.add(finpnl);
	}
}

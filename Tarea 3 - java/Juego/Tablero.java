package Juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import static javax.swing.JOptionPane.showMessageDialog;

import Cartas.*;

public class Tablero {
	public List<Ramo> semestre;
	int horasDisp;
	
	public Tablero() {
		horasDisp = 12;
		semestre = new ArrayList<Ramo>(10);
	}

	/**
	* resetTab:
	* Reinicia el tablero en caso de que se quiera jugar de nuevo.
	*
	* @return void:Solo realiza la accion.
	*/
	public void resetTab() {
		horasDisp = 12;
		semestre.clear();
	}

	/**
	* getHoras:
	* Retorna la cantidad de horas disponibles.
	*
	* @return int:La cantidad de horas disponibles.
	*/
	public int getHoras() {
		return horasDisp;
	}

	public void jugarEstudio(Estudio est, int pos, JPanel pnl, JLabel hdisp) {
		/*aplica la carta estudio a la carta de
		ramo en la posicion pos, descontando la cantidad de horas disponibles indicadas en la
		carta*/

		if(pos == -1){
			JPopupMenu elegRamo = new JPopupMenu("Elige sobre que Ramo jugar la Carta: ");
			for(Ramo r : semestre){
				JMenuItem ramo = new JMenuItem(r.nombre);
				ramo.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JMenuItem menuItem = (JMenuItem) e.getSource(); 
						horasDisp -= est.horas;
						semestre.get(elegRamo.getComponentZOrder(menuItem)).anadirEstudio(est);
						hdisp.setText("Horas Disponibles: "+horasDisp);
						if(horasDisp <= 0){
							showMessageDialog(null, "Se acabo tu turno! ya no tienes mas horas disponibles.");
							return;
						}
					}
				});
				elegRamo.add(ramo);
			}
			elegRamo.show(pnl, 100, 50);
		}
		
		
	}
	
	/**
	* mostrarRamos:
	* Muestra los ramos en el Tablero, ya que realice una GUI, el tablero se muestra a lo largo de todo el programa, por eso preferi usar esta funcion
	* paramostrar los ramos en el tablero.
	*
	* @param pnl JPanel:JPanel en el que se muestran los ramos.
	* @return void:Solo realiza la accion.
	*/
	public void mostrarRamos(JPanel pnl) {

		pnl.removeAll();
		pnl.revalidate();
		pnl.repaint();
		pnl.setBounds(200, 230, 800, 250);
		pnl.setOpaque(false);
		pnl.setVisible(true);
		
		for (Carta r : semestre) {
			JButton but = new JButton(r.nombre);
			but.setIcon(new ImageIcon("cartaRamo.png"));
			but.setText(r.nombre);
			but.setHorizontalTextPosition(SwingConstants.CENTER);
			but.setForeground(Color.white);
			but.setOpaque(false);
			but.setContentAreaFilled(false);
			but.setBorderPainted(false);
			but.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					r.mostrarCarta();
				}
			});
			pnl.add(but);
		}
	}
	/**
	* agregarRamo:
	* Agrega un ramo al tablero.
	*
	* @param r Ramo:Elramo a agregar al semestre.
	* @param c Mazo:Mazo del que viene la carta ramo.
	* @return void:Solo realiza la accion.
	*/
	public void agregarRamo(Ramo r, Mazo c) {
		semestre.add(r);
		c.draw();
	}

	
}

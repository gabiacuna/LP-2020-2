package Cartas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;

import Juego.*;

public class Evento extends Carta {

	public enum tipoEv {
		RAV, Buff, cCoord;
	}

	public tipoEv evento;

	public Evento(String n, String l, tipoEv ev) {
		super(n, l);
		evento = ev;
	}

	/**
	* aplicarEvento:
	* Hace lo que se pidio, pero tiene algunos parametros extra por la GUI.
	*
	* @param tablero Tablero:El tablero del juago.
	* @param mazo Mazo:Mazo carrera para devolver el ramo sobre el cual se aplica RAV.
	* @param mano Mano:mano para devolver las cartas lugo de jugar RAV.
	* @param opPnl JPanel:Panel en el cual se mostrara el popup menu para elegir sobre que ramo aplicar la carta evento.
	* @param tabPnl JPanel:Tablero del juego, necesario para actualizar cualquier cambio en los ramos.
	* @param manPnl JPanel:Panel que muestra la mano, necesario para actualizar cualquier cambio en la mano.
	* @return void:Solo realiza la accion.
	*/
	public void aplicarEvento(Tablero tablero, Mazo mazo, Mano mano, JPanel opPnl, JPanel tabPnl, JPanel manPnl) {	//RAV y Cambio de Cordinacion

		JPopupMenu elegirRamo = new JPopupMenu("Elige a que ramo le quieres aplicar la carta evento :)");
		for(Ramo r : tablero.semestre){
			JMenuItem ramo = new JMenuItem(r.nombre);
			ramo.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JMenuItem menuItem = (JMenuItem) e.getSource(); 
					JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent(); 
					int pos = popupMenu.getComponentZOrder(menuItem);

					switch (evento) {
						case RAV:
							int cantEst = r.estudios.size();

							for(int i = 0; i < cantEst; i++){
								Estudio estudiada = r.estudios.get(0);
								mano.anadirCarta(estudiada);
								System.out.println("Se devolvio " + estudiada.nombre + " a la mano");
								r.estudios.remove(estudiada);
							}

							mazo.putBack(r);

							tablero.semestre.remove(pos);

							tablero.mostrarRamos(tabPnl);
							mano.mostrarMano(manPnl);

							break;

						case cCoord:

							if (Math.random() < 0.5) {
								r.setcBonus(3);
								showMessageDialog(null, "Al ramo, " + r.nombre + " se le agregaron 3 creditos");
							} else {
								r.setcBonus(-3);
								showMessageDialog(null, "Al ramo, " + r.nombre + " se le quitaron -3 creditos");
							}
							break;
						default:
							break;
					}
				}
			});
			elegirRamo.add(ramo);
		}
		elegirRamo.show(opPnl, 100, 50);
	}

	public void aplicarEvento(Tablero tablero, Area a) { // Buff
		for (int i = 0; i < tablero.semestre.size(); i++) {
			Ramo r = tablero.semestre.get(i);
			if (r.area == a) {
				for (int j = 0; j < r.estudios.size(); j++) {
					Estudio e = r.estudios.get(j);
					e.max *= 1.25;
				}
			}
		}
		showMessageDialog(null, "Se aumento en un 25% el puntaje maximo de las cartas de estudio aplicadas sobre las cartas de area " + a.getNcomp());
	}

	/**
	* jugarEvento:
	* Este metodo redirecciona la carta de evento elegida para jugar hacia la funcion que le corresponda.
	*
	* @param tablero Tablero: Es el tablero del juego, necesario para la funcion aplicarEvento.
	* @param mazo Mazo: Es el mazo carrera que se necesita para el evento RAV.
	* @param mano Mano: Mano, que se necesita en caso de aplicar el evento RAV.
	* @param pnl JPanel: Es el panel en el que se dan las opciones, ahi se muestra el popup menu para elegir a que ramo aplicarle RAV.
	* @param tabPnl JPanel: Es el panel en el que se muestran los ramos en el tablero, para actualizarlo una vez que se hayan aplicado los eventos.
	* @param manPnl JPanel: Es el panel en el que se muestra lamano, para actualizarla una vez que se haya aplicado RAV.
	* @return void: solo realiza la accion.
	*/
	public void jugarEvento(Tablero tablero,Mazo mazo, Mano mano, JPanel pnl, JPanel tabPnl, JPanel manPnl){
		
		if(this.nombre == "Buff Mate"){
			aplicarEvento(tablero, Area.Mat);
		} else if (this.nombre == "Buff Hum"){
			aplicarEvento(tablero, Area.Hum);
		} else if (this.nombre == "Buff Info"){
			aplicarEvento(tablero, Area.Inf);			
		} else {
			aplicarEvento(tablero, mazo, mano, pnl, tabPnl, manPnl);
		}

		tablero.mostrarRamos(tabPnl);
	}
	
	@Override
	public void mostrarCarta() {
		JFrame frame = new JFrame("Carta Evento:" + nombre);

		JOptionPane.showMessageDialog(frame,
				"Nombre:\t" + nombre +
				"\nArea:\t" + evento.name() +
			    "\nLore:\t" + lore,
			    "Carta Evento:\t" + nombre,
			    JOptionPane.INFORMATION_MESSAGE);

	}

	//Comentado en Carta
	@Override
	public String getName(){
		return "Evento";
	}

}
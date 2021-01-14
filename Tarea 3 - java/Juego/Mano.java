package Juego;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

public class Mano {

	public List<Carta> mano;

	public Mano() {
		mano = new ArrayList<Carta>(6);
	}

	public void mostrarMano(JPanel pnl) {

		pnl.removeAll();
		pnl.revalidate();
		pnl.repaint();
		pnl.setBounds(0, 500, 1200, 400);
		pnl.setVisible(true);

		for (Carta c : mano) {
			JButton carta = new JButton();
			if(c.getName()=="Estudio"){
				carta.setIcon(new ImageIcon("cartaEstudio.png"));
			} else {
				carta.setIcon(new ImageIcon("cartaEvento.png"));
			}
			carta.setText(c.nombre);
			carta.setHorizontalTextPosition(SwingConstants.CENTER);
			carta.setForeground(Color.white);
			carta.setOpaque(false);
			carta.setContentAreaFilled(false);
			carta.setBorderPainted(false);
			carta.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					c.mostrarCarta();
				}
			});
			pnl.add(carta);
		}

	}

	/**
	* seleccionarCarta:
	* Es una modificacion de del metodo original, seleccionarCarta, pero que funciona para la GUI. Y dependiendo de la carta que se seleccione se llama a su efecto. 
	*
	* @param popup JPopupMenu:Popup menu que se muestra en la zona de Info del tablero para elegir una carta.
	* @param opPnl Jpanel:Panel en el que se muestra el popup menu.
	* @param t Tablero:Se necesita el tablero dependiendo de la carta que se eliga.
	* @param mC Mazo:Mazo Carrera, dependiendo de la carta elegida, se puede necesitar (RAV). 
	* @param mP JPanel:Panel en el que se encuentra la mano, este se necesita para actualizarla en la gui. 
	* @param tP JPanel:Panel en el que se encuentra el tablero, se necesita segun la carta quye se eliga para poder actualizarlo. 
	* @param hdisp JLabel:Es la Label que se debe actualizar una vez que se juaga una carta de estudio y bajen las horas disponibles. 
	* @return Tipo:Descripcion.
	*/
	public void seleccionarCarta(JPopupMenu popup, JPanel opPnl, Tablero t, Mazo mC, JPanel mP, JPanel tP, JLabel hdisp){
		Mano mano_act = this;
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					JMenuItem menuItem = (JMenuItem) e.getSource(); 
					JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent(); 
					
					Carta elegida = mano.get(popupMenu.getComponentZOrder(menuItem));

					if(elegida.getName() == "Estudio"){
						if(t.horasDisp < ((Estudio)elegida).horas){
							showMessageDialog(null, "No tienes tiempo suficiente para realizar esta accion,\nprueba con otra o termina el turno u.u");
							return;
						}
						t.jugarEstudio((Estudio)elegida, -1, opPnl, hdisp);
					}else if(elegida.getName() == "Evento"){
						((Evento)elegida).jugarEvento(t, mC, mano_act, opPnl, tP, mP);
					}

					bajarCarta(popupMenu.getComponentZOrder(menuItem), mP);
					t.mostrarRamos(tP);
				
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		};
		
		for(Carta c : mano){
			JMenuItem carta = new JMenuItem(c.nombre);
			carta.addActionListener(al);
			popup.add(carta);
		}
		
		popup.show(opPnl, 100, 50);

	}
	
	public void anadirCarta(Carta carta) {
		if(mano.size() >= 6){
			showMessageDialog(null, "Ya tienes 6 cartas en tu mano");
			return;
		}
		mano.add(carta);
	}
	
	//No la uso nunca ya que se llama con el JPanel para mostrar las opciones en la GUI.
	public Carta seleccionarCarta(int pos) {
		return mano.get(pos);
	}

	/**
	* bajarCarta:
	* Elimina una carta de la mano del jugador
	*
	* @param i int:posicion en la que se encuentra la carta.
	* @param m JPanel:Panel que contiene a la mano, para poder actualizarlo.
	* @return void:solo realiza la accion.
	*/
	public void bajarCarta(int i, JPanel m) {
		mano.remove(i);
		this.mostrarMano(m);
	}
}

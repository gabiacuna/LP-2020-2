package Cartas;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Estudio extends Carta {
	
	final String C = "Comun";
	final String R = "Rara";
	final String E = "Epica";
	
	public int horas;
	Area area;
	int min, max;
	String tipo;
	
	public Estudio(String n, String l, int horas, Area a) {
		super(n, l);
		area = a;
		if(horas == 2) {
			tipo = C;
			this.horas = 2;
			min = 18;
			max = 26;
		}else if(horas == 3) {
			tipo = R;
			this.horas = 3;
			min = 28;
			max = 40;
		}else if(horas == 4) {
			tipo = E;
			this.horas = 4;
			min = 20;
			max = 90;
		}else {
			System.out.println("El tipo ingresado no existe");
		}
	}
	
	public int caluclarBonus() {
		return (int) ((Math.random() * (max+1 - min)) + min);
	}

	@Override	
	public void mostrarCarta() {
		JFrame frame = new JFrame("Carta Ramo:" + area);
		
		JOptionPane.showMessageDialog(frame,
				"Nombre:\t" + nombre +
				"\nArea:\t" + area.getNcomp() +
				"\nTipo:\t" + tipo +
			    "\nLore:\t" + lore +
			    "\nHoras:\t" + horas,
			    "Carta Estudio:\t" + nombre,
			    JOptionPane.INFORMATION_MESSAGE);
	}

	//Comentada en Carta.java
	@Override
	public String getName() {
		return "Estudio";
	}

}

package Cartas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Ramo extends Carta {
	
	Area area;	//Inclui el atributo creditos en el enum Area
	int cBonus;
	List<Estudio> estudios;
	
	public Ramo(String n, String l, Area a) {
		super(n, l);
		area = a;
		cBonus = 0;
		estudios = new ArrayList<Estudio>();
	}
	
	/**
	* setcBonus:
	* Le da valor al parametro cBonus, en caso de quse aplique la carta evento Cambio de Coordinacion sobre el ramo
	*
	* @param cBonus int:valor del bonus, puede ser 3 o -3.
	* @return void:Solo asigna el valor.
	*/
	public void setcBonus(int cBonus) {
		this.cBonus = cBonus;
	}

	public int caluclarNota() {
		int nf = 1;
		String txt = "";
		for (int i = 0; i < estudios.size(); i++) {
			double bon = estudios.get(i).caluclarBonus();
			if(estudios.get(i).area.getNcomp() == this.area.getNcomp()) {
				bon *= 1.25;
				txt += "Se aplico bonus ( " + this.area + "-" + estudios.get(i).nombre + ")\n";
			}
			nf += bon;
		}
		if(txt.length() > 1){
			JOptionPane.showMessageDialog(null, txt);
		}
		
		nf -= 2*(area.getC() + cBonus);
		if(nf>100){nf = 100;}
		return nf;
	}
	
	public void anadirEstudio(Estudio estudio) {
		estudios.add(estudio);
	}
	
	@Override
	public void mostrarCarta() {
		JFrame frame = new JFrame("Carta Ramo:" + area);
		
		String txt = "Nombre:\t" + nombre +
				"\nArea:\t" + area.getNcomp() +
			    "\nLore:\t" + lore +
			    "\nCreditos:\t" + area.getC();
		
		if(estudios.size() > 0) {
			txt += "\nCartas de Estudio jugadas:\n";
		}else {
			txt += "\nNo se han jugado cartas de Estudio";
		}
		
		for (int i = 0; i < estudios.size(); i++) {
			txt += "  ·" + estudios.get(i).nombre + "( " + estudios.get(i).tipo + " " + estudios.get(i).area.getNcomp()+
					"~" + estudios.get(i).horas +"h )\n";
		}
		
		JOptionPane.showMessageDialog(frame,
				txt,
			    "Carta Ramo:\t" + nombre,
			    JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public String getName() {
		return "Ramo";
	}

}

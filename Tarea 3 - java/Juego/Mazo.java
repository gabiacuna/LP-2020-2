package Juego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Cartas.Carta;

public class Mazo {
	
	List<Carta> mazo;
	String name;
	
	public Mazo() {
		mazo = new ArrayList<Carta>();
	}
	
	public Carta draw() {
		if(mazo.size() <= 0) {
			return null;
		}
		Carta c = mazo.get(0); 
		mazo.remove(0);
		return c;
	}
	
	public void shuffle() {
		Collections.shuffle(mazo);
	}
	
	public void putBack(Carta carta) {
		mazo.add(carta);
	}
}

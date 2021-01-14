package Cartas;

public abstract class Carta {
	
	public enum Area {
		Hum(2, "Humanista"), Inf(5, "Informatica"), Mat(7, "Matematica");

		//private static final Creditos[] creditos = Creditos.values();
		private final int cred;
		private final String name;

		Area(int val, String n) {
			 this.cred = val;
			 this.name = n;
		}

		public int getC() {
			return cred;
		}

		public String getNcomp() {
			return name;
		}
	}
	
	public String nombre;
	String lore;
	
	public Carta(String n, String l) {
		nombre = n;
		lore = l;
	}
	
	public abstract void mostrarCarta();

	/**
	* getName:
	* Es para ver de que tipo es la carta, retorna el nombre de la clase
	*
	* @return String:Es el nombre de la Clase a la que pertenece el objeto.
	*/
	public abstract String getName();
}

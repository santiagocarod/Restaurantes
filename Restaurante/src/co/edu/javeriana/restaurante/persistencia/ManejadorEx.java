package co.edu.javeriana.restaurante.persistencia;

public class ManejadorEx extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public ManejadorEx(String mensaje) {
		this.mensaje=mensaje;
	}

	@Override
	public String toString() {
		return  mensaje;
	}

}

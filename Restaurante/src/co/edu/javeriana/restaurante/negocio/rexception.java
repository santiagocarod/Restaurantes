package co.edu.javeriana.restaurante.negocio;

public class rexception extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String mensaje;
	public rexception(String mensaje) {
		this.mensaje=mensaje;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return mensaje;
	}
	

}

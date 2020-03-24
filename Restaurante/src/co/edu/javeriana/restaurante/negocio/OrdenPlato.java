package co.edu.javeriana.restaurante.negocio;
import java.io.Serializable;

/**
 * 
 */

/**
 * @author santiago
 *
 */
public class OrdenPlato implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int cantidad;
	Plato plato;

	/**
	 * Constructor de la clase intermedia de la relacion muchos a muchos de orden y
	 * plato, el constructor asigna un plato y una cantidad a la orden para asi
	 * saber cuantos platos hay de un tipo en una orden
	 * 
	 * @param plato Plato a ser asignado a la orden
	 * @param cantidad Cantidad del mismo plato que se pidieron en esta orden
	 */
	public OrdenPlato(Plato plato, int cantidad) {
		this.plato = plato;
		this.cantidad = cantidad;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the plato
	 */
	public Plato getPlato() {
		return plato;
	}

	/**
	 * @param plato
	 *            the plato to set
	 */
	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrdenPlato [cantidad=" + cantidad + ", plato=" + plato + "]";
	}

}

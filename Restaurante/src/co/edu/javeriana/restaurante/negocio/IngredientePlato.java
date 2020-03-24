package co.edu.javeriana.restaurante.negocio;
import java.io.Serializable;

/**
 * 
 */

/**
 * @author santiago
 *
 */
public class IngredientePlato implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int cantidad;
	Ingrediente ingrediente;

	/**
	 * Constructor de la clase IngredientePlato que es la que se encarga de mantener
	 * la relacion muchos a muchos entre plato e ingrediente, este constructor
	 * recibe por parametro todos los valores del nuevo objeto intermedio
	 * 
	 * @param ingrediente El ingrediente que se esta agregando al plato
	 * @param cantidad la cantidad del respectivo ingrediente.
	 */
	public IngredientePlato(Ingrediente ingrediente, int cantidad) {
		this.ingrediente = ingrediente;
		this.cantidad = cantidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IngredientePlato [cantidad=" + cantidad + ", ingrediente=" + ingrediente + "]";
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
	 * @return the ingrediente
	 */
	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	/**
	 * @param ingrediente
	 *            the ingrediente to set
	 */
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

}

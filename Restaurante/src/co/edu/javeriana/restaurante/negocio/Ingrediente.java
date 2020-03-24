package co.edu.javeriana.restaurante.negocio;
import java.io.Serializable;

/**
 * 
 */

/**
 * @author santiago
 *
 */
public class Ingrediente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nombre;
	private long precioUnitario;
	private String descripcionUnidad;
	private int inventario;
	private int minimo;

	/**
	 * Constructor de una ingrediente que pone toos los valores de un ingrediente en
	 * los que son por default.
	 */
	public Ingrediente() {
		codigo = 0;
		nombre = "";
		precioUnitario = 0;
		descripcionUnidad = "";
		inventario = 0;
		minimo = 0;
	}

	/**
	 * Constructor de un nuevo ingrediente que recibe por parametro todos los datos
	 * de un nuevo ingrediente.
	 * 
	 * @param cod
	 *            codigo del nuevo ingrediente
	 * @param nombre
	 *            Nombre del nnuevo Ingrediente.
	 * @param precioUni
	 *            Precio Unitario del nuevo Ingrediente
	 * @param descripcionU
	 *            Descripcion unitaria del nuevo ingrediente.
	 * @param inventario
	 *            Cuantos existen en inventario del ingrediente
	 * @param minimo
	 *            El minimo que se debe tener en inventario del nuevo ingrediente.
	 */
	public Ingrediente(int cod, String nombre, int precioUni, String descripcionU, int inventario, int minimo) {
		this.codigo = cod;
		this.nombre = nombre;
		this.precioUnitario = precioUni;
		this.descripcionUnidad = descripcionU;
		this.inventario = inventario;
		this.minimo = minimo;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the precioUnitario
	 */
	public long getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario
	 *            the precioUnitario to set
	 */
	public void setPrecioUnitario(long precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return the descripcionUnidad
	 */
	public String getDescripcionUnidad() {
		return descripcionUnidad;
	}

	/**
	 * @param descripcionUnidad
	 *            the descripcionUnidad to set
	 */
	public void setDescripcionUnidad(String descripcionUnidad) {
		this.descripcionUnidad = descripcionUnidad;
	}

	/**
	 * @return the inventario
	 */
	public int getInventario() {
		return inventario;
	}

	/**
	 * @param inventario
	 *            the inventario to set
	 */
	public void setInventario(int inventario) {
		this.inventario = inventario;
	}

	/**
	 * @return the minimo
	 */
	public int getMinimo() {
		return minimo;
	}

	/**
	 * @param minimo
	 *            the minimo to set
	 */
	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ingrediente [codigo=" + codigo + ", nombre=" + nombre + ", precioUnitario=" + precioUnitario
				+ ", descripcionUnidad=" + descripcionUnidad + ", inventario=" + inventario + ", minimo=" + minimo
				+ "]";
	}

	/**
	 * Metodo que recibe por parametro la cantidad de ingredientes a eliminar del
	 * inventario del mismo ingrediente. En dado caso de que la cantidad a eliminar
	 * sea mayor al del inventario menos el minimo pues no se puede eliminar por que
	 * no se estaria manteniendo el minimo, en dado caso de que no entonces si se
	 * puede pedir el plato y se eliminan los ingredientes del inventario.
	 * 
	 * @param can cantidad de ingredientes a eliminar del inventario
	 * @return verdadero si se pudo eliminar del inventario y falso en caso contrario.
	 */
	public boolean eliminarInventario(int can) {
		boolean b = true;
		if (can > this.inventario - this.minimo) {
			b = false;
		} else {
			setInventario(this.inventario - can);
		}
		return b;
	}

}

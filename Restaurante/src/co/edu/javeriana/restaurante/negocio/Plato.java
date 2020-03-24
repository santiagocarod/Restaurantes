package co.edu.javeriana.restaurante.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author santiago
 *
 */
public abstract class Plato implements Serializable {
	public enum enumEstado{ACTIVO, DESCONTINUADO};
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected int codigo;
	protected String nombre;
	protected int precio;
	protected int precioAcumulado;
	protected int vecesPedido;
	protected enumEstado estado;
	protected List<IngredientePlato> ingredientePlato = new ArrayList<IngredientePlato>();
	

	/**
	 * Constructor de un nuevo plato que recibe por parametro la informacion ya
	 * sacada de un archivo o por el estilo y crea un nuevo plato
	 * 
	 * @param codPlato
	 *            El codigo del nuevo plato a ser creado.
	 * @param nomPlato
	 *            El nombre del nuevo plato a ser creado.
	 */
	public Plato(int codPlato, String nomPlato) {
		this.codigo = codPlato;
		this.nombre = nomPlato;
		precioAcumulado = 0;
		vecesPedido = 0;
		estado=enumEstado.ACTIVO;
	}
	

	/**
	 * @return the estado
	 */
	public enumEstado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(enumEstado estado) {
		this.estado = estado;
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
	 * @return the precio
	 */
	public int getPrecio() {
		return precio;
	}

	/**
	 * @param precio
	 *            the precio to set
	 */
	public void setPrecio(int precio) {
		this.precio = precio;
	}

	/**
	 * @return the precioAcumulado
	 */
	public int getPrecioAcumulado() {
		return precioAcumulado;
	}

	/**
	 * @param precioAcumulado
	 *            the precioAcumulado to set
	 */
	public void setPrecioAcumulado(int precioAcumulado) {
		this.precioAcumulado = precioAcumulado;
	}

	/**
	 * @return the vecesPedido
	 */
	public int getVecesPedido() {
		return vecesPedido;
	}

	/**
	 * @param vecesPedido
	 *            the vecesPedido to set
	 */
	public void setVecesPedido(int vecesPedido) {
		this.vecesPedido = vecesPedido;
	}

	/**
	 * @return the ingredientePlato
	 */
	public List<IngredientePlato> getIngredientePlato() {
		return ingredientePlato;
	}

	/**
	 * @param ingredientePlato
	 *            the ingredientePlato to set
	 */
	public void setIngredientePlato(List<IngredientePlato> ingredientePlato) {
		this.ingredientePlato = ingredientePlato;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Plato [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", precioAcumulado="
				+ precioAcumulado + ", vecesPedido=" + vecesPedido + ", ingredientePlato=" + ingredientePlato + "]";
	}

	public abstract void calcularPrecio();

	/**
	 * Metodo que permite agregar un ingrediente a un respectivo plato sabiendo cual
	 * es la referencia al ingrediente y la cantidad de este mismo ingrediente para
	 * el plato. Lo hace por medio de la clase intermedia IngredientePlato
	 * 
	 * @param ingrediente
	 *            Ingrediente a ser agregado a el plato
	 * @param cantidad
	 *            La cantidad de este mismo ingrediente.
	 */
	public void agregarIngrediente(Ingrediente ingrediente, int cantidad) {
		IngredientePlato ingPlat = new IngredientePlato(ingrediente, cantidad);
		ingredientePlato.add(ingPlat);

	}

	/**
	 * Metodo que aumenta la cantidad de veces que un mismo plato ha sido ordenado.
	 * 
	 * @param cantidad
	 *            recibe como parametro la cantidad de veces que el plato ha sido
	 *            ordenado para poder aumentar en 1 dicha cantidad.
	 */
	public void aumentarVeces(int cantidad) {
		this.vecesPedido += cantidad;
		this.precioAcumulado += (this.precio * cantidad);
	}

	/**
	 * Metodo que agrega el IVA al precio del plato
	 * 
	 */
	public void agregarIva() {
		int nuevo = (int) (this.precio * 0.16);
		precio += nuevo;
	}

	/**
	 * Metodo que recorre todos los atributos de ingredientePlato de cierto plato y
	 * trata de eliminar la cantidad de ingredientes almacenada en IngredientePlato
	 * a cada ingrediente para saber si se puede hacer la orden con un plato en
	 * especifico.
	 * 
	 * @return Un booleano verdadero si se pudo eliminar del inventario y falso si
	 *         no.
	 */
	public boolean eliminarInventario() {
		boolean b = true;
		for (IngredientePlato ip : ingredientePlato) {
			int can = ip.getCantidad();
			Ingrediente i = ip.getIngrediente();
			if (!(i.eliminarInventario(can))) {
				b = false;
			}
		}
		return b;
	}

	/**
	 * Metodo que revisa que si a un plato se le puede eliminar todos los
	 * ingredientes de el mismo plato.
	 * 
	 * @param cantidad la cantidad de platos que se van a eliminar.
	 * @return verdadero en dado caso que se pueda eliminar y falso en dado caso que no.
	 */
	public boolean eliminarInventario(int cantidad) {
		boolean b = true;
		for (IngredientePlato ip : ingredientePlato) {
			int can = ip.getCantidad();
			Ingrediente i = ip.getIngrediente();
			if (!(i.eliminarInventario(can * cantidad))) {
				b = false;
			}
		}
		return b;
	}

}

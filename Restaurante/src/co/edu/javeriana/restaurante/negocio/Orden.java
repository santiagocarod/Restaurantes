package co.edu.javeriana.restaurante.negocio;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author santiago
 *
 */
public class Orden implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private long valor;
	LocalDateTime fecha;
	List<OrdenPlato> ordenPlato = new ArrayList<OrdenPlato>();

	/**
	 * Constructor de una orden que asigna a la fecha de la orden la del momento en
	 * que se hace la hora, tomando en cuenta tanto horas como minutos
	 * 
	 * @param numOrden
	 *            El numero de orden de la nueva orden el cual viene desde un
	 *            contador.
	 */
	public Orden(int numOrden) {
		this.codigo = numOrden;
		this.fecha = LocalDateTime.now();
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
	 * @return the valor
	 */
	public long getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(long valor) {
		this.valor = valor;
	}

	/**
	 * @return the fecha
	 */

	public void agregarPlato(Plato plato, int cantidad) {
		OrdenPlato ordPla = new OrdenPlato(plato, cantidad);
		ordenPlato.add(ordPla);
		this.valor += (plato.getPrecio() * cantidad);
	}

	/**
	 * @return the fecha
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the ordenPlato
	 */
	public List<OrdenPlato> getOrdenPlato() {
		return ordenPlato;
	}

	/**
	 * @param ordenPlato
	 *            the ordenPlato to set
	 */
	public void setOrdenPlato(List<OrdenPlato> ordenPlato) {
		this.ordenPlato = ordenPlato;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Orden [codigo=" + codigo + ", valor=" + valor + ", fecha=" + fecha + ", ordenPlato=" + ordenPlato + "]";
	}

}

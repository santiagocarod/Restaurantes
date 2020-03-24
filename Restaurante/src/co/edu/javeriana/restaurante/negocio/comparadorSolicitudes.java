package co.edu.javeriana.restaurante.negocio;
import java.util.Comparator;

/**
 * @author Daniel
 *
 */
public class comparadorSolicitudes implements Comparator {
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * Metodo que compara la cantidad de veces que dos platos han sido pedidos.
	 * Si dos platos han sido ordenados la misma cantidad de veces retorna 0
	 * Si el plato 1 ha sido ordenado mas veces que el plato 2, retorna 1
	 * Si el plato 1 fue pedido menos veces que l plato 2, retorna -1
	 */
	public int compare(Object o1, Object o2) {
		Plato p1 = (Plato) o1;
		Plato p2 = (Plato) o2;
		if (p1.getVecesPedido() == p2.getVecesPedido()) {
			return 0;
		} else {
			if (p1.getVecesPedido() > p2.getVecesPedido()) {
				return 1;
			} else
				return -1;
		}
	}
}

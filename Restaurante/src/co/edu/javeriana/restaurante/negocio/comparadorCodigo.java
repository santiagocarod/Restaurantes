package co.edu.javeriana.restaurante.negocio;
import java.util.Comparator;

public class comparadorCodigo implements Comparator{
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * Metodo
	 * que compara dos objetos de tipo plato con relacion a su codigo. En
	 * este caso como son enteros los numeros que estamos comparando solamente
	 * necesitamos restar un numero a otro y asi podemos saber cual es menor o mayor
	 * o son iguales. Primero recibe dos objetos genericos y los convierte a platos.
	 * Despues los resta.
	 * Si retorna un numero >0 entonces significa que el primer plato es mayor
	 * Retorna 0 si son iguales,
	 * Retorne un numero<0 en dado caso de que el primero sea menor que el segundo.
	 */
	public int compare(Object o1, Object o2) {
		Plato p1 = (Plato) o1;
		Plato p2 = (Plato) o2;
		return p1.getCodigo()-p2.getCodigo();
	}

}

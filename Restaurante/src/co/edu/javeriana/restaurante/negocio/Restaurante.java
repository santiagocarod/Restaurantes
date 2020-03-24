package co.edu.javeriana.restaurante.negocio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.edu.javeriana.restaurante.negocio.PlatoCarta.enumDia;
import co.edu.javeriana.restaurante.presentacion.Utils;

/**
 * @author SANTIAGO
 *
 */
/**
 * @author SANTIAGO
 *
 */
/**
 * @author SANTIAGO
 *
 */
/**
 * @author SANTIAGO
 *
 */
/**
 * @author SANTIAGO
 *
 */
public class Restaurante implements Serializable, IRestaurante {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	Map<Integer, Plato> platos = new HashMap<Integer, Plato>();
	List<Orden> ordenes = new ArrayList<Orden>();;

	/**
	 * @return the ingredientes
	 */
	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	/**
	 * @param ingredientes
	 *            the ingredientes to set
	 */
	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	/**
	 * @return the platos
	 */
	public Map<Integer, Plato> getPlatos() {
		return platos;
	}

	/**
	 * @param platos
	 *            the platos to set
	 */
	public void setPlatos(Map<Integer, Plato> platos) {
		this.platos = platos;
	}

	/**
	 * @return the ordenes
	 */
	public List<Orden> getOrdenes() {
		return ordenes;
	}

	/**
	 * @param ordenes
	 *            the ordenes to set
	 */
	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Restaurante [ingredientes=" + ingredientes + "\r\n" + ", platos=" + platos + "\r\n" + ", ordenes="
				+ ordenes + "]" + "\r\n";
	}

	/**
	 * Metodo que crea un nuevo ingrediente dentro de una lista de ingredientes
	 * 
	 * @param cod
	 *            Codigo del ingrediente
	 * @param nombre
	 *            Nombre del ingrediente
	 * @param precioUni
	 *            Precio por unidad del incrediente
	 * @param descripcionU
	 *            Descripcion del ingrediente
	 * @param inventario
	 *            Cantidad del ingrediente
	 * @param minimo
	 *            Minimo de ingredientes que pueden quedar en inventario
	 */
	public void agregarIngrediente(int cod, String nombre, int precioUni, String descripcionU, int inventario,
			int minimo) {
		Ingrediente ingrediente = new Ingrediente(cod, nombre, precioUni, descripcionU, inventario, minimo);
		ingredientes.add(ingrediente);
	}

	/**
	 * Metodo que crea un nuevo plato diario y lo agrega a la lista de platos que
	 * esta dentro del restaurante. Primero lo crea y despues lo agrega.
	 * 
	 * @param codPlato
	 *            codigo del nuevo plato a agregar a la lista
	 * @param nomPlato
	 *            El nombre del plato el cual va a ser agregado a la lista de
	 *            platos.
	 */
	public void agregarPlatoDiario(int codPlato, String nomPlato) {
		PlatoDiario plato = new PlatoDiario(codPlato, nomPlato);
		platos.put(plato.getCodigo(), plato);

	}

	/**
	 * Metodo que crea un nuevo plato carta y lo agrega a la lista de platos que
	 * esta dentro del restaurante. Primero lo crea y despues lo agrega.
	 * 
	 * @param codPlato
	 *            codigo del nuevo plato a agregar a la lista
	 * @param nomPlato
	 *            El nombre del plato el cual va a ser agregado a la lista de
	 *            platos.
	 */
	public void agregarPlatoDiario(int codPlato, String nomPlato, String dia) {
		enumDia e=Utils.convertirDias(dia);
		PlatoCarta plato = new PlatoCarta(codPlato, nomPlato, e);
		platos.put(plato.getCodigo(), plato);

	}

	/**
	 * Metodo que busca un ingrediente en la lista de ingredientes y devuelve su
	 * referencia. Lo hace por un parametro que es el codigo del ingrediente.
	 * 
	 * @param cod
	 *            Codigo del ingrediente a buscar.
	 * @return Retorna null si no encontro ningun ingrediente y el objeto
	 *         ingrediente en caso de haberlo encontrado.
	 */
	public Ingrediente buscarIngrediente(int cod) {
		Ingrediente i = null;
		for (Ingrediente ing : ingredientes) {
			if (ing.getCodigo() == cod) {
				i = ing;
			}
		}
		return i;
	}

	/**
	 * Metodo que busca un plato en la lista de platos y devuelve su referencia. Lo
	 * hace por un parametro que es el codigo del plato.
	 * 
	 * @param cod
	 *            Codigo del plato a buscar.
	 * @return Retorna null si no encontro ningun plato y el objeto plato en caso de
	 *         haberlo encontrado.
	 */
	public Plato buscarPlato(int cod) {
		return platos.get(cod);
	}

	/**
	 * Metodo que agrega un Ingrediente a un plato, para que la clase intermedia de
	 * la relacion muchos a muchos entre plato e ingrediente pueda saber primero
	 * cual es el ingrediente a agregar a la orden y segundo cuantos de estos mismos
	 * ingredientes fueron necesarios para esta misma plato.
	 * 
	 * @param codPlato
	 *            Codigo del plato al cual se le van a agregar un ingrediente.
	 * @param codIng
	 *            Codigo del ingrediente a a ser agregado al plato.
	 * @param cantidad
	 *            cantidad del mismo ingrediente a ser agregado al plato.
	 */
	public void agregarIngredientePlato(int codPlato, int codIng, int cantidad) {
		Ingrediente ingrediente = buscarIngrediente(codIng);
		Plato plato = buscarPlato(codPlato);
		plato.agregarIngrediente(ingrediente, cantidad);

	}

	/**
	 * Metodo que busca una orden en la lista de ordenes y devuelve su referencia.
	 * Lo hace por un parametro que es el codigo de la orden.
	 * 
	 * @param cod
	 *            Codigo de la orden a buscar.
	 * @return Retorna null si no encontro ninguna orden y el objeto orden en caso
	 *         de haberlo encontrado.
	 */
	public Orden buscarOrden(int cod) {
		Orden o = null;
		for (Orden ord : ordenes) {
			if (ord.getCodigo() == cod) {
				o = ord;
			}
		}
		return o;
	}

	/**
	 * Metodo que agrega una orden a la lista de ordenes que tiene el sistema.
	 * 
	 * @param numOrden
	 *            Numero de la nueva orden, viene desde un contador en otra clase.
	 */
	public void agregarOrden(int numOrden) {
		Orden orden = new Orden(numOrden);
		ordenes.add(orden);
	}
	
	public void agregarOrden(Orden o) {
		for(OrdenPlato op:o.getOrdenPlato()) {
			Plato p=op.getPlato();
			p.setVecesPedido(p.getVecesPedido()+op.getCantidad());
			p.setPrecioAcumulado(p.getPrecioAcumulado()+(p.getPrecio()*op.getCantidad()));
		}
		ordenes.add(o);
	}


	/**
	 * Metodo que agrega un plato a una orden, para que la clase intermedia de la
	 * relacion muchos a muchos entre plato y orden pueda saber primero cual es el
	 * plato a agregar a la orden y segundo cuantos de estos mismos platos fueron
	 * pedidos para esta misma orden.
	 * 
	 * @param codPlato
	 *            Codigo del plato a ser agregado a la orden.
	 * @param codOrden
	 *            Codigo de la orden a la cual se le va a agregar este plato.
	 * @param cantidad
	 *            Cantidad del mismo plato que se le va a agregar a esta misma
	 *            orden.
	 */
	public void agregarPlatoOrden(int codPlato, int codOrden, int cantidad) {
		Plato plato = buscarPlato(codPlato);
		Orden orden = buscarOrden(codOrden);
		orden.agregarPlato(plato, cantidad);
		plato.aumentarVeces(cantidad);

	}

	/**
	 * Metodo que agrega el iva al precio de cierto plato, primero busca el plato y
	 * despues llama al metodo de plato que agrega el iva al plato en si.
	 * 
	 * @param codPlato
	 *            codigo del plato al cual se le tiene que calcular y agregar el IVA
	 */
	public void agregarIva(int codPlato) {
		Plato plato = buscarPlato(codPlato);
		if (plato != null) {
			plato.agregarIva();
		}

	}

	/**
	 * Metodo que busca todas las ordenes de un dia en especifico que recibe por
	 * parametro.
	 * 
	 * @param cal
	 *            la fecha a la cual le va a buscar todas las ordenes.
	 * @return la lista de ordenes a las cuales le encontro que su fecha era la del
	 *         dia en especifico.
	 */
	public List<Orden> ordenesDia(LocalDate cal) {
		List<Orden> ord = new ArrayList<Orden>();
		for (Orden o : ordenes) {
			if (o.getFecha().toLocalDate().equals(cal)) {
				ord.add(o);
			}
		}
		return ord;
	}

	/**
	 * Metodo de restaurante que elimina la cantidad de ingredientes necesaria para
	 * cierto plato asi se puede mantener el inventario actualizado. En dado caso de
	 * llegar al minimo de cierto ingrediente no deja que se pida el plato.
	 * 
	 * @param codPlato
	 *            codigo del plato para saber que ingredientes se va a eliminar.
	 * @return Un booleano que retorna verdadero si se pudo eliminar por lo tanto se
	 *         puede hacer la orden y falso si no se pudo hacer el pedido.
	 */
	public boolean eliminarInventario(int codPlato) {
		Plato plato = buscarPlato(codPlato);
		return plato.eliminarInventario();
	}

	/**
	 * Metodo que revisa todas las ordenes de un restaurante y si alguna tiene un
	 * precio de 0 entonces sera eliminada de la lista de ordenes por que no tiene
	 * importancia para las cuentas.
	 * 
	 */
	public void revisarOrdenes() {
		for (int i = 0; i < ordenes.size(); i++) {
			if (ordenes.get(i).getValor() == 0) {
				ordenes.remove(i);
			}
		}
	}

	/**
	 * Metodo que orden la lista de platos y lo hace con respecto a su precio total,
	 * que se calcula mediante su precio por unidad multiplicado por la cantidad de
	 * veces que ha sido pedido, esto nos da que tan rentable ha sido el plato.
	 * 
	 */

	public List<Plato> ordenarPlatosPrecio(List<Plato> pla) {
		Collections.sort(pla, new comparadorPrecioTotal());
		Collections.reverse(pla);
		return pla;
	}

	/**
	 * Metodo que ordena los platos de manera descendente de acuerdo a la cantidad
	 * de veces que el plato fue pedido. Se ordena de plato mas pedido al menos
	 * pedido.
	 * 
	 */
	public List<Plato> ordenarPlatosSolicitudes(List<Plato> pl) {
		Collections.sort(pl, new comparadorSolicitudes());
		Collections.reverse(pl);
		return pl;
	}

	/**
	 * Metodo que ordena una lista de platos con el comparador de su codigo.
	 * @param menu La lista de platos que se va a ordenar
	 * @return una lista de platos ordenada.
	 */
	public List<Plato> ordenarCodigo(List<Plato> menu) {
		Collections.sort(menu, new comparadorCodigo());
		return menu;
	}

	/**
	 * Revisa si hay suficiente inventario de cada ingrediente y devuelve veradero
	 * si se pudo eliminar y falso en dado caso de que no se haya podido eliminar.
	 * 
	 * @param codPlato El codigo del plato a revisar los ingrientes.
	 * @param cantidad La cantidad del mismo plato que se van a tratar de eliminar
	 * @return
	 */
	public boolean eliminarInventario(int codPlato, int cantidad) {
		Plato p = buscarPlato(codPlato);
		return p.eliminarInventario(cantidad);
	}

	/** Metodo que agrega in ingrediente a la lista de ingredientes
	 * @param leerIngredientes La lista que contiene los ingredientes de un plato
	 */
	public void agregarIngredientes(List<Ingrediente> leerIngredientes) {
		for(Ingrediente i: leerIngredientes) {
			if(buscarIngrediente(i.getCodigo())==null) {
				ingredientes.add(i);
			}
		}
		
	}

	/** Metodo que agrega un plato al map de platos
	 * @param leerPlatos El mapa que contiene los platos del restaurante
	 */
	public void agregarPlatos(HashMap<Integer, Plato> leerPlatos) {
		List<Plato> leidos= new ArrayList<Plato>(leerPlatos.values());
		for(Plato p:leidos) {
			if(buscarPlato(p.getCodigo())==null) {
				platos.put(p.getCodigo(), p);
			}
		}
		
	}

	public void agregarPlato(Plato plato) {
		platos.put(plato.getCodigo(),plato);	
	}

}

package co.edu.javeriana.restaurante.negocio;

import java.time.LocalDate;
import java.util.List;

public interface IRestaurante {
	
	public void agregarIngrediente(int cod, String nombre, int precioUni, String descripcionU, int inventario,
			int minimo);
	public void agregarPlatoDiario(int codPlato, String nomPlato) ;
	public void agregarPlatoDiario(int codPlato, String nomPlato, String dia);
	public Ingrediente buscarIngrediente(int cod);
	public Plato buscarPlato(int cod);
	public Orden buscarOrden(int cod);
	public void agregarIngredientePlato(int codPlato, int codIng, int cantidad) ;
	public void agregarOrden(int numOrden);
	public void agregarPlatoOrden(int codPlato, int codOrden, int cantidad);
	public void agregarIva(int codPlato);
	public List<Orden> ordenesDia(LocalDate cal);
	public boolean eliminarInventario(int codPlato);
	public List<Plato> ordenarPlatosPrecio(List<Plato> pla);
	public List<Plato> ordenarPlatosSolicitudes(List<Plato> pl);
	public List<Plato> ordenarCodigo(List<Plato> menu) ;
	public boolean eliminarInventario(int codPlato, int cantidad);

}

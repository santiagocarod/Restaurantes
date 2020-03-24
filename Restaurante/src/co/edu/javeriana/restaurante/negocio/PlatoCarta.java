package co.edu.javeriana.restaurante.negocio;

public class PlatoCarta extends Plato {
	public enum enumDia{LUNES,MARTES,MIERCOLES,JUEVES,VIERNES,SABADO,DOMINGO};
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	enumDia dia;

	
	
	

	/**
	 * @return the dia
	 */
	public enumDia getDia() {
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia(enumDia dia) {
		this.dia = dia;
	}

	/**
	 * El constructor de la clase plato de carta, llama al constructor de la super
	 * clase que es la clase Plato
	 * 
	 * @param codPlato
	 *            el codigo del plato nuevo
	 * @param nomPlato
	 *            el nombre del plato nuevo a crear
	 * @param dia
	 *            el dia del plato en el que se puede pedir.
	 */
	public PlatoCarta(int codPlato, String nomPlato, enumDia dia) {
		super(codPlato, nomPlato);
		this.dia = dia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.javeriana.restaurante.negocio.Plato#calcularPrecio() Es el metodo
	 * que hace la implementacion del metodo abstracto de la clase plato, como es un
	 * plato diario se le tiene que agregar al valor su 10%.
	 */
	@Override
	public void calcularPrecio() {
		int total = 0;
		for (IngredientePlato ip : ingredientePlato) {
			int can = ip.getCantidad();
			Ingrediente i = ip.getIngrediente();
			total += i.getPrecioUnitario() * can;

		}
		total += total * 0.1;
		this.setPrecio(total);

	}


}

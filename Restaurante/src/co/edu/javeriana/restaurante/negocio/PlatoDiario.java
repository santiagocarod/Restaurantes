package co.edu.javeriana.restaurante.negocio;

public class PlatoDiario extends Plato {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase plato Diario el cual puede estar todos los dias
	 * en el menu, llama al construcctor de la super clase que es la clase Plato.
	 * 
	 * @param codPlato
	 *            El codigo del nuevo plato a crear.
	 * @param nomPlato
	 *            El nombre del plato a crear.
	 */
	public PlatoDiario(int codPlato, String nomPlato) {
		super(codPlato, nomPlato);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.javeriana.restaurante.negocio.Plato#calcularPrecio() Es la
	 * implementacion del metodo declarado en la clase plato como abstracto, en
	 * donde se calcula para cada ingrediente su precio y se multiplica por la
	 * cantidad.
	 */
	@Override
	public void calcularPrecio() {
		int total = 0;
		for (IngredientePlato ip : ingredientePlato) {
			int can = ip.getCantidad();
			Ingrediente i = ip.getIngrediente();
			total += i.getPrecioUnitario() * can;

		}
		this.setPrecio(total);

	}

}

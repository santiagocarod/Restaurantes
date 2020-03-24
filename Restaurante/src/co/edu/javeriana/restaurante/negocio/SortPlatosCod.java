package co.edu.javeriana.restaurante.negocio;

import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class SortPlatosCod implements Comparator  {

	@Override
	public int compare(Object o1, Object o2) {
		Plato p1=(Plato) o1;
		Plato p2=(Plato) o2;
		return p1.getCodigo()-p2.getCodigo();
	}


}

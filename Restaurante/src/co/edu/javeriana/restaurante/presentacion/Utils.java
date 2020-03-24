package co.edu.javeriana.restaurante.presentacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.edu.javeriana.restaurante.negocio.PlatoCarta.enumDia;

public class Utils {

	/**
	 * Metodo que devuelve el dia de la semana en el que esta el sistema, en el
	 * formato que esta dado en el enunciado.
	 * 
	 * @return Ej: Si el dia de hoy es martes, devuelve una cadena "ma", si es
	 *         Jueves devuelve "ju"
	 */
	public static String diaSemana() {
		LocalDate hoy = LocalDate.now();
		String dow = hoy.getDayOfWeek().toString();
		if (dow.equalsIgnoreCase("MONDAY")) {
			return "lu";
		} else {
			if (dow.equalsIgnoreCase("TUESDAY")) {
				return "ma";
			} else {
				if (dow.equalsIgnoreCase("WEDNESDAY")) {
					return "mi";
				} else {
					if (dow.equalsIgnoreCase("THURSDAY")) {
						return "ju";
					} else {
						if (dow.equalsIgnoreCase("FRIDAY")) {
							return "vi";
						} else {
							if (dow.equalsIgnoreCase("SATURDAY")) {
								return "sa";
							} else {
								if (dow.equalsIgnoreCase("SUNDAY")) {
									return "do";
								}
							}
						}
					}
				}
			}

		}

		return "";
	}

	/** Metodo que recibe un string y lo convierte a los enumerados respectivos de dia
	 * @param dia el dia de la semana a convertir
	 * @return retorna el dia de la semana convertido al enumerado correspondiente
	 */
	public static enumDia convertirDias(String dia) {
		if (dia.equalsIgnoreCase("lu") || dia.equalsIgnoreCase("lunes")) {
			return enumDia.LUNES;
		}
		if (dia.equalsIgnoreCase("ma") || dia.equalsIgnoreCase("martes")) {
			return enumDia.MARTES;
		}
		if (dia.equalsIgnoreCase("mi") || dia.equalsIgnoreCase("miercoles")) {
			return enumDia.MIERCOLES;
		}
		if (dia.equalsIgnoreCase("ju") || dia.equalsIgnoreCase("jueves")) {
			return enumDia.JUEVES;
		}
		if (dia.equalsIgnoreCase("vi") || dia.equalsIgnoreCase("viernes")) {
			return enumDia.VIERNES;
		}
		if (dia.equalsIgnoreCase("sa") || dia.equalsIgnoreCase("sabado")) {
			return enumDia.SABADO;
		}
		if (dia.equalsIgnoreCase("do") || dia.equalsIgnoreCase("domingo")) {
			return enumDia.DOMINGO;
		}
		return null;
	}

	public static boolean sePuede(enumDia dia) {
		LocalDate hoy = LocalDate.now();
		String dow = hoy.getDayOfWeek().toString();
		if (dow.equals("MONDAY") && (dia == enumDia.LUNES)) {
			return true;
		}
		if (dow.equals("TUESDAY") && (dia == enumDia.MARTES)) {
			return true;
		}
		if (dow.equals("WEDNESDAY") && (dia == enumDia.MIERCOLES)) {
			return true;
		}
		if (dow.equals("THURSDAY") && (dia == enumDia.JUEVES)) {
			return true;
		}
		if (dow.equals("FRIDAY") && (dia == enumDia.VIERNES)) {
			return true;
		}
		if (dow.equals("SATURDAY") && (dia == enumDia.SABADO)) {
			return true;
		}
		if (dow.equals("SUNDAY") && (dia == enumDia.DOMINGO)) {
			return true;
		}
		return false;

	}

	public static String getExtension(String name) {
		List<String> s = new ArrayList<String>();
		s = Arrays.asList(name.split("."));
		System.out.println(s.toString());
		if (s.isEmpty()) {
			return "";
		} else {
			return s.get(1);
		}
	}

}

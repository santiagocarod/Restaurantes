package co.edu.javeriana.restaurante.persistencia;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import co.edu.javeriana.restaurante.negocio.Ingrediente;
import co.edu.javeriana.restaurante.negocio.Plato;
import co.edu.javeriana.restaurante.negocio.PlatoCarta;
import co.edu.javeriana.restaurante.negocio.PlatoCarta.enumDia;
import co.edu.javeriana.restaurante.negocio.PlatoDiario;
import co.edu.javeriana.restaurante.negocio.Restaurante;
import co.edu.javeriana.restaurante.presentacion.Utils;

public class ManejadorArchivos {

	/**
	 * Metodo que salva un restaurante a un archivo indicado por el usuario.
	 * Mediante serializacion.
	 * 
	 * @param restaurante
	 *            Restaurante que va a guardar el metodo
	 * @param destino
	 *            el destino o nombre del archivo en donde van a quedar guardados
	 *            todos los datos del restaurante.
	 */
	public static void salvarRestaurante(Restaurante restaurante, String destino) {
		try {
			File f = new File("./" + destino);
			FileOutputStream stream = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(stream);

			out.writeObject(restaurante);

			out.close();

		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Metodo que carga un restaurante que fue guardado en un archivo dentro del
	 * proyecto. Mediante deserializacion.
	 * 
	 * @param archivo
	 *            EL nombre del archivo el cual va a ser cargado al sistema.
	 * @return Un restaurante el cual es el que cargo desde el archivo para que en
	 *         donde lo llamen conozcan el restaurante que cargo al sistema.
	 */
	public static Restaurante cargarRestaurante(String archivo) {
		Restaurante restaurante = null;
		try {
			File f = new File("./" + archivo);
			FileInputStream stream = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(stream);
			restaurante = (Restaurante) in.readObject();
			in.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return restaurante;
	}

	/**
	 * Metodo que lee los ingredientes que estan guardados en un archivo de
	 * ingredientes con un formatio en especifico, retorna una lista de ingredientes
	 * que edespues sera asignada al restaurante al cual le pertenece esta lista de
	 * ingredientes.
	 * 
	 * @param archivoIngredientes
	 *            El nombre del archivo el cual va a ser leido y que tiene dentro de
	 *            si la informacion de los ingredientes en un formato especificado.
	 * @return Una lista de ingredientes la cual posteriormente sera asignada a un
	 *         restaurante.
	 */
	public static List<Ingrediente> leerIngredientes(String archivoIngredientes) throws ManejadorEx {
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		try {
			FileInputStream stream = new FileInputStream(archivoIngredientes);
			DataInputStream in = new DataInputStream(stream);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String linea;
			linea = reader.readLine();
			while (!(linea = reader.readLine()).equals("0")) {
				List<String> l1 = Arrays.asList(linea.split("\\*"));
				int cod = Integer.parseInt(l1.get(0).trim());
				String nom = l1.get(1);
				int precUnit = Integer.parseInt(l1.get(2).trim());
				String descUnit = l1.get(3);
				int inventario = Integer.parseInt(l1.get(4).trim());
				int min = Integer.parseInt(l1.get(5).trim());
				Ingrediente ingrediente = new Ingrediente(cod, nom, precUnit, descUnit, inventario, min);
				ingredientes.add(ingrediente);
				// restaurante.agregarIngrediente(codPlato, nomPlato, precUnit, descUnit,
				// inventario, min);
			}
			reader.close();
		}catch(FileNotFoundException e1) {
			throw new ManejadorEx("Archivo no encontrado");
		}catch (IOException e) {
			throw new ManejadorEx("Error al Leer el Archivo");
		} catch (Exception e) {
			throw new ManejadorEx("Error al Leer el Archivo");
		} finally {
		}
		return ingredientes;
	}

	/**
	 * Una funcion que lee los platos desde un archivo con un formato en especifico,
	 * el cual al mismo tiempo que lee los platos ademas lee sus respectivos
	 * ingredientes y asigna sus ingredientes con la cantidad correspondiente a cada
	 * plato, ademas despues tambien calcula el valor de cada plato y le calcula el
	 * IVA y se lo suma al valor de cada plato.
	 * 
	 * @param archivoPlatos
	 *            Nombre del archivo de donde se va a leer.
	 * @param restaurante
	 *            Restaurante con la lista de ingredientes de donde se va a sacar
	 *            unos ingredientes para cada plato.
	 * @return Un Hashmap con todos los platos, de llave esta el codigo y de valor
	 *         esta el plato.
	 */
	public static HashMap<Integer, Plato> leerPlatos(String archivoPlatos, Restaurante restaurante) throws ManejadorEx {
		HashMap<Integer, Plato> platos = new HashMap<Integer, Plato>();
		try {
			FileInputStream stream = new FileInputStream(archivoPlatos);
			DataInputStream in = new DataInputStream(stream);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String linea;
			while (!(linea = reader.readLine()).equals("#FIN")) {
				linea = reader.readLine();
				linea = reader.readLine();
				List<String> l1 = Arrays.asList(linea.trim().split("\\*"));
				int codPlato = Integer.parseInt(l1.get(0).trim());
				String nomPlato = l1.get(1);

				if (l1.get(2).equalsIgnoreCase("diario")) {
					PlatoDiario plato = new PlatoDiario(codPlato, nomPlato);
					linea = reader.readLine();
					while (!(linea = reader.readLine()).trim().equals("0")) {
						List<String> l2 = Arrays.asList(linea.split(" "));
						int codIng = Integer.parseInt(l2.get(0).trim());
						int cantidad = Integer.parseInt(l2.get(1).trim());
						Ingrediente ing = restaurante.buscarIngrediente(codIng);
						plato.agregarIngrediente(ing, cantidad);
					}
					plato.calcularPrecio();
					plato.agregarIva();
					platos.put(plato.getCodigo(), plato);
				} else {
					String dia = l1.get(3).trim();
					enumDia e = Utils.convertirDias(dia);
					PlatoCarta plato = new PlatoCarta(codPlato, nomPlato, e);
					linea = reader.readLine();
					while (!(linea = reader.readLine()).trim().equals("0")) {
						List<String> l2 = Arrays.asList(linea.split(" "));
						int codIng = Integer.parseInt(l2.get(0).trim());
						int cantidad = Integer.parseInt(l2.get(1).trim());
						Ingrediente ing = restaurante.buscarIngrediente(codIng);
						plato.agregarIngrediente(ing, cantidad);
					}
					plato.calcularPrecio();
					plato.agregarIva();
					platos.put(plato.getCodigo(), plato);
				}

			}
			reader.close();
		} catch(FileNotFoundException e1) {
			throw new ManejadorEx("Archivo no encontrado");
		}catch (IOException e) {
			throw new ManejadorEx("Error al Leer el Archivo");
		} catch (Exception e) {
			throw new ManejadorEx("Error al Leer el Archivo");
		} finally {
		}
		return platos;

	}

}

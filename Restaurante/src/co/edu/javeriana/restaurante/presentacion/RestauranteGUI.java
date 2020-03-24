package co.edu.javeriana.restaurante.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import co.edu.javeriana.restaurante.negocio.Ingrediente;
import co.edu.javeriana.restaurante.negocio.IngredientePlato;
import co.edu.javeriana.restaurante.negocio.Orden;
import co.edu.javeriana.restaurante.negocio.Plato;
import co.edu.javeriana.restaurante.negocio.PlatoCarta;
import co.edu.javeriana.restaurante.negocio.PlatoCarta.enumDia;
import co.edu.javeriana.restaurante.negocio.PlatoDiario;
import co.edu.javeriana.restaurante.negocio.Restaurante;
import co.edu.javeriana.restaurante.negocio.SortPlatosCod;
import co.edu.javeriana.restaurante.negocio.rexception;
import co.edu.javeriana.restaurante.persistencia.ManejadorArchivos;
import co.edu.javeriana.restaurante.persistencia.ManejadorEx;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import java.awt.Color;

public class RestauranteGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ListSelectionModel listSelectionModel;
	private Map<Integer, Plato> mapPlatos;
	private Map<Integer, Integer> cant;

	private JTable table;
	private Vector<Vector<Object>> rowData;
	private String[] columnNames = { "Codigo", "Nombre", "Precio Unitario", "Descripcion", "Inventario", "Minimo" };
	private Vector<String> columnNamesv;

	private JTable platos;
	private Vector<Vector<Object>> rowDataP;
	private String[] columnNamesP = { "Codigo", "Nombre", "Tipo", "Dia" };
	private Vector<String> columnNamesvP;

	private Restaurante restaurante = new Restaurante();

	private JTable ingredientes;
	private Vector<Vector<Object>> rowData2;
	private String[] columnNames2 = { "Codigo", "Nombre", "Precio Unitario", "Cantidad", };
	private Vector<String> columnNamesv2;

	private Vector<Vector<Object>> rowDataSel;
	private String[] columnNamesSel = { "Codigo", "Nombre", "Precio Unitario", "Cantidad", "Seleccion" };
	private Vector<String> columnNamesvSel;

	private Vector<Vector<Object>> rowDataM;
	private String[] columnNamesM = { "Codigo", "Nombre", "Precio", "Tipo", "Dia" };
	private Vector<String> columnNamesvM;
	private JTable menu;

	private Vector<Vector<Object>> rowDataO;
	private String[] columnNamesO = { "Codigo", "Nombre", "Precio", "Tipo", "Dia", "Cantidad", "Seleccion" };
	private Vector<String> columnNamesvO;
	private JTable orden;

	private Vector<Vector<Object>> rowDataNO;
	private String[] columnNamesNO = { "Codigo", "Fecha", "Valor" };
	private Vector<String> columnNamesvNO;
	private JTable nueva;

	private Vector<Vector<Object>> rowDataD;
	private String[] columnNamesD = { "Codigo", "Fecha", "Valor" };
	private Vector<String> columnNamesvD;
	private JTable dia;
	private JTextField diaTotal;

	/**
	 * Create the frame.
	 */
	public RestauranteGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Imagenes\\food.png"));
		setTitle("Proyecto POO 3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 470);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Datos Basicos");
		menuBar.add(mnNewMenu);

		JMenuItem mntmIngredientes = new JMenuItem("Ingredientes");
		mntmIngredientes.setIcon(new ImageIcon("Imagenes\\002-popcorn.png"));
		mnNewMenu.add(mntmIngredientes);

		JMenuItem mntmPlatos = new JMenuItem("Platos");
		mntmPlatos.setIcon(new ImageIcon("Imagenes\\spaghetti.png"));
		mnNewMenu.add(mntmPlatos);

		JMenu mnServicios = new JMenu("Servicios");
		menuBar.add(mnServicios);

		JMenuItem mntmAgregarorden = new JMenuItem("AgregarOrden");
		mntmAgregarorden.setIcon(new ImageIcon("Imagenes\\order-food.png"));
		mnServicios.add(mntmAgregarorden);

		JMenuItem mntmOrdenesDelDia = new JMenuItem("Ordenes del Dia");
		mntmOrdenesDelDia.setIcon(new ImageIcon("Imagenes\\clipboard.png"));
		mnServicios.add(mntmOrdenesDelDia);

		JMenuItem mntmMenuDelRestaurante = new JMenuItem("Menu del Restaurante");
		mntmMenuDelRestaurante.setIcon(new ImageIcon("Imagenes\\restaurant-menu.png"));
		mnServicios.add(mntmMenuDelRestaurante);

		JMenuItem mntmReportes = new JMenuItem("Reportes");
		mntmReportes.setIcon(new ImageIcon("Imagenes\\browser.png"));
		mnServicios.add(mntmReportes);

		JMenu mnRespaldo = new JMenu("Respaldo");
		menuBar.add(mnRespaldo);

		JMenuItem mntmCargarguardarSistema = new JMenuItem("Cargar/Guardar Sistema");
		mntmCargarguardarSistema.setIcon(new ImageIcon("Imagenes\\folder.png"));
		mnRespaldo.add(mntmCargarguardarSistema);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(240, 255, 255));
		panel.setBackground(new Color(240, 255, 255));
		tabbedPane.addTab("Servicios", null, panel, null);
		panel.setLayout(null);

		JLabel lblDatosBasicos = new JLabel("Datos Basicos");
		lblDatosBasicos.setBounds(10, 31, 89, 14);
		panel.add(lblDatosBasicos);

		JButton btnIngredientes = new JButton("Ingredientes");
		btnIngredientes.setIcon(new ImageIcon("Imagenes\\002-popcorn.png"));
		btnIngredientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnIngredientes.setBounds(0, 56, 126, 23);
		panel.add(btnIngredientes);

		JButton btnPlatos = new JButton("Platos");
		btnPlatos.setIcon(new ImageIcon("Imagenes\\spaghetti.png"));
		btnPlatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnPlatos.setBounds(151, 56, 105, 23);
		panel.add(btnPlatos);

		JLabel lblServicios = new JLabel("Servicios");
		lblServicios.setBounds(10, 116, 75, 14);
		panel.add(lblServicios);

		JButton btnAgregarOrden = new JButton("Agregar Orden");
		btnAgregarOrden.setIcon(new ImageIcon("Imagenes\\order-food.png"));
		btnAgregarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnAgregarOrden.setBounds(0, 141, 157, 23);
		panel.add(btnAgregarOrden);

		JButton btnOrdenesDelDia = new JButton("Ordenes del Dia");
		btnOrdenesDelDia.setIcon(new ImageIcon("Imagenes\\clipboard.png"));
		btnOrdenesDelDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnOrdenesDelDia.setBounds(179, 141, 178, 23);
		panel.add(btnOrdenesDelDia);

		JButton btnNewButton = new JButton("Menu del Restaurante");
		btnNewButton.setIcon(new ImageIcon("Imagenes\\restaurant-menu.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);
			}
		});
		btnNewButton.setBounds(151, 168, 206, 23);
		panel.add(btnNewButton);

		JButton btnReportes = new JButton("Reportes");
		btnReportes.setIcon(new ImageIcon("Imagenes\\browser.png"));
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(6);
			}
		});
		btnReportes.setBounds(0, 168, 126, 23);
		panel.add(btnReportes);

		JLabel lblRespaldo = new JLabel("Respaldo");
		lblRespaldo.setBounds(10, 219, 75, 14);
		panel.add(lblRespaldo);

		JButton btnGuardarcargarSistema = new JButton("Guardar/Cargar Sistema");
		btnGuardarcargarSistema.setIcon(new ImageIcon("Imagenes\\folder.png"));
		btnGuardarcargarSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(7);
			}
		});
		btnGuardarcargarSistema.setBounds(0, 257, 201, 23);
		panel.add(btnGuardarcargarSistema);

		JLabel lblRestauranteJaveriano = new JLabel("Restaurante Javeriano");
		lblRestauranteJaveriano.setBounds(526, 31, 165, 14);
		panel.add(lblRestauranteJaveriano);

		JLabel Javeriana = new JLabel("");
		Javeriana.setIcon(new ImageIcon("Imagenes\\javeriana.jpg"));
		// C:\\Users\\SANTIAGO\\Documents\\III
		// Semestre\\POO\\POO3-Caro-Martinez\\POO3-Caro-Martinez\\
		Javeriana.setBounds(475, 56, 235, 261);
		panel.add(Javeriana);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(240, 255, 255));
		panel_1.setBackground(new Color(240, 255, 255));
		tabbedPane.addTab("Ingredientes", new ImageIcon("Imagenes\\002-popcorn.png"), panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 726, 293);
		panel_1.add(scrollPane);

		rowData = new Vector<Vector<Object>>();
		columnNamesv = new Vector<String>(Arrays.asList(columnNames));

		rowData2 = new Vector<Vector<Object>>();
		columnNamesv2 = new Vector<String>(Arrays.asList(columnNames2));

		rowDataSel = new Vector<Vector<Object>>();
		columnNamesvSel = new Vector<String>(Arrays.asList(columnNamesSel));

		rowDataM = new Vector<Vector<Object>>();
		columnNamesvM = new Vector<String>(Arrays.asList(columnNamesM));

		rowDataO = new Vector<Vector<Object>>();
		columnNamesvO = new Vector<String>(Arrays.asList(columnNamesO));

		rowDataNO = new Vector<Vector<Object>>();
		columnNamesvNO = new Vector<String>(Arrays.asList(columnNamesNO));

		rowDataD = new Vector<Vector<Object>>();
		columnNamesvD = new Vector<String>(Arrays.asList(columnNamesD));

		scrollPane.setViewportView(table);

		table = new JTable(rowData, columnNamesv);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollPane.setViewportView(table);

		JButton btnCargarIngredientes = new JButton("Cargar Ingredientes");
		btnCargarIngredientes.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc) Metodo que por medio de un FileChooser permite seleccionar y
			 * cargar el archivo de ingredientes
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File("."));
					chooser.setFileFilter(filter);
					int r = chooser.showOpenDialog(null);
					if (r == JFileChooser.APPROVE_OPTION) {
						restaurante.agregarIngredientes(
								ManejadorArchivos.leerIngredientes(chooser.getSelectedFile().getAbsolutePath()));
						JOptionPane.showMessageDialog(null, "Archivo Cargado Con Exito", "EXITO",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Se Cancelo la carga del Archivo", "CANCELADO",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (ManejadorEx e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "CANCELADO", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "CANCELADO", JOptionPane.ERROR_MESSAGE);
				}
				refrescarIng(scrollPane, restaurante.getIngredientes());

			}
		});

		btnCargarIngredientes.setBounds(10, 311, 150, 23);
		panel_1.add(btnCargarIngredientes);

		JButton btnAlmacenar = new JButton("Almacenar Ingrediente");
		btnAlmacenar.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc) Metodo que permite almacenar el archivo de ingredientes
			 * previamente cargado.
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					int cod = (int) table.getValueAt(table.getRowCount() - 1, 0);
					if (table.getValueAt(table.getRowCount() - 1, 1) == null) {
						throw new rexception("Por Favor Ingrese un Nombre");
					}
					String nombre = (String) table.getValueAt(table.getRowCount() - 1, 1);
					if (table.getValueAt(table.getRowCount() - 1, 2) == null) {
						throw new rexception("Por Favor Ingrese un Precio Unitario");
					}
					int prUni = Integer.parseInt((String) table.getValueAt(table.getRowCount() - 1, 2));
					if (table.getValueAt(table.getRowCount() - 1, 3) == null) {
						throw new rexception("Por Favor Ingrese una Descripcion");
					}
					String descripcion = (String) table.getValueAt(table.getRowCount() - 1, 3);
					if (table.getValueAt(table.getRowCount() - 1, 4) == null) {
						throw new rexception("Por Favor Ingrese un Inventario");
					}
					int inv = Integer.parseInt((String) table.getValueAt(table.getRowCount() - 1, 4));
					if (table.getValueAt(table.getRowCount() - 1, 5) == null) {
						throw new rexception("Por Favor Ingrese un Minimo");
					}
					int min = Integer.parseInt((String) table.getValueAt(table.getRowCount() - 1, 5));
					if (min > inv) {
						throw new rexception("El inventario tiene que ser mayor al minimo");
					}
					restaurante.agregarIngrediente(cod, nombre, prUni, descripcion, inv, min);
					btnAlmacenar.setEnabled(false);
				} catch (rexception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAlmacenar.setEnabled(false);
		btnAlmacenar.setBounds(320, 311, 163, 23);
		panel_1.add(btnAlmacenar);

		JButton btnNuevoIngrediente = new JButton("Nuevo Ingrediente");
		btnNuevoIngrediente.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc) Metodo que permite agregar un nuevo ingrediente a la lista de
			 * ingredientes y almacenarlo y guardarlo en el archivo previamente cargado
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				int cod = 0;
				if (restaurante.getIngredientes().isEmpty()) {
					cod = 10;
				} else {
					cod = restaurante.getIngredientes().get(restaurante.getIngredientes().size() - 1).getCodigo() + 1;
				}
				agregarIng(scrollPane, cod);
				btnAlmacenar.setEnabled(true);
			}
		});
		btnNuevoIngrediente.setBounds(170, 311, 140, 23);
		panel_1.add(btnNuevoIngrediente);

		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnRegresar.setBounds(496, 311, 89, 23);
		panel_1.add(btnRegresar);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 255, 255));
		panel_2.setForeground(new Color(240, 255, 255));
		tabbedPane.addTab("Platos", new ImageIcon("Imagenes\\spaghetti.png"), panel_2, null);
		panel_2.setLayout(null);

		JLabel lblPlatos = new JLabel("Platos");
		lblPlatos.setBounds(10, 11, 46, 14);
		panel_2.add(lblPlatos);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 36, 719, 95);
		panel_2.add(scrollPane_1);

		rowDataP = new Vector<Vector<Object>>();
		columnNamesvP = new Vector<String>(Arrays.asList(columnNamesP));

		platos = new JTable(rowDataP, columnNamesvP);

		scrollPane_1.setViewportView(platos);

		JLabel lblIngredientes = new JLabel("Ingredientes");
		lblIngredientes.setBounds(10, 144, 87, 14);
		panel_2.add(lblIngredientes);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 173, 719, 95);
		panel_2.add(scrollPane_2);

		ingredientes = new JTable();
		scrollPane_2.setViewportView(ingredientes);

		JButton btnNewButton_1 = new JButton("Cargar Platos");
		btnNewButton_1.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc) Metodo que carga al sistema el archivo de texto de platos
			 * correspondiente
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File("."));
					chooser.setFileFilter(filter);
					int r = chooser.showOpenDialog(null);
					if (r == JFileChooser.APPROVE_OPTION) {
						restaurante.agregarPlatos(
								ManejadorArchivos.leerPlatos(chooser.getSelectedFile().getAbsolutePath(), restaurante));
						JOptionPane.showMessageDialog(null, "Archivo Cargado Con Exito", "EXITO",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Se Cancelo la carga del Archivo", "CANCELADO",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (ManejadorEx e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "CANCELADO", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "CANCELADO", JOptionPane.ERROR_MESSAGE);
				}
				refrescarPlatos(scrollPane_1, scrollPane_2);

			}

		});

		btnNewButton_1.setBounds(8, 301, 126, 23);
		panel_2.add(btnNewButton_1);

		JButton AlmacenarPlato = new JButton("Almacenar Plato");
		AlmacenarPlato.setEnabled(false);
		AlmacenarPlato.setBounds(280, 301, 146, 23);
		panel_2.add(AlmacenarPlato);
		AlmacenarPlato.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc) Metodo que almacena en un archivo de texto la lista de paltos
			 * previamente cargada
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					int cod = (int) platos.getValueAt(platos.getRowCount() - 1, 0);
					if (platos.getValueAt(platos.getRowCount() - 1, 1) == null) {
						throw new rexception("Por Favor Ingrese un Nombre");
					}
					String nombre = (String) platos.getValueAt(platos.getRowCount() - 1, 1);
					if (platos.getValueAt(platos.getRowCount() - 1, 2) == null) {
						throw new rexception("Por Favor Ingrese un Tipo");
					}
					String tipo = (String) platos.getValueAt(platos.getRowCount() - 1, 2);

					Plato plato = null;
					if (tipo.replaceAll("\\s+", "").equalsIgnoreCase("Carta")) {
						if (platos.getValueAt(platos.getRowCount() - 1, 3) == null) {
							throw new rexception("Por Favor Ingrese un dia");
						}
						String d = (String) platos.getValueAt(platos.getRowCount() - 1, 3);
						if (Utils.convertirDias(d) == null) {
							throw new rexception("Por Favor Ingrese un dia valido");
						}
						enumDia dia = Utils.convertirDias(d);
						PlatoCarta pc = new PlatoCarta(cod, nombre, dia);
						plato = (Plato) pc;
					} else {
						if (tipo.replaceAll("\\s+", "").equalsIgnoreCase("Diario")) {
							PlatoDiario pd = new PlatoDiario(cod, nombre);
							plato = (Plato) pd;
						} else {
							throw new rexception("Por Favor Ingrese un tipo valido");
						}
					}
					List<IngredientePlato> ingPlatos = new ArrayList<IngredientePlato>();
					for (int i = 0; i < ingredientes.getRowCount(); i++) {
						Boolean isChecked = Boolean.valueOf(ingredientes.getValueAt(i, 4).toString());
						if (isChecked) {
							int codi = Integer.parseInt(ingredientes.getValueAt(i, 0).toString());
							Ingrediente ing = restaurante.buscarIngrediente(codi);
							if (ingredientes.getValueAt(i, 3) == null) {
								throw new rexception("Por Favor Ingrese una Cantidad para " + ing.getNombre());
							}
							float cant = Float.parseFloat(ingredientes.getValueAt(i, 3).toString());
							IngredientePlato ip = new IngredientePlato(ing, (int) cant);
							ingPlatos.add(ip);
						}
					}
					if (ingPlatos.isEmpty()) {
						throw new rexception("Por Favor Ingrese Platos");
					}
					plato.setIngredientePlato(ingPlatos);
					plato.calcularPrecio();
					plato.agregarIva();
					restaurante.agregarPlato(plato);
					refrescarPlatos(scrollPane_1, scrollPane_2);
					AlmacenarPlato.setEnabled(false);
				} catch (rexception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Por favor Ingrese Numeros en las cantidades", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnNuevoPlato = new JButton("Nuevo Plato");
		btnNuevoPlato.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc) Metodo que permite agregar a la lista de platos un nuveo plato
			 * con sus respectivos ingredientes y lo almacena al archivo de texto
			 * previamente cargado al sistema
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				int cod = 0;
				if (restaurante.getPlatos().isEmpty()) {
					cod = 100;
				} else {
					List<Plato> platos = new ArrayList<Plato>(restaurante.getPlatos().values());
					platos = restaurante.ordenarCodigo(platos);
					cod = platos.get(platos.size() - 1).getCodigo() + 10;
				}
				agregarPlato(scrollPane_1, cod);
				AlmacenarPlato.setEnabled(true);
				refrescarIngSel(scrollPane_2, restaurante.getIngredientes());

			}
		});
		btnNuevoPlato.setBounds(144, 301, 126, 23);
		panel_2.add(btnNuevoPlato);

		JButton btnRegresar_1 = new JButton("Regresar");
		btnRegresar_1.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)Metodo que permite regresar a la pestania principal
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnRegresar_1.setBounds(436, 301, 89, 23);
		panel_2.add(btnRegresar_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 255, 255));
		panel_3.setForeground(new Color(240, 255, 255));
		tabbedPane.addTab("Agregar Orden", new ImageIcon("Imagenes\\order-food.png"), panel_3, null);
		panel_3.setLayout(null);

		JLabel lblPlatos_1 = new JLabel("Platos");
		lblPlatos_1.setBounds(10, 91, 46, 14);
		panel_3.add(lblPlatos_1);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 116, 719, 185);
		panel_3.add(scrollPane_4);

		orden = new JTable();
		scrollPane_4.setViewportView(orden);

		JButton btnRegresar_3 = new JButton("Regresar");
		btnRegresar_3.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)Metodo que hace que se regrese a la pagina principal
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnRegresar_3.setBounds(600, 312, 89, 23);
		panel_3.add(btnRegresar_3);

		JButton btnAlmacenarOrden = new JButton("Almacenar Orden");
		btnAlmacenarOrden.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc) Metodo que permite almacenar en el sistema la lista de ordenes
			 * realizadas de acuerdo al dia en que cada orden se hizo
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					boolean es = true;
					if (!mapPlatos.isEmpty()) {
						List<Plato> lp = new ArrayList<Plato>();
						lp.addAll(mapPlatos.values());
						for (Plato p : lp) {
							if (!p.eliminarInventario(cant.get(p.getCodigo()))) {
								es = false;
							}
						}
						if (es) {
							Orden o = new Orden(Integer.parseInt(nueva.getValueAt(0, 0).toString()));
							for (Plato p : lp) {
								o.agregarPlato(p, cant.get(p.getCodigo()));
							}
							restaurante.agregarOrden(o);
							JOptionPane.showMessageDialog(null, "Se agrego su orden", "Exito",
									JOptionPane.INFORMATION_MESSAGE);

						} else {
							throw new rexception("No hay suficiente inventario para todos los platos");
						}
					} else {
						throw new rexception("Por favor ingrese al menos 1 plato");
					}
				} catch (rexception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Por favor Ingrese solo numeros en las cantidades", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAlmacenarOrden.setBounds(426, 312, 146, 23);
		panel_3.add(btnAlmacenarOrden);

		JLabel lblNuevaOrden = new JLabel("Nueva Orden");
		lblNuevaOrden.setBounds(10, 11, 81, 14);
		panel_3.add(lblNuevaOrden);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 36, 719, 44);
		panel_3.add(scrollPane_5);

		nueva = new JTable();
		scrollPane_5.setViewportView(nueva);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 255, 255));
		panel_4.setForeground(new Color(240, 255, 255));
		tabbedPane.addTab("Ordenes de un Dia", new ImageIcon("Imagenes\\clipboard.png"), panel_4, null);
		panel_4.setLayout(null);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(10, 144, 719, 102);
		panel_4.add(scrollPane_6);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(73, 42, 166, 38);
		panel_4.add(dateChooser);

		JButton btnBuscarOrdenes = new JButton("Buscar Ordenes");
		btnBuscarOrdenes.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc) Metodo que busca en la lista de ordenes las ordenes
			 * correspondientes al dia que se solicito a buscar
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					Date date = dateChooser.getDate();
					if (date == null) {
						throw new rexception("Por favor escoga una fecha ");
					}
					LocalDate lDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					List<Orden> ordenes = restaurante.ordenesDia(lDate);
					if (ordenes.isEmpty()) {
						refrescarDia(scrollPane_6, new ArrayList<Orden>());
						throw new rexception("No hay ordenes en este dia");
					}
					refrescarDia(scrollPane_6, ordenes);
				} catch (rexception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscarOrdenes.setBounds(479, 42, 138, 23);
		panel_4.add(btnBuscarOrdenes);

		dia = new JTable();
		scrollPane_6.setViewportView(dia);

		JButton btnRegresar_4 = new JButton("Regresar");
		btnRegresar_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnRegresar_4.setBounds(537, 314, 108, 23);
		panel_4.add(btnRegresar_4);

		JLabel lblTotalVentas = new JLabel("Total Ventas");
		lblTotalVentas.setBounds(378, 277, 87, 14);
		panel_4.add(lblTotalVentas);

		diaTotal = new JTextField();
		diaTotal.setEditable(false);
		diaTotal.setBounds(499, 274, 86, 20);
		panel_4.add(diaTotal);
		diaTotal.setColumns(10);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(240, 255, 255));
		panel_5.setForeground(new Color(240, 255, 255));
		tabbedPane.addTab("Menu del Restaurante", new ImageIcon("Imagenes\\restaurant-menu.png"), panel_5, null);
		panel_5.setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 11, 719, 299);
		panel_5.add(scrollPane_3);

		menu = new JTable();
		scrollPane_3.setViewportView(menu);

		JButton btnRegresar_2 = new JButton("Regresar");
		btnRegresar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnRegresar_2.setBounds(581, 311, 89, 23);
		panel_5.add(btnRegresar_2);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 255, 255));
		panel_6.setForeground(new Color(240, 255, 255));
		tabbedPane.addTab("Reportes", new ImageIcon("Imagenes\\browser.png"), panel_6, null);
		panel_6.setLayout(null);

		JTextArea reporte = new JTextArea();
		reporte.setEditable(false);
		reporte.setBounds(50, 131, 637, 135);
		panel_6.add(reporte);

		JButton btnNewButton_3 = new JButton("Generar y ver platos m\u00E1s solicitados");
		btnNewButton_3.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc) Metodo que busca los platos mas solicitados y los ordena de
			 * mayor a menor. Imprime la lista de platos mas solicitados en pantalla, y los
			 * guarda en un archivo de texto.
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
				List<Plato> sol = new ArrayList<Plato>(restaurante.getPlatos().values());
				sol = restaurante.ordenarPlatosSolicitudes(sol);
				String a = new String();
				for (int i = 0; i < 3; i++) {
					a += sol.get(i).getCodigo() + "	" + sol.get(i).getNombre() + "	" + sol.get(i).getVecesPedido()
							+ "\r\n";
				}
				String re = new String("--PLATOS MAS SOLICITADOS:\r\n" + "Codigo	nombre	solicitudes\r\n" + a);
				reporte.setText(re);
				JFileChooser fileChooser = new JFileChooser(".");
				int r = fileChooser.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fileChooser.getSelectedFile();
						if (!Utils.getExtension(file.getName()).equalsIgnoreCase("txt")) {
							file = new File(file.toString() + ".txt"); // append .xml if "foo.jpg.xml" is OK
						}
						FileOutputStream fo = new FileOutputStream(file);
						DataOutputStream writer;
						writer = new DataOutputStream(fo);
						writer.writeBytes(re);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
					}
				} else {
					JOptionPane.showMessageDialog(null, "El usuario Cancelo");
				}

			}

		});

		JButton btnNewButton_2 = new JButton("Generar y ver platos m\u00E1s rentables");
		btnNewButton_2.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc) Metodo que busca los platos mas rentables y los ordena de mayor
			 * a menor. Imprime la lista de platos mas rentables en pantalla, y los guarda
			 * en un archivo de texto.
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
				List<Plato> sol = new ArrayList<Plato>(restaurante.getPlatos().values());
				sol = restaurante.ordenarPlatosPrecio(sol);
				String a = new String();
				for (int i = 0; i < 3; i++) {
					a += sol.get(i).getCodigo() + "	" + sol.get(i).getNombre() + "	" + sol.get(i).getPrecioAcumulado()
							+ "\r\n";
				}
				String re = new String("--PLATOS MAS RENTABLES:\r\n" + "Codigo	nombre	Precio\r\n" + a);
				reporte.setText(re);
				JFileChooser fileChooser = new JFileChooser(".");
				int r = fileChooser.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fileChooser.getSelectedFile();
						if (!Utils.getExtension(file.getName()).equalsIgnoreCase("txt")) {
							file = new File(file.toString() + ".txt");
						}
						FileOutputStream fo = new FileOutputStream(file);
						DataOutputStream writer;
						writer = new DataOutputStream(fo);
						writer.writeBytes(re);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
					}
				} else {
					JOptionPane.showMessageDialog(null, "El usuario Cacelo");
				}

			}
		});
		btnNewButton_2.setBounds(211, 29, 260, 40);
		panel_6.add(btnNewButton_2);
		btnNewButton_3.setBounds(211, 80, 260, 40);
		panel_6.add(btnNewButton_3);

		JButton btnRegresar_5 = new JButton("Regresar");
		btnRegresar_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnRegresar_5.setBounds(473, 276, 89, 23);
		panel_6.add(btnRegresar_5);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(240, 255, 255));
		panel_7.setForeground(new Color(240, 255, 255));
		tabbedPane.addTab("Guardar y Cargar el Sistema", new ImageIcon("Imagenes\\folder.png"), panel_7, null);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(150, 90, 94, 23);
		btnGuardar.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc) Metodo que a traves de un FileChooser permite guardar el
			 * sistema completo a un archivo en el directorio.
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(".");
				int i = chooser.showSaveDialog(null);
				if (i == JFileChooser.APPROVE_OPTION) {
					try {
						File file = chooser.getSelectedFile();
						FileOutputStream fo = new FileOutputStream(file);
						ObjectOutputStream writer;
						writer = new ObjectOutputStream(fo);
						writer.writeObject(restaurante);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
					}
				} else {
					JOptionPane.showMessageDialog(null, "El usuario Cancelo");
				}

			}
		});
		panel_7.setLayout(null);
		panel_7.add(btnGuardar);

		JButton btnCargar = new JButton("Cargar");
		btnCargar.setBounds(428, 90, 84, 23);
		btnCargar.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)Metodo que a traves de un FileChooser permite cargar el sistema
			 * completo desde un archivo del directorio.
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(".");
				int i = chooser.showSaveDialog(null);
				if (i == JFileChooser.APPROVE_OPTION) {
					try {
						File file = chooser.getSelectedFile();
						FileInputStream fo = new FileInputStream(file);
						ObjectInputStream reader;
						reader = new ObjectInputStream(fo);
						restaurante = (Restaurante) reader.readObject();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error al leer el archivo", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "El usuario Cancelo");
				}

			}
		});
		panel_7.add(btnCargar);

		JButton btnRegresar_6 = new JButton("Regresar");
		btnRegresar_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnRegresar_6.setBounds(286, 178, 94, 23);
		panel_7.add(btnRegresar_6);

		ChangeListener cl = new ChangeListener() {

			/*
			 * (non-Javadoc) Metodo que cuando se cambia a una pestania actualiza la
			 * pestania que se haya elegido
			 * 
			 * @see
			 * javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
			 */
			@Override
			public void stateChanged(ChangeEvent e) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				if (index == 5) {
					List<Plato> platos = new ArrayList<Plato>();
					platos.addAll(restaurante.getPlatos().values());
					ordenarPlatosCodigo(platos);
					refrescarMenu(scrollPane_3, platos);
				}
				if (index == 3) {
					List<Plato> platos1 = new ArrayList<Plato>();
					List<Plato> platos2 = new ArrayList<Plato>();
					platos1.addAll(restaurante.getPlatos().values());
					ordenarPlatosCodigo(platos1);
					for (Plato p : platos1) {
						if (p instanceof PlatoCarta) {
							PlatoCarta pc = (PlatoCarta) p;
							if (Utils.sePuede(pc.getDia())) {
								platos2.add(p);
							}
						} else {
							platos2.add(p);
						}
					}
					refrescarIngO(scrollPane_4, platos2);
					refrescarIngNO(scrollPane_5);
					int cod = (restaurante.getOrdenes().size() * 100) + 100;
					agregarOrd(scrollPane_5, cod);
					mapPlatos = new HashMap<Integer, Plato>();
					cant = new HashMap<Integer, Integer>();
				}
				if (index == 2) {
					List<Ingrediente> list = new ArrayList<Ingrediente>();
					refrescarIngSel(scrollPane_2, list);
					refrescarPlatos(scrollPane_1, scrollPane_2);
				}
				if (index == 1) {
					refrescarIng(scrollPane, restaurante.getIngredientes());
				}

			}
		};
		tabbedPane.addChangeListener(cl);

		mntmIngredientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		mntmPlatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);

			}
		});
		mntmAgregarorden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);

			}
		});
		mntmOrdenesDelDia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);

			}
		});
		mntmMenuDelRestaurante.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);

			}
		});
		mntmReportes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(6);

			}
		});
		mntmCargarguardarSistema.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(7);

			}
		});

	}

	public JTable getTable() {
		if (table == null) {
			rowData = new Vector<Vector<Object>>();
			columnNamesv = new Vector<String>(Arrays.asList(columnNames));
			table = new JTable(rowData, columnNamesv);
		}
		return table;
	}

	public JTable getTableP() {
		if (platos == null) {
			rowDataP = new Vector<Vector<Object>>();
			columnNamesvP = new Vector<String>(Arrays.asList(columnNamesP));
			table = new JTable(rowDataP, columnNamesvP);
		}
		return platos;
	}

	public JTable getTableM() {
		if (menu == null) {
			rowDataM = new Vector<Vector<Object>>();
			columnNamesvM = new Vector<String>(Arrays.asList(columnNamesM));
			menu = new JTable(rowDataM, columnNamesvM);
		}
		return menu;
	}

	public JTable getTable2() {
		if (ingredientes == null) {
			rowData2 = new Vector<Vector<Object>>();
			columnNamesv2 = new Vector<String>(Arrays.asList(columnNames2));
			ingredientes = new JTable(rowData2, columnNamesv2);
		}
		return ingredientes;
	}

	public JTable getTableNO() {
		if (nueva == null) {
			rowDataNO = new Vector<Vector<Object>>();
			columnNamesvNO = new Vector<String>(Arrays.asList(columnNamesNO));
			nueva = new JTable(rowDataNO, columnNamesvNO);
		}
		return nueva;
	}

	public JTable getTableD() {
		if (dia == null) {
			rowDataD = new Vector<Vector<Object>>();
			columnNamesvD = new Vector<String>(Arrays.asList(columnNamesD));
			dia = new JTable(rowDataD, columnNamesvD);
		}
		return dia;
	}

	public JTable getTableSel() {
		if (ingredientes == null) {
			rowDataSel = new Vector<Vector<Object>>();
			columnNamesvSel = new Vector<String>(Arrays.asList(columnNamesSel));
			// DefaultTableModel model = new DefaultTableModel(rowDataSel, columnNamesvSel);
			// // ingredientes = new JTable(model) {
			// //
			// // private static final long serialVersionUID = 1L;
			// //
			// // @Override
			// // public Class getColumnClass(int column) {
			// // switch (column) {
			// // case 0:
			// // return String.class;
			// // case 1:
			// // return String.class;
			// // case 2:
			// // return Integer.class;
			// // case 3:
			// // return Double.class;
			// // case 4:
			// // return Boolean.class;
			// // default:
			// // return Boolean.class;
			// // }
			// // }
			// //
			// // };

		}
		return ingredientes;
	}

	public JTable getTableO() {
		if (orden == null) {
			rowDataO = new Vector<Vector<Object>>();
			columnNamesvO = new Vector<String>(Arrays.asList(columnNamesO));
			DefaultTableModel model = new DefaultTableModel(rowDataO, columnNamesvO);
			orden = new JTable(model) {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unchecked")
				@Override
				public Class getColumnClass(int column) {
					switch (column) {
					case 0:
						return Integer.class;
					case 1:
						return String.class;
					case 2:
						return Integer.class;
					case 3:
						return String.class;
					case 4:
						return String.class;
					case 5:
						return String.class;
					case 6:
						return Boolean.class;
					default:
						return Boolean.class;
					}
				}

			};

		}
		return orden;
	}

	/**
	 * Metodo que actualiza la tabla de ingredientes
	 * 
	 * @param scrollPane
	 *            el scrollPane donde se encuentra la tabla de ingredientes
	 * @param ingredientes
	 *            Lista de ingredientes
	 */
	public void refrescarIng(JScrollPane scrollPane, List<Ingrediente> ingredientes) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if (table != null) {
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
		for (Ingrediente i : ingredientes) {
			Object[] fila = new Object[6];
			fila[0] = i.getCodigo();
			fila[1] = i.getNombre();
			fila[2] = i.getPrecioUnitario();
			fila[3] = i.getDescripcionUnidad();
			fila[4] = i.getInventario();
			fila[5] = i.getMinimo();
			Vector<Object> filaIng = new Vector<Object>(Arrays.asList(fila));
			rowData.add(filaIng);
		}
		table = new JTable(rowData, columnNamesv);
		scrollPane.setViewportView(getTable());
	}

	/**
	 * Metodo que actualiza la tabla de platos
	 * 
	 * @param scrollPane
	 *            El scrollPane donde se encuentra la tabla de platos
	 * @param platos
	 *            Lista de platos
	 */
	public void refrescarMenu(JScrollPane scrollPane, List<Plato> platos) {
		DefaultTableModel model = (DefaultTableModel) menu.getModel();
		if (menu != null) {
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
		for (Plato i : platos) {
			Object[] fila = new Object[5];
			fila[0] = i.getCodigo();
			fila[1] = i.getNombre();
			fila[2] = i.getPrecio();
			if (i instanceof PlatoDiario) {
				fila[3] = "Diario";
			} else {
				fila[3] = "Carta";
				PlatoCarta pc = (PlatoCarta) i;
				fila[4] = pc.getDia();
			}
			Vector<Object> filaIng = new Vector<Object>(Arrays.asList(fila));
			rowDataM.add(filaIng);
		}
		menu = new JTable(rowDataM, columnNamesvM);
		scrollPane.setViewportView(getTableM());
	}

	/**
	 * Metodo que actualiza la tabla de ingredientes de la pestania de platos
	 * 
	 * @param scrollPane
	 *            El scrollPane donde se encuentran la tabla de ingredientes
	 * @param ingre
	 *            Lista de ingredientes
	 */
	public void refrescarIng2(JScrollPane scrollPane, List<IngredientePlato> ingre) {
		DefaultTableModel model = (DefaultTableModel) ingredientes.getModel();
		if (ingredientes != null) {
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
		for (IngredientePlato i : ingre) {
			Object[] fila = new Object[4];
			fila[0] = i.getIngrediente().getCodigo();
			fila[1] = i.getIngrediente().getNombre();
			fila[2] = i.getIngrediente().getPrecioUnitario();
			fila[3] = i.getCantidad();
			Vector<Object> filaIng = new Vector<Object>(Arrays.asList(fila));
			rowData2.add(filaIng);
		}
		ingredientes = new JTable(rowData2, columnNamesv2);
		scrollPane.setViewportView(getTable2());
	}

	/**
	 * Metodo que refresca la tabla de ordenes del dia
	 * 
	 * @param scrollPane
	 *            El scrollPane donde se encuentran las ordenes y los dias
	 * @param ordenes
	 *            Lista de ordenes
	 */
	public void refrescarDia(JScrollPane scrollPane, List<Orden> ordenes) {
		int total = 0;
		DefaultTableModel model = (DefaultTableModel) dia.getModel();
		if (dia != null) {
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
		for (Orden o : ordenes) {
			Object[] fila = new Object[3];
			fila[0] = o.getCodigo();
			fila[1] = o.getFecha().getYear() + "-" + o.getFecha().getMonthValue() + "-" + o.getFecha().getDayOfMonth();
			fila[2] = o.getValor();
			total += o.getValor();
			Vector<Object> filaIng = new Vector<Object>(Arrays.asList(fila));
			rowDataD.add(filaIng);
		}
		diaTotal.setText(String.valueOf(total));
		dia = new JTable(rowDataD, columnNamesvD);
		scrollPane.setViewportView(getTableD());
	}

	/**
	 * Metodo que refresca la lista de platos
	 * 
	 * @param scrollPane
	 *            El scrollPane donde se encuentra la tabla de la lista de platos
	 */
	public void refrescarIngNO(JScrollPane scrollPane) {
		DefaultTableModel model = (DefaultTableModel) nueva.getModel();
		if (nueva != null) {
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
		nueva = new JTable(rowDataNO, columnNamesvNO);
		scrollPane.setViewportView(getTableNO());
	}

	/**
	 * Metodo que refresca la lista de ingredientes del plato seleccionado
	 * 
	 * @param scrollPane
	 *            scrollPane donde se encuentra la tabla de inredientes del plato
	 *            seleccionado
	 * @param ingr
	 *            Lista de ingredientes
	 */
	public void refrescarIngSel(JScrollPane scrollPane, List<Ingrediente> ingr) {
		DefaultTableModel model = (DefaultTableModel) ingredientes.getModel();
		if (ingredientes != null) {
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
		DefaultTableModel modelo = new DefaultTableModel(rowDataSel, columnNamesvSel);
		ingredientes = new JTable(modelo) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return Double.class;
				case 4:
					return Boolean.class;
				default:
					return Boolean.class;
				}
			}

		};
		for (Ingrediente i : ingr) {
			modelo.addRow(new Object[] { i.getCodigo(), i.getNombre(), i.getPrecioUnitario(), null, false });
		}

		scrollPane.setViewportView(getTableSel());
	}

	/**
	 * Metodo que actualiza la lista de platos de acuerdo a los ingredientes
	 * seleccionados para el plato nuevo
	 * 
	 * @param scrollPane
	 *            scrollPande donde se encuentra la tabla de la lista de platos
	 * @param pla
	 *            Lista de platos
	 */
	public void refrescarIngO(JScrollPane scrollPane, List<Plato> pla) {

		DefaultTableModel model = (DefaultTableModel) orden.getModel();
		if (orden != null) {
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
		DefaultTableModel modelo = new DefaultTableModel(rowDataO, columnNamesvO);
		orden = new JTable(modelo) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				case 6:
					return Boolean.class;
				default:
					return Boolean.class;
				}
			}

		};
		for (Plato p : pla) {
			String tipo = new String();
			String dia = null;
			if (p instanceof PlatoCarta) {
				tipo = "Carta";
				PlatoCarta pc = (PlatoCarta) p;
				dia = new String(pc.getDia().toString());
			} else {
				tipo = "Diario";
			}
			modelo.addRow(new Object[] { p.getCodigo(), p.getNombre(), p.getPrecio(), tipo, dia, null, false });
		}
		orden.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				for (int i = 0; i < orden.getRowCount(); i++) {
					Boolean isChecked = Boolean.valueOf(orden.getValueAt(i, 6).toString());
					if (isChecked) {
						Object o = orden.getValueAt(i, 5);
						if (o != null) {
							try {
								int codigo = Integer.parseInt(orden.getValueAt(i, 0).toString());
								mapPlatos.put(codigo, restaurante.buscarPlato(codigo));
								cant.put(codigo, Integer.parseInt(orden.getValueAt(i, 5).toString()));
								actualizarPrecio();
							} catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(null,
										"Por favor solo ingrese\n numeros en las cantidades", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					} else {
						if (mapPlatos.get(Integer.parseInt(orden.getValueAt(i, 0).toString())) != null) {
							platos.remove(Integer.parseInt(orden.getValueAt(i, 0).toString()));
							actualizarPrecio();
						}
					}
				}
			}

			private void actualizarPrecio() {
				int precio = 0;
				List<Plato> p = new ArrayList<Plato>();
				p.addAll(mapPlatos.values());
				for (Plato a : p) {
					precio += a.getPrecio() * cant.get(a.getCodigo());
				}
				nueva.setValueAt(precio, 0, 2);

			}
		});
		scrollPane.setViewportView(getTableO());
	}

	/**
	 * Metodo que actualiza la lista de platos dependiendo del tipo de plato y de su
	 * dia
	 * 
	 * @param scrollPane_1
	 * @param scrollPane_2
	 */
	public void refrescarPlatos(JScrollPane scrollPane_1, JScrollPane scrollPane_2) {
		DefaultTableModel model = (DefaultTableModel) platos.getModel();
		List<Plato> LPla = new ArrayList<Plato>();
		LPla.addAll(restaurante.getPlatos().values());
		LPla = ordenarPlatosCodigo(LPla);
		if (platos != null) {
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
		for (Plato p : LPla) {
			Object[] fila = new Object[4];
			fila[0] = p.getCodigo();
			fila[1] = p.getNombre();
			if (p instanceof PlatoDiario) {
				fila[2] = "Diario";
			} else {
				PlatoCarta pc = (PlatoCarta) p;
				fila[2] = "Carta";
				fila[3] = pc.getDia();
			}

			Vector<Object> filaPlato = new Vector<Object>(Arrays.asList(fila));
			rowDataP.add(filaPlato);
		}
		platos = new JTable(rowDataP, columnNamesvP);
		platos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = platos.getSelectedRow();
				if (row != -1) {
					Plato p = restaurante.buscarPlato((int) platos.getValueAt(row, 0));
					List<IngredientePlato> ing = p.getIngredientePlato();
					refrescarIng2(scrollPane_2, ing);
				}
			}
		});
		scrollPane_1.setViewportView(getTableP());

	}

	/**
	 * Metodo que ordena la lista de platos por codigo
	 * 
	 * @param platos
	 *            Lista de platos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Plato> ordenarPlatosCodigo(List<Plato> platos) {
		Collections.sort(platos, new SortPlatosCod());
		return platos;
	}

	/**
	 * Metodo que permite agregar un nuevo ingrediente la lista de ingredientes y
	 * asigna automaticamente un codigo al mismo
	 * 
	 * @param scrollPane
	 *            Tabla donde se agrega el ingrediente nuevo
	 * @param cod
	 *            Codigo del ingrediente
	 */
	public void agregarIng(JScrollPane scrollPane, int cod) {
		Object[] fila = new Object[6];
		fila[0] = cod;
		Vector<Object> filaIng = new Vector<Object>(Arrays.asList(fila));
		rowData.add(filaIng);
		table = new JTable(rowData, columnNamesv);
		scrollPane.setViewportView(getTable());
	}

	/**
	 * Metodo que agrega una nueva orden y le asigna automaticamente un codigo a la
	 * misma
	 * 
	 * @param scrollPane
	 *            Tabla donde se agregan las ordenes realizadas
	 * @param cod
	 *            Codigo de la orden
	 */
	public void agregarOrd(JScrollPane scrollPane, int cod) {
		Object[] fila = new Object[3];
		fila[0] = cod;
		fila[1] = LocalDate.now().toString();
		Vector<Object> filaIng = new Vector<Object>(Arrays.asList(fila));
		rowDataNO.add(filaIng);
		nueva = new JTable(rowDataNO, columnNamesvNO);
		scrollPane.setViewportView(getTableNO());
	}

	/**
	 * Metodo que agrega un nuevo plato y le asigna un codigo automaticamente
	 * 
	 * @param scrollPane
	 *            Tabla donde se agrega un plato nuevo
	 * @param cod
	 *            Codigo del plato
	 */
	public void agregarPlato(JScrollPane scrollPane, int cod) {
		Object[] fila = new Object[4];
		fila[0] = cod;
		Vector<Object> filaPlato = new Vector<Object>(Arrays.asList(fila));
		rowDataP.add(filaPlato);
		platos = new JTable(rowDataP, columnNamesvP);
		scrollPane.setViewportView(getTableP());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestauranteGUI frame = new RestauranteGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}

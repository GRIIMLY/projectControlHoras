package co.com.samtel.ControlAccesos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.com.samtel.ControlAccesos.util.FileView;
import javax.swing.UIManager;
import javax.swing.JLayeredPane;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Label;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.JInternalFrame;
import java.awt.SystemColor;

public class controlAcceso extends JFrame {

	private JPanel contentPane;
	private JTextField txtRuta;
	private FileView f = new FileView();
	JFileChooser fc = new JFileChooser();
	private viewControlAccesoController vc = new viewControlAccesoController();
	private JComboBox cbxDesde;
	private JComboBox cbxHasta;
	private JComboBox cbxAnio;
	private int year = 0;
	private int mes = 0;
	private int diaI = 0;
	private int diaF = 0;
	private JTextField txtCodigo;
	private JLabel lblNumeroRegistrosMostrar;
	private JButton lblReportes;
	private JTextField txtAlert;
	private JPanel panelBodyCsv;
	private JPanel panelBodyReporte;
	private JPanel panelBodyManteniento;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// ApplicationContext applicationContext = new
					// ClassPathXmlApplicationContext("configuration-bean.xml");
					controlAcceso frame = new controlAcceso();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public controlAcceso() {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtAlert.setText("");
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setEnabled(false);
		contentPane.add(panel, BorderLayout.NORTH);

		JPanel header = new JPanel();
		header.setBorder(null);
		header.setBackground(new Color(38, 49, 81));

		JPanel panelContentImagenSamtel = new JPanel();
		panelContentImagenSamtel.setBackground(new Color(255, 255, 255));
		panelContentImagenSamtel.setLayout(null);

		JLabel lblContentImageSamtel = new JLabel("");
		lblContentImageSamtel.setBounds(171, 0, 127, 56);
		lblContentImageSamtel.setIcon(new ImageIcon("src/main/resources/samtel.PNG"));
		panelContentImagenSamtel.add(lblContentImageSamtel);

		lblReportes = new JButton("Reportes");
		lblReportes.setBounds(0, 64, 191, 37);
		lblReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblReportes.setBackground(new Color(41, 65, 104));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblReportes.setBackground(new Color(38, 49, 81));
			}

		});
		Border emptyBorder = BorderFactory.createEmptyBorder();
		lblReportes.setBorder(emptyBorder);
		lblReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getTxtAlert().setText("");
				getPanelBodyCsv().setVisible(false);
				getPanelBodyManteniento().setVisible(false);
				getPanelBodyReporte().setVisible(true);
			}
		});
		header.setLayout(null);

		JButton lblMantenimiento = new JButton("Mantenimiento");
		lblMantenimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTxtAlert().setText("");
				getPanelBodyCsv().setVisible(false);
				getPanelBodyReporte().setVisible(false);
				getPanelBodyManteniento().setVisible(true);
			}
			
		});
		lblMantenimiento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMantenimiento.setBackground(new Color(41, 65, 104));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblMantenimiento.setBackground(new Color(38, 49, 81));
			}

		});
		lblMantenimiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimiento.setForeground(new Color(240, 255, 255));
		lblMantenimiento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMantenimiento.setBackground(new Color(38, 49, 81));
		lblMantenimiento.setBounds(0, 101, 191, 37);
		lblMantenimiento.setBorder(emptyBorder);
		header.add(lblMantenimiento);

		JLabel lblIconManten = new JLabel();
		lblIconManten.setBounds(0, 0, 1, 1);
		lblIconManten.setIcon(new ImageIcon("src/main/resources/config.png"));
		lblMantenimiento.add(lblIconManten);

		lblReportes.setForeground(new Color(240, 255, 255));
		lblReportes.setBackground(new Color(38, 49, 81));
		lblReportes.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		header.add(lblReportes);

		JLabel lblIconReporte = new JLabel();
		lblIconReporte.setBounds(0, 64, 46, 37);
		lblIconReporte.setIcon(new ImageIcon("src/main/resources/iconreport.png"));
		lblReportes.add(lblIconReporte);

		JButton lblSubirArchivo = new JButton("Carga de csv");
		lblSubirArchivo.setBorder(emptyBorder);
		lblSubirArchivo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSubirArchivo.setBackground(new Color(41, 65, 104));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblSubirArchivo.setBackground(new Color(38, 49, 81));
			}

		});
		lblSubirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTxtAlert().setText("");
				getPanelBodyManteniento().setVisible(false);
				getPanelBodyReporte().setVisible(false);
				getPanelBodyCsv().setVisible(true);
			}
		});
		lblSubirArchivo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubirArchivo.setForeground(new Color(240, 255, 255));
		lblSubirArchivo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubirArchivo.setBackground(new Color(38, 49, 81));
		lblSubirArchivo.setBounds(0, 138, 191, 37);
		header.add(lblSubirArchivo);

		JLabel lblIconSubirArchivo = new JLabel();
		lblIconSubirArchivo.setBounds(0, 0, 1, 1);
		lblIconSubirArchivo.setIcon(new ImageIcon("src/main/resources/up.png"));
		lblSubirArchivo.add(lblIconSubirArchivo);

		JPanel panelTituloApp = new JPanel();
		panelTituloApp.setBounds(0, 0, 191, 55);
		panelTituloApp.setBackground(new Color(124, 196, 79));
		header.add(panelTituloApp);
		panelTituloApp.setLayout(null);

		Label lblTituloApp = new Label("Control \r\nde Acceso");
		lblTituloApp.setAlignment(Label.CENTER);
		lblTituloApp.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTituloApp.setForeground(new Color(255, 255, 255));
		lblTituloApp.setBounds(0, 10, 191, 45);
		panelTituloApp.add(lblTituloApp);

		JPanel panelSeparador = new JPanel();
		panelSeparador.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelSeparador.setBackground(new Color(38, 49, 81));

		panelBodyReporte = new JPanel();
		panelBodyReporte.setBackground(Color.WHITE);
		panelBodyReporte.setVisible(true);

		panelBodyCsv = new JPanel();
		panelBodyCsv.setBackground(Color.WHITE);
		panelBodyCsv.setVisible(false);

		txtAlert = new JTextField();
		txtAlert.setEditable(false);
		txtAlert.setBorder(emptyBorder);
		txtAlert.setBackground(SystemColor.control);
		txtAlert.setColumns(10);

		panelBodyManteniento = new JPanel();
		panelBodyManteniento.setVisible(false);
		panelBodyManteniento.setBackground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(header, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panelBodyManteniento, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panelBodyCsv, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panelBodyReporte, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(panelSeparador, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(panelContentImagenSamtel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtAlert, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(header, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panelContentImagenSamtel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(panelSeparador, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(18)
									.addComponent(panelBodyManteniento, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(17)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(panelBodyReporte, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
										.addComponent(panelBodyCsv, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtAlert, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(7)))
					.addGap(80))
		);
		panelBodyManteniento.setLayout(null);

		JLabel lblNumeroRegistros = new JLabel("Numero de registros:");
		lblNumeroRegistros.setBounds(91, 58, 145, 18);
		panelBodyManteniento.add(lblNumeroRegistros);
		lblNumeroRegistros.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblNumeroRegistrosMostrar = new JLabel("0000");
		lblNumeroRegistrosMostrar.setBounds(131, 87, 88, 18);
		panelBodyManteniento.add(lblNumeroRegistrosMostrar);
		lblNumeroRegistrosMostrar.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnConsultar.setBackground(new Color(0, 124, 255));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnConsultar.setBackground(new Color(0, 106, 217));

			}
		});
		btnConsultar.setForeground(Color.WHITE);
		btnConsultar.setBounds(85, 116, 134, 23);
		btnConsultar.setBackground(new Color(0, 106, 217));
		btnConsultar.setBorder(emptyBorder);
		panelBodyManteniento.add(btnConsultar);

		JLabel lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setBounds(285, 55, 53, 18);
		panelBodyManteniento.add(lblCodigo);
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtCodigo = new JTextField();
		txtCodigo.setBounds(285, 73, 134, 23);
		panelBodyManteniento.add(txtCodigo);
		txtCodigo.setColumns(10);

		JButton btnLimpiarr = new JButton("Limpiar");
		btnLimpiarr.setForeground(Color.WHITE);
		btnLimpiarr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLimpiarr.setBackground(new Color(83, 172, 79));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLimpiarr.setBackground(new Color(124, 196, 79));
			}
		});
		btnLimpiarr.setBorder(emptyBorder);
		btnLimpiarr.setBounds(285, 116, 134, 23);
		btnLimpiarr.setBackground(new Color(124, 196, 79));
		panelBodyManteniento.add(btnLimpiarr);
		btnLimpiarr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (vc.deleteDataControlAcceso(getTxtCodigo().getText())) {
					getTxtAlert().setText("Se elimino la información con éxito...");
					getTxtAlert().setForeground(Color.GREEN);
				} else {
					getTxtAlert().setText("Error al eliminar la información... por favor verifique el codigo");
					getTxtAlert().setForeground(Color.RED);

				}
			}
		});
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				getLblNumeroRegistrosMostrar().setText(vc.getNumberData().toString());
				;
				System.out.println(vc.getNumberData().toString());
			}
		});
		panelBodyCsv.setLayout(null);

		txtRuta = new JTextField();
		txtRuta.setBounds(84, 57, 202, 20);
		panelBodyCsv.add(txtRuta);
		txtRuta.setBackground(Color.WHITE);
		txtRuta.setEditable(false);
		txtRuta.setColumns(10);

		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.setBounds(296, 56, 96, 23);
		panelBodyCsv.add(btnAbrir);
		btnAbrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnAbrir.setBackground(new Color(0, 106, 217));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAbrir.setBackground(new Color(0, 124, 255));
			}
		});
		btnAbrir.setForeground(Color.WHITE);
		btnAbrir.setBorder(emptyBorder);
		btnAbrir.setBackground(new Color(0, 124, 255));

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(262, 144, 184, 23);
		panelBodyCsv.add(btnGuardar);
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGuardar.setBackground(new Color(108, 171, 68));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnGuardar.setBackground(new Color(124, 196, 79));
			}
		});
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(new Color(124, 196, 79));
		btnGuardar.setBorder(emptyBorder);
		btnGuardar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

			}
		});
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// verifica que el FileChoose no este vacio.
					// de no tenerlo copia el archivo desde la ruta de origen a la ruta de destino
					if (fc != null) {

						Boolean val = f.createDirec(fc.getSelectedFile().getAbsolutePath(),
								fc.getSelectedFile().getName());

						if (val == true) {
							String name_file = fc.getSelectedFile().getName();

							// volvemos nulo a el filechooser para que no se vuelva a cargar.
							fc = null;

							try {

								// se hace la carga a la base de datos
								Boolean resp = vc.cargarData(name_file);
								if (resp == true) {

									// enviamos un mensaje para indicar que el archivo se subio de manera correcta

									getTxtAlert().setText("El archivo se cargo de manera Exitosa");
									getTxtAlert().setForeground(Color.green);

								} else {
									// enviamos un mensaje para indicar que el archivo se subio de manera correcta
									getTxtAlert().setText("Error de archivo, verifique que es el correcto");
									getTxtAlert().setForeground(Color.red);
								}

							} catch (Exception e2) {
								e2.printStackTrace();
								getTxtAlert().setForeground(Color.red);
								getTxtAlert().setText("Error registrando en la base de datos");
							}

							// borramos el nmobre del archivo que se tomo
							txtRuta.setText("");

						} else {
							// borramos el nmobre del archivo que se tomo
							txtRuta.setText("");
							// volvemos nulo a el filechooser para que no se vuelva a cargar.
							fc = null;
							// enviamos un mensaje para indicar que el archivo fallo al subirs
							getTxtAlert().setText("Fallo al subir el archivo");
							getTxtAlert().setForeground(Color.red);
							System.out.println("el archivo no se cargo, verifica cual es el problema");
						}

					} else {
						txtRuta.setText("");
						getTxtAlert().setText("Seleccione un archivo");
						getTxtAlert().setForeground(Color.red);
					}
				} catch (NullPointerException e2) {
					System.out.println(e2);
					txtRuta.setText("");
					getTxtAlert().setText("Seleccione un archivo");
					getTxtAlert().setForeground(Color.red);
				}

			}
		});
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					fc = f.openfolders();
					txtRuta.setText(fc.getSelectedFile().getName());

				} catch (IOException e) {

					e.printStackTrace();
				} catch (NullPointerException en) {

				}

			}
		});
		panelBodyReporte.setLayout(null);

		JLabel lblSeleccioneElTipo = new JLabel("Tipo reporte ");
		lblSeleccioneElTipo.setBounds(52, 21, 230, 16);
		panelBodyReporte.add(lblSeleccioneElTipo);

		JComboBox cbxTipoReporte = new JComboBox();
		cbxTipoReporte.setBackground(Color.WHITE);
		cbxTipoReporte.setBounds(52, 42, 326, 20);
		panelBodyReporte.add(cbxTipoReporte);
		cbxTipoReporte.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtAlert.setText("");
			}
		});
		cbxTipoReporte.setModel(new DefaultComboBoxModel(
				new String[] { "Seleccione el tipo reporte ...", "Retardos\t", "Menor Horas Trabajadas",
						"Mayor Horas Trabajadas", "Control de horas diarias", "Control Accesos Ordenados" }));

		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(75, 73, 35, 14);
		panelBodyReporte.add(lblAo);

		cbxAnio = new JComboBox();
		cbxAnio.setBackground(Color.WHITE);
		cbxAnio.setBounds(60, 93, 64, 22);
		panelBodyReporte.add(cbxAnio);

		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(161, 73, 35, 14);
		panelBodyReporte.add(lblMes);

		JComboBox cbxMes = new JComboBox();
		cbxMes.setBackground(Color.WHITE);
		cbxMes.setBounds(149, 93, 64, 22);
		panelBodyReporte.add(cbxMes);

		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(245, 73, 35, 14);
		panelBodyReporte.add(lblDesde);

		cbxDesde = new JComboBox();
		cbxDesde.setBackground(Color.WHITE);
		cbxDesde.setBounds(231, 93, 64, 22);
		panelBodyReporte.add(cbxDesde);

		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(337, 73, 35, 14);
		panelBodyReporte.add(lblHasta);

		cbxHasta = new JComboBox();
		cbxHasta.setBackground(Color.WHITE);
		cbxHasta.setBounds(314, 93, 64, 22);
		panelBodyReporte.add(cbxHasta);

		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGenerar.setBackground(new Color(83, 172, 76));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnGenerar.setBackground(new Color(124, 196, 79));

			}
		});
		btnGenerar.setBorder(emptyBorder);
		btnGenerar.setForeground(Color.WHITE);
		btnGenerar.setBounds(363, 171, 93, 23);
		btnGenerar.setBackground(new Color(124, 196, 79));
		panelBodyReporte.add(btnGenerar);

		JButton btnDescartar = new JButton("Descartar");
		btnDescartar.setForeground(Color.WHITE);
		btnDescartar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDescartar.setBackground(new Color(222, 0, 38));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDescartar.setBackground(new Color(244, 35, 56));

			}
		});
		btnDescartar.setBounds(260, 171, 93, 23);
		btnDescartar.setBackground(new Color(244, 35, 56));
		btnDescartar.setBorder(emptyBorder);
		panelBodyReporte.add(btnDescartar);
		btnDescartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbxDesde.removeAllItems();
				cbxHasta.removeAllItems();
				cbxMes.removeAllItems();
				cbxAnio.removeAllItems();
				System.out.println("El reporte seleccionado es: " + cbxTipoReporte.getSelectedIndex());
				cbxTipoReporte.setSelectedIndex(0);
				mes = 0;
				year = 0;
				diaI = 0;
				diaF = 0;
				getTxtAlert().setText("");

			}
		});
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// este me permite conocer cual es el index o posicion del reporte seleccionado
				int reporte = cbxTipoReporte.getSelectedIndex();

				// validaci�n se realiza las acciones si el mes, a�o y dias son diferentes a
				// cero
				if (year != 0 && mes != 0 && diaI != 0 && diaF != 0) {

					// mensaje en caso de que todo marche bien

					// se toma el indice y se hace un switch para hacer las respectivas accioens de
					// acuerdo a el tipo de reporte seleccionado
					switch (reporte) {
					case 1:

						// Metodo que me permite llamar a el controlador universal que se encargara de
						// llamar a los otros metodos para generar el reporte de los usuarios que llegan
						// tarde con respecto a la hora estipulada
						vc.AlertaRetardos(mes, year, diaI, diaF);

						// asignamos un mensaje en caso de que todo marche bien.
						getTxtAlert().setText("Reporte generado correctamente");
						getTxtAlert().setForeground(Color.black);
						break;
					case 2:

						// Metodo que me permite llamar a el controlador universal que se encargara de
						// llamar a los otros metodos para generar el reporte de los usuarios que no
						// cumplen con las horas laborales estipuladas

						vc.AlertaMenorHorasLaboradas(mes, year, diaI, diaF);

						// mensaje en caso de que todo marche bien
						getTxtAlert().setText("Reporte generado correctamente");
						getTxtAlert().setForeground(Color.black);
						break;
					case 3:

						// Metodo que me permite llamar a el controlador universal que se encargara de
						// llamar a los otros metodos para generar el reporte de los usuarios que
						// superan el numero de horas laboradas
						vc.AlertaMayorHorasLaboradas(mes, year, diaI, diaF);

						// mensaje en caso de que todo marche bien
						getTxtAlert().setText("Reporte generado correctamente");
						getTxtAlert().setForeground(Color.black);
						break;
					case 4:
						getTxtAlert().setText("Generando Reporte espere ...");
						getTxtAlert().setForeground(Color.blue);
						vc.getAllControlRegistros(mes, year, diaI, diaF);
						getTxtAlert().setText("Reporte generado correctamente");
						getTxtAlert().setForeground(Color.GREEN);
						break;
					case 5:
						getTxtAlert().setText("Generando Reporte espere ...");
						getTxtAlert().setForeground(Color.blue);
						vc.getAllControlRegistrosOrd(mes, year, diaI, diaF);
						getTxtAlert().setText("Reporte generado correctamente");
						getTxtAlert().setForeground(Color.GREEN);
						break;
					default:
						break;
					}

				} else {

					getTxtAlert().setText("Ingrese los parametros de busqueda");
					getTxtAlert().setForeground(Color.red);
				}

				cbxDesde.removeAllItems();
				cbxHasta.removeAllItems();
				cbxMes.removeAllItems();
				cbxAnio.removeAllItems();
				System.out.println("El reporte seleccionado es: " + cbxTipoReporte.getSelectedIndex());
				cbxTipoReporte.setSelectedIndex(0);
				mes = 0;
				year = 0;
				diaI = 0;
				diaF = 0;

				System.out.println("los valores son:" + " a�o: " + year + " mes: " + mes + " dia inicial: " + diaI
						+ " dia final " + diaF);
			}
		});
		cbxHasta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				diaF = Integer.parseInt(String.valueOf(cbxHasta.getSelectedItem()));
				System.out.println(diaF);
			}
		});
		cbxDesde.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				// muestra los dias en los que se registro el control de acceso biometrico
				diaI = Integer.parseInt(String.valueOf(cbxDesde.getSelectedItem()));
				System.out.println(diaI);
			}

			@Override
			public void focusGained(FocusEvent e) {
				cbxDesde.removeAllItems();
				cbxHasta.removeAllItems();
				List<Integer> data = vc.findbyRequer(year, mes, 3);
				for (int i = 0; i < data.size(); i++) {
					cbxDesde.addItem(data.get(i));
					cbxHasta.addItem(data.get(i));
				}
			}
		});
		cbxDesde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		cbxMes.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// remueve los item para volver a asignarlso de acuerdo al a�o
				cbxMes.removeAllItems();

				// llamamos al metodo que me `permitira traer todos los meses disponibles que
				// estan registrados en la bse de datos control acceso para buscar el mes
				List<Integer> data = vc.findbyRequer(year, 0, 2);
				for (int i = 0; i < data.size(); i++) {
					cbxMes.addItem(data.get(i));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				// se le asigna el valor a la variable mes con el valor seleccionado en el campo
				// del mes
				mes = Integer.parseInt(String.valueOf(cbxMes.getSelectedItem()));
				System.out.println(mes);
			}
		});
		cbxMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		cbxAnio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// removemos los a�os disponibles de acuerdo a la busqueda en la base de datos
				cbxAnio.removeAllItems();
				// llamado al metodo para buscar todos los a�os disponibles
				List<Integer> data = vc.findbyRequer(0, 0, 1);
				for (int i = 0; i < data.size(); i++) {
					cbxAnio.addItem(data.get(i));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// asignamos a la variable del a�o el valor que se le selecciono en el combo box

				year = Integer.parseInt(String.valueOf(cbxAnio.getSelectedItem()));
				System.out.println(year);
			}
		});
		panelSeparador.setLayout(new CardLayout(0, 0));
		panel.setLayout(gl_panel);
	}

	public JTextField getTxtAlert() {
		return txtAlert;
	}

	public JComboBox getCbxDesde() {
		return cbxDesde;
	}

	public JComboBox getCbxHasta() {
		return cbxHasta;
	}

	public JComboBox getCbxAnio() {
		return cbxAnio;
	}

	public JLabel getLblNumeroRegistrosMostrar() {
		return lblNumeroRegistrosMostrar;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JPanel getPanelBodyCsv() {
		return panelBodyCsv;
	}

	public JPanel getPanelBodyReporte() {
		return panelBodyReporte;
	}
	public JPanel getPanelBodyManteniento() {
		return panelBodyManteniento;
	}
}

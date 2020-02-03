import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JLabel;

public class pinacoteca {

	private JFrame frmPinacoteca;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuItem mntmSalir;
	private JMenu mnMantenimiento;
	private JMenuItem mntmAltas;
	private JMenuItem mntmBajas;
	private JMenu mnOperaciones;
	private JMenuItem mntmModificaciones;
	private JMenu mnConsulta;
	private JMenuItem mntmListado1;
	private JMenuItem mntmListado2;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private altas a;
	private listado1 l1;
	private listado2 l2;
	private baja b;
	private salas s;
	private modificacion m;
	private ImageIcon sorolla;
	private JLabel lblNewLabel;
	private JMenuItem mntmSalas;
	private JSeparator separator_3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pinacoteca window = new pinacoteca();
					window.frmPinacoteca.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	private void cargarDriver() {//Esto se debe incluir y ser llamado en el constructor initialize
		try {
		Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception ex) {
		//No hace falta escribir nada
		}
		}
	
	public pinacoteca() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPinacoteca = new JFrame();
		frmPinacoteca.setTitle("PINACOTECA");
		frmPinacoteca.setResizable(false);
		frmPinacoteca.setBounds(100, 100, 455, 355);
		frmPinacoteca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPinacoteca.getContentPane().setLayout(null);
		
		sorolla=new ImageIcon("sorolla.jpg");
		
		lblNewLabel = new JLabel();
		lblNewLabel.setIcon(sorolla);
		lblNewLabel.setBounds(0, 0, 450, 300);
		frmPinacoteca.getContentPane().add(lblNewLabel);
		frmPinacoteca.setLocationRelativeTo(null);
				
		menuBar = new JMenuBar();
		frmPinacoteca.setJMenuBar(menuBar);
		
		mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		mntmAltas = new JMenuItem("Altas");
		mntmAltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(a==null)a=new altas();
				a.setVisible(true);
			}
		});
		mnMantenimiento.add(mntmAltas);
		
		separator = new JSeparator();
		mnMantenimiento.add(separator);
		
		mntmBajas = new JMenuItem("Bajas");
		mntmBajas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(b==null)b=new baja();
				b.setVisible(true);
			}
		});
		mnMantenimiento.add(mntmBajas);
		
		separator_1 = new JSeparator();
		mnMantenimiento.add(separator_1);
		
		mnConsulta = new JMenu("Consulta");
		mnMantenimiento.add(mnConsulta);
		
		mntmListado1 = new JMenuItem("Listado1");
		mntmListado1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(l1==null)l1=new listado1();
				l1.setVisible(true);
				l1=null;
			}
		});
		mnConsulta.add(mntmListado1);
		
		separator_2 = new JSeparator();
		mnConsulta.add(separator_2);
		
		mntmListado2 = new JMenuItem("Listado2");
		mntmListado2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(l2==null)l2=new listado2();
				l2.setVisible(true);
				l2=null;
			}
		});
		mnConsulta.add(mntmListado2);
		
		mnOperaciones = new JMenu("Operaciones");
		menuBar.add(mnOperaciones);
		
		mntmModificaciones = new JMenuItem("Modificaciones");
		mntmModificaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(m==null)m=new modificacion();
				m.setVisible(true);
				m=null;
			}
		});
		mnOperaciones.add(mntmModificaciones);
		
		separator_3 = new JSeparator();
		mnOperaciones.add(separator_3);
		
		mntmSalas = new JMenuItem("Salas");
		mntmSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(s==null)s=new salas();
				s.setVisible(true);
				s=null;
			}
		});
		mnOperaciones.add(mntmSalas);
		cargarDriver();// Aquí es llamado el la memoria Driver que es cargada en el método cargarDriver
	}
}

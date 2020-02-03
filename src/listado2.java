import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 


@SuppressWarnings("serial")
public class listado2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblListado;
	private JButton btnVolver;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel modelo=new DefaultTableModel();//Para usar la tabla

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			listado2 dialog = new listado2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public listado2() {
		setResizable(false);
		setTitle("LISTADO 2");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblListado = new JLabel("Listado 2 ");
		lblListado.setBounds(37, 35, 70, 14);
		contentPanel.add(lblListado);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
			}
		});
		btnVolver.setBounds(317, 31, 89, 23);
		contentPanel.add(btnVolver);
		
		Border bordeTabla = BorderFactory.createEmptyBorder(0, 0, 0, 0);//Para eliminar el borde del scroll panel
		
		scrollPane = new JScrollPane();//Para colocarlo se pincha y se arrastra.
		scrollPane.setBorder(bordeTabla);
		scrollPane.setBounds(37, 74, 369, 161);
		contentPanel.add(scrollPane);
		
		table = new JTable(modelo);//Hemos creado el objeto "modelo" de la clase "DefaultTableModel"
		scrollPane.setViewportView(table);
		
		modelo.addColumn("Código");//Este se añade a mano
		modelo.addColumn("Título");
		modelo.addColumn("Pintor");
		modelo.addColumn("Año");
		modelo.addColumn("Precio");
		
		try{
			
			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd_pinacoteca","root","");
			Statement comando=conexion.createStatement();
			ResultSet registro=comando.executeQuery("select * from pinturas order by codigo");
			
			while(registro.next()){//para crear la tabla
				
				Object[]fila=new Object[modelo.getColumnCount()];
				
				for (int i = 0; i < modelo.getColumnCount(); i++) {
					
					fila[i]=registro.getObject(i+1);
					
				}
				modelo.addRow(fila);//Añadimos las filas
			}
			
			conexion.close();
			
		}catch(SQLException e){
			setTitle(e.toString());
		}
	}
}

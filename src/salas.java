import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@SuppressWarnings("serial")
public class salas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblIntroduzcaElNombre;
	private JTextField textFieldSala;
	private JButton btnListar;
	private JButton btnVolver;
	private DefaultTableModel modelo=new DefaultTableModel();//Para usar la tabla
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnLimpiar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			salas dialog = new salas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public salas() {
		setResizable(false);
		setTitle("SALAS");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 444, 268);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		lblIntroduzcaElNombre = new JLabel("Introduzca el nombre de la sala");
		lblIntroduzcaElNombre.setBounds(32, 32, 185, 14);
		contentPanel.add(lblIntroduzcaElNombre);
		
		textFieldSala = new JTextField();
		textFieldSala.setBounds(230, 29, 86, 20);
		contentPanel.add(textFieldSala);
		textFieldSala.setColumns(10);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					boolean flag=false;
				
					if(textFieldSala.getText().isEmpty()){
						
						JOptionPane.showMessageDialog(null, "Por favor, introduzca el nombre de la sala", "Información", JOptionPane.INFORMATION_MESSAGE);
						textFieldSala.requestFocus();
					}
					else{
						
						try{
							
							
							Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd_pinacoteca","root","");
							Statement comando=conexion.createStatement();
							ResultSet registro=comando.executeQuery("select titulo,pintor,nombre from pinturas p join sala s on  p.id_sala=s.id_sala where nombre='"+textFieldSala.getText()+"' order by titulo");
							
							scrollPane.setVisible(true);
							btnLimpiar.setVisible(true);
							
							while(registro.next()){//Para crear la tabla
								
								Object[]fila=new Object[modelo.getColumnCount()];
								
								for (int i = 0; i < modelo.getColumnCount(); i++) {
									
									fila[i]=registro.getObject(i+1);
								}
								modelo.addRow(fila);//Se añaden las filas
							}
							if(modelo.getRowCount()==0)	flag=true;
							conexion.close();
							
						}catch(SQLException ex){
							setTitle(ex.toString());
					}
					
				}
					if(flag){
						
						scrollPane.setVisible(false);
						textFieldSala.setText("");
						textFieldSala.requestFocus();
						btnLimpiar.setVisible(false);
						JOptionPane.showMessageDialog(null, "La sala no existe", "Información", JOptionPane.INFORMATION_MESSAGE);
						}
			}
		});
		btnListar.setBounds(326, 28, 89, 23);
		contentPanel.add(btnListar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
			}
		});
		btnVolver.setBounds(326, 62, 89, 23);
		contentPanel.add(btnVolver);
		
		Border bordeTabla = BorderFactory.createEmptyBorder(0, 0, 0, 0);//Para eliminar el borde del scroll panel
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBorder(bordeTabla);
		scrollPane.setBounds(32, 100, 379, 117);
		contentPanel.add(scrollPane);
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setVisible(false);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textFieldSala.setText("");
				textFieldSala.requestFocus();
				scrollPane.setVisible(false);
				btnLimpiar.setVisible(false);
				modelo.setNumRows(0);
			}
		});
		btnLimpiar.setBounds(326, 228, 89, 23);
		contentPanel.add(btnLimpiar);
		
		modelo.addColumn("Título");
		modelo.addColumn("Pintor");
		modelo.addColumn("Sala");
		
	}
}

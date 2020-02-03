import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class modificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblTtulo;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxTitulo;
	private JLabel lblAutor;
	private JLabel lblPrecio;
	private JLabel lblNuevoPrecio;
	private JLabel lblAo;
	private JLabel lblCdigo;
	private JTextField textFieldAutor;
	private JTextField textFieldPrecio;
	private JTextField textFieldNuevoPrecio;
	private JTextField textFieldAnio;
	private JTextField textFieldCodigo;
	private JButton btnModificar;
	private JButton btnLimpiar;
	private JButton btnVolver;
	private JLabel lblSala;
	private JTextField textFieldSala;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			modificacion dialog = new modificacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public modificacion() {
		setResizable(false);
		//setResizable(false);
		setTitle("MODIFICACI\u00D3N");
		setBounds(100, 100, 457, 352);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(38, 25, 63, 14);
		contentPanel.add(lblTtulo);
		
		comboBoxTitulo = new JComboBox();
		comboBoxTitulo.setFocusable(false);
		
		try{
			comboBoxTitulo.addItem("Seleccione");//Añado seleccine al comboBox
			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd_pinacoteca","root","");
			Statement comando=conexion.createStatement();
			ResultSet registro=comando.executeQuery("select titulo from pinturas order by titulo");

			while(registro.next()){

				comboBoxTitulo.addItem(String.valueOf(registro.getString("titulo")));
			}
		}catch(SQLException e){
			setTitle(e.toString());
		}
		comboBoxTitulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{

					Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd_pinacoteca","root","");
					Statement comando=conexion.createStatement();
					ResultSet registro=comando.executeQuery("Select * from pinturas where titulo='"+comboBoxTitulo.getSelectedItem().toString()+"'");

					if(registro.next()){

						textFieldAutor.setText(registro.getString("pintor"));
						textFieldPrecio.setText(registro.getString("precio"));
						textFieldAnio.setText(registro.getString("año"));
						textFieldCodigo.setText(registro.getString("codigo"));	
						textFieldSala.setText(registro.getString("id_sala"));
					}

					conexion.close();

				}catch(SQLException ex){
					setTitle(ex.toString());
				}
			}
		});
		comboBoxTitulo.setBounds(146, 25, 120, 20);
		contentPanel.add(comboBoxTitulo);
		
		lblAutor = new JLabel("Autor");
		lblAutor.setBounds(38, 66, 46, 14);
		contentPanel.add(lblAutor);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(38, 109, 46, 14);
		contentPanel.add(lblPrecio);
		
		lblNuevoPrecio = new JLabel("Nuevo precio");
		lblNuevoPrecio.setBounds(38, 150, 91, 14);
		contentPanel.add(lblNuevoPrecio);
		
		lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(38, 193, 46, 14);
		contentPanel.add(lblAo);
		
		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(38, 235, 46, 14);
		contentPanel.add(lblCdigo);
		
		textFieldAutor = new JTextField();
		textFieldAutor.setFocusable(false);
		textFieldAutor.setBackground(Color.WHITE);
		textFieldAutor.setEditable(false);
		textFieldAutor.setBounds(146, 63, 120, 20);
		contentPanel.add(textFieldAutor);
		textFieldAutor.setColumns(10);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setFocusable(false);
		textFieldPrecio.setBackground(Color.WHITE);
		textFieldPrecio.setEditable(false);
		textFieldPrecio.setBounds(146, 106, 120, 20);
		contentPanel.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		textFieldNuevoPrecio = new JTextField();
		textFieldNuevoPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char tecla=e.getKeyChar();
				if (tecla<'0' || tecla>'9'){
					e.consume();
				}
			}
		});
		textFieldNuevoPrecio.setBounds(146, 147, 120, 20);
		contentPanel.add(textFieldNuevoPrecio);
		textFieldNuevoPrecio.setColumns(10);
		
		textFieldAnio = new JTextField();
		textFieldAnio.setFocusable(false);
		textFieldAnio.setBackground(Color.WHITE);
		textFieldAnio.setEditable(false);
		textFieldAnio.setBounds(146, 190, 120, 20);
		contentPanel.add(textFieldAnio);
		textFieldAnio.setColumns(10);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setFocusable(false);
		textFieldCodigo.setRequestFocusEnabled(false);
		textFieldCodigo.setBackground(Color.WHITE);
		textFieldCodigo.setEditable(false);
		textFieldCodigo.setBounds(146, 232, 120, 20);
		contentPanel.add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFocusable(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					if(comboBoxTitulo.getSelectedItem().toString().equalsIgnoreCase("Seleccione")){
						
						JOptionPane.showMessageDialog(null, "Por favor,seleccione un cuadro", "Información", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(textFieldNuevoPrecio.getText().isEmpty()){

						JOptionPane.showMessageDialog(null, "Por favor,Introduzca nuevo precio", "Información", JOptionPane.INFORMATION_MESSAGE);
						textFieldNuevoPrecio.requestFocus();
					}
					else{

						Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd_pinacoteca","root","");
						Statement comando=conexion.createStatement();
						comando.executeUpdate("update pinturas set precio='"+Integer.valueOf(textFieldNuevoPrecio.getText())+"' where titulo='"+comboBoxTitulo.getSelectedItem()+"'");

						JOptionPane.showMessageDialog(null, "El precio ha sido modificado", "Información", JOptionPane.INFORMATION_MESSAGE);
						textFieldPrecio.setText(textFieldNuevoPrecio.getText());
						textFieldNuevoPrecio.setText("");
						conexion.close();
					}

				}catch(SQLException ex){
					setTitle(ex.toString());
				}
			}

		});
		btnModificar.setBounds(311, 24, 89, 23);
		contentPanel.add(btnModificar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFocusable(false);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldAutor.setText("");
				textFieldPrecio.setText("");
				textFieldAnio.setText("");
				textFieldCodigo.setText("");
				textFieldSala.setText("");
				comboBoxTitulo.setSelectedIndex(0);
			}
		});
		btnLimpiar.setBounds(311, 62, 89, 23);
		contentPanel.add(btnLimpiar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFocusable(false);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldAutor.setText("");
				textFieldPrecio.setText("");
				textFieldAnio.setText("");
				textFieldCodigo.setText("");
				textFieldSala.setText("");
				comboBoxTitulo.setSelectedIndex(0);
				dispose();
			}
		});
		btnVolver.setBounds(311, 105, 89, 23);
		contentPanel.add(btnVolver);
		
		lblSala = new JLabel("Sala");
		lblSala.setBounds(38, 279, 46, 14);
		contentPanel.add(lblSala);
		
		textFieldSala = new JTextField();
		textFieldSala.setRequestFocusEnabled(false);
		textFieldSala.setFocusable(false);
		textFieldSala.setEditable(false);
		textFieldSala.setBackground(Color.WHITE);
		textFieldSala.setBounds(146, 276, 120, 20);
		contentPanel.add(textFieldSala);
		textFieldSala.setColumns(10);
	}
}

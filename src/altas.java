import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;//importar para manejar la base de datos
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


@SuppressWarnings("serial")
public class altas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblCodigo;
	private JLabel lblTitulo;
	private JLabel lblPintor;
	private JLabel lblAnio;
	private JLabel lblPrecio;
	private JTextField textFieldCodigo;
	private JTextField textFieldTitulo;
	private JTextField textFieldPintor;
	private JTextField textFieldAnio;
	private JTextField textFieldPrecio;
	private JButton btnComprobar;
	private JButton btnVolver;
	private JButton btnLimpiar;
	private JButton btnAlta;
	private JLabel lblSala;
	private JTextField textFieldSala;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			altas dialog = new altas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public altas() {
		setResizable(false);
		setTitle("ALTAS");
		setBounds(100, 100, 454, 346);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		contentPanel.setBounds(0, 0, 448, 314);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(38, 28, 46, 14);
		contentPanel.add(lblCodigo);

		lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setVisible(false);
		lblTitulo.setBounds(38, 66, 46, 14);
		contentPanel.add(lblTitulo);

		lblPintor = new JLabel("Pintor");
		lblPintor.setVisible(false);
		lblPintor.setBounds(38, 109, 46, 14);
		contentPanel.add(lblPintor);

		lblAnio = new JLabel("A\u00F1o");
		lblAnio.setVisible(false);
		lblAnio.setBounds(38, 153, 46, 14);
		contentPanel.add(lblAnio);

		lblPrecio = new JLabel("Precio");
		lblPrecio.setVisible(false);
		lblPrecio.setBounds(38, 198, 46, 14);
		contentPanel.add(lblPrecio);

		textFieldCodigo = new JTextField();
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char tecla=e.getKeyChar();
				if (tecla<'0' || tecla>'9'){
					e.consume();
				}
			}
		});
		textFieldCodigo.setBounds(139, 25, 158, 20);
		contentPanel.add(textFieldCodigo);
		textFieldCodigo.setColumns(10);

		textFieldTitulo = new JTextField();
		textFieldTitulo.setVisible(false);
		textFieldTitulo.setFocusable(true);
		textFieldTitulo.setBounds(139, 65, 158, 20);
		contentPanel.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);

		textFieldPintor = new JTextField();
		textFieldPintor.setVisible(false);
		textFieldPintor.setBounds(139, 106, 158, 20);
		contentPanel.add(textFieldPintor);
		textFieldPintor.setColumns(10);

		textFieldAnio = new JTextField();
		textFieldAnio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char tecla=e.getKeyChar();
				if(tecla<'0' || tecla>'9'){
					e.consume();
				}
			}
		});
		textFieldAnio.setVisible(false);
		textFieldAnio.setBounds(139, 150, 158, 20);
		contentPanel.add(textFieldAnio);
		textFieldAnio.setColumns(10);

		textFieldPrecio = new JTextField();
		textFieldPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char tecla=e.getKeyChar();
				if(tecla<'0' || tecla>'9'){
					e.consume();
				}
			}
		});
		textFieldPrecio.setVisible(false);
		textFieldPrecio.setBounds(139, 195, 158, 20);
		contentPanel.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(textFieldCodigo.getText().isEmpty()){

					JOptionPane.showMessageDialog(null, "Por favor, introduzca el código", "Información", JOptionPane.INFORMATION_MESSAGE);
					textFieldCodigo.requestFocus();
				}
				else{
					try{

						Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd_pinacoteca","root","");
						Statement comando=conexion.createStatement();
						ResultSet registro=comando.executeQuery("Select * from pinturas where codigo="+textFieldCodigo.getText()+"");

						if(registro.next()){
							
							JOptionPane.showMessageDialog(null, "El código existe", "Información", JOptionPane.INFORMATION_MESSAGE);
							textFieldTitulo.setVisible(true);
							textFieldPintor.setVisible(true);
							textFieldAnio.setVisible(true);
							textFieldPrecio.setVisible(true);
							textFieldSala.setVisible(true);
							lblTitulo.setVisible(true);
							lblPintor.setVisible(true);
							lblAnio.setVisible(true);
							lblPrecio.setVisible(true);
							lblSala.setVisible(true);
							

							textFieldCodigo.setText(registro.getString("codigo"));
							textFieldTitulo.setText(registro.getString("titulo"));
							textFieldPintor.setText(registro.getString("pintor"));
							textFieldAnio.setText(registro.getString("año"));
							textFieldPrecio.setText(registro.getString("precio"));
							textFieldSala.setText(registro.getString("id_sala"));
						
						}
						else {

							JOptionPane.showMessageDialog(null, "El código no existe. Introduzca nuevo código", "Información", JOptionPane.INFORMATION_MESSAGE);
							textFieldTitulo.setVisible(true);
							textFieldPintor.setVisible(true);
							textFieldAnio.setVisible(true);
							textFieldPrecio.setVisible(true);
							textFieldSala.setVisible(true);
							lblTitulo.setVisible(true);
							lblPintor.setVisible(true);
							lblAnio.setVisible(true);
							lblPrecio.setVisible(true);
							lblSala.setVisible(true);
							btnLimpiar.setVisible(true);
							btnAlta.setVisible(true);	
							textFieldTitulo.requestFocus();
						}

						conexion.close();

					}catch(SQLException ex){
						setTitle(ex.toString());
					}
				}
			}
		});
		btnComprobar.setBounds(307, 24, 107, 23);
		contentPanel.add(btnComprobar);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				lblTitulo.setVisible(false);
				lblPintor.setVisible(false);
				lblAnio.setVisible(false);
				lblPrecio.setVisible(false);
				lblSala.setVisible(false);
				textFieldTitulo.setVisible(false);
				textFieldPintor.setVisible(false);
				textFieldAnio.setVisible(false);
				textFieldPrecio.setVisible(false);
				textFieldSala.setVisible(false);
				textFieldSala.setVisible(false);
				textFieldCodigo.setText("");
				textFieldCodigo.requestFocus();
				btnLimpiar.setVisible(false);
				btnAlta.setVisible(false);
				textFieldCodigo.setText("");
				textFieldTitulo.setText("");
				textFieldPintor.setText("");
				textFieldAnio.setText("");
				textFieldPrecio.setText("");
				textFieldSala.setText("");

				dispose();
			}
		});
		btnVolver.setBounds(307, 65, 108, 23);
		contentPanel.add(btnVolver);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textFieldTitulo.setText("");
				textFieldPintor.setText("");
				textFieldAnio.setText("");
				textFieldPrecio.setText("");
				textFieldSala.setText("");
				textFieldTitulo.requestFocus();
			}
		});
		btnLimpiar.setVisible(false);
		btnLimpiar.setBounds(208, 280, 89, 23);
		contentPanel.add(btnLimpiar);

		btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{

					if(textFieldTitulo.getText().isEmpty()){

						JOptionPane.showMessageDialog(null, "Por favor, introduzca el título", "Información", JOptionPane.INFORMATION_MESSAGE);
						textFieldTitulo.requestFocus();
					}
					else if(textFieldPintor.getText().isEmpty()){

						JOptionPane.showMessageDialog(null, "Por favor, introduzca el pintor", "Información", JOptionPane.INFORMATION_MESSAGE);
						textFieldPintor.requestFocus();
					}
					else if(textFieldAnio.getText().isEmpty()){

						JOptionPane.showMessageDialog(null, "Por favor, introduzca el año", "Información", JOptionPane.INFORMATION_MESSAGE);
						textFieldAnio.requestFocus();
					}
					else if(textFieldPrecio.getText().isEmpty()){

						JOptionPane.showMessageDialog(null, "Por favor, introduzca el precio", "Información", JOptionPane.INFORMATION_MESSAGE);
						textFieldPrecio.requestFocus();
					}
					else if(textFieldSala.getText().isEmpty()){
						
						JOptionPane.showMessageDialog(null, "Por favor, introduzca el código de la sala", "Información", JOptionPane.INFORMATION_MESSAGE);
						textFieldSala.requestFocus();
					}
					else{

						Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd_pinacoteca","root","");
						Statement comando=conexion.createStatement();
						comando.executeUpdate("insert into pinturas(codigo,titulo,pintor,año,precio,id_sala)values("+textFieldCodigo.getText()+",'"+textFieldTitulo.getText()+"','"+textFieldPintor.getText()+"',"+textFieldAnio.getText()+","+textFieldPrecio.getText()+","+textFieldSala.getText()+")");
						
						JOptionPane.showMessageDialog(null, "Alta realizada", "Información", JOptionPane.INFORMATION_MESSAGE);
						lblTitulo.setVisible(false);
						lblPintor.setVisible(false);
						lblAnio.setVisible(false);
						lblPrecio.setVisible(false);
						lblSala.setVisible(false);
						textFieldTitulo.setVisible(false);
						textFieldPintor.setVisible(false);
						textFieldAnio.setVisible(false);
						textFieldPrecio.setVisible(false);
						textFieldSala.setVisible(false);
						textFieldCodigo.setText("");
						textFieldCodigo.requestFocus();
						btnLimpiar.setVisible(false);
						btnAlta.setVisible(false);
						textFieldCodigo.setText("");
						textFieldTitulo.setText("");
						textFieldPintor.setText("");
						textFieldAnio.setText("");
						textFieldPrecio.setText("");
						textFieldSala.setText("");

						conexion.close();
					}
				}catch(SQLException ex){
					setTitle(ex.toString());	
				}
			}
		});
		btnAlta.setVisible(false);
		btnAlta.setBounds(310, 280, 89, 23);
		contentPanel.add(btnAlta);
		
		lblSala = new JLabel("Sala");
		lblSala.setVisible(false);
		lblSala.setBounds(38, 245, 46, 14);
		contentPanel.add(lblSala);
		
		textFieldSala = new JTextField();
		textFieldSala.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char tecla=e.getKeyChar();
				if(tecla<'0' || tecla>'9'){
					e.consume();
				}
			}
		});
		textFieldSala.setVisible(false);
		textFieldSala.setBounds(139, 242, 158, 20);
		contentPanel.add(textFieldSala);
		textFieldSala.setColumns(10);
		btnLimpiar.setVisible(false);
	}
}

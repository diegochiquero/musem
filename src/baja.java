import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
//import sun.rmi.transport.Connection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


@SuppressWarnings("serial")
public class baja extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblIntroduzcaElCdigo;
	private JTextField textFieldBaja;
	private JButton btnBaja;
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			baja dialog = new baja();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public baja() {
		setResizable(false);//Para que la ventana no pueda redimensionarse
		setTitle("BAJA");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		contentPanel.setBounds(0, 0, 444, 268);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		lblIntroduzcaElCdigo = new JLabel("C\u00F3digo a dar de baja");
		lblIntroduzcaElCdigo.setBounds(38, 28, 138, 14);
		contentPanel.add(lblIntroduzcaElCdigo);
		
		textFieldBaja = new JTextField();
		textFieldBaja.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char tecla=e.getKeyChar();
				if(tecla<'0' || tecla>'9'){
					e.consume();
				}
			}
		});
		textFieldBaja.setBounds(202, 25, 86, 20);
		contentPanel.add(textFieldBaja);
		textFieldBaja.setColumns(10);
		
		btnBaja = new JButton("Baja");
		btnBaja.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {

				if(textFieldBaja.getText().isEmpty()){

					JOptionPane.showMessageDialog(null, "Por favor, introduzca el código", "Información", JOptionPane.INFORMATION_MESSAGE);
					textFieldBaja.requestFocus();
				}
				else{
					try{

						Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd_pinacoteca","root","");
						Statement comando=conexion.createStatement();
						ResultSet registro=comando.executeQuery("select codigo from pinturas where codigo="+textFieldBaja.getText()+"");
						
						if(registro.next()){

							int op=JOptionPane.showConfirmDialog(null, "Esta usted seguro de borrar");

							if(op==0){

								int valor=comando.executeUpdate("delete from pinturas where codigo="+textFieldBaja.getText()+"");

								if(valor==1){

									JOptionPane.showMessageDialog(null, "El código ha sido borrardo", "Informacion", JOptionPane.INFORMATION_MESSAGE);
									textFieldBaja.setText("");
									textFieldBaja.requestFocus();
								}
							}
							else{

								textFieldBaja.setText("");
								textFieldBaja.requestFocus();
							}
						}
						else{

							JOptionPane.showMessageDialog(null, "El código no existe. Introduzca nuevo código", "Información", JOptionPane.INFORMATION_MESSAGE);
							textFieldBaja.setText("");
							textFieldBaja.requestFocus();
						}
						conexion.close();
					}catch(SQLException e){
						setTitle(e.toString());
					}
				}
			}
		});
		btnBaja.setBounds(322, 24, 89, 23);
		contentPanel.add(btnBaja);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
			}
		});
		btnVolver.setBounds(322, 58, 89, 23);
		contentPanel.add(btnVolver);
	}
}

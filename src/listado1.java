import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class listado1 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblListado;
	private JTextArea textArea;
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			listado1 dialog = new listado1();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public listado1() {
		setResizable(false);
		setTitle("LISTADO 1");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 444, 268);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		lblListado = new JLabel("Listado 1 ");
		lblListado.setBounds(37, 35, 79, 14);
		contentPanel.add(lblListado);

		textArea = new JTextArea();
		textArea.setBounds(37, 74, 369, 161);
		contentPanel.add(textArea);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				textArea.setText("");
			}
		});
		btnVolver.setBounds(317, 31, 89, 23);
		contentPanel.add(btnVolver);

		try{

			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd_pinacoteca","root","");
			Statement comando=conexion.createStatement();
			ResultSet registro=comando.executeQuery("select * from pinturas order by codigo");

			while(registro.next()){

				textArea.append(

						registro.getString("codigo")+"  "+registro.getString("titulo")+"  "+registro.getString("pintor")+"  "+registro.getString("año")+"  "+registro.getString("precio")+"\n");
			}
			conexion.close();
			
		}catch(SQLException e){
			setTitle(e.toString());
		}
	}
}

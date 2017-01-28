import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class Clients extends JFrame {

	private JPanel contentPane;
	private JTextField txtBarcode;
	private JTextField textField;
	private JLabel label1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clients frame = new Clients();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Clients() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\Downloads\\a7c01c438bc4d025535578be728b7174.jpg"));
		setTitle("CLIENTS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtBarcode = new JTextField();
		txtBarcode.setText("Barcode");
		txtBarcode.setBounds(10, 11, 86, 20);
		contentPane.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		label1 = new JLabel("");
		label1.setBounds(86, 58, 46, 14);
		contentPane.add(label1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
// TODO: Local variable could be declared final
				int barcode = Integer.parseInt(txtBarcode.getText());
				String connectionURL = "jdbc:mysql://localhost:3306/apothiki";
	            String username = "root";
	            String password = "";
	            Connection conn;
	            String apothemata = "0";
	            int iparxei = 0;
				try {
					conn = DriverManager.getConnection(connectionURL,username,password);
				    Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM apothemata");
					while(rs.next()){
						int barc = rs.getInt("barcode");
						if ( barcode == barc){
							iparxei = 1;
							apothemata = rs.getString("posa");
						}
					}
					if (iparxei == 1) {
						
						label1.setText(apothemata);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        
				
			}
				
			
		});
		btnSearch.setBounds(112, 10, 89, 23);
		contentPane.add(btnSearch);
		
		JLabel label = new JLabel("\u03A5\u03C0\u03AC\u03C1\u03C7\u03BF\u03C5\u03BD");
		label.setBounds(10, 58, 86, 14);
		contentPane.add(label);
		
		
		
		textField = new JTextField();
		textField.setText("\u03A0\u03CC\u03C3\u03B1 \u03B8\u03B5\u03C2 ?");
		textField.setBounds(10, 83, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u039A\u03C1\u03AC\u03C4\u03B7\u03C3\u03B7");
		button.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				label1.setText("");
				int barcode = Integer.parseInt(txtBarcode.getText());
				String connectionURL = "jdbc:mysql://localhost:3306/apothiki";
	            String username = "root";
	            String password = "";
	            Connection conn;
	            int apothemata = 0;
	            String emfanise = "";
	            int iparxei = 0;
				try {
					conn = DriverManager.getConnection(connectionURL,username,password);
				    Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM apothemata");
					while(rs.next()){
						int barc = rs.getInt("barcode");
						if ( barcode == barc){
							iparxei = 1;
							apothemata = rs.getInt("posa");
							emfanise =  Integer.toString(apothemata);
							
						}
					}
					if (iparxei == 1) {
						int posathes = Integer.parseInt(textField.getText());
						if (posathes > apothemata) {
							JOptionPane.showMessageDialog(null, "Το απόθεμα δεν επαρκεί", "Notice",
                                    JOptionPane.ERROR_MESSAGE);		
						 	label1.setText(emfanise); 
						}else {
							String query = "update apothemata set posa = ? where barcode = ? ";
			            	PreparedStatement preparedStmt = conn.prepareStatement(query);
			            	apothemata = apothemata - posathes;
			            	emfanise = Integer.toString(apothemata) ;
			            	preparedStmt.setInt(1, apothemata);
			            	preparedStmt.setInt (2, barcode);
			            	preparedStmt.executeUpdate();
			            	JOptionPane.showMessageDialog(null, "Το απόθεμα εχει κρατηθεί", "INFO",
                                    JOptionPane.INFORMATION_MESSAGE);	
			            	
			            	label1.setText(emfanise); 
			            	
						}
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        
				
			}
			
		});
		button.setBounds(112, 83, 89, 23);
		contentPane.add(button);
		
		JButton btnReload = new JButton("Reload");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton)e.getSource();
			    Window window = SwingUtilities.windowForComponent(button);
			    window.setVisible(false);
		    Clients s = new Clients();
	          s.setVisible(true);
			}
		});
		btnReload.setBounds(335, 227, 89, 23);
		contentPane.add(btnReload);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 253);
		contentPane.add(panel);
	}
}

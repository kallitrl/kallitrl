import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField1;
	private JTextField txtBarcode;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField txtBarcode1;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	public Admin() {
		setForeground(Color.ORANGE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\Downloads\\images...jpg"));
		setTitle("ADMIN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 535, 426);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClient = new JButton("\u0394\u03B7\u03BC\u03B9\u03BF\u03C5\u03C1\u03B3\u03AF\u03B1 Client");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Συμπλήρωσε username και password");
				
			}
		});
		btnClient.setBounds(10, 11, 134, 23);
		contentPane.add(btnClient);
		
		JButton btnNewButton = new JButton("\u0394\u03B9\u03B1\u03B3\u03C1\u03B1\u03C6\u03AE Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Συπλήρωσε username");
			}
		});
		btnNewButton.setBounds(10, 45, 134, 23);
		contentPane.add(btnNewButton);
		
		button = new JButton("\u03A0\u03C1\u03BF\u03C3\u03B8\u03AE\u03BA\u03B7 \u03A0\u03C1\u03BF\u03B9\u03CC\u03BD\u03C4\u03BF\u03C2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Συπλήρωσε τα στοιχεία του προϊόντος");
			}
		});
		button.setBounds(149, 11, 172, 23);
		contentPane.add(button);
		
		JButton btnNewButton1 = new JButton("\u0391\u03C6\u03B1\u03AF\u03C1\u03B5\u03C3\u03B7 \u03A0\u03C1\u03BF\u03B9\u03CC\u03BD\u03C4\u03BF\u03C2");
		btnNewButton1.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Συπλήρωσε το όνομα και το barcode του προϊόντος");
			}
		});
		btnNewButton1.setBounds(149, 45, 172, 23);
		contentPane.add(btnNewButton1);
		
		textField = new JTextField();
		textField.setBounds(10, 96, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField1 = new JTextField();
		textField1.setBounds(10, 139, 86, 20);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 124, 86, 14);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 79, 69, 14);
		contentPane.add(lblUsername);
		
		JButton btnNewButton2 = new JButton("\u0394\u03B7\u03BC\u03B9\u03BF\u03C5\u03C1\u03B3\u03AF\u03B1");
		
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user= textField.getText();
				String pass= textField1.getText();
				if(textField.getText().isEmpty() || textField1.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Δεν μπορείς να κάνεις δημιουργία χώρις να έχεις βάλει τα στοιχεία του νέου χρήστη.","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else{
					String connectionURL = "jdbc:mysql://localhost:3306/apothiki";
		            String username = "root";
		            String password = "";
		            int role =2 ;
		            Connection conn;
					try {
						conn = DriverManager.getConnection(connectionURL,username,password);
						PreparedStatement ps = null;
						if (user != null) {
							if (pass != null) {
								Statement stmt2 = conn.createStatement();
								ResultSet rs = stmt2.executeQuery("SELECT * FROM Login");
								int iparxei = 0;
								while(rs.next()) {
									String onoma = rs.getString("username");
									if ( onoma.equals(user) ){
										iparxei = 1 ;
									} 
								
							}if ( iparxei < 1) {
								ps = conn.prepareStatement (
								        "INSERT INTO login (username, password, role) VALUES(?,?,?)");
								        ps.setString (1, user);
								        ps.setString (2, pass);
								        ps.setInt (3, role);
								        ps.executeUpdate ();
								        JOptionPane.showMessageDialog(null, "User Inserted", "INFO",
			                                    JOptionPane.INFORMATION_MESSAGE);
								        textField.setVisible(false);
								        textField1.setVisible(false);
								        button.setVisible(false);
								} else {
									JOptionPane.showMessageDialog(null, "User Already Exists", "Warning",
		                                    JOptionPane.INFORMATION_MESSAGE);}
								
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton2.setBounds(100, 95, 118, 23);
		contentPane.add(btnNewButton2);
		
		JButton btnDelete = new JButton("\u0394\u03B9\u03B1\u03B3\u03C1\u03B1\u03C6\u03AE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user= textField.getText();
				String connectionURL = "jdbc:mysql://localhost:3306/apothiki";
	            String username = "root";
	            String password = "";
	            
					Connection conn;
					String usr =null;
					try {
						conn = DriverManager.getConnection(connectionURL,username,password);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM Login");
						int found = 0 ;
					
						while (rs.next()){
							String onoma = rs.getString("username");
							if ( onoma.equals(user)){
								found = 1;
								usr = rs.getString("username");
							}
							
						}
						if (found == 1){
							String query = "delete from login where username = ?";
						      PreparedStatement preparedStmt = conn.prepareStatement(query);
						      preparedStmt.setString(1, usr);
						      preparedStmt.execute();
							JOptionPane.showMessageDialog(null, "User Found and Deleted", "Notice",
	                                JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "User Not Found", "Warning",
	                                JOptionPane.ERROR_MESSAGE);
						}
				
				
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
		});
		btnDelete.setBounds(100, 139, 118, 23);
		contentPane.add(btnDelete);
		
		txtBarcode = new JTextField();
		txtBarcode.setHorizontalAlignment(SwingConstants.CENTER);
		txtBarcode.setText("Barcode");
		txtBarcode.setBounds(10, 255, 147, 20);
		contentPane.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JButton btnNewButton4 = new JButton("Search");
		btnNewButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int barcode = Integer.parseInt(txtBarcode.getText());
				String connectionURL = "jdbc:mysql://localhost:3306/apothiki";
	            String username = "root";
	            String password = "";
	            Connection apothiki;
	            String apothemata = "0";
	            int iparxei = 0;
				try {
					apothiki = DriverManager.getConnection(connectionURL,username,password);
				    Statement stmt = apothiki.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM Apothemata");
					while(rs.next()){
						int barc = rs.getInt("barcode");
						if ( barcode == barc){
							iparxei = 1;
							apothemata = rs.getString("posa");
						}
					}
					if (iparxei == 1) {
						JLabel label = new JLabel("");
						label.setBounds(304, 255, 46, 14);
						contentPane.add(label);
						label.setText(apothemata);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        
				
			}
		});
		btnNewButton4.setBounds(35, 286, 89, 23);
		contentPane.add(btnNewButton4);
		
		JLabel lblNewLabel = new JLabel("\u0388\u03C7\u03BF\u03C5\u03BD \u03B1\u03C0\u03BF\u03BC\u03B5\u03AF\u03BD\u03B5\u03B9 :");
		lblNewLabel.setBounds(193, 258, 101, 14);
		contentPane.add(lblNewLabel);
		
	
		textField2 = new JTextField();
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setText("\u038C\u03BD\u03BF\u03BC\u03B1");
		textField2.setBounds(353, 96, 86, 20);
		contentPane.add(textField2);
		textField2.setColumns(10);
		
		textField3 = new JTextField();
		textField3.setHorizontalAlignment(SwingConstants.CENTER);
		textField3.setText("\u03A0\u03BF\u03C3\u03CC\u03C4\u03B7\u03C4\u03B1");
		textField3.setBounds(353, 178, 86, 20);
		contentPane.add(textField3);
		textField3.setColumns(10);
		
		txtBarcode1 = new JTextField();
		txtBarcode1.setText("Barcode");
		txtBarcode1.setHorizontalAlignment(SwingConstants.CENTER);
		txtBarcode1.setBounds(353, 139, 86, 20);
		contentPane.add(txtBarcode1);
		txtBarcode1.setColumns(10);
		
		JButton btnNewButton5 = new JButton("\u03A0\u03C1\u03BF\u03C3\u03B8\u03AE\u03BA\u03B7");
		btnNewButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String connectionURL = "jdbc:mysql://localhost:3306/apothiki";
	            String username = "root";
	            String password = "";
	            try {
					Connection apothiki = DriverManager.getConnection(connectionURL,username,password);
					PreparedStatement ps = null;
				
					Statement stmt = apothiki.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM Apothemata");
					int barc = Integer.parseInt(txtBarcode1.getText());
					int apoth = 0;
					int iparxei = 0;
					while(rs.next()){
					int barcode = rs.getInt("barcode");
					
					if ( barc == barcode ){
						iparxei = 1 ;
						apoth = rs.getInt("posa");
					}
					
					}
					if (iparxei == 0){
						ps = apothiki.prepareStatement (
						        "INSERT INTO apothemata ( onoma, posa, barcode) VALUES(?,?,?)");
// TODO: Local variable could be declared final
						        String onoma = textField2.getText();
						        int posa = Integer.parseInt(textField3.getText());
						        int barcode = Integer.parseInt(txtBarcode1.getText());
						      
						      
						        ps.setString (1, onoma);
						        ps.setInt (2, posa);
						        ps.setInt (3, barcode);
						        ps.executeUpdate ();
						        JOptionPane.showMessageDialog(null, "Το απόθεμα έχει εισαχθεί", "Notice",
	                                    JOptionPane.INFORMATION_MESSAGE);
						      
						 
					
					            }else if (iparxei == 1) {
					            	int posa = Integer.parseInt(textField3.getText());
							        int barcode = Integer.parseInt(txtBarcode1.getText());
					            	String query = "update apothemata set posa = ? where barcode = ? ";
					            	PreparedStatement preparedStmt = apothiki.prepareStatement(query);
					            	posa = posa + apoth;
					            	preparedStmt.setInt(1, posa);
					            	preparedStmt.setInt (2, barcode);
					            	preparedStmt.executeUpdate();
							        JOptionPane.showMessageDialog(null, "Το απόθεμα έχει ανανεωθεί", "INFO",
		                                    JOptionPane.INFORMATION_MESSAGE);
								}
					            
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
		
	
			
			}
		});
		btnNewButton5.setBounds(252, 95, 89, 23);
		contentPane.add(btnNewButton5);
		
		JButton btnNewButton6 = new JButton("\u0394\u03B9\u03B1\u03B3\u03C1\u03B1\u03C6\u03AE");
		btnNewButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String connectionURL = "jdbc:mysql://localhost:3306/apothiki";
		            String username = "root";
		            String password = "";
					Connection apothiki = DriverManager.getConnection(connectionURL,username,password);
					Statement stmt = apothiki.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM Apothemata");
					int found = 0 ;
					int id = 0;
					int barcode = Integer.parseInt(txtBarcode1.getText());
					while (rs.next()){
						int barc = rs.getInt("barcode");
						if ( barcode == barc){
							found = 1;
							barcode = rs.getInt("barcode");
						}
						
					}
					if (found == 1){
						String query = "delete from apothemata where barcode = ?";
					      PreparedStatement preparedStmt = apothiki.prepareStatement(query);
					      preparedStmt.setInt(1, barcode);
					      preparedStmt.execute();
						JOptionPane.showMessageDialog(null, "Απόθεμα διαγράφτηκε", "INFO",
                                JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Απόθεμα δεν βρέθηκε", "Warning",
                                JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton6.setBounds(252, 138, 89, 23);
		contentPane.add(btnNewButton6);
		
		JButton btnNewButton3 = new JButton("Reload");
		btnNewButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton)e.getSource();
			    Window window = SwingUtilities.windowForComponent(button);
			    window.setVisible(false);
		    Admin s = new Admin();
	          s.setVisible(true);
			}
		});
		btnNewButton3.setBounds(420, 353, 89, 23);
		contentPane.add(btnNewButton3);
	}

}

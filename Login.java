import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import java.awt.Toolkit;



public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Login() {
		setTitle("USERS");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\Downloads\\images.png.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				 String connectionURL = "jdbc:mysql://localhost:3306/apothiki";
	              String username = "root";
	              String pass = "";

	              Connection conn;
	              int checked = 0;
					int eideerror = 0;
				try {
					conn = DriverManager.getConnection(connectionURL,username,pass);
					Statement stmt = conn.createStatement();
					String a = textField.getText();
					String b = passwordField.getText();

					ResultSet rs = stmt.executeQuery("SELECT * FROM Login");
					
					while(rs.next()){
						String acheck=rs.getString("username");
						String bcheck=rs.getString("password");
						int rolecheck = rs.getInt("role") ;
						if ( a.equals(acheck) ){
							if ( b.equals(bcheck)){
								if (rolecheck == 1){
									JOptionPane.showMessageDialog(null, " Connection Complete", "NOTICE",
		                                    JOptionPane.INFORMATION_MESSAGE);
								 eideerror = 1;	
									checked = 1;
									
								} else if (rolecheck == 2){
									JOptionPane.showMessageDialog(null, " Connection Complete", "NOTICE",
		                                    JOptionPane.INFORMATION_MESSAGE);
								 eideerror = 1;	
									checked = 2;
									
								}else if(rolecheck==0) {
									 JOptionPane.showMessageDialog(null, "Uknown Privileges! Please contact the administrator", "Warning",
			                                    JOptionPane.ERROR_MESSAGE);
									 eideerror = 0;
								}
							}else {
								 JOptionPane.showMessageDialog(null, "Wrong Password! If you can't remember it , please contact the administrator", "Warning",
		                                    JOptionPane.ERROR_MESSAGE);
								 eideerror = 1;
							}
										
							} 
					}
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					if (checked == 1){
// TODO: Local variable could be declared final
						Admin s = new Admin();
						s.setVisible(true);
					} else if (checked == 2){
// TODO: Local variable could be declared final
						Clients s = new Clients();
						s.setVisible(true);
					}
	              
			}
					
		});
		btnNewButton.setBounds(177, 128, 89, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(177, 61, 89, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 92, 89, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(97, 64, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("Password");
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setBounds(97, 95, 69, 14);
		contentPane.add(lblNewLabel1);
	}
}

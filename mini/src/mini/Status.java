package mini;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Status extends JFrame {

	private JPanel contentPane;
	private static JTextField textField;
	public int accNo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,int accNo) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				int bal;
				try {
					Status frame = new Status(accNo);
					frame.setVisible(true);
					try{  
						int balance;
						
						Class.forName("com.mysql.jdbc.Driver");  
						Connection con=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/project","root","root123");  
						Statement stmt=con.createStatement();
						
						ResultSet rs=stmt.executeQuery("select balance from user_fund where ac_no="+accNo);
						while(rs.next()) {
						balance=rs.getInt(1);
//						System.out.println("hello");
//						System.out.println(balance);
						textField.setText(Integer.toString(balance));
						}
					}
					catch(Exception e1) {
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Status(int accNo) {
		this.accNo=accNo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.BLACK);
		contentPane.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setEnabled(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(172, 63, 238, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblRemainingBalance = new JLabel("Remaining Balance");
		lblRemainingBalance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRemainingBalance.setBounds(31, 63, 158, 38);
		contentPane.add(lblRemainingBalance);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setForeground(Color.BLACK);
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Option.main(null, accNo);
				JComponent comp=(JComponent)e.getSource();
				Window win=SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnGoBack.setBounds(10, 210, 130, 29);
		contentPane.add(btnGoBack);
	}
}

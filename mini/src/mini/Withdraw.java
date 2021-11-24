package mini;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Withdraw extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;
	public int accNo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,int accNo) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Withdraw frame = new Withdraw(accNo);
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
	public Withdraw(int accNo) {
		this.accNo=accNo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAmount = new JLabel("amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAmount.setBounds(44, 72, 64, 28);
		contentPane.add(lblAmount);
		
		textField = new JTextField();
		textField.setBounds(118, 80, 191, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPin = new JLabel("pin");
		lblPin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPin.setBounds(44, 154, 49, 28);
		contentPane.add(lblPin);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(118, 162, 191, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amt=Integer.parseInt(textField.getText());
				int pin=Integer.parseInt(textField_1.getText());
				boolean matched=false;
				try{  
					int bal=0;
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/project","root","root123");  
					Statement stmt=con.createStatement();  
//					System.out.println("connected");
//					System.out.println("connected"+accNo);
					ResultSet rs=stmt.executeQuery("select * from details where ac_no="+accNo);
					
					while(rs.next()) {
						if(pin==rs.getInt(5) && accNo==rs.getInt(7)) {
//							System.out.println("hello");
							matched=true;
//							System.out.println("pin Match");
							ResultSet rs1=stmt.executeQuery("select * from user_fund where ac_no="+accNo);
			
							while(rs1.next()) {
							bal=rs1.getInt(2);
							if(bal>=amt) {
							bal=bal-amt;
//							System.out.println(rs1.getInt(1)+" "+rs1.getInt(2));
							
							PreparedStatement p=null;
							p=con.prepareStatement("Update user_fund set balance=? where ac_no=?");
							p.setInt(1, bal);
							p.setInt(2, accNo);
							p.executeUpdate();
							JOptionPane.showMessageDialog(null,"collected","Withdraw",JOptionPane.PLAIN_MESSAGE);
							textField_1.setText(null);
							textField.setText(null);
							con.close();
							}
							}
							System.out.println("Insufficient balance");
						}
						else
							System.out.println("wrong pin");
					}
					
				}
				catch(Exception e1) {
					
				}
				Option.main(null, accNo);
				JComponent comp=(JComponent)e.getSource();
				Window win=SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSubmit.setBounds(81, 220, 122, 33);
		contentPane.add(btnSubmit);
		
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Option.main(null, accNo);
				JComponent comp=(JComponent)e.getSource();
				Window win=SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		btnNewButton.setBounds(315, 220, 85, 30);
		contentPane.add(btnNewButton);
	}
}

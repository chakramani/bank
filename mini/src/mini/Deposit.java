package mini;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Deposit extends JFrame {

	private JPanel contentPane;
	private JTextField txtDeposit;
	static my user;
	public int accNo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,int accNo) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deposit frame = new Deposit(accNo);
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
	public Deposit(int accNo) {
		this.accNo=accNo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterTheAmount = new JLabel("enter the amount");
		lblEnterTheAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTheAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterTheAmount.setBounds(108, 36, 166, 22);
		contentPane.add(lblEnterTheAmount);
		
		txtDeposit = new JTextField();
		txtDeposit.setBounds(139, 79, 135, 19);
		contentPane.add(txtDeposit);
		txtDeposit.setColumns(10);
		
		JButton btnDeposit = new JButton("Deposit");		
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dep=Integer.parseInt(txtDeposit.getText());
						try{  
							int bal;
//							System.out.println("Inside deposit");
//							System.out.println("Account No="+accNo);
							
							Class.forName("com.mysql.jdbc.Driver");  
							Connection con=DriverManager.getConnection(  
							"jdbc:mysql://localhost:3306/project","root","root123");  
							Statement stmt=con.createStatement(); 
							
							
							ResultSet rs=stmt.executeQuery("select * from user_fund where ac_no="+accNo);
							rs.next();
							bal=rs.getInt(2);
							bal+=dep;
//							System.out.println(rs.getInt(1)+" "+rs.getInt(2));
//							while(rs.next()) {
//								System.out.println(rs.getInt(1)+" "+rs.getInt(2));
//								
//							}
							PreparedStatement p=null;
							p=con.prepareStatement("Update user_fund set balance=? where ac_no=?");
							p.setInt(1, bal);
							p.setInt(2, accNo);
							p.executeUpdate();
//							label.setText("deposited");
							System.out.println("deposited");
							JOptionPane.showMessageDialog(null,"deposited","deposit",JOptionPane.PLAIN_MESSAGE);
							txtDeposit.setText(null);
//							t2.setText(null);
							con.close();
					}catch(Exception e1) {
						
					}
						
						Option.main(null, accNo);
						JComponent comp=(JComponent)e.getSource();
						Window win=SwingUtilities.getWindowAncestor(comp);
						win.dispose();
			}
		});
		btnDeposit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeposit.setBounds(139, 147, 109, 27);
		contentPane.add(btnDeposit);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Option.main(null, accNo);
				JComponent comp=(JComponent)e.getSource();
				Window win=SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(306, 198, 88, 26);
		contentPane.add(btnExit);
	}
}

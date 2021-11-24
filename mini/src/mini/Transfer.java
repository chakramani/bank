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

public class Transfer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	int total;
	public int accNo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,int accNo) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transfer frame = new Transfer(accNo);
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
	public Transfer(int accNo) {
		this.accNo=accNo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAcNo = new JLabel("A/C no");
		lblAcNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAcNo.setBounds(65, 75, 49, 27);
		contentPane.add(lblAcNo);
		
		textField = new JTextField();
		textField.setBounds(163, 81, 179, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBalance.setBounds(65, 136, 59, 27);
		contentPane.add(lblBalance);
		
		textField_1 = new JTextField();
		textField_1.setBounds(163, 142, 179, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSubmit = new JButton("submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int acc=Integer.parseInt(textField.getText());
				int bal=Integer.parseInt(textField_1.getText());
				int curBal;
				boolean greatBal=false;
				try{  
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/project","root","root123");  
					Statement stmt=con.createStatement(); 
					
					ResultSet rs=stmt.executeQuery("select balance from user_fund where ac_no="+accNo);
					while(rs.next()) {
					if(rs.getInt(1)>=bal) {
						curBal=rs.getInt(1);
//						System.out.println("Current balance ="+curBal);
						PreparedStatement p1=null;
						p1=con.prepareStatement("Update user_fund set balance=? where ac_no=?");
						p1.setInt(1, (curBal-bal));
//						System.out.println("Current balance ="+(curBal-bal));
						p1.setInt(2, accNo);
						p1.executeUpdate();
						greatBal=true;
					}else
						System.out.println("insufficient amount");
					}
				
					if(greatBal==true) {
						ResultSet rs1=stmt.executeQuery("select * from user_fund where ac_no="+acc);
						
					while(rs1.next()) {
						 
						if(acc==rs1.getInt(1)) {
							total=rs1.getInt(2);
							total+=bal;
							PreparedStatement p=null;
							p=con.prepareStatement("Update user_fund set balance=? where ac_no=?");
							p.setInt(1, total);
							p.setInt(2, acc);
							
							p.executeUpdate();
							JOptionPane.showMessageDialog(null,"transfered","Transfer Balance",JOptionPane.PLAIN_MESSAGE);
							textField_1.setText(null);
							textField.setText(null);
							
							
							
							}
					}
					}

					con.close();
					
					
				
					}
				catch(Exception e1){ 
					System.out.println(e1);
					}
				Option.main(null, accNo);
				JComponent comp=(JComponent)e.getSource();
				Window win=SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}

			private int getInt(int i) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSubmit.setBounds(163, 196, 85, 21);
		contentPane.add(btnSubmit);
		
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
		btnExit.setBounds(321, 198, 85, 21);
		contentPane.add(btnExit);
	}
}

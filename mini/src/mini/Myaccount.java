package mini;
import java.awt.*;
import java.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.ActionListener;

import javax.swing.*;

class my implements ActionListener{
	JTextField t1,t3;
	JPasswordField t2;
	JLabel l3;
	JFrame jf;
	public int accountNo;
	my(){
		jf=new JFrame("Login");
		jf.setVisible(true);
		jf.setLayout(null);
		jf.setSize(400,400);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel l1=new JLabel("Username");
		l1.setBounds(50,100,200,20);
		jf.add(l1);
		
		t1=new JTextField();
		jf.add(t1);
		t1.setBounds(110,100,200,20);
		
		JLabel l2=new JLabel("pin");
		jf.add(l2);
		l2.setBounds(50,150,200,20);
		
		
		t2=new JPasswordField();
		jf.add(t2);
		t2.setBounds(110,150,200,20);
		
		JButton b1=new JButton("Login");
		jf.add(b1);
		b1.setBounds(150,200,80,20);
		b1.addActionListener(this);
		
		l3=new JLabel();
		jf.add(l3);
		l3.setBounds(150,210,200,200);
		
	}
	public int getAccountNo() {
		return accountNo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s1=t1.getText();
		@SuppressWarnings("deprecation")
		String s2=t2.getText();
		boolean matched=false;
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/project","root","root123");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from login");  
			while(rs.next()) {
				if(s1.equals(rs.getString(1))) {
					if(s2.equals(rs.getString(2))) {
						matched=true;
						
						JOptionPane.showMessageDialog(null,"SUCCESSFULY MATCHED!!!","LOGIN",JOptionPane.PLAIN_MESSAGE);
						t1.setText(null);
						t2.setText(null);
						
						accountNo=Integer.parseInt(rs.getString(3));
//						System.out.println("account no is==>>"+accountNo);
						Option.main(null,accountNo);
						jf.dispose();
					}
				}
			}
			if(matched!=true) {
				l3.setText("not matched");
			} 
			}
		catch(Exception e1){ 
			System.out.println(e1);
			}  	
	}
}

public class Myaccount {

	public static void main(String[] args) {
		new  my();
	}
}

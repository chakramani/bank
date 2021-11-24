package mini;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

class check implements ActionListener{

		JLabel l1,l2,l3;
		JButton b1,b2,b3;
		JFrame jf;
		check(){
			jf=new JFrame ("ACCOUNT");
			
			jf.setLayout(null);

			b1=new JButton ("HAVE ACCOUNT");
			jf.add(b1);
			b1.setBounds(150,100,200,30);
			b1.setFont(new Font("Arial", Font.PLAIN, 17));
			b1.addActionListener(this);
			
			l1=new JLabel();
			jf.add(l1);
			l1.setBounds(150,200,200,20);
			
			b2=new JButton ("CREATE ACCOUNT");
			jf.add(b2);
			b2.setBounds(150,150,200,30);
			b2.setFont(new Font("Arial", Font.PLAIN, 17));
			b2.addActionListener(this);
			
//			b3=new JButton ("DELETE");
//			jf.add(b3);
//			b3.setBounds(150,200,200,30);
//			b3.setFont(new Font("Arial", Font.PLAIN, 17));
//			b3.addActionListener(this);
			
			l2=new JLabel();
			jf.add(l2);
			l2.setBounds(150,250,100,20);
			
			l3=new JLabel();
			jf.add(l3);
			l3.setBounds(100,300,300,30);
			l3.setFont(new Font("Arial", Font.PLAIN, 17));
			
			jf.setVisible(true);
			jf.setSize(500,400);
			jf.setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==b1) {
			Myaccount.main(null);
			jf.dispose();
			}
			else if(e.getSource()==b2) {
				Create.main(null);
				jf.dispose();
			}	
//				else if(e.getSource()==b3) {
//					try {
//						Class.forName("com.mysql.jdbc.Driver");  
//						Connection con=DriverManager.getConnection(  
//						"jdbc:mysql://localhost:3306/project","root","root123");  
//						Statement stmt=con.createStatement();
//						//ResultSet rs=stmt.executeQuery("delete");
//						l3.setText("Account deleted");
//					}catch(Exception e1){ 
//						System.out.println(e1);
//					} 
//				}
			}
		}
public class Account {
	
	public static void main(String[] args) {
	new check();

	}

}

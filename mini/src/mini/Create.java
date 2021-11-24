package mini;

import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

class CreateAccount implements ActionListener{
	JTextField fname,lname,address,dob,pin,deposit,username,ano;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JButton b1;
	JFrame jf;
	CreateAccount(){
		jf=new JFrame ("CREATING ACCOUNT");
		jf.setVisible(true);
		jf.setSize(500,650);
		jf.setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);
		jf.setLayout(null);
		
		jf.setLayout(null);
		l1=new JLabel ("FILL THE DETAILS");
		jf.add(l1);
		l1.setBounds(130,50,200,20);
		
		l1=new JLabel ("First/Middle name");
		jf.add(l1);
		l1.setBounds(50,100,200,20);
		
		fname=new JTextField();
		jf.add(fname);
		fname.setBounds(50,120,200,20);
		
		l2=new JLabel ("Last name");
		jf.add(l2);
		l2.setBounds(50,160,200,20);
		
		lname=new JTextField();
		jf.add(lname);
		lname.setBounds(50,180,200,20);
		
		l3=new JLabel ("Address");
		jf.add(l3);
		l3.setBounds(50,210,200,20);
		
		address=new JTextField();
		jf.add(address);
		address.setBounds(50,230,200,20);
		
		l4=new JLabel ("DOB");
		jf.add(l4);
		l4.setBounds(50,260,200,20);
		
		dob=new JTextField();
		jf.add(dob);
		dob.setBounds(50,280,200,20);
		
		
		l5=new JLabel ("pin");
		jf.add(l5);
		l5.setBounds(50,310,200,20);
		
		pin=new JTextField();
		jf.add(pin);
		pin.setBounds(50,330,200,20);
		
		l7=new JLabel ("Username");
		jf.add(l7);
		l7.setBounds(50,360,200,20);
		
		username=new JTextField();
		jf.add(username);
		username.setBounds(50,380,200,20);

		l8=new JLabel ("Ac no");
		jf.add(l8);
		l8.setBounds(50,460,200,20);
		
		ano=new JTextField();
		jf.add(ano);
		ano.setBounds(50,480,200,20);
		
		
		b1=new JButton("create");
		jf.add(b1);
		b1.setBounds(150,510,80,30);
		b1.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String fName=fname.getText();//
		String lName=lname.getText();
		String addr=address.getText();
		String birth=dob.getText();  
		int pass=Integer.parseInt(pin.getText());
		String uName=username.getText();
		int acc=Integer.parseInt(ano.getText());
        
        try{  
        	Date date=new SimpleDateFormat("dd/MM/yyyy").parse(birth);
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/project","root","root123");  
			Statement stmt=con.createStatement();   
			PreparedStatement p=null;
			p=con.prepareStatement("Insert into details(fname,lname,address,DOB,pin,username,ac_no) values (?,?,?,?,?,?,?)");
			 p.setString(1, fName);
			 p.setString(2, lName);
			 p.setString(3, addr);
			 p.setString(4, birth);
			 p.setInt(5,pass);
			 p.setString(6, uName);
			 p.setInt(7, acc);
			 System.out.println("account is created!!!");
			 p.executeUpdate();
			con.close();  
			}
		catch(Exception e1){ 
			System.out.println(e1);
			}  
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/project","root","root123");  
			Statement stmt=con.createStatement();   
			PreparedStatement p2=null;
			PreparedStatement p1=null;
			p2=con.prepareStatement("Insert into login(username,password,ac_no) values (?,?,?)");
			p2.setString(1, uName);
			p2.setInt(2, pass);
			p2.setInt(3, acc);
			p2.executeUpdate();
			p1=con.prepareStatement("Insert into user_fund(ac_no,balance) values(?,?)");
			p1.setInt(1, acc);
			p1.setInt(2, 0);
			p1.executeUpdate();
			con.close();
			JOptionPane.showMessageDialog(null,"SUCCESSFUL!!!","CREATE",JOptionPane.PLAIN_MESSAGE);
			username.setText(null);
			pin.setText(null);
			Myaccount.main(null);
			jf.dispose();
        }catch(Exception e2) {
        	
        }
	}
}


public class Create {

	public static void main(String[] args) {
		new CreateAccount();
	}
}

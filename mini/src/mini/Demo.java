package mini;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Demo {
	public static void main(String[] args) {
		new login();

	}

}

class login implements ActionListener{
	JTextField t1,t2;
	JLabel l1,l2,l3,l4;
	JPasswordField p1;
	JFrame jf;
	login(){
		jf=new JFrame ("START");
		jf.setVisible(true);
		jf.setSize(350,350);
		//jf.setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);
		jf.setLayout(null);
		
		l1=new JLabel ("BANKING SIMULATION");
		jf.add(l1);
		l1.setBounds(130,50,200,20);
		
		l2=new JLabel ("USERNAME");
		jf.add(l2);
		l2.setBounds(50,100,200,20);
		
		t1=new JTextField();
		jf.add(t1);
		t1.setBounds(50,120,200,20);
		
		l3=new JLabel ("PIN");
		jf.add(l3);
		l3.setBounds(50,150,200,20);
		
		t2=new JPasswordField();
		jf.add(t2);
		t2.setBounds(50,180,100,20);
		
		JButton b1=new JButton ("OK");
		jf.add(b1);
		b1.setBounds(150,210,80,20);
		jf.setForeground(Color.BLACK);
		//b1.setBackground(new Color(200,13,10));
		b1.addActionListener(this);
		
//		JButton b1=new JButton ("go back");
//		jf.add(b1);
//		b1.setBounds(150,210,80,20);
//		jf.setForeground(Color.BLACK);
//		//b1.setBackground(new Color(200,13,10));
//		b1.addActionListener(this);
		
		l4=new JLabel();
		jf.add(l4);
		l4.setBounds(150,250,100,20);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String s1=t1.getText();
		String s2=t2.getText();
		 int pin=Integer.parseInt(s2);
		 if((pin==242572) && (s1.equals("chakra"))) {
				
				JOptionPane.showMessageDialog(null,"SUCCESSFUL!!!","LOGIN",JOptionPane.PLAIN_MESSAGE);
				t1.setText(null);
				t2.setText(null);
				Account acc = new Account();
				acc.main(null);
				jf.dispose();
				
//				Account.main(null);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"invalid","login error",JOptionPane.ERROR_MESSAGE);
				t1.setText(null);
				t2.setText(null);
				
			}
				
		 
			 
	}
}	
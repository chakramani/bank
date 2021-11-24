package mini;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
class option1 implements ActionListener{
	JButton b1,b2,b3,b4,b5;
	JFrame jf;
	public int accNo;
	option1(int accNo){
		this.accNo=accNo;
		jf=new JFrame ("OPTION");
		jf.setVisible(true);
		jf.setSize(400,380);
		jf.setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);
		jf.setLayout(null);
	
	b1=new JButton ("DEPOSIT");
	jf.add(b1);
	b1.setBounds(130,80,160,30);
	jf.setForeground(Color.BLACK);
	b1.addActionListener(this);

	b2=new JButton ("TRANSFER");
	jf.add(b2);
	b2.setBounds(130,130,160,30);
	jf.setForeground(Color.BLACK);
	b2.addActionListener(this);	
	
	b3=new JButton ("BALANCE ENQUERY");
	jf.add(b3);
	b3.setBounds(130,180,160,30);
	jf.setForeground(Color.BLACK);
	b3.addActionListener(this);
	
	b4=new JButton ("WITHDRAW");
	jf.add(b4);
	b4.setBounds(130,230,160,30);
	jf.setForeground(Color.BLACK);
	b4.addActionListener(this);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			Deposit.main(null,accNo);
			jf.dispose();
			}
			else if(e.getSource()==b2) {
			Transfer.main(null,accNo);
			jf.dispose();
			}
			else if(e.getSource()==b3) {
				Status.main(null,accNo);
				jf.dispose();
				}
			else if(e.getSource()==b5) {
				Loan.main(null);
				//jf.dispose();
			}
			else if(e.getSource()==b4) {
				Withdraw.main(null, accNo);
				jf.dispose();
			}
	}
}
public class Option {

	public static void main(String[] args,int acc) {
		new option1(acc);

	}
}

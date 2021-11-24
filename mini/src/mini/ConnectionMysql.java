package mini;

//import java.sql.*;
//
//public class ConnectionMysql {
//
//	public static void main(String[] args) {
//		
//	
//	try{  
//		Class.forName("com.mysql.jdbc.Driver");  
//		Connection con=DriverManager.getConnection(  
//		"jdbc:mysql://localhost:3306/project","root","root123");  
//		//here sonoo is database name, root is username and password  
//		Statement stmt=con.createStatement();  
////		ResultSet rs=stmt.executeQuery("select * from login");  
////		while(rs.next())  
////		System.out.println(rs.getString(1)+"  "+rs.getString(2));  
//		System.out.println("Connected");
////		con.close();  
//		}
//	catch(Exception e){ 
//		System.out.println(e);
//		}  
//	}	  
//
//}
class P{
int x;
P(int i){
// x=i;
	i=x;
}
void show(){
System.out.println("x= "+x);
}
}
class Q extends P{
int y;
Q(int i,int j){
 super(i);
 y=j;
}
void show(){
System.out.println("y= "+y);
}
}
public class ConnectionMysql {
public static void main(String[] args) {
Q q=new Q(10,20);
q.show();
}
}

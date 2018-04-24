package translator;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.beans.*;
public class text {
	private Connection con;
	private java.sql.Statement st;
	private ResultSet rs;
	
	public text() {
		try {
			Class.forName("com.mysql.jdbc.Driver");	
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop1","root","123456");
			st = con.createStatement();
		}catch(Exception ex) {
			System.out.println("Error:"+ex);
		}
	}
	public void getData() {
		try {
			String query  = "select * from product";
			rs = st.executeQuery(query);
			System.out.println("Records from Database");
			while(rs.next()) {
				String name =rs.getString("name");
				String price = rs.getString("price");
				System.out.println("Name: "+name+"   "+"price: "+price);
				
			}
		}catch(Exception ex) {
			System.out.println(ex);	
			}
	}
}

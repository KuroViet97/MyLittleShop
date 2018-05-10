package com.product.Service;
import java.beans.Statement;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.beans.*;
public class getDataFromDB {
	//get product name
	public static String getData(String barcode) {
		String productName = null;
		try {
			Connection con;
			java.sql.Statement st;
			ResultSet rs;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "SELECT productname FROM product WHERE barcode ='"+barcode+"'";
			rs = st.executeQuery(query);		
			while(rs.next()) {
					productName=rs.getString("productname");
				}
			//Close connections
			 try { if (rs != null) rs.close(); } catch (Exception e) {};
			 try { if (st != null) st.close(); } catch (Exception e) {};
			try { if (con != null) con.close(); } catch (Exception e) {};
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return productName;
	}
	//get product price
	public static String getPrice(String barcode) {
		String productName = null;
		try {
			Connection con;
			java.sql.Statement st;
			ResultSet rs;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "SELECT price FROM product WHERE barcode ='"+barcode+"'";
			rs = st.executeQuery(query);		
			while(rs.next()) {
					productName=rs.getString("price");
				}
			 try { if (rs != null) rs.close(); } catch (Exception e) {};
			 try { if (st != null) st.close(); } catch (Exception e) {};
			try { if (con != null) con.close(); } catch (Exception e) {};
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return productName;
	}
	
	//create new OUT transaction
	public static void createnewtransaction(int ShopID,String barcode,int quantity) {
		try {
			Connection con;
			java.sql.Statement st;
			int rs ;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			
			long time = System.currentTimeMillis();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
			String query  = "insert into transaction(ShopID,Barcode,Date,Status,Quantity)"+"Values("+ShopID+",'"+barcode+"','"+timestamp+"','OUT',"+quantity+")";
			rs = st.executeUpdate(query);
			 try { if (st != null) st.close(); } catch (Exception e) {};
			try { if (con != null) con.close(); } catch (Exception e) {};
			}catch(Exception ex) {
					ex.printStackTrace();
			}
	}
	public static boolean compareTransaction(int shopID, int qty, String barcode) {
		int totalIN=0, totalOUT=0;
		try {
			Connection con;
			ResultSet rs;
			java.sql.Statement st;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "SELECT SUM(quantity) FROM transaction WHERE shopid="+shopID+" AND barcode='"+barcode+"' AND Status='IN'";
			rs = st.executeQuery(query);
			rs.next();
			totalIN = rs.getInt("SUM(quantity)");
			query  = "SELECT SUM(quantity) FROM transaction WHERE shopid="+shopID+" AND barcode='"+barcode+"' AND Status='OUT'";
			rs = st.executeQuery(query);	
			rs.next();
			totalOUT = rs.getInt("SUM(quantity)");
			 try { if (rs != null) rs.close(); } catch (Exception e) {};
			 try { if (st != null) st.close(); } catch (Exception e) {};
			try { if (con != null) con.close(); } catch (Exception e) {};
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
		totalOUT = totalOUT + qty;
		if(totalIN-totalOUT<0) {
			return false;
		}else return true;
	}
	
	public static String getAllShopNames() {
		String productName = null;
		try {
			Connection con;
			java.sql.Statement st;
			ResultSet rs;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "SELECT * FROM shop";
			rs = st.executeQuery(query);		
			while(rs.next()) {
				if(productName == null) {
					productName=rs.getString("ShopName");
				}
				else {
					productName=productName+"---"+rs.getString("ShopName");
				}
				}

			 try { if (rs != null) rs.close(); } catch (Exception e) {};
			 try { if (st != null) st.close(); } catch (Exception e) {};
			try { if (con != null) con.close(); } catch (Exception e) {};
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return productName;
	}
	//Get quantity of shop Item
	public static String getShopItemQuantity(int ShopID,String barcode) {
		int totalIN=0;
		int totalOUT=0;
		String name=null;
		String productName=null;
		String price=null;
		try {
			Connection con;
			java.sql.Statement st;
			ResultSet rs ;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "SELECT SUM(Quantity),product.Productname,product.price FROM shop JOIN transaction ON shop.ShopID=transaction.ShopID JOIN product ON transaction.Barcode=product.Barcode WHERE shop.ShopID="+ShopID+" AND transaction.Barcode='"+barcode+"' AND transaction.Status='OUT'";
			rs=st.executeQuery(query);
			rs.next();
			name=rs.getString("Productname");
			price=rs.getString("price");
			totalOUT=rs.getInt("SUM(Quantity)");
			query  = "SELECT SUM(Quantity),product.Productname FROM shop JOIN transaction ON shop.ShopID=transaction.ShopID JOIN product ON transaction.Barcode=product.Barcode WHERE shop.ShopID="+ShopID+" AND transaction.Barcode='"+barcode+"' AND transaction.Status='IN'";
			rs=st.executeQuery(query);
			rs.next();
			totalIN=rs.getInt("SUM(Quantity)");
			productName="Name: "+name+" Price: "+price+" Quantity: "+(totalIN-totalOUT);
			 try { if (rs != null) rs.close(); } catch (Exception e) {};
			 try { if (st != null) st.close(); } catch (Exception e) {};
			try { if (con != null) con.close(); } catch (Exception e) {};
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return productName;
	}
	
	public static String getAllShopIDs() {
		String productName = null;
		try {
			Connection con;
			java.sql.Statement st;
			ResultSet rs;
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "SELECT * FROM shop";
			rs = st.executeQuery(query);		
			while(rs.next()) {
				if(productName == null) {
					productName=rs.getString("ShopID");
				}
				else {
					productName=productName+"---"+rs.getString("ShopID");
				}
				}
			 try { if (rs != null) rs.close(); } catch (Exception e) {};
			 try { if (st != null) st.close(); } catch (Exception e) {};
			try { if (con != null) con.close(); } catch (Exception e) {};
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return productName;
	}
	public static void AddShop(String shopName,String employeeName,String passWord) {
		try {
			Connection con;
			java.sql.Statement st;
			int rs ;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String shaPass=null;
			Sha256 a =new Sha256();
			shaPass=a.hash(passWord);
			String query  = "insert into shop(ShopName,employee,pass) values('"+shopName+"','"+employeeName+"','"+shaPass+"')";
			rs = st.executeUpdate(query);
			 try { if (st != null) st.close(); } catch (Exception e) {};
			try { if (con != null) con.close(); } catch (Exception e) {};
			}catch(Exception ex) {
					ex.printStackTrace();
			}
	}
	//New in transaction(w/Barcode known)
	public static void createNewINTransaction(int ShopID,String barcode,int quantity) {
		try {
			Connection con;
			java.sql.Statement st;
			int rs ;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			
			long time = System.currentTimeMillis();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
			String query  = "insert into transaction(ShopID,Barcode,Date,Status,Quantity)"+"Values("+ShopID+",'"+barcode+"','"+timestamp+"','IN',"+quantity+")";
			rs = st.executeUpdate(query);
			 try { if (st != null) st.close(); } catch (Exception e) {};
			try { if (con != null) con.close(); } catch (Exception e) {};
			}catch(Exception ex) {
					ex.printStackTrace();
			}
	}
	//Create new IN transaction 2
	public static void insertTransactionIn(int shopID, int quantity, String productname) {
		try {
			//establish connection
			Connection con;
			//create java sql
			java.sql.Statement st;
			//call driver, initialize information
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			//get the connection to database
			con = DriverManager.getConnection(jdbcURL,username,password);
			//assign statement
			st = con.createStatement();
			//create a result set
			ResultSet rs;
			String query = "SELECT * FROM product WHERE productname='"+productname+"'";
			rs=st.executeQuery(query);
			rs.next();
			String barcode = rs.getString("barcode");
			long time = System.currentTimeMillis();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
			query  = "INSERT INTO transaction (shopID,quantity,barcode,date,Status) VALUES"+"("+shopID+","+quantity+",'"+barcode+"','"+timestamp+"','IN')";
			st.execute(query);
			 try { if (rs != null) rs.close(); } catch (Exception e) {};
			 try { if (st != null) st.close(); } catch (Exception e) {};
			try { if (con != null) con.close(); } catch (Exception e) {};
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//View all transactions
	public static ArrayList<String> viewTransaction(String start, String end) {
		ArrayList<String> dataset = new ArrayList <String>();
		try {
			Connection con;
			java.sql.Statement st;
			ResultSet rs;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			//String query = "SELECT * FROM transactions";
			String query  = "SELECT t.*, p.productname, p.price FROM transaction AS t INNER JOIN product AS p ON t.barcode = p.barcode WHERE t.date BETWEEN '"+start+" 00:00:00'"+"AND '"+end+" 23:59:59'";
			rs = st.executeQuery(query);
			while(rs.next()) {
					dataset.add(rs.getString("ShopID")+"---"+rs.getString("barcode")+"---"+rs.getString("productname")+"---"+rs.getString("price")+"---"+rs.getString("date")+"---*"+rs.getString("status")+"*---"+rs.getString("quantity")+"---"+rs.getString("shopid"));
				}

			 try { if (rs != null) rs.close(); } catch (Exception e) {};
			 try { if (st != null) st.close(); } catch (Exception e) {};
			try { if (con != null) con.close(); } catch (Exception e) {};
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			return dataset;
	}
	//get all product names
	public static String getAllProductNames() {
		String productName = null;
		try {
			Connection con;
			java.sql.Statement st;
			ResultSet rs;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "SELECT * FROM product";
			rs = st.executeQuery(query);		
			while(rs.next()) {
				if(productName == null) {
					productName=rs.getString("ProductName");
				}
				else {
					productName=productName+"---"+rs.getString("ProductName");
				}
				}
			 try { if (rs != null) rs.close(); } catch (Exception e) {};
			 try { if (st != null) st.close(); } catch (Exception e) {};
			try { if (con != null) con.close(); } catch (Exception e) {};
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return productName;
	}
	//add a new product to product
	public static void AddProduct(String barcode,String productname,int price) {
		try {
			Connection con;
			java.sql.Statement st;
			int rs ;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "insert into product(Barcode,Productname,Price) values('"+barcode+"','"+productname+"',"+price+")";
			rs = st.executeUpdate(query);
			 try { if (st != null) st.close(); } catch (Exception e) {};
			 try { if (con != null) con.close(); } catch (Exception e) {};
			}catch(Exception ex) {
					ex.printStackTrace();
			}finally {
			   
			}
	}
	//check if product exists, return false if product already exist
	public static boolean checkProduct(String barcode,String productName) {
		String check=null;
		try {
			Connection con;
			java.sql.Statement st;
			ResultSet rs ;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "SELECT * FROM product WHERE Barcode='"+barcode+"' OR ProductName='"+productName+"'";
			rs = st.executeQuery(query);
			while(rs.next()) {
				check=rs.getString("ProductName");
				if(check!=null) {
					return false;
				}
			}
			st.close();
			rs.close();
			}catch(Exception ex) {
					ex.printStackTrace();
			}
		return true;
	}

	public static String getAllProductBarcodes() {
		String productName = null;
		try {
			Connection con;
			java.sql.Statement st;
			ResultSet rs;
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "SELECT * FROM product";
			rs = st.executeQuery(query);		
			while(rs.next()) {
				if(productName == null) {
					productName=rs.getString("Barcode");
				}
				else {
					productName=productName+"---"+rs.getString("Barcode");
				}
				}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return productName;
	}
	
	
	
	
	
	
	
	
	
	
	//old stuff
	//check in for manager
	public  boolean checkLogin(String usernam,String passwor) throws ClassNotFoundException, SQLException {
			Connection con;
			java.sql.Statement st;
			ResultSet rs ;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/shop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query = "select * from login";
			rs = st.executeQuery(query);
			String check = "";
			while(rs.next()) {
				String a = rs.getString("username");
				String b = rs.getString("pass");
				if(usernam.equals(a)  && passwor.equals(b)) {
					check= "true";
					return true;
					}
				}
			return false;
	}
	//check login for employee
	public  boolean checkLogin2(int shopID,String usernam,String passwor) throws ClassNotFoundException, SQLException {
		Connection con;
		java.sql.Statement st;
		ResultSet rs ;
		Class.forName("com.mysql.jdbc.Driver");	
		String jdbcURL = "jdbc:mysql://localhost:3306/shop";
		String username = "root";
		String password = "";
		con = DriverManager.getConnection(jdbcURL,username,password);
		st = con.createStatement();
		String query = "select * from shop";
		rs = st.executeQuery(query);
		String check = "";
		while(rs.next()) {
			int shop = rs.getInt("ShopID");
			String a = rs.getString("employee");
			String b = rs.getString("pass");
			if(shop==shopID && usernam.equals(a)  && passwor.equals(b)) {
				check= "true";
				return true;
				}
			}
		return false;
}
	
	public static void main(String[] args) {
		
	}
}

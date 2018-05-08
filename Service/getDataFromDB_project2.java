package com.product.Service;
import java.beans.Statement;
import java.sql.*;
import java.beans.*;
public class getDataFromDB {
	
	//get product name based on the barcode scanned in the server side
	public static String getBarcodeProduct(int barcode) {
		String productName = null;
		try {
			Connection con;
			java.sql.Statement st;
			ResultSet rs;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/mylittleshop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "SELECT productname FROM product WHERE barcode ="+barcode;
			rs = st.executeQuery(query);		
			while(rs.next()) {
					productName=rs.getString("productname");
				}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return productName;
	}
	//insert transaction
	public static String insertTransaction(int shopID, int qty, int barcode) {
		try {
			Connection con;
			java.sql.Statement st;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/mylittleshop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "INSERT INTO transactions (shopID,qty,barcode,date,stat) VALUES"+"("+shopID+","+qty+","+barcode+",now(),'OUT')";
			st.execute(query);		
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
		return "";
	}
	public static boolean compareTransaction(int shopID, int qty, int barcode) {
		int totalIN=0, totalOUT=0;
		try {
			Connection con;
			ResultSet rs;
			java.sql.Statement st;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/mylittleshop";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "SELECT SUM(qty) FROM transactions WHERE shopid="+shopID+" AND barcode="+barcode+" AND stat='IN'";
			rs = st.executeQuery(query);		
			rs.next();
			totalIN = rs.getInt("SUM(qty)");
			query  = "SELECT SUM(qty) FROM transactions WHERE shopid="+shopID+" AND barcode="+barcode+" AND stat='OUT'";
			rs = st.executeQuery(query);	
			rs.next();
			totalOUT = rs.getInt("SUM(qty)");
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
		totalOUT = totalOUT + qty;
		if(totalIN-totalOUT<0) {
			return false;
		}else return true;
	}
	public String viewDatabase(String shop) { 
		String getString = "" ;
		try {
			Connection con;
			java.sql.Statement st;
			ResultSet rs;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/"+shop;
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(jdbcURL,username,password);
			st = con.createStatement();
			String query  = "select * from product";
			rs = st.executeQuery(query);
			while(rs.next()) {
				String name =rs.getString("name");
				String price = rs.getString("price");
				String amount = rs.getString("quantity");
				getString += "Name:"+name+","+"Price:"+price+","+"Amount:"+amount +"\n";
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		return getString;
		}
	public  String getData(String shop,int code) {
		String getString = "";
		try {
		Connection con;
		java.sql.Statement st;
		ResultSet rs;
		Class.forName("com.mysql.jdbc.Driver");	
		String jdbcURL = "jdbc:mysql://localhost:3306/"+shop;
		String username = "root";
		String password = "";
		con = DriverManager.getConnection(jdbcURL,username,password);
		st = con.createStatement();
		String query ="select* from product where id="+code;
		rs = st.executeQuery(query);
		while(rs.next()) {
			String name = rs.getString("name");
			String price = rs.getString("price");
			getString = "Name:"+name+" "+"Price:"+price;	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return getString;
	}
	public void update(String shop,int code, int quantity) {
		try {
		Connection con;
		java.sql.Statement st;
		ResultSet rs ;
		Class.forName("com.mysql.jdbc.Driver");	
		String jdbcURL = "jdbc:mysql://localhost:3306/"+shop;
		String username = "root";
		String password = "";
		con = DriverManager.getConnection(jdbcURL,username,password);
		st = con.createStatement();
		String query  = "select quantity from product where id ="+code;
		rs = st.executeQuery(query);
		int change = 0;
		while(rs.next()) {
			String amount = rs.getString("quantity");
			change = Integer.parseInt(amount)-quantity;
			}	
		rs.close();
		String update = "update product set quantity="+change+" where id ="+code;
			st.executeUpdate(update);
		}catch(Exception ex) {
				ex.printStackTrace();
		}
	}
	public  boolean checkLogin(String usernam,String passwor) throws ClassNotFoundException, SQLException {
			Connection con;
			java.sql.Statement st;
			ResultSet rs ;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/loginkey";
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
	public static void main(String args[]) {
		//System.out.println(getBarcodeProduct(1));
		System.out.println(compareTransaction(1, 40, 1));
	}
}

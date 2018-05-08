package com.product.Service;
import java.beans.Statement;
import java.sql.*;
import java.beans.*;
public class getDataFromDB {
	public String viewDatabase(String shop) { 
		String getString = "" ;
		try {
			Connection con;
			java.sql.Statement st;
			ResultSet rs;
			Class.forName("com.mysql.jdbc.Driver");	
			String jdbcURL = "jdbc:mysql://localhost:3306/"+shop;
			String username = "root";
			String password = "123456";
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
		String password = "123456";
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
		String password = "123456";
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
			String password = "123456";
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
}

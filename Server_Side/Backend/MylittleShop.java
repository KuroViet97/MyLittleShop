package com.product.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class MylittleShop {
	//database
	static File history = new File("history.xml");
	//Authentication
	public static boolean ValidateInfo(String username, String password) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException{
		getDataFromDB get = new getDataFromDB();
		Sha256 hash = new Sha256();
		String hashValueDB = new String();
		String hashValue = hash.hash(password);
		return get.checkLogin(username, hashValue);
	}
	//Employee authentication
	public static boolean ValidateInfoEmployee(int ShopID,String username, String password) throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException, SQLException{
		getDataFromDB get = new getDataFromDB();
		Sha256 hash = new Sha256();
		String hashValueDB = new String();
		String hashValue = hash.hash(password);
		return get.checkLogin2(ShopID,username, hashValue);
	}
	//Retrieve data from database depended on the input from client
	public static String getData(String barcode) {
		//method to get data
		getDataFromDB get = new getDataFromDB();
		return get.getData(barcode);
	}
	//Retrieve price form barcode
	public static String getPrice(String barcode) {
		//method to get data
		getDataFromDB get = new getDataFromDB();
		return get.getPrice(barcode);
	}
	//New function create new transaction
	public static void createnewtrans(int ShopID,String barcode,int Quantity) {
		getDataFromDB get = new getDataFromDB();
		get.createnewtransaction(ShopID, barcode, Quantity);
	}
	//Compare sum IN & OUT
	public static boolean comparetransactions(int ShopID,int quantity,String barcode) {
		getDataFromDB get = new getDataFromDB();
		return get.compareTransaction(ShopID, quantity, barcode);
	}
	//Get all shop Names
	public static String getAllShopNames() {
		getDataFromDB get = new getDataFromDB();
		return get.getAllShopNames();
	}
	//Get all shop IDs
	public static String getAllShopIDs() {
		getDataFromDB get = new getDataFromDB();
		return get.getAllShopIDs();
	}
	//Get item quantity from shop
	public static String getShopItemQuantity(int ShopID,String barcode) {
		getDataFromDB get = new getDataFromDB();
		return get.getShopItemQuantity(ShopID,barcode);
	}
	//add a new shop
	public static void AddShop(String shopname,String employeeName,String passWord) {
		getDataFromDB get = new getDataFromDB();
		get.AddShop(shopname,employeeName,passWord);
	}
	//Create new IN transaction
	public static void createNewINTransaction(int ShopID,String barcode,int Quantity) {
		getDataFromDB get = new getDataFromDB();
		get.createNewINTransaction(ShopID, barcode, Quantity);
	}
	//Create new IN transaction(w/o barcode)
	public static void insertTransactionIn(int shopID, int quantity, String productname) {
		getDataFromDB get = new getDataFromDB();
		get.insertTransactionIn(shopID, quantity, productname);
	}
	//view all transactions
	public static ArrayList<String> viewTransaction(String start, String end){
		getDataFromDB get=new getDataFromDB();
		return get.viewTransaction(start,end);
	}
	//get all product names
	public static String getAllProductNames() {
		getDataFromDB get = new getDataFromDB();
		return get.getAllProductNames();
	}
	//add a new product
	public static void AddProduct(String barcode,String productname,int price) {
		getDataFromDB get=new getDataFromDB();
		get.AddProduct(barcode, productname,price);
	}
	//check if product already exist
	public static boolean checkProduct(String barcode,String productName) {
		getDataFromDB get= new getDataFromDB();
		return get.checkProduct(barcode, productName);
	}
	//get all product barcodes
	public static String getAllProductBarcodes() {
		getDataFromDB get=new getDataFromDB();
		return get.getAllProductBarcodes();
	}
	
	
	
	
	
	
	
	
	
	
	//Old stuff
	//Insert history 
	
}
package com.servlet;
//Call functions from MyLittleShop.java on server
import java.io.File;
import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.product.service.MylittleShopClassNotFoundExceptionException;
import com.product.service.MylittleShopIOExceptionException;
import com.product.service.MylittleShopParserConfigurationExceptionException;
import com.product.service.MylittleShopSAXExceptionException;
import com.product.service.MylittleShopSQLExceptionException;
import com.product.service.MylittleShopStub;
import com.product.service.MylittleShopStub.*;
public class ClientGUI {
	//static File clientkey = new File("clientkey.xml");
	//static File managerkey = new File("managerkey.xml");
	static File history = new File("history.xml");
	static File shop1 =  new File("shop1.xml");
	static File shop2 = new File("shop2.xml");
	static File shop3 = new File("shop3.xml");
	//scaning code, and return the name ,price of that code in shop 1
	public static String getData(String code) throws RemoteException, MylittleShopParserConfigurationExceptionException, MylittleShopIOExceptionException, MylittleShopSAXExceptionException  {
		//When creating client web service, importing http://localhost:8080/Server/services/MylittleShop?wsdl (this is the xml file for
	    //describing server-web service, it will tell the client how to compose a web service request and the interface that is
	    // provided by the server ,all of thing contained in MylittleShopStub class.
	    
	    // this GUI class for taking some function of MylittleShopStub to send the request and receive the response
		MylittleShopStub ser = new MylittleShopStub();
		GetData setCode =  new GetData();
		setCode.setBarcode(code);
		GetDataResponse getCode = null;
		getCode = ser.getData(setCode);
		return getCode.get_return();
	}
	//get product price
	public static String getPrice(String code) throws RemoteException, MylittleShopParserConfigurationExceptionException, MylittleShopIOExceptionException, MylittleShopSAXExceptionException  {
		//When creating client web service, importing http://localhost:8080/Server/services/MylittleShop?wsdl (this is the xml file for
	    //describing server-web service, it will tell the client how to compose a web service request and the interface that is
	    // provided by the server ,all of thing contained in MylittleShopStub class.
	    
	    // this GUI class for taking some function of MylittleShopStub to send the request and receive the response
		MylittleShopStub ser = new MylittleShopStub();
		GetPrice setCode =  new GetPrice();
		setCode.setBarcode(code);
		GetPriceResponse getCode = null;
		getCode = ser.getPrice(setCode);
		return getCode.get_return();
	}
	//quan's stuffs
	public static void createnewtransaction(int ShopID,String Barcode,int Quantity) throws RemoteException {
		MylittleShopStub ser = new MylittleShopStub();
		Createnewtrans trans=new Createnewtrans();
		trans.setShopID(ShopID);
		trans.setBarcode(Barcode);
		trans.setQuantity(Quantity);
		ser.createnewtrans(trans);
	}
	public static boolean comparetransaction(int ShopID, int Quantity, String Barcode) throws RemoteException {
		MylittleShopStub ser = new MylittleShopStub();
		Comparetransactions compare = new Comparetransactions();
		compare.setBarcode(Barcode);
		compare.setShopID(ShopID);
		compare.setQuantity(Quantity);
		ComparetransactionsResponse response=ser.comparetransactions(compare);
		return response.get_return();
	}
	public static String getAllShopNames() throws RemoteException {
		MylittleShopStub ser = new MylittleShopStub();
		GetAllShopNames get= new GetAllShopNames();
		GetAllShopNamesResponse nameresponse=ser.getAllShopNames(get);
		return nameresponse.get_return();
	}
	public static String getAllShopIDs() throws RemoteException {
		MylittleShopStub ser = new MylittleShopStub();
		GetAllShopIDs get= new GetAllShopIDs();
		GetAllShopIDsResponse nameresponse=ser.getAllShopIDs(get);
		return nameresponse.get_return();
	}
	public static String getShopItemQuantity(int ShopID,String Barcode) throws RemoteException {
		MylittleShopStub ser = new MylittleShopStub();
		GetShopItemQuantity get=new GetShopItemQuantity();
		get.setShopID(ShopID);
		get.setBarcode(Barcode);
		GetShopItemQuantityResponse response = ser.getShopItemQuantity(get);
		return response.get_return();
	}
	public static void addShop(String Shopname,String employeeName,String passWord) throws RemoteException {
		MylittleShopStub ser = new MylittleShopStub();
		AddShop get=new AddShop();
		get.setShopname(Shopname);
		get.setEmployeeName(employeeName);
		get.setPassWord(passWord);
		ser.addShop(get);
	}
	public static void createNewINTransaction(int ShopID,String Barcode,int Quantity) throws RemoteException {
		MylittleShopStub ser = new MylittleShopStub();
		CreateNewINTransaction trans=new CreateNewINTransaction();
		trans.setShopID(ShopID);
		trans.setBarcode(Barcode);
		trans.setQuantity(Quantity);
		ser.createNewINTransaction(trans);
	}
	public static void insertTransactionIn(int shopID, int quantity, String productname) throws RemoteException{
		MylittleShopStub ser = new MylittleShopStub();
		InsertTransactionIn get=new InsertTransactionIn();
		get.setProductname(productname);
		get.setQuantity(quantity);
		get.setShopID(shopID);
		ser.insertTransactionIn(get);
	}
	public static String[] viewTransaction(String start, String end) throws RemoteException{
		MylittleShopStub ser = new MylittleShopStub();
		ViewTransaction get=new ViewTransaction();
		get.setStart(start);
		get.setEnd(end);
		ViewTransactionResponse response=ser.viewTransaction(get);
		return response.get_return();
	}
	public static String getAllProductNames() throws RemoteException {
		MylittleShopStub ser=new MylittleShopStub();
		GetAllProductNames get = new GetAllProductNames();
		GetAllProductNamesResponse response=ser.getAllProductNames(get);
		return response.get_return();
	}
	public static void AddProduct(String Barcode,String productname,int price) throws RemoteException {
		MylittleShopStub ser=new MylittleShopStub();
		AddProduct get = new AddProduct();
		get.setBarcode(Barcode);
		get.setProductname(productname);
		get.setPrice(price);
		ser.addProduct(get);
	}
	public static boolean checkProduct(String Barcode,String productName) throws RemoteException {
		MylittleShopStub ser=new MylittleShopStub();
		CheckProduct get=new CheckProduct();
		get.setBarcode(Barcode);
		get.setProductName(productName);
		CheckProductResponse response=ser.checkProduct(get);
		return response.get_return();
	}
	public static String getAllProductBarcodes() throws RemoteException {
		MylittleShopStub ser=new MylittleShopStub();
		GetAllProductBarcodes get = new GetAllProductBarcodes();
		GetAllProductBarcodesResponse response=ser.getAllProductBarcodes(get);
		return response.get_return();
	}
	//Validate employee info
	public static boolean ValidateInfoEmployee(int ShopID,String username, String password) throws RemoteException, MylittleShopClassNotFoundExceptionException, MylittleShopParserConfigurationExceptionException, MylittleShopSQLExceptionException, MylittleShopIOExceptionException, MylittleShopSAXExceptionException {
		MylittleShopStub ser = new MylittleShopStub();
		ValidateInfoEmployee check = new ValidateInfoEmployee();
		check.setShopID(ShopID);
		check.setUsername(username);
		check.setPassword(password);
		ValidateInfoEmployeeResponse valid = ser.validateInfoEmployee(check);
		return valid.get_return();
	}
	
	
	
	
	
	//old stuffs
	public static boolean validInfo(String username,String password) throws RemoteException, MylittleShopParserConfigurationExceptionException, MylittleShopIOExceptionException, MylittleShopSAXExceptionException, MylittleShopClassNotFoundExceptionException, MylittleShopSQLExceptionException {
		MylittleShopStub ser = new MylittleShopStub();
		ValidateInfo check = new ValidateInfo();
		check.setUsername(username);
		check.setPassword(password);
		ValidateInfoResponse valid = ser.validateInfo(check);
		return valid.get_return();
	}/*
	public static void main(String []args) throws RemoteException, MylittleShopParserConfigurationExceptionException, MylittleShopIOExceptionException, MylittleShopSAXExceptionException {
		//testing code = 0,will return the name and price of product 1
		String[] a=viewTransaction("2009-12-12","2015-12-12");
		//System.out.println(a[1]);
			update1(1,2);
			viewDatabase1();
			
			
			History("shop1",LocalDateTime.now().toString(),"salad","200");
	}*/
}

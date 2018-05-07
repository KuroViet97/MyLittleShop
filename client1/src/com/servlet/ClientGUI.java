package com.servlet;

import java.io.File;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.axis2.AxisFault;
import com.product.service.MylittleShopIOExceptionException;
import com.product.service.MylittleShopParserConfigurationExceptionException;
import com.product.service.MylittleShopSAXExceptionException;
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
	public static void getData1(int code) throws RemoteException, MylittleShopParserConfigurationExceptionException, MylittleShopIOExceptionException, MylittleShopSAXExceptionException  {
		//When creating client web service, importing http://localhost:8080/Server/services/MylittleShop?wsdl (this is the xml file for
	    //describing server-web service, it will tell the client how to compose a web service request and the interface that is
	    // provided by the server ,all of thing contained in MylittleShopStub class.
	    
	    // this GUI class for taking some function of MylittleShopStub to send the request and receive the response
		MylittleShopStub ser = new MylittleShopStub();
		GetData1 setCode =  new GetData1();
		setCode.setCode(code);
		GetData1Response getCode = null;
		getCode = ser.getData1(setCode);
		System.out.println(getCode.get_return());
	}
	//updating the amount of product 1 in shop1
	public static void update1(int code, int amount) throws RemoteException {
		MylittleShopStub ser = new MylittleShopStub();
		Update1 up = new Update1();
		up.setCode(code);
		up.setAmount(amount);
		ser.update1(up);
	}
	//viewing the database of shop1
	public static void viewDatabase1() throws RemoteException, MylittleShopParserConfigurationExceptionException, MylittleShopIOExceptionException, MylittleShopSAXExceptionException {
		MylittleShopStub ser = new MylittleShopStub();
		ViewDatabase1 view = new ViewDatabase1();
		ViewDatabase1Response viewResponse =  ser.viewDatabase1(view);
		System.out.println(viewResponse.get_return());
	}
	//viewing history 
	public static void History(String shop,String time,String name, String amount) throws RemoteException {
		MylittleShopStub ser = new MylittleShopStub();
		History setview = new History();
		setview.setShop(shop);
		setview.setTime(time);
		setview.setName(name);
		setview.setAmount(amount);
		ser.history(setview);
		//checking the history.xml will see the transaction record
	}
	public static void main(String []args) throws RemoteException, MylittleShopParserConfigurationExceptionException, MylittleShopIOExceptionException, MylittleShopSAXExceptionException {
		//testing code = 0,will return the name and price of product 1
	//		getData1(1);
			viewDatabase1();
			update1(1,2);
			viewDatabase1();
			History("shop1",LocalDateTime.now().toString(),"salad","200");
	}
}

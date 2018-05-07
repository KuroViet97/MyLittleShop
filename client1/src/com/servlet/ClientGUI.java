package com.servlet;

import java.io.File;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;
import com.product.service.MylittleShopIOExceptionException;
import com.product.service.MylittleShopParserConfigurationExceptionException;
import com.product.service.MylittleShopSAXExceptionException;
import com.product.service.MylittleShopStub;
import com.product.service.MylittleShopStub.GetData;
import com.product.service.MylittleShopStub.GetDataResponse;

public class ClientGUI {
	public static void getData(int code) throws AxisFault  {
		//When creating client web service, importing http://localhost:8080/Server/services/MylittleShop?wsdl (this is the xml file for
	    //describing server-web service, it will tell the client how to compose a web service request and the interface that is
	    // provided by the server ,all of thing contained in MylittleShopStub class.
	    
	    // this GUI class for taking some function of MylittleShopStub to send the request and receive the response
		MylittleShopStub service = new MylittleShopStub();
		//sending the request
		GetData get = new GetData();
		//code is the code when scanning the Barcode 
		get.setCode(code);
		//receiving the response
		GetDataResponse s = null;
		try {
			s = service.getData(get);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MylittleShopParserConfigurationExceptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MylittleShopIOExceptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MylittleShopSAXExceptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(s.get_return());
	}
	public static void main(String []args) throws AxisFault {
		//testing code = 0,will return the name and price of product 1
			getData(0);
	}
}

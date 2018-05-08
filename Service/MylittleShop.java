package com.product.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

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
	//Retrieve data from database depended on the input from client
	public static String getData1( int code) {
		//method to get data
		getDataFromDB get = new getDataFromDB();
		return get.getData("shop1", code);
	}
	public static String getData2( int code) throws SAXException, IOException, ParserConfigurationException {
		//method to get data
		getDataFromDB get = new getDataFromDB();
		return get.getData("shop2", code);
	}
	public static String getData3( int code) throws SAXException, IOException, ParserConfigurationException {
		//method to get data
		getDataFromDB get = new getDataFromDB();
		return get.getData("shop3", code);
	}
	//Update database
	public static void Update1(int code,int amount)  {
		//Updating the amount of product in database
		getDataFromDB get = new getDataFromDB();
		get.update("shop1", code , amount);
	}
	public static void Update2(int code,int amount)  {
		//Updating the amount of product in database
		getDataFromDB get = new getDataFromDB();
		get.update("shop2", code, amount);
	}
	public static void Update3(int code,int amount)  {
		//Updating the amount of product in database
		getDataFromDB get = new getDataFromDB();
		get.update("shop3", code, amount);
	}
	//View database
	public static String viewDatabase1() {
		getDataFromDB get = new getDataFromDB();
		return get.viewDatabase("shop1");
	}
	public static String viewDatabase2() throws SAXException, IOException, ParserConfigurationException {
		getDataFromDB get = new getDataFromDB();
		return get.viewDatabase("shop2");
	}
	public static String viewDatabase3() throws SAXException, IOException, ParserConfigurationException {
		getDataFromDB get = new getDataFromDB();
		return get.viewDatabase("shop3");
	}
	//Insert history 
	public static void History(String shop,String time,String name, String amount)
	{
		try
		{
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(history);
			XPath xpath = XPathFactory.newInstance().newXPath();
			Node root = (Node) xpath.evaluate("/history", doc, XPathConstants.NODE);
			Element entry = doc.createElement(shop);
			entry.setAttribute("time", time);
			Element	name1 = doc.createElement("name");
			name1.setTextContent(name);
			Element amount1 = doc.createElement("amount");
			amount1.setTextContent(amount);
			root.appendChild(entry);
			entry.appendChild(name1);
			entry.appendChild(amount1);	
			
			Source source = new DOMSource(doc);
			StreamResult sresult = new StreamResult(new OutputStreamWriter(new FileOutputStream(history), "ISO-8859-1"));
			Transformer xformer = TransformerFactory.newInstance().newTransformer();
			xformer.transform(source,sresult);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}

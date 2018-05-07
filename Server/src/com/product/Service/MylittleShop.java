package com.product.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
	static File clientkey = new File("clientkey.xml");
	static File managerkey = new File("managerkey.xml");
	static File history = new File("history.xml");
	static File shop1 = new File("shop1.xml");
	static File shop2 = new File("shop2.xml");
	static File shop3 = new File("shop3.xml");
	
	//Retrieve data from database depended on the input from client
	public static String getData1( int code) throws SAXException, IOException, ParserConfigurationException {
		//method to get data
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(shop1);
		doc.getDocumentElement().normalize();
		NodeList nlist = doc.getElementsByTagName("product");
		String str = "";
		String str1 = null;
		Node nNode = nlist.item(code-1);
		Element eElement = (Element) nNode;
		str = eElement.getElementsByTagName("name").item(0).getTextContent();
		str1 = eElement.getElementsByTagName("price").item(0).getTextContent();
		return "Name :" + str + "\n" + "Price:"+ str1;
	}
	public static String getData2( int code) throws SAXException, IOException, ParserConfigurationException {
		//method to get data
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(shop2);
		doc.getDocumentElement().normalize();
		NodeList nlist = doc.getElementsByTagName("product");
		String str = "";
		String str1 = null;
		Node nNode = nlist.item(code-1);
		Element eElement = (Element) nNode;
		str = eElement.getElementsByTagName("name").item(0).getTextContent();
		str1 = eElement.getElementsByTagName("price").item(0).getTextContent();
		return "Name :" + str + "\n" + "Price:"+ str1;
	}
	public static String getData3( int code) throws SAXException, IOException, ParserConfigurationException {
		//method to get data
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(shop3);
		doc.getDocumentElement().normalize();
		NodeList nlist = doc.getElementsByTagName("product");
		String str = "";
		String str1 = null;
		Node nNode = nlist.item(code-1);
		Element eElement = (Element) nNode;
		str = eElement.getElementsByTagName("name").item(0).getTextContent();
		str1 = eElement.getElementsByTagName("price").item(0).getTextContent();
		return "Name :" + str + "\n" + "Price:"+ str1;
	}
	//Update database
	public static void Update1(int code,int amount)  {
		//Updating the amount of product in database
		try{
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(shop1);
		doc.getDocumentElement().normalize();
		Node product = doc.getElementsByTagName("product").item(code-1);
		NodeList list = product.getChildNodes();
		int change ;
		String num = "";
		for (int i = 0; i < list.getLength(); i++) {
		   Node node = list.item(i);
		   if ("amount".equals(node.getNodeName())) {
			 change = (Integer.parseInt(node.getTextContent())-amount);
			 num = "" +change;
			 node.setTextContent(num);
		   }
		}
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(new OutputStreamWriter(new FileOutputStream(shop1), "ISO-8859-1"));
		transformer.transform(source, result);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	public static void Update2(int code,int amount)  {
		//Updating the amount of product in database
		try{
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(shop2);
		doc.getDocumentElement().normalize();
		Node product = doc.getElementsByTagName("product").item(code-1);
		NodeList list = product.getChildNodes();
		int change ;
		String num = "";
		for (int i = 0; i < list.getLength(); i++) {
		   Node node = list.item(i);
		   if ("amount".equals(node.getNodeName())) {
			 change = (Integer.parseInt(node.getTextContent())-amount);
			 num = "" +change;
			 node.setTextContent(num);
		   }
		}
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(new OutputStreamWriter(new FileOutputStream(shop2), "ISO-8859-1"));
		transformer.transform(source, result);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	public static void Update3(int code,int amount)  {
		//Updating the amount of product in database
		try{
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(shop3);
		doc.getDocumentElement().normalize();
		Node product = doc.getElementsByTagName("product").item(code-1);
		NodeList list = product.getChildNodes();
		int change ;
		String num = "";
		for (int i = 0; i < list.getLength(); i++) {
		   Node node = list.item(i);
		   if ("amount".equals(node.getNodeName())) {
			 change = (Integer.parseInt(node.getTextContent())-amount);
			 num = "" +change;
			 node.setTextContent(num);
		   }
		}
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(new OutputStreamWriter(new FileOutputStream(shop3), "ISO-8859-1"));
		transformer.transform(source, result);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	//View database
	public static String viewDatabase1() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(shop1);
		doc.getDocumentElement().normalize();
		NodeList nlist = doc.getElementsByTagName("product");
		String full = ""; 
		Node nNode = null ;
		Element eElement = null;
		for(int i = 0; i <nlist.getLength();i++) {
			nNode = nlist.item(i);
			eElement = (Element) nNode;
			full  = full + "Name:"+ eElement.getElementsByTagName("name").item(0).getTextContent()+","
					+ "Price:"+ eElement.getElementsByTagName("price").item(0).getTextContent()+","
					+ "Amount:" + eElement.getElementsByTagName("amount").item(0).getTextContent()+"\n";
		}
		return full;
	}
	public static String viewDatabase2() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(shop2);
		doc.getDocumentElement().normalize();
		NodeList nlist = doc.getElementsByTagName("product");
		String full = ""; 
		Node nNode = null ;
		Element eElement = null;
		for(int i = 0; i <nlist.getLength();i++) {
			nNode = nlist.item(i);
			eElement = (Element) nNode;
			full  = full + "Name:"+ eElement.getElementsByTagName("name").item(0).getTextContent()+","
					+ "Price:"+ eElement.getElementsByTagName("price").item(0).getTextContent()+","
					+ "Amount:" + eElement.getElementsByTagName("amount").item(0).getTextContent()+"\n";
		}
		return full;
	}
	public static String viewDatabase3() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(shop3);
		doc.getDocumentElement().normalize();
		NodeList nlist = doc.getElementsByTagName("product");
		String full = ""; 
		Node nNode = null ;
		Element eElement = null;
		for(int i = 0; i <nlist.getLength();i++) {
			nNode = nlist.item(i);
			eElement = (Element) nNode;
			full  = full + "Name:"+ eElement.getElementsByTagName("name").item(0).getTextContent()+","
					+ "Price:"+ eElement.getElementsByTagName("price").item(0).getTextContent()+","
					+ "Amount:" + eElement.getElementsByTagName("amount").item(0).getTextContent()+"\n";
		}
		return full;
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

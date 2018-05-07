package com.product.Service;
import java.io.File;
import java.io.IOException;

import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class MylittleShop {
	//database
	static File data = new File("data.xml");
	//Retrieve data from database depended on the input from client
	public static String getData(int code) throws SAXException, IOException, ParserConfigurationException {
		//method to get data
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		Document doc = factory.newDocumentBuilder().parse(data);
		doc.getDocumentElement().normalize();
		NodeList nlist = doc.getElementsByTagName("product");
		String str = null;
		String str1 = null;
		Node nNode = nlist.item(code);
		Element eElement = (Element) nNode;
		str = eElement.getElementsByTagName("name").item(0).getTextContent();
		str1 = eElement.getElementsByTagName("price").item(0).getTextContent();
		return "Name :" + str + "\n" + "Price:"+ str1;
	}
}

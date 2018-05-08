package com.product.Service;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
/**
 * 
 * @author Viet
 * Class for hash function (Sha256)
 */

public class Sha256 {
	//hash data
	public String hash(String data){
		String result = null;
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashing = digest.digest(data.getBytes("UTF-8"));
			return bytesToHex(hashing);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	//convert byte value to hexadecimal value
	private String bytesToHex(byte[] hashing){
		return DatatypeConverter.printHexBinary(hashing);
	}
	
}

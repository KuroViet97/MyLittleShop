package hash;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

/**
 * 
 * @author Viet
 * SHA-256 HASHING (user & password)
 *
 */
public class Sha256 {
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
	private String bytesToHex(byte[] hashing){
		return DatatypeConverter.printHexBinary(hashing);
	}
}


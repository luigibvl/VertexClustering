package hashFunction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.encoders.Hex;

public class Md5 extends HashFunction{
	
	public Md5() {}
	
	@Override
	public String getHash(String stringa){
		
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(stringa.getBytes());
			byte[] messageDigestMD5 = messageDigest.digest();
			StringBuffer stringBuffer = new StringBuffer();

			for (byte bytes : messageDigestMD5) 
				stringBuffer.append(String.format("%02x", bytes & 0xff));
			
			
			return Hex.toHexString(messageDigestMD5).substring(0, 8);

		} catch (NoSuchAlgorithmException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
			return null;
		}
	}
	

}

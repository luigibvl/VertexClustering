package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.encoders.Hex;

public class Md5 implements HashFunctionInterface{

	public Md5() {}

	@Override
	public String getHash(String[] stringa){

		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			int[] result = new int[]{0,0,0,0,0,0,0,0,0,0};
			for(int i=0; i<10; i++){
				messageDigest.update(stringa[i].getBytes());
				byte[] messageDigestMD5 = messageDigest.digest();
				result[i] = messageDigestMD5.hashCode();
				
			}
			int sum=0;
			for(int j=0; j<10; j++){
				sum += result[j];
			}
			return Integer.toHexString(sum).substring(0,8);

			} catch (NoSuchAlgorithmException exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
				return null;
			}
		}


	}

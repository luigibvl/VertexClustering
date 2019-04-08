package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.encoders.Hex;

public class Sha256 implements HashFunctionInterface{

	public Sha256() {}

	@Override
	public String getHash(String[] stringa){
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			int[] result = new int[]{0,0,0,0,0,0,0,0,0,0};
			for(int i=0; i<10; i++){
				messageDigest.update(stringa[i].getBytes());
				byte[] messageDigestSha = messageDigest.digest();
				result[i] = messageDigestSha.hashCode();
			}
			int sum=0;
			for(int j=0; j<10; j++){
				sum += result[j];
			}
			return Integer.toHexString(sum).substring(10,18);

		} catch (NoSuchAlgorithmException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
			return null;
		}
	}

}

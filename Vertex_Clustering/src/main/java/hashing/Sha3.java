package hashing;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

public class Sha3 implements HashFunctionInterface{

	public Sha3() {}

	@Override
	public String getHash(String[] stringa){
		SHA3.DigestSHA3 digestSHA3 = new SHA3.DigestSHA3(256);
		int[] result = new int[]{0,0,0,0,0,0,0,0,0,0};
		for(int i=0; i<10; i++){
			byte[] digest = digestSHA3.digest(stringa[i].getBytes());
			result[i] = digest.hashCode();
		}
		int sum=0;
		for(int j=0; j<10; j++){
			sum += result[j];
		}
		

		return Integer.toHexString(sum).substring(29,37);
	}

}

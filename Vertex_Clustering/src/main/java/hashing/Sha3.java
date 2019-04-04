package hashing;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

public class Sha3 extends HashFunction{
	
	public Sha3() {}
	
	@Override
	public String getHash(String stringa){
		SHA3.DigestSHA3 digestSHA3 = new SHA3.DigestSHA3(256);
		byte[] digest = digestSHA3.digest(stringa.getBytes());
		digest.toString();

		return Hex.toHexString(digest).substring(29, 37);
	}

}

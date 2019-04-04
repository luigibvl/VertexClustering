package hashing;

import java.util.zip.Adler32;
import java.util.zip.Checksum;

public class Adler implements HashFunctionInterface{
	

	public Adler() {}
	
	@Override
	public String getHash(String stringa){
		Checksum adler = new Adler32();
		adler.update(stringa.getBytes(), 0, stringa.getBytes().length);
		long checksum =adler.getValue();
		return Long.toHexString(checksum);
		
	}
}

package hashFunction;

import java.util.zip.Adler32;
import java.util.zip.Checksum;

public class Adler extends HashFunction{
	

	public Adler() {}
	
	@Override
	public String getHash(String stringa){
		Checksum adler = new Adler32();
		adler.update(stringa.getBytes(), 0, stringa.getBytes().length);
		long checksum =adler.getValue();
		return Long.toHexString(checksum);
		
	}
}

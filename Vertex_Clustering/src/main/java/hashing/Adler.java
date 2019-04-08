package hashing;

import java.util.zip.Adler32;
import java.util.zip.Checksum;

public class Adler implements HashFunctionInterface{
	

	public Adler() {}
	
	@Override
	public String getHash(String[] stringa){
		Checksum adler = new Adler32();
		long[] checksum = new long[10];
		for(int i=0; i<10; i++){
			adler.update(stringa[i].getBytes(), 0, stringa[i].getBytes().length);
			checksum[i] = adler.getValue();
		}
		long sum=0;
		for(int i=0; i<10; i++){
			sum += checksum[i];
		}
		int checksum2 = Long.hashCode(sum);
		return Integer.toHexString(checksum2);
		
	}
}

package hashing;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class Crc32 implements HashFunctionInterface{

	public Crc32() {}

	@Override
	public String getHash(String[] stringa) {
		Checksum checksum = new CRC32();
		long[] checksumValue = new long[10];
		for(int i=0; i<10; i++){
			checksum.update(stringa[i].getBytes(), 0, stringa[i].length());
			checksumValue[i] = checksum.getValue();
		}
		long sum=0;
		for(int i=0; i<10; i++){
			sum += checksumValue[i];
		}
		int checksum2 = Long.hashCode(sum);
		String hex = Integer.toHexString(checksum2).toUpperCase();
		while (hex.length() < 8) {
			hex = "0" + hex;
		}
		String crc32 = hex;

		return crc32;
	}

}

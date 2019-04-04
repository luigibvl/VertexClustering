package hashFunction;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class Crc32 extends HashFunction{
	
	public Crc32() {}
	
	@Override
	public String getHash(String stringa) {
		Checksum checksum = new CRC32();
		checksum.update(stringa.getBytes(), 0, stringa.length());
		long checksumValue = checksum.getValue();

		String hex = Long.toHexString(checksumValue).toUpperCase();
		while (hex.length() < 8) {
			hex = "0" + hex;
		}
		String crc32 = hex;

		return crc32;
	}

}

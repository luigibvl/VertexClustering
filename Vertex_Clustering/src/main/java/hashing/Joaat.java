package hashing;

public class Joaat extends HashFunction{
	
	public Joaat() {}
	
	@Override
	public String getHash(String stringa){ 
		int result = 0;
		int offset = 0;
		byte[] b = stringa.getBytes();

		for (int i = 0; i < b.length; i++) { 
			byte curByte = b[offset + i]; 
			int h = (int)curByte; 

			result += h & 0x0FFL; 
			result += (result << 10); 
			result ^= (result >> 6); 
		} 

		result += (result << 3); 
		result ^= (result >> 11); 
		result += (result << 15); 
		
		String joaat = Integer.toHexString(result);
		while (joaat.length() < 8) {
			joaat = "0" + joaat;
		}
		
		return joaat; 
	}

}

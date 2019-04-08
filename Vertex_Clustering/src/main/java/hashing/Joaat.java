package hashing;

public class Joaat implements HashFunctionInterface{

	public Joaat() {}

	@Override
	public String getHash(String[] stringa){ 
		int[] result = new int[]{0,0,0,0,0,0,0,0,0,0};
		int offset = 0;
		for(int i=0; i<10; i++){
			byte[] b = stringa[i].getBytes();

			for (int j = 0; j < b.length; j++) { 
				byte curByte = b[offset + j]; 
				int h = (int)curByte; 

				result[i] += h & 0x0FFL; 
				result[i] += (result[i] << 10); 
				result[i] ^= (result[i] >> 6); 
			} 

			result[i] += (result[i] << 3); 
			result[i] ^= (result[i] >> 11); 
			result[i] += (result[i] << 15); 
		}
		int sum=0;
		for(int i=0; i<10; i++){
			sum += result[i];
		}
		String joaat = Integer.toHexString(sum);
		while (joaat.length() < 8) {
			joaat = "0" + joaat;
		}

		return joaat; 
	}

}

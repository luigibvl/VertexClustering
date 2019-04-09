package hashing;

public class Fnv implements HashFunctionInterface{
	
	
	private static final int FNV_32_PRIME = 0x01000193;

	
	public Fnv() {}

	@Override
	public String getHash(String stringa){

		int hval = 0x811c9dc5;
		byte[] bytes = stringa.getBytes();
		int size = bytes.length;

		for (int i = 0; i < size; i++){
			hval *= FNV_32_PRIME;
			hval ^= bytes[i];
		}
		return Integer.toHexString(hval);
	}

}


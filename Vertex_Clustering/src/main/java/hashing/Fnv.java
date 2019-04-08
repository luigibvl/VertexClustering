package hashing;

public class Fnv implements HashFunctionInterface{


	private static final int FNV_32_PRIME = 0x01000193;


	public Fnv() {}

	@Override
	public String getHash(String[] stringa){

		int[] hval = new int[] {0x811c9dc5,0x811c9dc5,0x811c9dc5,0x811c9dc5,0x811c9dc5,0x811c9dc5,0x811c9dc5,0x811c9dc5,0x811c9dc5,0x811c9dc5};
		for(int i=0; i<10; i++){
			byte[] bytes = stringa[i].getBytes();
			int size = bytes.length;

			for (int j = 0; j < size; j++){
				hval[i] *= FNV_32_PRIME;
				hval[i] ^= bytes[j];
			}
		}
		int sum=0;
		for(int i=0; i<10; i++){
			sum += hval[i];
		}
		return Integer.toHexString(sum);
	}

}

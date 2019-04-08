package hashing;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.jpountz.xxhash.StreamingXXHash32;
import net.jpountz.xxhash.XXHashFactory;

public class XxHash implements HashFunctionInterface{

	public XxHash(){}

	@Override
	public String getHash(String[] stringa) {
		XXHashFactory factory = XXHashFactory.fastestInstance();

		try{
			int[] hash = new int[]{0,0,0,0,0,0,0,0,0,0};
			for(int i=0; i<10; i++){
				byte[] data = stringa[i].getBytes();
				ByteArrayInputStream in = new ByteArrayInputStream(data);

				int seed = 0x9747b28c;  // Use to initialize the hash value, use whatever
				//  value you want, but always the same.

				StreamingXXHash32 hash32 = factory.newStreamingHash32(seed);

				byte[] buf = new byte[8]; // For real-world usage, use a larger buffer, like 8192 bytes
				for (;;){
					int read = in.read(buf);
					if (read == -1){
						break;
					}
					hash32.update(buf, 0, read);
				}
				hash[i] = hash32.getValue();
			}
			int sum=0;
			for(int j=0; j<10; j++){
				sum += hash[j];
			}
			return Integer.toHexString(sum);
		}
		catch(UnsupportedEncodingException ex){
			System.out.println(ex);
			return null;
		}
		catch(IOException ex){
			System.out.println(ex);
			return null;
		}
	}
}

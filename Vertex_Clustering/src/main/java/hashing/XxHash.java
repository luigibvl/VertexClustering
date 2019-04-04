package hashing;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.jpountz.xxhash.StreamingXXHash32;
import net.jpountz.xxhash.XXHashFactory;

public class XxHash extends HashFunction{

	public XxHash(){}
	
	@Override
	public String getHash(String stringa) {
		XXHashFactory factory = XXHashFactory.fastestInstance();
		 
	    try{
	    	byte[] data = stringa.getBytes();
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
	        int hash = hash32.getValue();
	 
	        return Integer.toHexString(hash);
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

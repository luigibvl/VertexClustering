package agiw.agiw2019;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.codec.digest.*;

public class PageClustering {

	public int createShingleVector(ShingleSet shingleSet) {
		//ShingleVector vector =  new ShingleVector();
		int first=300;
		for(Shingle s : shingleSet.getShingle_set()) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] hash = md.digest(s.concatTagSequence().getBytes(StandardCharsets.UTF_8));
				int current=hash[0]%8;				
				for(byte b : hash) {
					//TODO
//					int i = b%8;
//					if(i<s_byte)
//						s_byte=i;
				}
				
			if(current<first)
				first=current;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return first;
	}

}

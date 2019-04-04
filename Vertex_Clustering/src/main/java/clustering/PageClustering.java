package clustering;


import java.util.ArrayList;
import java.util.List;

import hashing.Adler;
import hashing.Crc32;
import hashing.FactoryHashFunctions;
import hashing.Fnv;
import hashing.HashFunction;
import hashing.Joaat;
import hashing.Md5;
import hashing.Sha256;
import hashing.Sha3;
import hashing.XxHash;
import shingle.Shingle;
import shingle.ShingleSet;
import shingle.ShingleVector;

public class PageClustering {


	public PageClustering() {}

	public static ShingleVector createShingleVector(ShingleSet shingleSet) {

		ShingleVector vector = new ShingleVector();

		FactoryHashFunctions factory=new FactoryHashFunctions();
		List<HashFunction> lisHashFunctions=new ArrayList<>();
		lisHashFunctions=factory.getFactory();

		for(int i=0;i<lisHashFunctions.size();i++) {
			HashFunction hashFunction=lisHashFunctions.get(i);
			int max = Integer.MAX_VALUE;
			String min = Integer.toString(max);
			for(Shingle s : shingleSet.getShingle_set()) {
				String input = s.concatTagSequence();
				String output = hashFunction.getHash(input);
				
				if(output.compareTo(min)<0)
					min=output;
			}
			
			int primo = Integer.parseInt(min.substring(0, min.length()/4), 16);
			int secondo = Integer.parseInt(min.substring(min.length()/4, min.length()/2), 16);
			int terzo = Integer.parseInt(min.substring(min.length()/2, min.length()*3/4), 16);
			int quarto = Integer.parseInt(min.substring(min.length()*3/4, min.length()), 16);
			
			vector.getVector()[i] = Integer.toString(primo^secondo^terzo^quarto);
		}
		
		return vector;
	}
}

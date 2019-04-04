package clustering;


import java.util.ArrayList;
import java.util.List;


import hashFunction.Adler;
import hashFunction.Crc32;
import hashFunction.FactoryHashFunctions;
import hashFunction.Fnv;
import hashFunction.HashFunction;
import hashFunction.Joaat;
import hashFunction.Md5;
import hashFunction.Sha256;
import hashFunction.Sha3;
import hashFunction.XxHash;
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
			long min = Long.MAX_VALUE;
			for(Shingle s : shingleSet.getShingle_set()) {
				String input = s.concatTagSequence();
				String output = hashFunction.getHash(input);
				long decimal = Long.parseLong(output, 16);

				if(decimal<min)
					min=decimal;
			}
			vector.getVector()[i] = min;
		}
		return vector;
	}
}

package hashing;

import java.util.ArrayList;
import java.util.List;

public class FactoryHashFunctions {
	
	private List<HashFunctionInterface> lisHashFunctions;

	
	public FactoryHashFunctions() {
		this.lisHashFunctions=new ArrayList<>();
	}
	
	public List<HashFunctionInterface> getFactory() {
		
		this.lisHashFunctions.add(new Crc32());
		this.lisHashFunctions.add(new Adler());
		this.lisHashFunctions.add(new Fnv());
		this.lisHashFunctions.add(new Joaat());
		this.lisHashFunctions.add(new XxHash());
		this.lisHashFunctions.add(new Md5());
		this.lisHashFunctions.add(new Sha256());
		this.lisHashFunctions.add(new Sha3());
		
		return this.lisHashFunctions;
	}

}


package shingle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hashing.FactoryHashFunctions;
import hashing.HashFunction;


public class ShingleVector {
	
	private static String[] vector = new String[8];
	private Map<String,List<MaskedShingleVector>> masked_vectors;

	public ShingleVector() {
		this.masked_vectors = new HashMap<String,List<MaskedShingleVector>>();
		this.masked_vectors.put("6/8", new ArrayList<MaskedShingleVector>());
		this.masked_vectors.put("7/8", new ArrayList<MaskedShingleVector>());
		this.masked_vectors.put("8/8", new ArrayList<MaskedShingleVector>());
		
		//createMasks();
	}
	
	public String[] getVector() {
		return this.vector;
	}
	
	public void setVector(String[] vector) {
		this.vector = vector;
	}

	public Map<String, List<MaskedShingleVector>> getMasked_vectors() {
		return this.masked_vectors;
	}

	public void setMasked_vectors(Map<String, List<MaskedShingleVector>> masked_vectors) {
		this.masked_vectors = masked_vectors;
	}
	
	public static void createShingleVector(ShingleSet shingleSet) {

		//vector = new ShingleVector();

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
			
			vector[i] = Integer.toString(primo^secondo^terzo^quarto);
		}
		
	}
	
	public void createMasks(){
		//create 8/8 masks
		MaskedShingleVector mask8 = new MaskedShingleVector();
		mask8.setMasked_vector(this.vector);
		this.masked_vectors.get("8/8").add(mask8);
		
		
		//create 7/8 masks
		for(int i=0;i<this.vector.length;i++){
			
			String [] vectorTemp=new String[8];
			for(int j=0;j<8;j++) 
				vectorTemp[j]=this.vector[j];
			
			
			MaskedShingleVector mask7 = new MaskedShingleVector();
			mask7.setMasked_vector(vectorTemp);
			mask7.getMasked_vector()[i] = "*";
			
			this.masked_vectors.get("7/8").add(mask7);
		}
		
		//create 6/8 masks
		for(int i=0;i<this.vector.length;i++){
			for(int j=i+1;j<this.vector.length;j++){
				
				String [] vectorTemp=new String[8];
				for(int k=0;k<8;k++) 
					vectorTemp[k]=this.vector[k];
				
				MaskedShingleVector mask6 = new MaskedShingleVector();
				mask6.setMasked_vector(vectorTemp);
				mask6.getMasked_vector()[i] = "*";
				mask6.getMasked_vector()[j] = "*";
				this.masked_vectors.get("6/8").add(mask6);
			}
		}
	}
	

	@Override
	public String toString() {
		String value = "";
		for(String i : this.vector) {
			value =  value.concat(i);
		}
		return value;
	}

}

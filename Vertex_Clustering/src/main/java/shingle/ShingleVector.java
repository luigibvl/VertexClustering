package shingle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.hash.HashFunction;

import hashing.FactoryHashFunctions;
import hashing.HashFunctionInterface;


public class ShingleVector {

	private String[] vector;
	private Map<String,List<MaskedShingleVector>> masked_vectors;

	public ShingleVector() {
		this.masked_vectors = new HashMap<String,List<MaskedShingleVector>>();
		this.masked_vectors.put("6/8", new ArrayList<MaskedShingleVector>());
		this.masked_vectors.put("7/8", new ArrayList<MaskedShingleVector>());
		this.masked_vectors.put("8/8", new ArrayList<MaskedShingleVector>());
		this.vector = new String[8];
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

	public List<MaskedShingleVector> getAllMV(){
		return this.masked_vectors.values().stream()
		        .flatMap(List::stream)
		        .collect(Collectors.toList());
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
		String value = "[";
		for(String i : this.vector) {
			value =  value.concat(i) + " ";
		}
		return value + "]";
	}

	public void copy(MaskedShingleVector mv) {
		if(!mv.containsWildCard())
			this.vector=  mv.getMasked_vector().clone();
	}

}

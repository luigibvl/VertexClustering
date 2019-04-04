package shingle;

public class MaskedShingleVector {
	
	private String[] masked_vector;
	
	public MaskedShingleVector(){

	}

	public String[] getMasked_vector() {
		return masked_vector;
	}

	public void setMasked_vector(String[] masked_vector) {
		this.masked_vector = masked_vector;
	}
	
	@Override
	public String toString() {
		String value = "";
		for(String i : this.masked_vector) {
			value =  value.concat(i)+"\n";
		}
		return value;
	}
	
}


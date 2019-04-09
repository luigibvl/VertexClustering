package shingle;

public class MaskedShingleVector {

	private String[] masked_vector;
	private int count;

	public MaskedShingleVector(){

	}

	public String[] getMasked_vector() {
		return masked_vector;
	}

	public void setMasked_vector(String[] masked_vector) {
		this.masked_vector = masked_vector;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void incrementCount(){
		this.count += 1;
	}
	
	public void decrementCount(int i){
		this.count -= i;
	}

	//Ritorna true se il masked su cui viene invocato il metodo copre 'vector'
	public boolean cover(ShingleVector vector){
		for (int i = 0; i<this.masked_vector.length; i++){
			if (!this.masked_vector[i].equals(vector.getVector()[i]) && !this.masked_vector[i].equals("*"))
				return false;
		}
		return true;
	}

	//Per capire se il Masked Vector è un 8/8
	public boolean containsWildCard(){
		for (int i=0; i<this.masked_vector.length; i++){
			if(this.masked_vector[i].equals("*"))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String result = "Count = ".concat(Integer.toString(this.count) + " [");
		for(String value : this.masked_vector) {
			result = result.concat(value + " ");
		}
		
		return result + "]";
	}
	
	@Override
	public boolean equals(Object o){
		MaskedShingleVector mv = (MaskedShingleVector)o;
		for(int i=0;i<mv.masked_vector.length;i++){
			if(this.masked_vector[i]!=mv.masked_vector[i])
				return false;
		}
		return true;
	}
}


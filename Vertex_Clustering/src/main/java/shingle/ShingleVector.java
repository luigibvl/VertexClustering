package shingle;



public class ShingleVector {
	
	private long[] vector = new long[8];
	private long[] masked = new long[8];
	private int count;
	
	
	
	public long[] getVector() {
		return vector;
	}
	
	public void setVector(long[] vector) {
		this.vector = vector;
	}
	
	public long[] getMasked() {
		return masked;
	}
	
	public void setMasked(long[] masked) {
		this.masked = masked;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		String value = "";
		for(long i : this.vector) {
			value =  value.concat(Long.toString(i))+"\n";
		}
		return value;
	}

}

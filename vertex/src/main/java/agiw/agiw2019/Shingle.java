package agiw.agiw2019;

import java.util.List;

public class Shingle {
	
	List<String> tag_sequence;
	int length;
	
	public Shingle(int length) {
		this.length=length;
	}
	
	
	
	public List<String> getTag_sequence() {
		return tag_sequence;
	}



	public void setTag_sequence(List<String> tag_sequence) {
		this.tag_sequence = tag_sequence;
	}


	public String concatTagSequence() {
		String stringTag="";
		for(String tag : tag_sequence) {
			stringTag= stringTag.concat(tag);
		}
		return stringTag;
	}

	public int getLength() {
		return length;
	}



	public void setLength(int length) {
		this.length = length;
	}




}

package shingle;

import java.util.List;

public class Shingle {

	private List<String> tag_sequence;
	private int length;


	public Shingle(int length) {
		this.length=length;
	}


	public List<String> getTag_sequence() {
		return this.tag_sequence;
	}

	public void setTag_sequence(List<String> tag_sequence) {
		this.tag_sequence = tag_sequence;
	}

	public String concatTagSequence() {
		String stringTag="";
		for(String tag : this.tag_sequence) {
			stringTag= stringTag.concat(tag);
		}
		return stringTag;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}

package shingle;

import java.util.List;

import tag.Tag;

public class Shingle {

	private List<Tag> tag_sequence;
	private int length;


	public Shingle(int length) {
		this.length=length;
	}

	public List<Tag> getTag_sequence() {
		return this.tag_sequence;
	}

	public void setTag_sequence(List<Tag> tag_sequence) {
		this.tag_sequence = tag_sequence;
	}

	public String concatTagSequence() {
		String stringTag="";
		for(Tag tag : this.tag_sequence) {
			stringTag= stringTag.concat(tag.getTag());
		}
		return stringTag;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	@Override
	public String toString() {
		String value = "";
		for(Tag i : this.tag_sequence) {
			value =  value.concat(i.getTag());
		}
		return value;
	}
}


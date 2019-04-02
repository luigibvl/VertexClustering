package agiw.agiw2019;

import java.util.ArrayList;
import java.util.List;

public class ShingleSet {

	protected List<Shingle> shingle_set;
	
	public ShingleSet(List<String> page) {
		this.shingle_set=createShingles(page);
	} 
	

	public List<Shingle> createShingles(List<String> page) {
		List<Shingle> shingle_set= new ArrayList<>(); 
		for(int i=0; i<page.size()-10; i++) {
			Shingle shingle = new Shingle(10);
			shingle.setTag_sequence(page.subList(i, i+10));
			shingle_set.add(shingle);
		}
		return shingle_set;
	}


	public List<Shingle> getShingle_set() {
		return shingle_set;
	}


	public void setShingle_set(List<Shingle> shingle_set) {
		this.shingle_set = shingle_set;
	}
	
	
}

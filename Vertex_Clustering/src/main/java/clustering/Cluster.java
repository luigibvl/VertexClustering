package clustering;

import java.util.ArrayList;
import java.util.List;

import shingle.MaskedShingleVector;
import tag.Pagina;

public class Cluster {

	private MaskedShingleVector shingle_signature;
	private List<Pagina> pages;
	
	public Cluster(){
		this.pages = new ArrayList<>();
	}

	public MaskedShingleVector getSignature() {
		return shingle_signature;
	}

	public void setSignature(MaskedShingleVector shingle_signature) {
		this.shingle_signature = shingle_signature;
	}

	public List<Pagina> getPages() {
		return pages;
	}

	public void setPages(List<Pagina> pages) {
		this.pages = pages;
	}
	
	public void addPage(Pagina p){
		this.pages.add(p);
	}
	
	@Override
	public String toString(){
		String result = "Signature Cluster:"+ this.shingle_signature.toString() + " Pages' shingle vectors: ";
		for(Pagina p : this.pages){
			result += p.toString() + ", " ;
		}
		return result;
	}
	
	
}

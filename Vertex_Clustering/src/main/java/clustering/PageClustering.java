package clustering;

import java.util.List;

import shingle.MaskedShingleVector;
import shingle.ShingleSet;
import shingle.ShingleVector;
import tag.Tag;
import tag.TagPagina;

public class PageClustering {


	public PageClustering() {}

	public void algorithm(/*List<TagPagina> sample_pages*/TagPagina p){
		
		/*First pass*/
		HashTable table = new HashTable();
		
		//for (TagPagina p : sample_pages){
			
			//creazione vettore v
			List<Tag> p_taglist = p.getLista();
			ShingleSet shingleSet = new ShingleSet(p_taglist);
			ShingleVector v = new ShingleVector();
			v.createShingleVector(shingleSet);
			v.createMasks();
			//--------------------
			
			System.out.println(v.getMasked_vectors().toString());
			
			for (MaskedShingleVector mv : v.getAllMV()){
				if (table.contains(mv))
					mv.incrementCount();
				else{
					mv.setCount(1);
					table.insert(mv);
				}
			}
			
			System.out.println(v.getAllMV().toString()); //OK
		//}
		
		/*Second pass*/
		
		table.order_by_count();
		
		System.out.println(table.toString()); //OK
		
		List<MaskedShingleVector> all88MV = table.getAll88MaskedVector();
		
		System.out.println(all88MV.toString());
	}
}

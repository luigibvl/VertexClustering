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
			List<Tag> p_taglist = p.getListaTag();
			ShingleSet shingleSet = new ShingleSet(p_taglist);
			ShingleVector v;
			shingleSet.createShingleVector();
			v = shingleSet.getShingleVector();
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
		
		System.out.println(all88MV.toString()); //NON OK
		
		for (MaskedShingleVector mv : all88MV){
			
		}
		
		
	}
}

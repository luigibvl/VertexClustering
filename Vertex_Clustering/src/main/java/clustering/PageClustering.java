package clustering;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import shingle.MaskedShingleVector;
import shingle.ShingleVector;
import tag.Tag;
import tag.Pagina;

public class PageClustering {

	List<Cluster> clusters;

	public PageClustering() {
		this.clusters = new ArrayList<>();
	}

	public void algorithm(List<Pagina> sample_pages){

		/*First pass*/
		HashTable table = new HashTable();

		for (Pagina p : sample_pages){

			//creazione vettore v
			List<Tag> p_taglist = p.getListaTag();
			p.createShingles(p_taglist);
			ShingleVector v;
			p.createShingleVector();
			v = p.getShingleVector();
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
		}

		/*Second pass*/
		table.sort_by_count_increasing();
		//All 8/8 vectors v in H
		List<MaskedShingleVector> all88MV = table.getAll88MaskedVector();
		table.sort_by_count_decreasing();
		int count_for_decrement=0;
		boolean mv_with_max_count = false;
		for (MaskedShingleVector mv : all88MV){
			ShingleVector v1 = new ShingleVector();
			v1.copy(mv);
			if(mv.cover(v1) ){
				if(!mv_with_max_count){
					count_for_decrement = mv.getCount();
					mv_with_max_count = true;
				}
				else{
					mv.decrementCount(count_for_decrement);
				}
			}
		}
		table.deleteAllUnderTreshold(20);

		/*Third pass*/

		for(MaskedShingleVector mv: table.getTable_mvectors()){
			Cluster c = new Cluster();
			c.setSignature(mv);
			clusters.add(c);
		}
		for (Pagina p : sample_pages){
			table.sort_by_count_decreasing();
			for(MaskedShingleVector mv : table.getTable_mvectors()){
				if(mv.cover(p.getShingleVector())){
					Cluster c = getClusterFromSignature(mv);
					c.addPage(p);
				}
			}
		}
	}

	public List<Cluster> getClusters() {
		System.out.println("Stampa cluster");
		return clusters;
	}

	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}

	private Cluster getClusterFromSignature(MaskedShingleVector mv){
		for(Cluster c:this.clusters)
			if(c.getSignature().equals(mv))
				return c;
		return null;
	}


}


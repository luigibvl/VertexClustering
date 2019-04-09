package clustering;

import java.util.ArrayList;
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

		/**************************FIRST PASS************************/
		
		HashTable table = new HashTable();
		System.out.println(sample_pages.size());
		for (Pagina p : sample_pages){

			//creazione vettore v
			List<Tag> p_taglist = p.getListaTag();
			p.createShingles(p_taglist);
			ShingleVector v;
			p.createShingleVector();
			v = p.getShingleVector();
			v.createMasks();

			for (MaskedShingleVector mv : v.getAllMV()){
				if (table.contains(mv)!=null){
					MaskedShingleVector insider = table.contains(mv);
					insider.incrementCount();
				}
				else{
					mv.setCount(1);
					table.getTable_mvectors().add(mv);
				}
			}
		}
		/*************************END FIRST PASS********************/
		
		
		/*************************SECOND PASS***********************/
		
		table.sort_by_count_increasing();
		//Lista di tutti i vettori 8/8 in ordine crescente del count
		List<MaskedShingleVector> all88MV = table.getAll88MaskedVector();
		table.sort_by_count_decreasing();
		int count_for_decrement=0;
		MaskedShingleVector v1_max = new MaskedShingleVector();
		boolean mv_with_max_count = false;
		
		//per ogni 8/8 troviamo il masked che lo copre con il maggiore count
		//e diminuaiamo di un valore pari al suo count il count degli altri
		//Masked che coprono l'8/8
		for (MaskedShingleVector mv : all88MV){
			ShingleVector v = new ShingleVector();
			v.copy(mv);
			mv_with_max_count = false;
			for(MaskedShingleVector v1 : table.getTable_mvectors()){
				if(v1.cover(v) ){
					if(!mv_with_max_count){
						count_for_decrement = mv.getCount();
						mv_with_max_count = true;
						v1_max = v1;
					}
					else{
						if(!v1.equals(v1_max))
							v1.decrementCount(count_for_decrement);
					}
				}
			}
		}
		
		//Eliminiamo i Masked con un count al di sotto di una soglia predefinita
		table.deleteAllUnderTreshold(20);
		
		/*************************END SECOND PASS*************************/
		
		
		/*************************THIRD PASS******************************/
		
		//Creiamo un cluster per ogni Masked rimanente in H
		for(MaskedShingleVector mv: table.getTable_mvectors()){
			Cluster c = new Cluster();
			c.setSignature(mv);
			clusters.add(c);
		}
		
		boolean max_cover = false;
		//Per ogni pagina controlliamo quale masked copre il suo shingle vector
		//per poi aggiungere la pagina al cluster associato al masked
		for (Pagina p : sample_pages){
			table.sort_by_count_decreasing();
			max_cover = false;
			for(MaskedShingleVector mv : table.getTable_mvectors()){
				if(mv.cover(p.getShingleVector()) && !max_cover){
					Cluster c = getClusterFromSignature(mv);
					c.addPage(p);
					max_cover = true;
				}
			}
		}
		/*************************END THIRD PASS**************************/
		
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


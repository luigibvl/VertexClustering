package clustering;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import shingle.MaskedShingleVector;
import shingle.ShingleVector;

public class HashTable {

	/* Questa classe serve per istanziare l'hash table H descritta nell'algoritmo
	 * Come chiave è indicato il COUNT del vettore,
	 * Come valore c'è una LISTA di MASKED VECTOR associate a quel count
	 * 
	 * In questo modo troviamo velocemente i vettori con count massimo o minimo, richiesto spesso
	 * nell'algoritmo
	 * 
	 * Tuttavia abbiamo alcuni dubbi sul reperimento dei vettori 8/8
	 * Non è detto che questa sia la struttura dati ottimale
	 */
//	private Map<Integer, List<MaskedShingleVector>> count2masked_vectors;
//	
//	public HashTable(){
//		this.count2masked_vectors = new HashMap<>();
//	}
//	
//	public boolean contains(MaskedShingleVector masked_vector){
//		return this.count2masked_vectors.containsValue(masked_vector);
//	}
//	
//	public void increment_count(MaskedShingleVector masked_vector){
//		//non riesco a trovare un modo per cercare nella mappa il masked_vector del parametro
//		//bisognerebbe scorrere tutte le chiavi della mappa e per ogni chiave cercare dentro la lista associata
//		//lungo e brutto
//	}
	
	//_________________________________________________________________________________________
	
	private List<MaskedShingleVector> table_mvectors;
	
	public HashTable(){
		this.table_mvectors = new ArrayList<MaskedShingleVector>();
	}
	
	public void sort_by_count_increasing(){ 
		this.table_mvectors.sort(Comparator.comparingInt(MaskedShingleVector::getCount));
	}
	
	
	public List<MaskedShingleVector> getTable_mvectors() {
		return table_mvectors;
	}

	public void setTable_mvectors(List<MaskedShingleVector> table_mvectors) {
		this.table_mvectors = table_mvectors;
	}

	public void sort_by_count_decreasing(){ 
		this.table_mvectors.sort(Comparator.comparingInt(MaskedShingleVector::getCount).reversed());
	}
	
	public void order_by_mask(){
		//8/8 -> 7/8 -> 6/8
		//serve?
	}
	
	public MaskedShingleVector contains(MaskedShingleVector mv){
		for(MaskedShingleVector current: this.table_mvectors){
			if(current.equals(mv))
				return current;
		}
		return null;
	}
	
	public boolean insert(MaskedShingleVector mv){
		return this.table_mvectors.add(mv);
	}
	
	public List<MaskedShingleVector> getAll88MaskedVector(){
		return this.table_mvectors.stream()
				.filter(mv -> !mv.containsWildCard())
				.collect(Collectors.toCollection(() -> new ArrayList<MaskedShingleVector>()));
	}
	
	public MaskedShingleVector getFirstMVCovering(ShingleVector vector){
		return this.table_mvectors.stream()
				.filter(mv -> mv.cover(vector))
				.findFirst()
				.orElse(null);
	}
	
	public void delete(MaskedShingleVector mv){
		this.table_mvectors.remove(mv);
	}
	
	public void deleteAllUnderTreshold(int treshold){
		for(Iterator<MaskedShingleVector> iterator = this.table_mvectors.iterator(); iterator.hasNext();){
			MaskedShingleVector mv = iterator.next();
			if(mv.getCount()<treshold)
				iterator.remove();
		}
	}
	
	@Override
	public String toString(){
		String result="";
		for (MaskedShingleVector el : this.table_mvectors){
			result += el.toString() + "\n";
		}
		return result;
	}
}

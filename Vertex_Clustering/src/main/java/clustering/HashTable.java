package clustering;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
//		//bisognerebbe scorrere tutte le chiavi della mappa
//		//lungo e brutto
//	}
	
	//_________________________________________________________________________________________
	
	private List<MaskedShingleVector> table_mvectors;
	
	public HashTable(){
		this.table_mvectors = new ArrayList<MaskedShingleVector>();
	}
	
	public void order_by_count(){
		this.table_mvectors.sort(Comparator.comparingInt(MaskedShingleVector::getCount).reversed());
	}
	
	public void order_by_mask(){
		//8/8 -> 7/8 -> 6/8
		//serve?
	}
	
	public boolean contains(MaskedShingleVector mv){
		return this.table_mvectors.contains(mv);
	}
	
	public boolean insert(MaskedShingleVector mv){
		//mv.setCount(1);
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
		for (MaskedShingleVector mv : this.table_mvectors)
			if(mv.getCount()<treshold)
				this.delete(mv);
	}
}

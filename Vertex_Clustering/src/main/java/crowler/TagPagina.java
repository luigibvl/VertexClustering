package crowler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import clustering.PageClustering;
import shingle.Shingle;
import shingle.ShingleSet;
import shingle.ShingleVector;

public class TagPagina {


	private String strutturaPagina;
	
	public TagPagina(String strutturaPagina) {
		this.strutturaPagina=strutturaPagina;
	}

	public List<String> getListaTag(){
		
		List<String> page = new ArrayList<>();
		String[] tagSplittati = this.strutturaPagina.split("(?<=\\>)");
		page = Arrays.asList(tagSplittati);
		return page;
	}
	
}


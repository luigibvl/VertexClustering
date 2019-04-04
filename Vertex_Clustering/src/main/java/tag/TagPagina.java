package tag;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TagPagina {


	private String strutturaPagina;
	
	public TagPagina(String strutturaPagina) {
		this.strutturaPagina=strutturaPagina;
	}

	public List<String> getLista(){
		
		List<String> page = new ArrayList<>();
		String[] tagSplittati = this.strutturaPagina.split("(?<=\\>)");
		page = Arrays.asList(tagSplittati);
		return page;
	}
	
}


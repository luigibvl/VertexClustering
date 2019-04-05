package tag;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TagPagina {


	private String strutturaPagina;
	
	public TagPagina(String strutturaPagina) {
		this.strutturaPagina=strutturaPagina;
	}

	public List<Tag> getListaTag(){
		List<Tag> page = new ArrayList<>();
		String[] tagSplittati = this.strutturaPagina.split("(?<=\\>)");
		for (int i=0;i<tagSplittati.length;i++){
			Tag tag = new Tag(tagSplittati[i]);
			page.add(tag);
		}
		return page;
	}
	
}


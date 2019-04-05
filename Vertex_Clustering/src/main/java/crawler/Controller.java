package crawler;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import shingle.MaskedShingleVector;
import tag.Pagina;


public class Controller {

	private Document document;
	private String strutturaPagina;


	public Controller(Document document) {
		this.document=document;
		
	}


//	public void init(PrintWriter out) {
//		this.getPaginaStrutturata(out);
//		//this.getShingleSet(this.strutturaPagina);
//	}


	/**
	 * 
	 * @param out utile solo per ora credo (solo il parametro)
	 */
	public String parseHtml() {

		System.out.println(this.document.html()); //OK
		
		Pattern p = Pattern.compile("(</?[a-z]+(\\s[a-z]+=\".*\")*>)");
		Matcher m = p.matcher(this.document.html());

		this.strutturaPagina="";
		while(m.find()) {
			String tag = m.group(0);
			Pattern pattern = Pattern.compile("(\\s[a-z|:]+=\"(.*)\")+");
			Matcher matcher = pattern.matcher(tag);
			String noAttribute = matcher.replaceAll("");
			this.strutturaPagina=this.strutturaPagina.concat(noAttribute);
		}
		System.out.println(this.strutturaPagina);

		Pattern patt = Pattern.compile("(<script.*?</script>)|(<head.*</head>)");
		Matcher match = patt.matcher(this.strutturaPagina);
		String taggo=match.replaceAll("");
		return taggo;
	}


	/**
	 * metodo utile se si vogliono provare i metodi
	 * @param strutturaPagina
	 */

	public void getShingleSet(String strutturaPagina) {

		Pagina tagPagina = new Pagina(strutturaPagina);
		tagPagina.getListaTag();
		tagPagina.createShingleVector();
		tagPagina.getShingleVector().createMasks();

		//serve per stampare i masked per delle prove
		for (Map.Entry<String, List<MaskedShingleVector>> me : 
			tagPagina.getShingleVector().getMasked_vectors().entrySet()) {

			String key = me.getKey();
			List<MaskedShingleVector> valueList = me.getValue();
			System.out.println("Key: " + key);
			System.out.print("Values: ");
			for (MaskedShingleVector s : valueList) {
				System.out.print(s.toString() + " ");
			}
			System.out.println();
		}
	}
}

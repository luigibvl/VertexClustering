package crawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;

public class ParserHtml {

	private Document document;
	private String strutturaPagina;


	public ParserHtml(Document document) {
		this.document=document;
		
	}

	/**
	 * Parse l'html del documento togliendo attributi, head e script
	 * restituendo una stringa che comprende tutti e soli i tag relativi
	 * ad una struttura all'interno della pagina
	 * 
	 */
	public String parseHtml() {

		System.out.println(this.document.html());
		
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
		String parsedHtml=match.replaceAll("");
		return parsedHtml;
	}

}

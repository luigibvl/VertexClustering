package crawler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import clustering.PageClustering;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import shingle.ShingleSet;
import shingle.ShingleVector;
import tag.TagPagina;

public class MyCrawler extends WebCrawler {
	

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
			+ "|png|mp3|mp4|zip|gz))$");



	/**
	 * This method receives two parameters. The first parameter is the page
	 * in which we have discovered this new url and the second parameter is
	 * the new url. You should implement this function to specify whether
	 * the given url should be crawled or not (based on your crawling logic).
	 * In this example, we are instructing the crawler to ignore urls that
	 * have css, js, git, ... extensions and to only accept urls that start
	 * with "https://www.ics.uci.edu/". In this case, we didn't need the
	 * referringPage parameter to make the decision.
	 */

	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches()
				&& href.startsWith("https://www.ics.uci.edu/");
	}

	/**
	 * This function is called when a page is fetched and ready
	 * to be processed by your program.
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
		
			try {
				Document document = Jsoup.connect(url).get();
				
				try (PrintWriter out = new PrintWriter(
						new FileWriter("/test/tags.txt",true))) {

					Pattern p = Pattern.compile("(</?[a-z]+(\\s[a-z]+=\".*\")*>)");
					Matcher m = p.matcher(document.html());
					String strutturaPagina="";
					while(m.find()) {
						String tag = m.group(0);
						Pattern pattern = Pattern.compile("(\\s[a-z]+=\"(.*)\")+");
						Matcher matcher = pattern.matcher(tag);
						String noatt = matcher.replaceAll("");
						strutturaPagina=strutturaPagina.concat(noatt);
					}
					
					
					Pattern patt = Pattern.compile("(<script.*</script>)|(<head.*</head>)");
					Matcher match = patt.matcher(strutturaPagina);
					String taggo=match.replaceAll("");
					out.print(strutturaPagina);
					TagPagina tagPagina = new TagPagina(strutturaPagina);					
					ShingleSet shingleSet = new ShingleSet(tagPagina.getLista());					
					ShingleVector vector = new ShingleVector();
					vector.createShingleVector(shingleSet);
					

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
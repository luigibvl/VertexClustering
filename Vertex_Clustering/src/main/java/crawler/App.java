package crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import clustering.PageClustering;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import shingle.Shingle;
import shingle.ShingleSet;
import shingle.ShingleVector;
import tag.Tag;
import tag.TagPagina;


/**
 * Hello world!
 *
 */
public class App{
	
	public static void main( String[] args ) throws Exception{
		
		String crawlStorageFolder = "/test";
		int numberOfCrawlers = 7;

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);

		// Instantiate the controller for this crawl.
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

		// For each crawl, you need to add some seed urls. These are the first
		// URLs that are fetched and then the crawler starts following links
		// which are found in these pages
		controller.addSeed("https://www.imdb.com");


		// The factory which creates instances of crawlers.
		CrawlController.WebCrawlerFactory<MyCrawler> factory = MyCrawler::new;

		// Start the crawl. This is a blocking operation, meaning that your code
		// will reach the line after this only when crawling is finished.
		controller.start(factory, numberOfCrawlers);



		BufferedReader br = null;
		FileReader fr = null;

		try {
			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("/test/tags.txt");
			br = new BufferedReader(fr);

			String sCurrentLine;
			String tagSequence="";
			while ((sCurrentLine = br.readLine()) != null) {
				tagSequence = tagSequence.concat(sCurrentLine);
				//System.out.println(sCurrentLine);
			}
			
			TagPagina pagina_intera = new TagPagina(tagSequence);
			
//			List<Tag> page_as_taglist = pagina_intera.getLista();
//
//			ShingleSet shingleSet = new ShingleSet(page_as_taglist);
//			//Non serve perché è già chiamato dal costruttore di ShingleSet
//			//shingleSet.createShingles(page_as_taglist);
//			ShingleVector vector = new ShingleVector();
//			vector.createShingleVector(shingleSet);
//			vector.createMasks();
//			
//			System.out.println(vector.getMasked_vectors().toString());
			
			PageClustering pc = new PageClustering();
			pc.algorithm(pagina_intera);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}

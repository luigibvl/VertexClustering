package crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import clustering.Cluster;
import clustering.PageClustering;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import shingle.Shingle;
import shingle.ShingleVector;
import tag.Tag;
import tag.Pagina;


/**
 * Hello world!
 *
 */
public class App{
	
	public static void main( String[] args ) throws Exception{
		
		String crawlStorageFolder = "/Users/alessandrocimmino/Desktop/test";
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
		controller.addSeed("https://www.rottentomatoes.com");


		// The factory which creates instances of crawlers.
		CrawlController.WebCrawlerFactory<MyCrawler> factory = MyCrawler::new;

		// Start the crawl. This is a blocking operation, meaning that your code
		// will reach the line after this only when crawling is finished.
		controller.start(factory, numberOfCrawlers);
		
		//take parsed_html and start clustering
		BufferedReader br = null;
	    FileReader fr = null;

	    try {
	      fr = new FileReader("/Users/alessandrocimmino/Desktop/test/https___www.rottentomatoes.com_.txt");
	      br = new BufferedReader(fr);

	      String sCurrentLine;
	      String tagSequence="";
	      while ((sCurrentLine = br.readLine()) != null) {
	        tagSequence = tagSequence.concat(sCurrentLine);
	      }
	      
	      
	      Pagina page = new Pagina(tagSequence);
	      PageClustering clustering = new PageClustering();
	      List<Pagina> pages = new ArrayList<>();
	      pages.add(page);
	      clustering.algorithm(pages);
	      System.out.println("Stampa cluster");
	      List<Cluster> clusters = clustering.getClusters();
	      System.out.println(clusters.size());
	      for(Cluster c: clusters){
	    	  //System.out.println("Stampa cluster");
	    	  System.out.println(c.toString());
	      }
	      
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

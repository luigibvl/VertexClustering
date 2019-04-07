package crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import clustering.Cluster;
import clustering.PageClustering;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import tag.Pagina;


/**
 * Hello world!
 *
 */
public class App{

	public static void main( String[] args ) throws Exception{

		/*CRAWLER*/
		//		String crawlStorageFolder = "/test";
		//		int numberOfCrawlers = 7;
		//		CrawlConfig config = new CrawlConfig();
		//		config.setCrawlStorageFolder(crawlStorageFolder);
		//
		//		// Instantiate the controller for this crawl.
		//		PageFetcher pageFetcher = new PageFetcher(config);
		//		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		//		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		//		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		//
		//		// For each crawl, you need to add some seed urls. These are the first
		//		// URLs that are fetched and then the crawler starts following links
		//		// which are found in these pages
		//		controller.addSeed("https://www.rottentomatoes.com");
		//
		//
		//		// The factory which creates instances of crawlers.
		//		CrawlController.WebCrawlerFactory<MyCrawler> factory = MyCrawler::new;
		//
		//		// Start the crawl. This is a blocking operation, meaning that your code
		//		// will reach the line after this only when crawling is finished.
		//		controller.start(factory, numberOfCrawlers);

		//Lista delle pagina da clusterizzare
		List<Pagina> pages = new ArrayList<>();
		//		readContent("/test/pages/14.html", pages);
		//		readContent("/test/pages/6.html", pages);

		try (Stream<Path> paths = Files.walk(Paths.get("/test/pages/test"))) {
			paths
			.filter(Files::isRegularFile)
			.filter(p -> p.toString().endsWith(".html"))
			.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					try {
						readContent(filePath.toString(), pages);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			//Inizializzazione del clustering
			PageClustering clustering = new PageClustering();

			System.out.println(pages.size());

			//Inzio clustering
			clustering.algorithm(pages);
			System.out.println("Fine clustering");

			//Stampa dei cluster ottenuti
			List<Cluster> clusters = clustering.getClusters();
			System.out.println(clusters.size());
			for(Cluster c: clusters){
				System.out.println(c.toString());
			}
		}
	}

	public static List<Pagina> readContent(String filePath, List<Pagina> pages) {
		//take parsed_html and start clustering
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String sCurrentLine;
			String tagSequence="";
			while ((sCurrentLine = br.readLine()) != null) {
				tagSequence = tagSequence.concat(sCurrentLine);
			}

			Pagina page = new Pagina(tagSequence);
			pages.add(page);

			if (br != null)
				br.close();
			if (fr != null)
				fr.close();

			return pages;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			return null;
		}
	}

}

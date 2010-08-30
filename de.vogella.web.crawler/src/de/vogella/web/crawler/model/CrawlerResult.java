package de.vogella.web.crawler.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Data structure for a web crawler. Keeps track of the visited sites and keeps
 * a list of sites which needs still to be crawled.
 * 
 * @author Lars Vogel
 * 
 */
public enum  CrawlerResult {
	INSTANCE;
	
	private final List<Webpage> crawledSites = new ArrayList<Webpage>();
	private final List<Webpage> crawlerQueue = new ArrayList<Webpage>();
	private final List<Webpage> allWebpages = new ArrayList<Webpage>();
	private final List<WebpageProblem> error= new ArrayList<WebpageProblem>();

	public void add(Webpage webpage) {
		synchronized (this) {
			if (!allWebpages.contains(webpage)) {
				crawlerQueue.add(webpage);
				allWebpages.add(webpage);
			}
		}
	}

	/**
	 * Get next site to crawl. Can return null (if nothing to crawl)
	 */
	public Webpage next() {
		if (crawlerQueue.size() == 0) {
			return null;
		}
		synchronized (this) {
			// Need to check again if size has changed
			if (crawlerQueue.size() > 0) {
				Webpage page = crawlerQueue.get(0);
				crawlerQueue.remove(0);
				crawledSites.add(page);
				return page;
			}
			return null;
		}
	}

	public boolean hasNext() {
		return crawlerQueue.size() > 0;
	}

	/**
	 * Make defensive copy of the crawled webpage
	 */
	public List<Webpage> getWebpages() {
		List<Webpage> list = allWebpages;
		return list;
	}

	public void addLink(Link absoluteLink) {
		boolean found = false; 
		for (Webpage page: allWebpages){
			if (page.getUrl().equalsIgnoreCase(absoluteLink.getUrl())){
				page.getOutboundLinks().add(absoluteLink);
				found = true; 
			}
		}
		if (!found){
			throw new RuntimeException("Should not happen  " + absoluteLink.getUrl() + " " + absoluteLink.getLink());
		}
	}

	public List<WebpageProblem> getError() {
		return error;
	}
	
	

}

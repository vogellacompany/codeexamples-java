package de.vogella.pagerank.crawler.links;

import java.util.Comparator;

import de.vogella.web.crawler.model.Webpage;

public class LinkComparator implements Comparator<Webpage>{

	@Override
	public int compare(Webpage o1, Webpage o2) {
		if (o1.getInboundLinks().size() >o2.getInboundLinks().size()){
			return -1; 
		} 
		if (o1.getInboundLinks().size() < o2.getInboundLinks().size()){
			return 1; 
		} 
		return 0;
	}
}

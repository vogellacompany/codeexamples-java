package de.vogella.pagerank.crawler.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Collections;

import de.vogella.pagerank.crawler.PageCrawler;
import de.vogella.pagerank.crawler.PageLinkExtractor;
import de.vogella.pagerank.crawler.links.LinkComparator;
import de.vogella.pagerank.crawler.links.LinksUtil;
import de.vogella.web.crawler.checklists.CheckList;
import de.vogella.web.crawler.model.CrawlError;
import de.vogella.web.crawler.model.CrawlerResult;
import de.vogella.web.crawler.model.Link;
import de.vogella.web.crawler.model.Webpage;
import de.vogella.web.crawler.model.WebpageProblem;

public class CrawlVogella {
	private final static String CRAWL_START_PAGE = "http://www.vogella.de";

	// private final static String CRAWL_START_PAGE =
	// "http://www.vogella.de/code/de.vogella.rcp.intro.commands.popup/src/de/vogella/rcp/intro/commands/popup/Activator.html";

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		CrawlerResult webpages = CrawlerResult.INSTANCE;
		CheckList checkList = new CheckList();
		checkList.addWhiteList(new Webpage("http://www.vogella.de"));
		// checkList.addWhiteList(new Webpage("http://www.eclipse.org"));
		checkList.addBlackList(new Webpage("http://www.vogella.de/blog"));
		webpages.add(new Webpage(CRAWL_START_PAGE));

		PageCrawler crawler = new PageCrawler();
		int level = 1;
		while (webpages.hasNext() && level <= 5000)
			try {
				Webpage page = webpages.next();
				extractLinks(webpages, checkList, crawler, page);
				page.setCrawled(true);
				level++;
			} catch (Exception e) {
				e.printStackTrace();
			}

		prepareResult(webpages, level);

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Runtime " + elapsedTime);

	}

	private static void extractLinks(CrawlerResult webpages,
			CheckList checkList, PageCrawler crawler, Webpage page) {
		if (page != null && checkList.check(page)) {
			String pageContent;
			try {
				pageContent = crawler.getPage(page);
				PageLinkExtractor extractedLinks = new PageLinkExtractor();
				Collection<Link> links = extractedLinks.extractAllLinks(
						pageContent, page);
				System.out.println(rightPad("Crawled " + page.getUrl() + "\t",
						80)
						+ " Found " + links.size() + " Links");
				for (Link link : links) {
					Link absoluteLink = LinksUtil.createAbsoluteLink(link);
					Webpage foundWebpage = new Webpage(absoluteLink.getLink());
					webpages.add(foundWebpage);
					page.getOutboundLinks().add(absoluteLink);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				webpages.getError().add(
						new WebpageProblem(page, CrawlError.NOT_FOUND));
			}

		}
	}

	private static void prepareResult(CrawlerResult webpages, int level) {
		for (WebpageProblem problem : webpages.getError()) {
			System.out.println(problem.getError() + " " + problem.getPage());
		}

		for (Webpage page : webpages.getWebpages()) {
			if (page.isCrawled()) {

				if (page.getOutboundLinks().size() <= 0) {
					System.out.println(rightPad(page.getUrl(), 80)
							+ " No outbound links");
				}
			}
		}

		for (Webpage page : webpages.getWebpages()) {
			for (Link link : page.getOutboundLinks()) {
				for (Webpage inboundPage : webpages.getWebpages()) {
					if (inboundPage.getUrl().equals(link.getLink())) {
						inboundPage.getInboundLinks().add(link);
					}
				}
			}
		}

//		for (Webpage page : webpages.getWebpages()) {
//			System.out.println(page.getUrl() + " Inbound "
//					+ page.getInboundLinks().size());
//		}
		
		Collections.sort(webpages.getWebpages(), new LinkComparator() );
		for (int i=0; i<=webpages.getWebpages().size() && i<=100; i++){
			Webpage page = webpages.getWebpages().get(i);
			System.out.println(page.getUrl() + " Inbound "
					+ page.getInboundLinks().size());
		}
//		for (Webpage page : webpages.getWebpages()) {
//			System.out.println(page.getUrl() + " Inbound "
//					+ page.getInboundLinks().size());
//		}

//		for (Webpage page : webpages.getWebpages()) {
//			if (page.isCrawled()) {
//				if (page.getOutboundLinks().size() <= 0) {
//					System.out.println(rightPad(page.getUrl(), 80)
//							+ " No outbound links");
//				}
//			}
//		}
		System.out.println("Crawled Pages " + (level - 1));
		System.out.println("Number of links " + webpages.getWebpages().size());

	}

	public static String rightPad(String s, int width) {
		return String.format("%-" + width + "s", s);
	}

}

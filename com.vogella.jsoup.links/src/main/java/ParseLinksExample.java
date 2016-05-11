import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseLinksExample {

  public static void main(String[] args) {

	Document doc;
	try {

		doc = Jsoup.connect("http://www.vogella.com").get();

		// get title of the page
		String title = doc.title();
		System.out.println("Title: " + title);

		// get all links
		Elements links = doc.select("a[href]");
		for (Element link : links) {

			// get the value from href attribute
			System.out.println("\nLink : " + link.attr("href"));
			System.out.println("Text : " + link.text());
		}

	} catch (IOException e) {
		e.printStackTrace();
	}

  }

}

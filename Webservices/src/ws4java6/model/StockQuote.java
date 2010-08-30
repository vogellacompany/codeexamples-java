package ws4java6.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockQuote {
	public Stocks getYahooQuote(String aSymbol) {
		try {
			aSymbol = aSymbol.toUpperCase();
			URL url = new URL(String.valueOf((new StringBuffer(
					"http://finance.yahoo.com/q?s=")).append(aSymbol).append(
					"&d=v1")));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			System.out.println(br);
			String stopLine = String.valueOf((new StringBuffer(
					"<td nowrap align=left><a href=\"/q?s=")).append(aSymbol)
					.append("&d=t\">").append(aSymbol).append("</a></td>"));
			String line;
			while ((line = br.readLine()) != null) {
				int i = line.indexOf(stopLine);
				if (i > -1) {
					int start = line.indexOf("<td nowrap>");
					int stop = line.indexOf("</td>", start);
					String quote = line.substring(start + 14, stop - 4);
					System.out.println(quote);
					start = line.indexOf("<td nowrap>", stop);
					stop = stop = line.indexOf("</td>", start);
					String pointChange = line.substring(start + 11, stop);
					start = line.indexOf("<td nowrap>", stop);
					stop = stop = line.indexOf("</td>", start);
					String percentChange = line.substring(start + 11, stop);
					Stocks stock = new Stocks(aSymbol, quote, pointChange,
							percentChange);
					return stock;
				}
			}
			System.out.println("Didn't work");
			return new Stocks(aSymbol, "NA", "NA", "NA");
		} catch (Exception e) {
			System.out.println("Didn't work");
			return new Stocks(aSymbol, "NA", "NA", "NA");
		}
	}
}

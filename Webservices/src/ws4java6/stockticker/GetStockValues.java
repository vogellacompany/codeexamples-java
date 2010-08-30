package ws4java6.stockticker;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpException;

import ws4java6.data.IStockTimeSeries;
import ws4java6.data.StockTimeSeries;

public class GetStockValues {

	SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy", Locale.GERMAN);

	NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);

	NumberFormat pf = NumberFormat.getInstance(Locale.GERMAN);

	private final static Logger LOGGER = Logger.getLogger(GetStockValues.class
			.getName());
	private final String SERVERSTART = "http://de.old.finance.yahoo.com/d/quotes.csv?s=";
	private final String SERVEREND = "&f=sl1d1t1c1ohgv&e=.csv";

	public void updateHistory(IStockTimeSeries stock) {
		LOGGER.setLevel(Level.INFO);
		String inputLine;
		StringBuffer url = new StringBuffer(SERVERSTART);
		url.append(stock.getSymbol());
		url.append(SERVEREND);

		ReadStockFromWeb web = new ReadStockFromWeb();
		web.setUrl(url.toString());
		BufferedReader response = null;
		try {
			response = web.read();
		} catch (HttpException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Each line looks like the following
		// Stock symbol; Last Course; Time ; Date; Change; Open Course; Low;
		// High; Volume
		// BAS.DE;98,93;17:35;13/07/2007;+1,82;99,00;99,62;98,02;7100218
		LOGGER.info(response.toString());

		try {
			while ((inputLine = response.readLine()) != null) {
				LOGGER.info(inputLine);
				// item[0] Stock Symbol
				// item[1] Last course
				// item[2] Time
				// item[3] Date
				// item[4] Increase / Decrease
				// item[5] Opening value
				// item[6] Lowest Value
				// item[7] Highest Value
				// item[8] Volume

				String[] item = inputLine.split(";");
				int i = 0;
				for (String string : item) {
					LOGGER.info("Value number " + i + " " + string);
					i++;
				}

				// For now I ignore the time
				Date date;
				try {
					date = new SimpleDateFormat("dd/MM/yyyy").parse(item[3]);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
				// For now I ignore the change
				Double change = 0.0;
				System.out.println(item[4].substring(0, 1));
				System.out.println("+");
				System.out.println(item[4].substring(1));
				String s = item[4].substring(0, 1);
				if (s.equalsIgnoreCase("+")) {
					change = convertStringToDouble(item[4].substring(1));
				}

				if ((item[4].substring(1)).equalsIgnoreCase("-")) {
					change = (-1) * convertStringToDouble(item[4].substring(1));
				}

				Double open = convertStringToDouble(item[5]);
				Double low = convertStringToDouble(item[6]);
				Double high = convertStringToDouble(item[7]);
				// int volume;
				int volume = (Integer.parseInt(item[8].trim()));

				System.out.println(date);
				System.out.println(change);
				System.out.println(open);
				System.out.println(low);
				System.out.println(high);
				System.out.println(volume);

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		GetStockValues stockValues = new GetStockValues();
		StockTimeSeries stock = new StockTimeSeries();
		stock.setSymbol("BASF.de");
		stockValues.updateHistory(stock);
	}

	private Double convertStringToDouble(String s) {

		Locale l = new Locale("de", "DE");
		Locale.setDefault(l);
		NumberFormat nf = NumberFormat.getInstance();
		Double result = 0.0;
		try {
			if (Class.forName("java.lang.Long").isInstance(nf.parse(s))) {
				result = Double.parseDouble(String.valueOf(nf.parse(s)));
			} else {
				result = (Double) nf.parse(new String(s));
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return result;
	}
}

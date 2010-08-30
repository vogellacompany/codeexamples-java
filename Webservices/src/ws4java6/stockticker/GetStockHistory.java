package ws4java6.stockticker;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpException;

import ws4java6.data.IStockData;
import ws4java6.data.IStockTimeSeries;
import ws4java6.data.StockData;
import ws4java6.data.StockTimeSeries;

public class GetStockHistory {

	SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy", Locale.US);

	SimpleDateFormat dfAlt = new SimpleDateFormat("yy-MM-dd");

	private Calendar fromDate = Calendar.getInstance();

	private Calendar toDate = Calendar.getInstance();

	private final static Logger LOGGER = Logger.getLogger(GetStockHistory.class
			.getName());

	private IStockTimeSeries timeSeries = new StockTimeSeries();

	public void setFromDate(int year, int month, int day) {
		this.fromDate.set(year, month, day);
	}

	public void setToDate(int year, int month, int day) {
		this.toDate.set(year, month, day);
	}

	public void updateHistory(IStockTimeSeries stock) {
		LOGGER.setLevel(Level.WARNING);
		StringBuffer url = new StringBuffer(
				"http://ichart.yahoo.com/table.csv?s=");
		String symbol = null;
		symbol = stock.getSymbol();
		url.append(symbol);
		url.append("&d=" + toDate.get(Calendar.MONTH) + "&e="
				+ toDate.get(Calendar.DAY_OF_MONTH) + "&f="
				+ toDate.get(Calendar.YEAR));
		url.append("&g=d");
		url.append("&a=" + fromDate.get(Calendar.MONTH) + "&b="
				+ fromDate.get(Calendar.DAY_OF_MONTH) + "&c="
				+ fromDate.get(Calendar.YEAR));
		url.append("&ignore=.csv");
		LOGGER.info(url.toString());
		try {

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
			// The first line is the header, ignoring
			String inputLine = response.readLine();
			LOGGER.info(inputLine);

			while ((inputLine = response.readLine()) != null) {
				LOGGER.info(inputLine);
				System.out.println(inputLine);
				if (inputLine.startsWith("<"))
					continue;
				String[] item = inputLine.split(",");
				if (item.length < 6)
					continue;
				Calendar day = Calendar.getInstance();
				try {
					day.setTime(df.parse(item[0]));
				} catch (Exception e) {
					try {
						day.setTime(dfAlt.parse(item[0]));
					} catch (Exception e1) {
						LOGGER.severe(e1.getMessage());
						throw new RuntimeException(e1.getMessage());
					}
				}
				day.set(Calendar.HOUR, 0);
				day.set(Calendar.MINUTE, 0);
				day.set(Calendar.SECOND, 0);
				day.set(Calendar.MILLISECOND, 0);

				IStockData data = new StockData();
				data.setDate(day.getTime());
				data.setOpen(Double.parseDouble(item[1].replace(',', '.')));
				data.setHigh(Double.parseDouble(item[2].replace(',', '.')));
				data.setLow(Double.parseDouble(item[3].replace(',', '.')));
				data.setClose(Double.parseDouble(item[4].replace(',', '.')));
				data.setVolume(Long.parseLong(item[5]));
				timeSeries.add(data);
			}
			response.close();

		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	public IStockTimeSeries getTimeSeries() {
		return timeSeries;
	}

	// else
	// log.warn("Intraday data not supported for " + security.getCode() + " - "
	// + security.getDescription());

}

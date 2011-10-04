package de.vogella.birt.stocks.daomock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.vogella.birt.stocks.model.StockData;

public class StockDaoMock {

	public List<StockData> getStockValues(String company) {
		// Ignore the company and always return the data
		// A real implementation would of course use the company string
		List<StockData> history = new ArrayList<StockData>();
		// We fake the values, we will return fake value for 01.01.2009 -
		// 31.01.2009
		double begin = 2.5;
		for (int i = 1; i <= 31; i++) {
			Calendar day = Calendar.getInstance();
			day.set(Calendar.HOUR, 0);
			day.set(Calendar.MINUTE, 0);
			day.set(Calendar.SECOND, 0);
			day.set(Calendar.MILLISECOND, 0);
			day.set(Calendar.YEAR, 2009);
			day.set(Calendar.MONTH, 0);
			day.set(Calendar.DAY_OF_MONTH, i);
			StockData data = new StockData();
			data.setOpen(begin);
			double close = Math.round(begin + Math.random() * begin * 0.1);
			data.setClose(close);
			data.setLow(Math.round(Math.min(begin, begin - Math.random()
					* begin * 0.1)));
			data.setHigh(Math.round(Math.max(begin, close) + Math.random() * 2));
			data.setVolume(1000 + (int) (Math.random() * 500));
			begin = close;
			data.setDate(day.getTime());
			history.add(data);
			System.out.println("Hello");

		}
		return history;
	}
}
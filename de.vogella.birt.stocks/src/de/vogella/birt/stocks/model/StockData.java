package de.vogella.birt.stocks.model;

import java.util.Date;

/**
 * Domain model for stock data
 * @author Lars Vogel
 */

public class StockData {
	private Date date;
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

}
package ws4java6.data;

import java.util.Date;

public interface IStockData {

	/**
	 * Returns the price at the stock market closure
	 * 
	 * @return the close price.
	 */
	public abstract double getClose();

	public abstract void setClose(double close);

	/**
	 * Returns the bar's date and time.
	 * 
	 * @return the date and time.
	 */
	public abstract Date getDate();

	public abstract void setDate(Date date);

	/**
	 * Returns the highest price.
	 * 
	 * @return the highest price.
	 */
	public abstract double getHigh();

	public abstract void setHigh(double high);

	/**
	 * Returns the lowest price.
	 * 
	 * @return the lowest price.
	 */
	public abstract double getLow();

	public abstract void setLow(double low);

	public abstract double getOpen();

	public abstract void setOpen(double open);

	public abstract long getVolume();

	public abstract void setVolume(long volume);

}
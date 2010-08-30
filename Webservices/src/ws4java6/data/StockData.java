package ws4java6.data;

import java.util.Date;


/**
 * Hold the data for a stock for a certain date. The following data is captured: -
 * The opening value of the stock on this date - The closing value of the stock
 * on this date - The highest value of the stock on this date - The lowest value
 * of the stock on this date - The volume of the stock which was traded on this
 * date
 * 
 * 
 * @author Lars Vogel
 * @since 0.1
 */
public class StockData implements IStockData {
	Date date;
	double open = 0;
	double high = 0;
	double low = 0;
	double close = 0;
	long volume = 0;

	public StockData() {

	}

	/* (non-Javadoc)
	 * @see ws4java6.stockticker.IStockData#getClose()
	 */
	public double getClose() {
		return close;
	}

	/* (non-Javadoc)
	 * @see ws4java6.stockticker.IStockData#setClose(double)
	 */
	public void setClose(double close) {
		this.close = close;
	}

	/* (non-Javadoc)
	 * @see ws4java6.stockticker.IStockData#getDate()
	 */
	public Date getDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see ws4java6.stockticker.IStockData#setDate(java.util.Date)
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/* (non-Javadoc)
	 * @see ws4java6.stockticker.IStockData#getHigh()
	 */
	public double getHigh() {
		return high;
	}

	/* (non-Javadoc)
	 * @see ws4java6.stockticker.IStockData#setHigh(double)
	 */
	public void setHigh(double high) {
		this.high = high;
	}

	/* (non-Javadoc)
	 * @see ws4java6.stockticker.IStockData#getLow()
	 */
	public double getLow() {
		return low;
	}

	/* (non-Javadoc)
	 * @see ws4java6.stockticker.IStockData#setLow(double)
	 */
	public void setLow(double low) {
		this.low = low;
	}

	/* (non-Javadoc)
	 * @see ws4java6.stockticker.IStockData#getOpen()
	 */
	public double getOpen() {
		return open;
	}

	/* (non-Javadoc)
	 * @see ws4java6.stockticker.IStockData#setOpen(double)
	 */
	public void setOpen(double open) {
		this.open = open;
	}

	/* (non-Javadoc)
	 * @see ws4java6.stockticker.IStockData#getVolume()
	 */
	public long getVolume() {
		return volume;
	}

	/* (non-Javadoc)
	 * @see ws4java6.stockticker.IStockData#setVolume(long)
	 */
	public void setVolume(long volume) {
		this.volume = volume;
	}

}

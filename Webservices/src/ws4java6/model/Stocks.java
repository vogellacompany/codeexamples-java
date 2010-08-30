package ws4java6.model;

public class Stocks {

	public String getSymbol() {
		return mSymbol;
	}

	public String getQuote() {
		return mQuote;
	}

	public String getPointChange() {
		return mPointChange;
	}

	public String getPercentChange() {
		return mPercentChange;
	}

	public Stocks(String aSymbol, String aQuote, String aPointChange,
			String aPercentChange) {
		mSymbol = aSymbol;
		mQuote = aQuote;
		mPointChange = aPointChange;
		mPercentChange = aPercentChange;
	}

	private String mSymbol;
	private String mQuote;
	private String mPointChange;
	private String mPercentChange;

	public static void main(String[] args) {
		StockQuote lars = new StockQuote();
		lars.getYahooQuote("SAP.DE");
	}

}

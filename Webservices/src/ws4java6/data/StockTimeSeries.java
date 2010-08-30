package ws4java6.data;

import java.util.ArrayList;

public class StockTimeSeries implements IStockTimeSeries {
	private String symbol;
	private ArrayList<IStockData> stockHistory = new ArrayList<IStockData>();

	public void add(IStockData data) {
		stockHistory.add(data);
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int size() {
		return stockHistory.size();
	}

	public IStockData getStock(int i) {
		return stockHistory.get(i);
	}

}

package ws4java6.data;

public interface IStockTimeSeries {

	public abstract String getSymbol();

	public abstract void setSymbol(String symbol);

	public abstract void add(IStockData data);

	public abstract int size();

	public abstract IStockData getStock(int i);
}
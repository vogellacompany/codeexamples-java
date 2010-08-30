package ws4java6.main;

import ws4java6.data.StockTimeSeries;
import ws4java6.gui.MyModel;
import ws4java6.gui.View;
import ws4java6.stockticker.GetStockHistory;

public class Main {
	public static void main(String[] args) {
		StockTimeSeries lars = new StockTimeSeries();
		lars.setSymbol("BAS.DE");
		GetStockHistory history = new GetStockHistory();
		history.setFromDate(2003, 01, 01);
		history.setToDate(2007, 01, 01);
		history.updateHistory(lars);
		MyModel model = new MyModel();
		model.setModel(history.getTimeSeries());
		View view = new View();
		view.setModel(model);
		view.setVisible(true);

	}
}

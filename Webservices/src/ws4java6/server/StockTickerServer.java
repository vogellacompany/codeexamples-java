package ws4java6.server;

import javax.xml.ws.Endpoint;

import ws4java6.model.StockTicker;

public class StockTickerServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StockTicker impl = new StockTicker();
		// Create and publish the services on TCP port 8080
		// with the name stockTicker
		Endpoint endpoint = Endpoint.publish(
				"http://localhost:8080/stockTicker", impl);
	}
}

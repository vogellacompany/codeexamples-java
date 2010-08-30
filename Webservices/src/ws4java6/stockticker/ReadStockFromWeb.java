package ws4java6.stockticker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class ReadStockFromWeb {
	private final static Logger LOGGER = Logger
			.getLogger(ReadStockFromWeb.class.getName());
	private String url;

	public BufferedReader read() throws HttpException, IOException {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams()
				.setConnectionTimeout(5000);

		LOGGER.info("Connecting to the following website: " + url);
		HttpMethod method = new GetMethod(url);
		method.setFollowRedirects(true);

		client.executeMethod(method);

		LOGGER.info(method.getResponseBodyAsStream().toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(method
				.getResponseBodyAsStream()));
		return in;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

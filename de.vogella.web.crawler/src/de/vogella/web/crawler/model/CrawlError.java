package de.vogella.web.crawler.model;

public enum CrawlError {
	NOT_FOUND ("HTML return code 404 - Resource not found");
	private String errorText; 
	CrawlError(String s){
		errorText = s;
	}
	@Override
	public String toString() {
		return errorText;
	}
}

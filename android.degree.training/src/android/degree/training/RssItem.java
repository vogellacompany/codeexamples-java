package android.degree.training;

import test.test.test1.R;

public class RssItem {
	private String pubDate;
	private String lallala;

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getLallala() {
		return lallala;
	}

	public void setLallala(String lallala) {
		this.lallala = lallala;
	}

	@Override
	public String toString() {
		return "RssItem [pubDate=" + pubDate + ", lallala=" + lallala + "]";
	}

}

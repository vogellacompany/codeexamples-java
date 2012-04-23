package android.training.frankfurt;

public class RssItem {
	private String pubDate;
	private String description;

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RssItem [pubDate=" + pubDate + ", description=" + description
				+ "]";
	}

}

package com.fromdev.automation.feed.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Feed {
	private String title = "";
	private String appBaseUrl = "";
	private String appFeedUrl = "";
	private List<ShareableItem> items = new ArrayList<ShareableItem>();
	private int maxItems = 1000;

	public Feed(String title, String appBaseUrl, String appFeedUrl) {
		this.title = title;
		this.appBaseUrl = appBaseUrl;
		this.appFeedUrl = appFeedUrl;
	}

	public Feed(String title, String appBaseUrl, String appFeedUrl, int maxItems) {
		this(title, appBaseUrl, appFeedUrl);
		this.maxItems = maxItems;
	}

	public boolean addItem(ShareableItem item) {
		boolean success = false;
		if (!items.contains(item)) {
			if (items.size() >= maxItems) {
				items.remove(0);
			}
			items.add(item);
			success = true;

		}
		return success;
	}

	public String toRss(int itemNumber) {
		StringBuilder rss = new StringBuilder();
		rss.append(rssPrefix());
		if (itemNumber < items.size()) {
			ShareableItem item = items.get(itemNumber);
			if (item != null) {
				rss.append(item.toRssItem());
			}
		}
		rss.append(rssSuffix());
		return rss.toString();
	}

	public String toRss() {
		StringBuilder rss = new StringBuilder();
		rss.append(rssPrefix());
		rss.append(itemsToRss());
		rss.append(rssSuffix());
		return rss.toString();
	}

	public String randomItemToRss() {
		StringBuilder rss = new StringBuilder();
		rss.append(rssPrefix());
		ShareableItem randomItem = getRandomItem();
		if (randomItem != null) {
			rss.append(randomItem.toRssItem());
		}
		rss.append(rssSuffix());
		return rss.toString();
	}

	private String itemsToRss() {
		StringBuilder itemsRssBuilder = new StringBuilder();
		for (Iterator<ShareableItem> iterator = items.iterator(); iterator
				.hasNext();) {
			ShareableItem item = iterator.next();
			itemsRssBuilder.append(item.toRssItem());
			itemsRssBuilder.append("\n");
		}
		return itemsRssBuilder.toString();
	}

	public ShareableItem getRandomItem() {
		if (items.size() > 1) {
			int random = getRandomInRange();
			return items.get(random);
		}
		return null;
	}

	private int getRandomInRange() {
		return (int) (Math.random() * (items.size() - 1));
	}

	private String rssPrefix() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<rss version=\"2.0\"\n    xmlns:content=\"http://purl.org/rss/1.0/modules/content/\"\n    xmlns:wfw=\"http://wellformedweb.org/CommentAPI/\"\n    xmlns:dc=\"http://purl.org/dc/elements/1.1/\"\n    xmlns:atom=\"http://www.w3.org/2005/Atom\"\n>\n\n<channel>\n    <title>"
				+ title
				+ "</title>\n    <link>"
				+ appBaseUrl
				+ "</link>\n    <atom:link href=\""
				+ appFeedUrl
				+ "\" rel=\"self\" type=\"application/rss+xml\" />\n    <description>"
				+ title + "</description>\n    <language>en</language>";
	}

	private String rssSuffix() {
		return "</channel>\n</rss>";
	}
}

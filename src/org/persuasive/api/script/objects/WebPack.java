package org.persuasive.api.script.objects;

import java.io.IOException;

public class WebPack {
	private String faq;
	private String forum;
	private boolean connection = false;

	public WebPack(String faq, String forum, boolean connection) {

		this.faq = faq;
		this.connection = connection;
		this.forum = forum;
	}

	private String readWeb(String site) {
		try {
			return Web.readWebsite(site);
		} catch (IOException e) {
			return "Error reading: " + site;
		}

	}

	public void openForum() {
		try {
			Web.openWebsite(forum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getForum() {
		return forum;
	}

	public String getFAQ() {
		if (!connection) {
			return faq;
		}
		return readWeb(faq);
	}

}

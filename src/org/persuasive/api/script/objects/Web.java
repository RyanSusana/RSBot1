package org.persuasive.api.script.objects;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Web {
	public static void openWebsite(String url) throws IOException {
		Desktop.getDesktop().browse(java.net.URI.create(url));
	}

	public static String readWebsite(String url) throws IOException {
		String start = "";
		URL web = new URL(url);
		URLConnection yc = web.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			start = start + inputLine+"\n";

		}
		in.close();
		return start;
	}
}

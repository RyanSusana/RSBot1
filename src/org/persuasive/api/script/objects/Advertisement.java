package org.persuasive.api.script.objects;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public enum Advertisement {
	
	ADVERTISEMENT("http://aescripts.comuv.com/advertisements/AdvertAdvert.png","http://www.powerbot.org/community/topic/1052269-aeadvertisingfree-for-testing/"),
	KEZM0N("http://imageshack.us/a/img7/7752/bh54.png","http://www.powerbot.org/community/topic/1049762-%E2%99%95-07-eoc-%E2%98%85-dedicated-questing-syndicate-%E2%98%85-fast-%E2%98%85-extremely-cheap-%E2%98%85-professional-%E2%98%85-sponsor-90-fb-%E2%99%95/");
	
	String url = null;
	String imgUrl = null;


	Advertisement(String img, String url) {
		imgUrl = img;
		this.url = url;
	}

	public static Advertisement getRandom() {
		return values()[(int) (Math.random() * values().length)];
	}

	public  Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	public String getLink() {
		return url;
	}
}

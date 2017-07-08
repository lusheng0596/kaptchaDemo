package kaptcha.vo;

import java.awt.image.BufferedImage;

public class KeyBufferImage{
	private String key;
	private BufferedImage image;
	public KeyBufferImage(String key,BufferedImage image) {
		this.image=image;
		this.key=key;
	}
	public String getKey() {
		return this.key;
	}
	public BufferedImage getImage() {
		return this.image;
	}
}

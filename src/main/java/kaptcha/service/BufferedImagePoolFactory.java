package kaptcha.service;

import java.awt.image.BufferedImage;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.kaptcha.Producer;

import kaptcha.vo.KeyBufferImage;
@Component
public class BufferedImagePoolFactory extends BasePoolableObjectFactory<KeyBufferImage>{
	@Autowired
	private Producer captchaProducer;
	@Override
	public KeyBufferImage makeObject() throws Exception {
		String capText = captchaProducer.createText();
		BufferedImage bi = captchaProducer.createImage(capText);
		KeyBufferImage keyImage = new KeyBufferImage(capText,bi);
		return keyImage;
	}

}

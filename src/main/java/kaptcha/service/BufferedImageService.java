package kaptcha.service;

import javax.annotation.PostConstruct;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kaptcha.vo.KeyBufferImage;

@Service
public class BufferedImageService {
	private  GenericObjectPool<KeyBufferImage> imagePool;
	@Autowired
	private BufferedImagePoolFactory poolFactory;
	public KeyBufferImage getOneImage() {
		try {
			return imagePool.borrowObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void returnObj(KeyBufferImage image) {
		try {
			imagePool.returnObject(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@PostConstruct
	public void initImagePool() {
		imagePool = new GenericObjectPool<>(poolFactory);
		imagePool.setLifo(false);
		for(int i=0;i<30;i++) {
			try {
				imagePool.addObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

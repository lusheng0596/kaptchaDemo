package kaptcha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kaptcha.service.BufferedImageService;
import kaptcha.vo.KeyBufferImage;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = KaptchaApplication.class)
@SpringBootTest(classes = KaptchaApplication.class)
public class KaptchaTest {
	@Autowired
	BufferedImageService service;
	@Test
	public void test() {
		KeyBufferImage oneImage = service.getOneImage();
		System.out.println(oneImage.getKey());
	}
}

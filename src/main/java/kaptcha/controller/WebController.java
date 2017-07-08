package kaptcha.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kaptcha.service.BufferedImageService;
import kaptcha.vo.KeyBufferImage;

@Controller
@RequestMapping("/kaptcha/")
public class WebController {
	@Autowired
	private BufferedImageService imageService;

	@RequestMapping("getImg")
	public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		HttpSession session = request.getSession();
//		String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
//		System.out.println("******************验证码是: " + code + "******************");
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
		KeyBufferImage oneImage = imageService.getOneImage();
//		String capText = oneImage.getKey();
		// store the text in the session
//		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		// create the image with the text
		BufferedImage bi = oneImage.getImage();
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
			System.out.println(oneImage);
			imageService.returnObj(oneImage);
		}
		return null;
	}

}

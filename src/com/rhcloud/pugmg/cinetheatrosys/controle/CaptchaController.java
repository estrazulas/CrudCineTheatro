package com.rhcloud.pugmg.cinetheatrosys.controle;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.multitype.MultiTypeCaptchaService;
import com.rhcloud.pugmg.cinetheatrosys.modelo.auxiliares.UsuarioWeb;

/**
 * Classe para apresentar o captcha nos formulários abertos
 * que possam vir a utilizar este controle
 * @author Daniel Severo Estrázulas
 *
 */
@Resource
public class CaptchaController {
	
	public static final String CAPTCHA_IMAGE_FORMAT = "jpeg";

	@Autowired
	private MultiTypeCaptchaService captchaService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private Result result;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private UsuarioWeb usuarioWeb;
	
	public CaptchaController(){
		
	}
	
	
	/**
	 * O controle de captcha será acessado por /captcha, ai é gerada imagem
	 * randomica
	 * @return a imagem para o html
	 * @throws Exception
	 */
	@Path("/captcha")
	public InputStreamDownload captcha() throws Exception {
		// the output stream to render the captcha image as jpeg into
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
	

		ByteArrayInputStream in =null;
		try {

			String captchaId = request.getSession().getId();
			BufferedImage challenge = captchaService.getImageChallengeForID(
					captchaId, request.getLocale());

			ImageIO.write(challenge, CAPTCHA_IMAGE_FORMAT, jpegOutputStream);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (CaptchaServiceException e) {
			e.printStackTrace();
		}
		in = new ByteArrayInputStream(jpegOutputStream.toByteArray());
		
		InputStreamDownload isd = new InputStreamDownload(in, MediaType.IMAGE_JPEG_VALUE,
				"captcha.jpeg", true, jpegOutputStream.toByteArray().length);

		return isd;
	}
	
}
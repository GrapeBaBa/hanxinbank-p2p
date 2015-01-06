package com.hanxinbank.p2p.web.captcha.resource;

import com.google.code.kaptcha.Producer;
import com.hanxinbank.p2p.core.crypto.HmacSHA256;
import com.hanxinbank.p2p.web.captcha.CaptchaGenerationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import static com.hanxinbank.p2p.core.user.validator.CaptchaValidator.CAPTCHA_KEY;
import static com.hanxinbank.p2p.core.common.Cookies.*;

@Component
@Path("/captcha")
public class CaptchaResource {

    private static Logger logger = LoggerFactory.getLogger(CaptchaResource.class);

    @Autowired
    private Producer captchaProducer;

    @Value("{auth.secretkey}")
    private String secretKey;

    @GET
    @Produces("image/jpeg")
    public Response generateCaptcha(@Context HttpServletRequest request,
                                    @Context HttpServletResponse response) {
        String capText = captchaProducer.createText();
        BufferedImage bi = captchaProducer.createImage(capText);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "jpg", out);
        } catch (IOException e) {
            logger.error("Write captcha image exception", e);
            throw new CaptchaGenerationException("Write captcha image exception");
        }
        String encryptedCaptchaText = HmacSHA256.encrypt(capText.toLowerCase() + secretKey, secretKey);
        response.addCookie(toCookie(CAPTCHA_KEY, encryptedCaptchaText, Optional.ofNullable(request.getContextPath())));
        return Response.ok(out.toByteArray()).build();
    }

}
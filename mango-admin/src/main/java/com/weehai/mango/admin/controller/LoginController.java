package com.weehai.mango.admin.controller;

import com.alibaba.druid.util.FnvHash;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.poi.util.IOUtils;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.ch.IOUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author 卢水柏
 * @company WeeHai
 * @date 2019/12/27 11:53 下午
 **/
@RestController
public class LoginController {
    @Autowired
    private Producer producer;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response,HttpServletRequest request)
            throws SerialException, IOException{
        response.setHeader("Cache-Control","no-store,no-cache");
        response.setContentType("image/jpeg");
        String text=producer.createText();
        BufferedImage image = producer.createImage(text);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,text);

        ServletOutputStream out=response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        IOUtils.closeQuietly(out);

    }
}

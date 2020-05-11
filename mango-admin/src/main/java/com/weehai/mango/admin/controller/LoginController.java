package com.weehai.mango.admin.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.weehai.mango.admin.model.User;
import com.weehai.mango.admin.security.JwtAuthenticatioToken;
import com.weehai.mango.admin.service.UserService;
import com.weehai.mango.admin.util.PasswordUtils;
import com.weehai.mango.admin.util.SecurityUtils;
import com.weehai.mango.admin.vo.LoginBean;
import com.weehai.mango.core.http.HttpResult;

/**
 * @author 卢水柏
 * @company WeeHai
 * @date 2019/12/27 11:53 下午
 **/
@RestController
public class LoginController {
    @Autowired
    private Producer producer;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/captcha.jpg")
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
    /**
     * 登陆
     */
    @PostMapping(value="/login")
    public HttpResult login(@RequestBody LoginBean loginBean,HttpServletRequest request)
        throws IOException{
        String username=loginBean.getUsername();
        String password=loginBean.getPassword();
        String captcha=loginBean.getCaptcha();
        // 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(kaptcha == null){
            return HttpResult.error("验证码已失效");
        }
        if(!captcha.equals(kaptcha)){
            return HttpResult.error("验证码不正确");
        }
        // 用户信息
        User user = userService.findByName(username);
        // 账号不存在、密码错误
        if (user == null) {
            return HttpResult.error("账号不存在");
        }
        if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
            return HttpResult.error("密码不正确");
        }
        // 账号锁定
        if (user.getStatus() == 0) {
            return HttpResult.error("账号已被锁定,请联系管理员");
        }
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
        return HttpResult.ok(token);
    }

}

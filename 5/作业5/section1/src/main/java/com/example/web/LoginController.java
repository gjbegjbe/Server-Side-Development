package com.example.web;

import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login.html")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, @Valid LoginInfo loginInfo, Errors errors) {
        if(errors.hasErrors()){
            System.out.println("用户名必须是3-6位，口令必须是6位");
            return new ModelAndView("login","error","用户名必须是3-6位，口令必须是6位");
        }
        boolean isValidUser =
                userService.hasMatchUser(loginInfo.getUserName(),
                        loginInfo.getPassword());
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误。");
        } else {
            User user = userService.findUserByUserName(loginInfo
                    .getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.saveLog(user);
            request.getSession().setAttribute("t_user", user);
            return new ModelAndView("main");
        }
    }
}

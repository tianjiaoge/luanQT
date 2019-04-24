package com.luan.controller;

import com.luan.model.User;
import com.luan.service.UserService;
import com.luan.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public  String  index(){
        return  "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(User fromUser){
        userService.findUserByusername(fromUser.getUsername());
        return userService.userLogin(fromUser);
    }

    @RequestMapping("/main")
    public  String main(){
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(){
        SessionUtils.invalidate();
        return "redirect:index.do";
    }
}

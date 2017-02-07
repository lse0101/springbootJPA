package com.crazysalaryman.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lse0101 on 2017-02-06.
 */
@Controller
public class LoginController {

    @RequestMapping("loginForm")
    String loginForm() {
        return "loginForm";
    }
}

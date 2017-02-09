package com.crazysalaryman.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lse0101 on 2017-02-08.
 */

@RestController
public class HelloWorldController {

    @RequestMapping("Hello")
    public String hello(){
        return "HelloWorld";
    }
}

package com.crazysalaryman.web;

import com.crazysalaryman.domain.Customer;
import com.crazysalaryman.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by lse0101 on 2017-02-02.
 */
@Controller
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    String list(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        System.out.println("alksdjflkajsdflkafjsd");
        return "customers/list";
    }
}

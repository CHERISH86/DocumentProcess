package com.haier.documentprocess.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cherish
 * @date 2020/8/22
 * @description
 */
@RestController
public class testController {
    @GetMapping("/hello")
    public String hello(){
        return "牛批";
    }
}

package com.example.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/api")
public class ApiController {
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String coinRetry(@RequestParam String param) {
        return "Hello Guagua " + param;
    }
}

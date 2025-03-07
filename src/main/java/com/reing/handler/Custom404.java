package com.reing.handler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Custom404 implements ErrorController {

    @RequestMapping("/error")
    public String getErrorPath() {
        return "/templates/404.xhtml";
    }
}

package org.scsmksn.npl.auction.web.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericWebController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
public class CommonController extends GenericWebController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/contact-us")
    public String contactUs() {
        return "contact-us";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "about-us";
    }
}

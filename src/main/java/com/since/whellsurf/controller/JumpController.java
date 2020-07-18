package com.since.whellsurf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author luoxinyuan
 */
@Controller
@RequestMapping("/to")
public class JumpController {
    @RequestMapping("/activity")
    public String about(){
        return "/activity/index";
    }

    @RequestMapping("/addAward")
    public String attention(){
        return "/add_award/index";
    }

    @RequestMapping("/awardInfo")
    public String coperation(){
        return "/award_infor/index";
    }

    @RequestMapping("/createActivity")
    public String error(){
        return "/create_activity/index";
    }

    @RequestMapping("/findAward")
    public String push(){
        return "/find_award/index";
    }

    @RequestMapping("/index")
    public String user(){
        return "/index/index";
    }

    @RequestMapping("/lottery")
    public String test(){
        return "/lottery/index";
    }

    @RequestMapping("/share")
    public String buy(){
        return "/share/index";
    }

}

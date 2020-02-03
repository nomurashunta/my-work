package com.example.OAuthApp.controller;

import com.example.OAuthApp.Util;
//import com.example.apiApp.utils.Utilities;
import com.example.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@Validated
public class SummaryViewController {


    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public String summary(Model model) {
        model.addAttribute("test", "te");
        return "summary";
    }

    @RequestMapping(value = "/summary2")
    public String summary2(Model model,
                           @Valid @DateTimeFormat @RequestParam("dayBefore") String before,
                           @Valid @DateTimeFormat @RequestParam("dayAfter") String after) {
        System.out.println("aaaa" + Util.convertToLocalDate(before, "yyyy/MM/dd"));
        System.out.println("bbbb" + Util.convertToLocalDate(after, "yyyy/MM/dd"));
//        System.out.println("date" + before);
//        System.out.println("date2" + after);
        Util.convertToLocalDate("", "");
        //model.addAttribute("test", summaryService.getSummary());
        return "summary";
    }

//    @RequestMapping(value = "/summary2")
//    public String summary2(Model model,
//                           @RequestParam("date") String str) {
//        System.out.println("date" + str);
//        System.out.println("dateaaaa");
//        return "redirect:/top";
//    }

}

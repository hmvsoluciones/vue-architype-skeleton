package com.leroy.ronan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView home(){
        return new ModelAndView(new RedirectView("/index.html"));
    }

}

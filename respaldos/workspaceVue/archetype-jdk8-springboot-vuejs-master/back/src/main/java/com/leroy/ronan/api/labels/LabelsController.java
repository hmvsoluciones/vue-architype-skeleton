package com.leroy.ronan.api.labels;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/labels")
public class LabelsController {

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody Labels getLabels() {
        return new Labels();
    }

}

package org.launchcode.questionquestin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("map")
public class MapController {

    @GetMapping("")
    public String overworld(){
        return "playerQuiz/overworld";
    }

    @GetMapping("testLocation")
    public String testLocation(){
        return "playerQuiz/mapLocations/testLocation";
    }
}

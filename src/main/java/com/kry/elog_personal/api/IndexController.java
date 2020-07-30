package com.kry.elog_personal.api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
@RequiredArgsConstructor
@RestController
public class IndexController {
    private final HttpSession httpSession;

    @GetMapping(value={ "/" })
    public String index(RedirectAttributes redirectAttributes) {

        return httpSession.getAttribute("user").toString();
    }


}

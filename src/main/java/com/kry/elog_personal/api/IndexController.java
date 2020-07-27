package com.kry.elog_personal.api;

import com.kry.elog_personal.config.auth.SessionUser;
import com.kry.elog_personal.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
@RequiredArgsConstructor
@Controller
@RequestMapping(path="/api")
public class IndexController {
    private final HttpSession httpSession;
    @GetMapping(value={"", "/", "/error", "/*"})
    public String index() {
        return "index";
    }


    @GetMapping("/")
    public String index(Model model) throws Exception {
        SessionUser user= (SessionUser) httpSession.getAttribute("user");

       Object principal = SecurityContextHolder.getContext().toString();
       model.addAttribute("now",principal.toString());

        if(user!=null){
            model.addAttribute("SessionInfo",user.toString());
            model.addAttribute("userNamee", user.getClass().toString());
            model.addAttribute("userEmail", user.getEmail());
            model.addAttribute("userPicture", user.getPicture());
        }
        return "index";
    }
    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

}

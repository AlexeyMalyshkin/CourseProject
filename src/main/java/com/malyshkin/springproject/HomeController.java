package com.malyshkin.springproject;

import com.malyshkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "login";
    }

    @RequestMapping(value = "/403")
    public String accessDenied(){
        return "403";
    }

    @RequestMapping(value ="/user/hello")
    public String hello1(){
        return "user/hello";
    }

//    @RequestMapping(value = "select")
//    public String select2(){
//        return "test";
//    }

    @RequestMapping(value="/admin**")
//    @PreAuthorize("hasRole(ROLE_USER)")
    public String adminTest(){
        return "admin/edit";
    }

    @RequestMapping(value = "/select")
    public String select(Model model, HttpSession session) {
           System.out.println("Select");

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String login = authentication.getName();

//        User user = userService.findByLogin(login);
//
//        if (user.getRole().getName().equals("ROLE_ADMIN")) {
//            //
//            model.addAttribute("userWrapper",
//                    (new AdminController().wrap(null)));
//            //
//            session.setAttribute("user", user);
//            model.addAttribute("user", user);
//            return new ModelAndView("admin/admin", "users",
//            /* userService.findAll() */null);
//        } else {
//            return new ModelAndView("user/hello", "user", user);
//        }

        return "user/hello";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {

        model.addAttribute("error", "true");
        return "test";

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {

        return "test";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model, HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }
}

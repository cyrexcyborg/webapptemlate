package ru.sareth.webapp.controller.frontend;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.sareth.webapp.domain.User;
import ru.sareth.webapp.domain.UserRepository;

import javax.servlet.http.HttpSession;


/**
 * Created by DZorin on 06.11.2016.
 * OATTeam
 */
@Controller
public class MainController {

    private final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = {"/home","/"})
    public String getHome(ModelMap model) {
        LOGGER.info("Home opened");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        if (principal instanceof User) {
            String username = ((User)principal).getUserName();
            user = userRepository.findByUserName(username);
        }
        model.addAttribute("profile", user);
        return "home";
    }

    @RequestMapping(value = "/profile")
    public String getProfile(ModelMap model) {
        LOGGER.info("Profile opened");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        if (principal instanceof User) {
            String username = ((User)principal).getUserName();
            user = userRepository.findByUserName(username);
        }
        model.addAttribute("profile", user);
        return "profile";
    }

    @RequestMapping(value = "/login")
    public String getLogin(HttpSession mv) {
        LOGGER.info("Login page opened");
        return "login";
    }

    @RequestMapping(value = "/403")
    public String get403(HttpSession mv) {
        LOGGER.info("Error page opened");
        return "403";
    }


}

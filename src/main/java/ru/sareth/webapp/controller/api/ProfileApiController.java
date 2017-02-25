package ru.sareth.webapp.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sareth.webapp.domain.User;
import ru.sareth.webapp.domain.UserRepository;
import ru.sareth.webapp.util.Ajax;

import java.util.Map;

/**
 * Created by DZorin on 05.12.2016.
 * OATTeam
 */
@RestController
@RequestMapping(path = "/api/profile/")
public class ProfileApiController {
    private static final Logger LOG = LoggerFactory.getLogger(ProfileApiController.class);

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "change/", method = RequestMethod.POST)
    private Map changeProfile(@RequestBody User user) {
        User current = userRepository.findByUserName(user.getUserName());
        current.setAbout(user.getAbout());
        current.setFullName(user.getFullName());
        current.setTelegramId(user.getTelegramId());
        if(user.getPassword()!= null && !user.getPassword().equals("")) current.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(current);
        return Ajax.successResponse("Success");
    }
}

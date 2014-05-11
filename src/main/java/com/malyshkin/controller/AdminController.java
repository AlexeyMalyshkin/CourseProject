package com.malyshkin.controller;

import com.malyshkin.entity.User;
import com.malyshkin.service.RoleService;
import com.malyshkin.service.UserService;
import com.malyshkin.wrapper.ErrorMessage;
import com.malyshkin.wrapper.UserWrapper;
import com.malyshkin.wrapper.ValidationResponse;
import jj.play.ns.nl.captcha.Captcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    private static final Logger logger = LoggerFactory
            .getLogger(AdminController.class);

    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_SIGNUP = "signUp";

    private String answer = "";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private Validator userWrapperValidator;

    @RequestMapping(value = "/addUser.ajax")
    public @ResponseBody
    ValidationResponse addUserAjax(@Valid UserWrapper userWrapper,
            BindingResult result) {
        ValidationResponse validationResponse = new ValidationResponse();

        userWrapperValidator.validate(userWrapper, result);

        logger.info("errors count:" + result.getErrorCount());
        logger.info("another:" + result.getFieldErrorCount());
        for (ObjectError e : result.getAllErrors()) {
            logger.info(e.getDefaultMessage());
        }

        for (FieldError e : result.getFieldErrors()) {
            logger.info(e.getDefaultMessage());
        }

//        if (!result.hasErrors()) {
//            if (userWrapper.getCommandName().equals(COMMAND_ADD)) {
//                userService.create(unwrap(userWrapper));
//            } else {
//                User user = unwrap(userWrapper);
//                user.setId(userService.findByLogin(user.getLogin()).getId());
//                userService.update(user);
//            }
//
//            return validationResponse;
//        }

        List<FieldError> allErrors = result.getFieldErrors();
        List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
        for (FieldError objectError : allErrors) {
            errorMesages.add(new ErrorMessage(objectError.getField(),
                    objectError.getField() + "  "
                            + objectError.getDefaultMessage()));
        }
        validationResponse.setErrorMessageList(errorMesages);
        validationResponse.setStatus("FAIL");

        return validationResponse;
    }

//    @RequestMapping(value = "/getUsersList.ajax", method = RequestMethod.GET)
//    public @ResponseBody
//    List<UserWrapper> getWrappedUsersListAjax() {
//        List<UserWrapper> list = new ArrayList<UserWrapper>();
//        for (User user : userService.findAll()) {
//            list.add(wrap(user));
//        }
//        return list;
//    }
//
//    @RequestMapping(value = "/deleteUser.ajax", method = RequestMethod.POST)
//    public @ResponseBody
//    boolean deleteUserFromDatabase(@RequestParam String login) {
//
//        userService.remove(userService.findByLogin(login));
//        return true;
//    }

    @RequestMapping(value = "/edit")
    public String showEdit(@RequestParam String login,
            @RequestParam String command, Model model) {

        if (command.equals(COMMAND_ADD)) {
            logger.info("showAdd");
            model.addAttribute("userWrapper", wrap(null, command));
            model.addAttribute("command", "add");
            return "admin/edit";
        }

        if (command.equals(COMMAND_SIGNUP)) {
            logger.info("showSignUp");
            model.addAttribute("userWrapper", wrap(null, command));
            model.addAttribute("command", "signUp");
            return "admin/edit";
        }

        logger.info("showEdit");
//        model.addAttribute("userWrapper",
//                wrap(userService.findByLogin(login), command));
        String passwordAgain = new String();
        model.addAttribute("passwordAgain", passwordAgain);
        model.addAttribute("command", "edit");
        return "admin/edit";
    }

    @RequestMapping(value = "/editUser")
    public String editUser(@Valid UserWrapper userWrapper,
            BindingResult result, @RequestParam String command, Model model,
            HttpServletRequest request) {

        System.out.println("command -->" + userWrapper.getCommandName());
        command = userWrapper.getCommandName();

        userWrapperValidator.validate(userWrapper, result);

        if (command.equals(COMMAND_SIGNUP)) {
            System.out.println(request.getParameter("answer"));
            if (!request.getParameter("answer").equals(answer)) {
                FieldError error = new FieldError("invalid.captcha", "captcha",
                        "Invalid captcha");
                result.addError(error);
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("userWrapper", userWrapper);
            model.addAttribute("passwordAgain", "");

            logger.info("errors count:" + result.getErrorCount());
            for (ObjectError e : result.getAllErrors()) {
                logger.info(e.getDefaultMessage());
            }

            model.addAttribute("command", userWrapper.getCommandName());
            return "admin/edit";
        }
        return null;
    }

//
//        if (command.equals(COMMAND_ADD)) {
//            logger.info("addUser");
//
//            User user = unwrap(userWrapper);
//            userService.create(user);
//        }
//        if (command.equals(COMMAND_SIGNUP)) {
//            logger.info("addUser from registration");
//
//            User user = unwrap(userWrapper);
//            userService.create(user);
//            return "redirect:/j_spring_security_logout";
//        } else {

//            logger.info("editUser");
//
//            User user = unwrap(userWrapper);
//            user.setId(userService.findByLogin(user.getLogin()).getId());
//            userService.update(user);
//        }
//        List<User> users = userService.findAll();
//        model.addAttribute("users", users);
//
//        return "admin/admin";


//    @RequestMapping(value = "/remove", method = RequestMethod.GET)
//    public String delUser(ModelMap model, HttpServletRequest request) {
//        System.out.println("Inside delete");
//        User user = userService.findByLogin(request.getParameter("login"));
//        userService.remove(user);
//        //
//        List<User> users = userService.findAll();
//        model.addAttribute("userWrapper", wrap(null));
//        model.addAttribute("users", users);
//        //
//        return "admin/admin";
//    }

    private UserWrapper wrap(User user, String commandName) {
        UserWrapper userWrapper = wrap(user);
        userWrapper.setCommandName(commandName);
        return userWrapper;
    }

    public UserWrapper wrap(User user) {
        if (user == null) {
            return new UserWrapper();
        }
        UserWrapper userWrapper = new UserWrapper();
        userWrapper.setLogin(user.getLogin());
        userWrapper.setPassword(user.getPassword());
        userWrapper.setPassVerify(user.getPassVerify());
        userWrapper.setEmail(user.getEmail());
//        userWrapper.setFirstName(user.getFirstName());
//        userWrapper.setLastName(user.getLastName());
//        userWrapper.setBirthDate(user.getBirthDate().toString());
        userWrapper.setRole(user.getRole().getName());
//        userWrapper.setAge(user.getAge());

        return userWrapper;
    }

    private User unwrap(UserWrapper userWrapper) {
        User user = new User();
        user.setLogin(userWrapper.getLogin());
        user.setPassword(userWrapper.getPassword());
        user.setPassVerify(userWrapper.getPassVerify());
        user.setEmail(userWrapper.getEmail());
//        user.setFirstName(userWrapper.getFirstName());
//        user.setLastName(userWrapper.getLastName());
//        user.setBirthDate(Date.valueOf(userWrapper.getBirthDate()));

        if (userWrapper.getRole().equals("Admin")) {
            user.setRole(roleService.findByName("ROLE_ADMIN"));
        } else if (userWrapper.getRole().equals("User")) {
            user.setRole(roleService.findByName("ROLE_USER"));
        } else {
            user.setRole(roleService.findByName(userWrapper.getRole()));
        }

        return user;
    }

    @RequestMapping("/captcha")
    public void createCaptcha(HttpServletResponse resp) {
        Captcha captcha = new Captcha.Builder(200, 50).addText().addNoise()
                .addNoise().addNoise().addBackground().build();
        ServletOutputStream out = null;
        try {
            out = resp.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ImageIO.write(captcha.getImage(), "png", out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        answer = captcha.getAnswer();
        try {
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

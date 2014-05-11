package com.malyshkin.wrapper;

import com.malyshkin.service.UserService;
import com.malyshkin.controller.AdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserWrapperValidator implements Validator {

    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return UserWrapper.class.equals(arg0);
    }

    @Override
    public void validate(Object object, Errors errors) {
        System.out.println("Inside validate method");
        UserWrapper userWrapper = (UserWrapper) object;

        System.out.println("ROLE---------------------------->"
                + userWrapper.getRole());
        System.out.println("COMMAND---------------------->"
                + userWrapper.getCommandName());

        if (userWrapper.getCommandName().equals("add")
                || userWrapper.getCommandName().equals(
                        AdminController.COMMAND_SIGNUP)) {
//            if (userService.findByLogin(userWrapper.getLogin()) != null) {
//                errors.rejectValue("login", "invalid.login", "invalid login");
//            }
        }

        if (!userWrapper.getPassword().equals(userWrapper.getPassVerify())) {
            errors.rejectValue("passVerify", "invalid.password",
                    "invalid password");
        }

        if (!Pattern
                .compile(
                        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+"
                                + ")*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
                .matcher(userWrapper.getEmail()).matches()) {
            errors.rejectValue("email", "invalid.email", "invelid email");
        }

//        if (!userWrapper.getBirthDate().equals("")) {
//            if (!Pattern
//                    .compile("^\\d{4}-((0\\d)|(1[012]))-(([012]\\d)|3[01])$")
//                    .matcher(userWrapper.getBirthDate()).matches()) {
//                errors.rejectValue("birthDate", "invalid.birthDate",
//                        "invelid date format");
//            }
//        }
        System.out.println("after validate method");
        System.out.println("----");
        System.out.println(errors.getErrorCount());

        for (ObjectError e : errors.getAllErrors()) {
            System.out.println(e.getDefaultMessage());
        }

        System.out.println("----");

    }
}

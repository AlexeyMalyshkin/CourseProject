package com.malyshkin.controller.validator;

import com.malyshkin.entity.User;
import com.malyshkin.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator{

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if(userService.findUser(user.getEmail())!=null){
            errors.rejectValue("email", "email.allready.in.use");
        }

        if(StringUtils.isBlank(user.getPassword())){
            errors.rejectValue("password", "blank.password", "blank password");
        }

        if (!Pattern
                .compile(
                        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+"
                                + ")*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
                )
                .matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "invalid.email", "invalid email");
        }

    }
}

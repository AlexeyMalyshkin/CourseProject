package com.malyshkin.controller.api;

import com.malyshkin.entity.Category;
import com.malyshkin.entity.Role;
import com.malyshkin.entity.RoleType;
import com.malyshkin.entity.User;
import com.malyshkin.service.CategoryService;
import com.malyshkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class RestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    //  http://localhost:8080/CourseProject/api/getFullInfo/admin/admin
    @RequestMapping(value = "/getFullInfo/{email}/{password}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getFullInfoByEmail(@PathVariable String email, @PathVariable String password) {

        User user = userService.findUser(email);
        if(!user.getPassword().equals(shaPasswordEncoder.encodePassword(password,null))){
            return new User();
        }

        Map<Integer,List<Category>> categoryMap = categoryService.findForCurrentYear(user);

        user.setRole(new Role(){{setName(RoleType.USER.name());}});
        user.setPassword(password);
        user.setCategories(getAllCategories(categoryMap));

        return user;
    }


    //  http://localhost:8080/CourseProject/api/addUser/admin1/admin1
    @RequestMapping(value = "/addUser/{email}/{password}", method = RequestMethod.GET)
    public
    @ResponseBody
    User addUser(@PathVariable String email, @PathVariable String password) {

        User user = userService.findUser(email);
        User userTemp = new User();
        if(user ==null){
            userTemp.setRole(null);
            userTemp.setPassword(shaPasswordEncoder.encodePassword(password,null));
            userTemp.setEmail(email);

            userService.save(userTemp);
        }

        return userTemp;
    }

    private List<Category> getAllCategories(Map<Integer,List<Category>> categoryMap){
        List<Category> categories = new ArrayList<>();
        for(Integer key : categoryMap.keySet()){
            for(Category category : categoryMap.get(key)){
                category.setTransactions(null);
                category.setUser(null);
                categories.add(category);
            }
        }

        return categories;
    }
}

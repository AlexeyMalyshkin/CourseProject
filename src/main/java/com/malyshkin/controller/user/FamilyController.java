package com.malyshkin.controller.user;

import com.malyshkin.entity.User;
import com.malyshkin.service.FamilyInviteService;
import com.malyshkin.service.FamilyService;
import com.malyshkin.service.UserService;
import com.sun.tools.internal.ws.processor.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FamilyController
{
  @Autowired
  private FamilyService familyService;

  @Autowired
  private UserService userService;

  @Autowired
  private FamilyInviteService familyInviteService;


  @RequestMapping(value = "showFamilyPage")
  public String showFamilyPage(Model model){

    User user = getUserFromAuthentication();
    model.addAttribute("familyMembers", familyService.findFamily(user.getId()).getUsers());
    model.addAttribute("familyInvites", familyInviteService.findFamilyInvites(user));

    return "user/familyPage";
  }



  // Ajax
  @RequestMapping(value = "/changeRights", method = RequestMethod.GET)
  public @ResponseBody String changeRights(@RequestParam String id){
    User user = userService.findUserById(Long.parseLong(id));
    user.setFamilyAdmin(!user.isFamilyAdmin());
    userService.save(user);

//    System.out.println(id);
    return "name";
  }

  private User getUserFromAuthentication(){
    Authentication authentication = SecurityContextHolder.getContext()
      .getAuthentication();
    authentication.getName();

    return userService.findUser(authentication.getName());
  }
}

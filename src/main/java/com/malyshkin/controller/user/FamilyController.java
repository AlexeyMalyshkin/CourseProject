package com.malyshkin.controller.user;

import com.malyshkin.entity.Family;
import com.malyshkin.entity.FamilyInvite;
import com.malyshkin.entity.User;
import com.malyshkin.service.FamilyInviteService;
import com.malyshkin.service.FamilyService;
import com.malyshkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

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
    Family family;


    if(user.getFamily()!=null){
      family = familyService.findFamily(user.getFamily().getId());
    }
    else{
      family = new Family();
      user.setFamily(family);
      userService.save(user);
    }

    model.addAttribute("familyMembers", family.getUsers()!=null? family.getUsers(): new ArrayList<User>());

    model.addAttribute("familyInvites", familyInviteService.findFamilyInvitesByTo(user));

    // Provide #inviteMember pop-up:
    model.addAttribute("user", new User());

    // Provide acceptInvite:
    model.addAttribute("invite", new FamilyInvite());

    return "user/familyPage";
  }

  @RequestMapping(value = "/changeRights", method = RequestMethod.GET)
  public @ResponseBody String changeRights(@RequestParam String id){
    User user = userService.findUserById(Long.parseLong(id));
    user.setFamilyAdmin(!user.isFamilyAdmin());
    userService.save(user);

    return "success";
  }

  @RequestMapping(value = "inviteMember")
  public String inviteMember( User user){

    User foundUser = userService.findUser(user.getEmail());
    if(foundUser !=null){
      User currentUser = getUserFromAuthentication();

      FamilyInvite invite = new FamilyInvite();
      invite.setFamily(currentUser.getFamily());
      invite.setTo(foundUser);
      invite.setFrom(currentUser);

      familyInviteService.save(invite);
    }

    return "redirect:showFamilyPage";
  }

  @RequestMapping(value = "acceptInvite")
  public String acceptInvite(FamilyInvite familyInvite){
    User currentUser = getUserFromAuthentication();

    FamilyInvite foundFamilyInvite = familyInviteService.findFamilyInvite(familyInvite.getId());
    foundFamilyInvite.setAccepted(true);
    familyInviteService.save(foundFamilyInvite);

    currentUser.setFamily(foundFamilyInvite.getFamily());
    userService.save(currentUser);

    return "redirect:showFamilyPage";
  }

  @RequestMapping(value = "leaveFamily")
  public String leaveFamily(){
    User currentUser = getUserFromAuthentication();
    // here remove user from family if u need
    currentUser.setFamily(null);
    userService.save(currentUser);

      return "redirect:showFamilyPage";
  }


  private User getUserFromAuthentication(){
    Authentication authentication = SecurityContextHolder.getContext()
      .getAuthentication();
    authentication.getName();

    return userService.findUser(authentication.getName());
  }
}

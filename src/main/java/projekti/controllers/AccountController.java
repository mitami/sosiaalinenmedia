/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.controllers;

import java.security.Principal;
import java.util.List;
import projekti.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.models.Follow;
import projekti.services.AccountService;
import projekti.services.FollowService;
import projekti.services.MessageService;

/**
 *
 * @author mikko
 */
@Controller
public class AccountController {
  
  @Autowired
  private AccountService as;
  @Autowired
  private FollowService fs;
  @Autowired
  private MessageService ms;
  
  @GetMapping("/accounts/{id}/profile")
  public String getUserProfileByUsername(Model model, @PathVariable Long id) {
    //Account a = as.getUserByUsername(username);
    Account a = as.getOne(id);
    
    model.addAttribute("user", a);
    
    return "profile";
  }
  
  @GetMapping("/accounts/myprofile")
  public String getLoggedInUsersProfile(Model model, Principal principal) {
    Account a;
    try {
      a = as.getUserByUsername(principal.getName());
    } catch(Exception e) {
      return "redirect:/";
    }
    
    List<Follow> pendingFollowRequests = fs.findByTargetAndConfirmedFalse(a);
    
    model.addAttribute("user", a);
    model.addAttribute("pendingfollows", pendingFollowRequests);
    
    return "profile";
  }
  
  @PostMapping("/accounts")
  public String addUser(
          @RequestParam String username,
          @RequestParam String name,
          @RequestParam String password) {
    
    as.createAccount(name, username, password);
    
    return "redirect:/login";
  }
  
  @GetMapping("/accounts/new")
  public String register() {
    return "registration";
  }
  
  @GetMapping("/accounts/search")
  public String getOne(@RequestParam String username, Model model) {
    model.addAttribute("foundUser", as.getUserByUsername(username));
    return "usersearch";
  }
}

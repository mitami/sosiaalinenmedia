/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.controllers;

import projekti.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.services.AccountService;

/**
 *
 * @author mikko
 */
@Controller
public class AccountController {
  
  @Autowired
  private AccountService as;
  
  @GetMapping("/accounts/{username}/profile")
  public String getUserProfileByUsername(Model model, @PathVariable String username) {
    Account a = as.getUserByUsername(username);
    
    model.addAttribute("user", a);
    
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
}

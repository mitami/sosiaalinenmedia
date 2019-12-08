/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.controllers;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.models.Account;
import projekti.models.Message;
import projekti.services.AccountService;
import projekti.services.MessageService;

/**
 *
 * @author mikko
 */
@Controller
public class MessageController {
  
  @Autowired
  private MessageService ms;
  @Autowired
  private AccountService as;
  
  @GetMapping("/messages/{username}")
  public String getOne(@PathVariable String username, Model model) {
    Account user = as.getUserByUsername(username);
    List<Message> messages = ms.findByWriter(user);
    
    model.addAttribute("message", messages);
    
    return "messagelist";
  }
  
  @DeleteMapping("/messages/{id}")
  public String deleteOne(@PathVariable Long id) {
    Message msg = ms.getOne(id);
    ms.delete(msg);
    
    return "redirect:/accounts/myprofile";
  }
  
  @PostMapping("/messages")
  public String addMessage(@RequestParam("text") String text, Principal principal) {
    System.out.println("Call to create Message received! -----> " + text);
    Account user = as.getUserByUsername(principal.getName());
    ms.createMessage(text, user);
    
    return "redirect:/accounts/myprofile";
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projekti.models.Account;
import projekti.models.Follow;
import projekti.services.AccountService;
import projekti.services.FollowService;

/**
 *
 * @author mikko
 */
@Controller
public class FollowController {
  
  @Autowired
  private FollowService fs;
  @Autowired
  private AccountService as;
  
  @PostMapping("/follow/{id}")
  public String followUser(@PathVariable("id") Long targetId, Principal principal) {
    Account follower = as.getUserByUsername(principal.getName());
    Account target = as.getOne(targetId);
    Follow follow = new Follow(LocalDate.now(), false, follower, target);
    
    fs.save(follow);
    
    return "redirect:/accounts/myprofile";
  }
  
  @GetMapping("/follow/{id}/confirm")
  public String confirmFollow(@PathVariable("id") Long followId, Principal principal) {
    Follow follow = fs.findById(followId);
    Account user = as.getUserByUsername(principal.getName());
    
    if(Objects.equals(follow.getTarget().getId(), user.getId())) {
     follow.setConfirmed(true);
     //Haetaan seuraaja, vaikka se onkin talletettu seuraukseen, koska käyttäjä
     //voisi olla poistettu.
     Account follower = as.getOne(follow.getFollower().getId());
     List<Account> followers = user.getFollowers();
     followers.add(follower);
     user.setFollowers(followers);
     
     as.save(user);
     
     List<Account> followed = follower.getFollowed();
     followed.add(user);
     follower.setFollowed(followed);
     
     as.save(follower);
     
     return "redirect:/accounts/myprofile";
     
    } else {
     return "redirect:/accounts/myprofile"; 
    }
  }
  
  @GetMapping("/follow/{id}/decline")
  public String declineFollow(@PathVariable("id") Long followId, Principal principal) {
    Follow follow = fs.findById(followId);
    
    fs.delete(follow);
    
    return "redirect:/account/myprofile";
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import projekti.models.Account;
import projekti.models.Picture;
import projekti.services.AccountService;
import projekti.services.PictureService;

/**
 *
 * @author mikko
 */
@Controller
public class PictureController {
  
  @Autowired
  private AccountService as;
  
  @Autowired
  private PictureService ps;
  
  @GetMapping(path = "/accounts/{username}/pictures")
  public String getUsersPictures(@PathVariable String username, Model model) {
    List<Picture> pictures = ps.findByUsername(username);
    Account user = as.getUserByUsername(username);
    
    for(Picture p : pictures) {
      p.setContent(new byte[0]);
    } 
    
    model.addAttribute("picturedata", pictures);
    model.addAttribute("user", user);
    
    return "gallery";
  }
  
  @PostMapping(path = "/accounts/{id}/pictures")
  public String addPictureToUser(
          @PathVariable("id") Long id, MultipartFile file,
          Principal principal, @RequestParam("desc") String description) throws IOException{
    
    System.out.println("ADDPICTURE_TO_USER controller called !!" + file.getContentType());
    String username = principal.getName();
    
    if(file != null && file.getContentType().startsWith("image/")) {
      Picture picture = new Picture();
      picture.setContent(file.getBytes());

      ps.save(picture, username, description);
    }
    
    
    
    return "redirect:/accounts/" + username + "/pictures";
  }
  
  @PostMapping("/accounts/{userId}/pictures/{pictureId}/setprofile")
  public String setPictureAsProfile(@PathVariable Long userId, @PathVariable Long pictureId, Principal principal) {
    ps.setNewProfilePicture(pictureId, userId);
    
    return "redirect:/accounts/myprofile";
  }
  
  @DeleteMapping("/pictures/{id}")
  public String deleteOne(@PathVariable Long id, Principal principal) {
    String username = principal.getName();
    ps.deleteByIdAndUsername(id, username);
    
    return "redirect:/accounts/" + username +"/pictures";
  }
  
  @GetMapping(path = "/pictures/{id}", produces="image/*")
  @ResponseBody
  public byte[] getPictureById(@PathVariable Long id) {
    Picture picture = ps.getOne(id);
    
    return picture.getContent();
  }
  
  @GetMapping(path = "/accounts/{id}/pictures/profile", produces="image/*")
  @ResponseBody
  public byte[] getUsersProfilePicture(@PathVariable Long id) {
    Picture picture = ps.findByOwnerAndIsprofileTrue(id);
    
    return picture.getContent();
  }
  
  @GetMapping("/pictures/{id}/fullsize")
  public String fullSizeImage(@PathVariable Long id, Model model) {
    Picture picture = ps.getOne(id);
    picture.setContent(new byte[0]);
    
    model.addAttribute(picture);
    
    return "picture";
  }
}

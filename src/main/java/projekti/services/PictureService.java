/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.services;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekti.models.Account;
import projekti.models.Picture;
import projekti.repositories.AccountRepository;
import projekti.repositories.PictureRepository;

/**
 *
 * @author mikko
 */
@Service
public class PictureService {
  
  @Autowired
  private PictureRepository pr;
  @Autowired
  private AccountRepository ar;
  
  public boolean deleteByIdAndUsername(Long id, String username) {
    try{
      Account user = ar.findByUsername(username);
      pr.deleteByIdAndOwner(id, user);
      
      return true;
    } catch(Exception e) {
      return false;
    }
  }
  
  public Picture save(Picture picture) {
    return pr.save(picture);
  }
  
  public Picture save(Picture picture, String username, String description) {
    System.out.println("PICTURE_SERVICE --- SAVE called");
    Account user = ar.findByUsername(username);
    //Change to just retrieve amount of pictures
    List<Picture> usersPictures = pr.findByOwner(user);
    
    Picture saved = null;
    if(usersPictures.isEmpty()) {
      //Add as profile pic      
      picture.setIsprofile(true);
      picture.setDescription(description);
      picture.setOwner(user);
      pr.save(picture);
    } else if(usersPictures.size() == 10) {
      //Too manu pics, can't add
    } else {
      //Save it for the user as normal picture      
      picture.setIsprofile(false);
      picture.setDescription(description);
      picture.setOwner(user);
      pr.save(picture);
    }
    
    return saved;
  }
  
  public Picture getOne(Long id) {
    return pr.getOne(id);
  }
  
  public List<Picture> findByUsername(String username) {
    Account user = ar.findByUsername(username);
    List<Picture> usersPictures = pr.findByOwner(user);
    
    for(Picture p : usersPictures) {
      System.out.println("FIND_BY_USERNAME_PICTURES ---" + p.getId());
    }
    
    return usersPictures;
  }
  
  public Picture findByOwnerAndIsprofileTrue(String username) {
    Account user = ar.findByUsername(username);
    
    Picture p = pr.findByOwnerAndIsprofileTrue(user);
    
    return p;
  }
  
  public Picture findByOwnerAndIsprofileTrue(Long userId) {
    Account user = ar.getOne(userId);
    
    Picture p = pr.findByOwnerAndIsprofileTrue(user);
    
    return p;
  }
  
  public Picture setNewProfilePicture(Long newPictureId, Long userId) {
    Account user = ar.getOne(userId);
    Picture picture = pr.getOne(newPictureId);
    
    Account picOwner = ar.getOne(picture.getOwner().getId());
    
    if(Objects.equals(picOwner.getId(), user.getId())) {
      Picture oldProfilePic = pr.findByOwnerAndIsprofileTrue(user);
      if(oldProfilePic != null) {
        oldProfilePic.setIsprofile(false);
        pr.save(oldProfilePic);
      }
      
      picture.setIsprofile(true);
      
      pr.save(picture);
    }
    
    return picture;
  }
}

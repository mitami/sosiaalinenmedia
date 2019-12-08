/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekti.models.Account;
import projekti.models.Follow;
import projekti.repositories.FollowRepository;

/**
 *
 * @author mikko
 */
@Service
public class FollowService {
  
  @Autowired
  private FollowRepository fr;
  
  public Follow save(Follow follow) {
    return fr.save(follow);
  }
  
  public Follow findById(Long id) {
    return fr.getOne(id);
  }
  
  public List<Follow> findByFollower(Account account) {
    return fr.findByFollower(account);
  }
  
  public List<Follow> findByTarget(Account account) {
    return fr.findByTarget(account);
  }
  
  public List<Follow> findByTargetAndConfirmedFalse(Account account) {
    return fr.findByTargetAndConfirmedFalse(account);
  }
  
  public boolean delete(Follow follow) {
    try {
      fr.delete(follow);
    } catch(Exception e) {
      return false;
    }
    
    return true;
  }
}

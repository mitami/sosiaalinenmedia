/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.repositories;

import java.util.List;
import projekti.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekti.models.Account;

/**
 *
 * @author mikko
 */
@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
 
  public Picture deleteByIdAndOwner(Long id, Account owner);
  
  public List<Picture> findByOwner(Account owner);
  
  public Picture findByIdAndOwner(Long id, Account owner);
  
  public Picture findByOwnerAndIsprofileTrue(Account owner);
}

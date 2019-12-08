/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.repositories;

import java.util.List;
import projekti.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekti.models.Account;

/**
 *
 * @author mikko
 */
@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
  
  public List<Follow> findByFollower(Account account);
  
  public List<Follow> findByTarget(Account account);
  
  public List<Follow> findByTargetAndConfirmedFalse(Account account);
}

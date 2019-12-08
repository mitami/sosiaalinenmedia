/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.repositories;

import java.util.List;
import projekti.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekti.models.Account;

/**
 *
 * @author mikko
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
  
  public List<Message> findByTarget(Account account);
}

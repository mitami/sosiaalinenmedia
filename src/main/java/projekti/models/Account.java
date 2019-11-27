/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author mikko
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractPersistable<Long> {
  
  private String name;
  private String username;
  private String password;
  
  @OneToMany
  private List<Comment> comments;
  @OneToMany
  private List<Message> messages;
  @OneToMany
  private List<Picture> pictures;
}

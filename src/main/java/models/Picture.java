/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

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
public class Picture extends AbstractPersistable<Long> {
  
  private byte[] content;
  private boolean isprofile;
  private String description;
  
  @OneToMany
  private List<Comment> comments;
  @OneToMany
  private List<Like> likes;
  
}

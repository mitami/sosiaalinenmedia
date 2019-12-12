/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
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
  
  @Lob
  @Type(type = "org.hibernate.type.BinaryType")
  private byte[] content;
  
  private boolean isprofile;
  private String description;
  
  @OneToMany
  private List<Comment> comments;
  @OneToMany(mappedBy="targetPicture")
  private List<Fancy> likes;
  @ManyToOne
  private Account owner;
  
}

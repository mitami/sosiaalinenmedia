/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.models;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
public class Message extends AbstractPersistable<Long> {
  
  private String text;
  private LocalDate sent;
  
  @ManyToOne
  private Account target;
  @OneToMany(mappedBy="message")
  private List<Comment> comments;
  @OneToMany(mappedBy="targetMessage")
  private List<Fancy> likes;
}

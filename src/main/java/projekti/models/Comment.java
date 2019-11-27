/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.models;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class Comment extends AbstractPersistable<Long> {

  @ManyToMany(mappedBy = "comments")
  private List<Picture> pictures;
  
  private String text;
  private LocalDate sent;
  
  
  @ManyToOne
  private Account sender;
  @ManyToOne
  private Message message;
  @ManyToOne
  private Picture picture;
}

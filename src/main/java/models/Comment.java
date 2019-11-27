/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import javax.persistence.Entity;
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
  
  private String text;
  private LocalDate sent;
  
  
  private Account sender;
  private Message message;
  private Picture picture;
}

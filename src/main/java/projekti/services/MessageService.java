/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekti.models.Account;
import projekti.models.Message;
import projekti.repositories.AccountRepository;
import projekti.repositories.MessageRepository;

/**
 *
 * @author mikko
 */
@Service
public class MessageService {
  
  
  @Autowired
  private MessageRepository mr;
  @Autowired
  private AccountRepository ar;
  
  public List<Message> findByWriter(Account account) {
    return mr.findByTarget(account);
  }
  
  public boolean delete(Message msg) {
    try {
      mr.delete(msg);
    } catch(Exception e) {
      return false;
    }
    
    return true;
  }
  
  public Message save(Message msg) {
    return mr.save(msg);
  }
  
  public Message getOne(Long id) {
    return mr.getOne(id);
  }
  
  public Message createMessage(String text, Account sender) {
    Message msg = new Message();
    msg.setText(text);
    msg.setSent(LocalDate.now());
    msg.setTarget(sender);
    
    System.out.println("Message created: --->" + msg.getText());
    
    mr.save(msg);
    
    /*List<Message> messages = sender.getMessages();
    messages.add(msg);
    sender.setMessages(messages);
    
    for(Message m : messages) {
      System.out.println("Printing all user's messages: ---->" + m.getText());
    }
    
    try {
      ar.save(sender);
    } catch (Exception e) {
      System.out.println("Failed to save user after setting messages list!");
      return new Message();
    }*/
    
    
    return msg;
  }
}

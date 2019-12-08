/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.services;

import java.util.ArrayList;
import projekti.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projekti.repositories.AccountRepository;

/**
 *
 * @author mikko
 */
@Service
public class AccountService {
  
  @Autowired
  private AccountRepository ar;
  
  @Autowired
  private PasswordEncoder pe;
  
  public Account getUserByUsername(String username) {
    Account a = ar.findByUsername(username);
    return a;
  }
  
  public Account createAccount(String name, String username, String password) {
    Account a = new Account();
    a.setName(name);
    a.setUsername(username);
    a.setPassword(pe.encode(password));
    
    return ar.save(a);   
  }
  
  public Account getOne(Long id) {
    return ar.getOne(id);
  }
  
  public Account save(Account account) {
    return ar.save(account);
  }
}

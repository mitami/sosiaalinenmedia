/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import static org.fluentlenium.core.filter.FilterConstructor.withText;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mikko
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FluentLeniumPohjaTest extends org.fluentlenium.adapter.junit.FluentTest {
  
  @LocalServerPort
  private Integer port;
  
  @Test
  public void frontPageDisplayed() {
    goTo("http://localhost:" + port + "/");
    
    assertTrue(pageSource().contains("Tervetuloa"));
  }
  
  @Test
  public void canRegisterAndSignIn() {
    goTo("http://localhost:" + port + "/");
    
    find("a", withText("Register")).click();
    
    find("#name").fill().with("testi");
    find("#username").fill().with("testi");
    find("#password").fill().with("testi");
    
    find("#register").click();
    
    assertTrue(pageSource().contains("Please sign in"));
    
    find("#username").fill().with("testi");
    find("#password").fill().with("testi");
    
    find("button", withText("Sign in")).click();
    
    assertTrue(pageSource().contains("testi"));
  }
}

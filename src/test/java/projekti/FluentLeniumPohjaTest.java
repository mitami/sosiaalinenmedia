/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

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
}

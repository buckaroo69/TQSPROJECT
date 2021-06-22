package logisticsmarshall.tqs.ua.controllers;// Generated by Selenium IDE
import io.github.bonigarcia.seljup.SeleniumJupiter;
import logisticsmarshall.tqs.ua.model.Delivery;
import logisticsmarshall.tqs.ua.model.User;
import logisticsmarshall.tqs.ua.repository.DeliveryRepository;
import logisticsmarshall.tqs.ua.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(SeleniumJupiter.class)
public class MainDriverIT {

  @Autowired
  UserRepository userRepository;

  @Autowired
  DeliveryRepository deliveryRepository;

  @Autowired
  BCryptPasswordEncoder passwordEncoder;

  private Map<String, Object> vars;
  JavascriptExecutor js;
  @BeforeEach
  public void setUp() {

  }
  @AfterEach
  public void tearDown() {
    // User user = userRepository.findByName("rider");
    // user.setPassword("rider");
    // userRepository.save(user);

    // Delivery delivery = deliveryRepository.findDeliveryById(1L);
    //Finish here
    //driver.quit();
  }
  @Test
  public void mainDriverIntegration(ChromeDriver driver) {
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    driver.get("http://backendmain:8080/login");
    driver.manage().window().setSize(new Dimension(1456, 876));
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.id("username")).sendKeys("rider");
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.id("password")).sendKeys("rider");
    driver.findElement(By.id("login-submit")).click();
    driver.findElement(By.cssSelector(".blog-box")).click();
    assertThat(driver.findElement(By.cssSelector("h2:nth-child(2)")).getText(), is("Welcome rider"));
    driver.findElement(By.cssSelector(".container:nth-child(1)")).click();
    driver.findElement(By.cssSelector(".container > div > h2")).click();
    assertThat(driver.findElement(By.cssSelector(".container > div > h2")).getText(), is("My Average Reputation:5.0"));
    driver.findElement(By.cssSelector("html")).click();
    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(1) > td:nth-child(1)"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(4)")).click();
    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)")).click();
    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)")).click();
    {
      WebElement element = driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)")).click();
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("tr:nth-child(2) > td:nth-child(1)"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(4)")).click();
    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(5) > div:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(5) > div:nth-child(1)")).getText(), is("REQUESTED"));
    driver.findElement(By.cssSelector("html")).click();
    driver.findElement(By.cssSelector(".btn")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("div:nth-child(1) > .btn"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(5) > div:nth-child(1)")).click();
    driver.findElement(By.cssSelector("p")).click();
    driver.findElement(By.cssSelector("div:nth-child(1) > .btn")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(5)")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(4)")).click();

    driver.findElement(By.cssSelector("p")).click();
    driver.findElement(By.cssSelector(".container:nth-child(3)")).click();
    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(5) > div:nth-child(1)")).click();
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(5)")).click();

    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(4)")).click();
    driver.findElement(By.cssSelector("p")).click();

    driver.findElement(By.cssSelector(".container:nth-child(1)")).click();
    driver.findElement(By.linkText("Profile")).click();

    driver.findElement(By.id("password")).sendKeys("rider");
    driver.findElement(By.id("newPassword")).sendKeys("newpass");

    driver.findElement(By.cssSelector(".col-3 > .button")).click();

    driver.findElement(By.id("username")).sendKeys("rider");
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.id("password")).sendKeys("newpass");
    driver.findElement(By.id("login-submit")).click();
    driver.findElement(By.linkText("LogisticsMarshall")).click();
    driver.findElement(By.cssSelector("h2")).click();
    assertThat(driver.findElement(By.cssSelector("h2")).getText(), is("Welcome to Logistics Marshall"));
    driver.findElement(By.cssSelector(".style2")).click();
    driver.findElement(By.linkText("Profile")).click();
    driver.findElement(By.cssSelector(".col-9 > label")).click();
    assertThat(driver.findElement(By.linkText("Log Out")).getText(), is("Log Out"));
    driver.findElement(By.linkText("Log Out")).click();
  }
}

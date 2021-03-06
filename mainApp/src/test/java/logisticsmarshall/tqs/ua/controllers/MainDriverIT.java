package logisticsmarshall.tqs.ua.controllers;// Generated by Selenium IDE
import io.github.bonigarcia.seljup.SeleniumJupiter;
import logisticsmarshall.tqs.ua.model.Delivery;
import logisticsmarshall.tqs.ua.model.User;
import logisticsmarshall.tqs.ua.repository.UserRepository;
import logisticsmarshall.tqs.ua.services.DeliveryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class MainDriverIT {

    @LocalServerPort
    int serverPort;

    String url;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeliveryService deliveryService;

    @BeforeEach
    public void setUp() {
        url = String.format("http://backendmain:%d/", serverPort);


      User user = userRepository.findByName("marchingfood");
      Delivery del = new Delivery();
      del.setCompany(user.getCompany());
      del.setStage(Delivery.Stage.REQUESTED);
      del.setAddress("IT Test Delivery");
      del.setPickupAddress(user.getCompany().getAddress());
      del.setOrderTimestamp(Timestamp.valueOf(LocalDateTime.now()));
      del.setPriority(Delivery.Priority.HIGHPRIORITY);
      deliveryService.postDelivery(del);
    }

    @Test
    void mainDriverIntegration(ChromeDriver driver) {
        driver.get(url + "login");
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
        assertThat(driver.findElement(By.xpath("(//tr/td[5]/div[1])[last()]")).getText(), is("REQUESTED"));
        driver.findElement(By.cssSelector("html")).click();
        driver.findElement(By.xpath("(//button)[last()]")).click();
        {
          List<WebElement> elements = driver.findElements(By.cssSelector("div:nth-child(1) > .btn"));
          assert(elements.size() > 0);
        }
        driver.findElement(By.xpath("(//button)[last()-1]")).click();
        driver.findElement(By.cssSelector("p")).click();
        driver.findElement(By.cssSelector(".btn")).click();
        driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(5)")).click();
        driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(4)")).click();

        driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(4)")).click();
        driver.findElement(By.cssSelector("p")).click();

        driver.findElement(By.cssSelector(".container:nth-child(1)")).click();
        driver.findElement(By.linkText("Profile")).click();
        driver.findElement(By.id("password")).sendKeys("rider");
        driver.findElement(By.id("newPassword")).sendKeys("newpass");
        driver.findElement(By.cssSelector(".col-3 > .button")).click();

        driver.findElement(By.id("username")).sendKeys("rider");
        driver.findElement(By.id("password")).sendKeys("newpass");
        driver.findElement(By.id("login-submit")).click();
        driver.findElement(By.linkText("LogisticsMarshall")).click();
        driver.findElement(By.cssSelector("h2")).click();
        assertThat(driver.findElement(By.cssSelector("h2")).getText(), is("Welcome to Logistics Marshall"));
        driver.findElement(By.cssSelector(".style2")).click();
        driver.findElement(By.linkText("Profile")).click();
        driver.findElement(By.id("password")).sendKeys("newpass");
        driver.findElement(By.id("newPassword")).sendKeys("rider");
        driver.findElement(By.cssSelector(".col-3 > .button")).click();
    }
}

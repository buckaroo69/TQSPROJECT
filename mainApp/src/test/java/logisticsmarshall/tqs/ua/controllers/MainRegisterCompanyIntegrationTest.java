package logisticsmarshall.tqs.ua.controllers;// Generated by Selenium IDE
import io.github.bonigarcia.seljup.SeleniumJupiter;
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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(SeleniumJupiter.class)
public class MainRegisterCompanyIntegrationTest {
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @BeforeEach
  public void setUp() {

  }
  @AfterEach
  public void tearDown() {
    //driver.quit();
  }
  @Test
  public void mainRegisterCompanyIntegration(ChromeDriver driver) {
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    driver.get("http://localhost:8080/login");
    driver.manage().window().setSize(new Dimension(1456, 876));
    {
      List<WebElement> elements = driver.findElements(By.linkText("Log In"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.linkText("Log In")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("span > a"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector("span > a")).click();
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).sendKeys("FoodRestaurant");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.name("email")).sendKeys("foodrestaurant@mail.com");
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.name("password")).sendKeys("foodrestaurant");
    driver.findElement(By.name("role")).click();
    driver.findElement(By.cssSelector("#div1 > h3")).click();
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("#div1 > h3"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.id("deliveryType")).click();
    driver.findElement(By.id("deliveryType")).sendKeys("food");
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("Street close to the church");
    driver.findElement(By.id("phoneNumber")).click();
    driver.findElement(By.id("phoneNumber")).sendKeys("9122222222");
    driver.findElement(By.cssSelector(".alt")).click();
  }
}

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class RicaricaSaldoSeleniumTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void tC91() {
    driver.get("http://localhost:8080/VeicHomeIS/");
    driver.manage().window().setSize(new Dimension(1552, 840));
    driver.findElement(By.id("prova")).click();
    driver.findElement(By.id("text")).click();
    driver.findElement(By.id("text")).sendKeys("giuseppe70");
    driver.findElement(By.id("pass")).click();
    driver.findElement(By.id("pass")).sendKeys("qwerty1");
    driver.findElement(By.cssSelector("button:nth-child(3)")).click();
    driver.findElement(By.linkText("12500€")).click();
    driver.findElement(By.name("ricarica")).click();
    {
      WebElement dropdown = driver.findElement(By.name("ricarica"));
      dropdown.findElement(By.xpath("//option[. = '60000']")).click();
    }
    driver.findElement(By.cssSelector(".submit-btn")).click();
    driver.findElement(By.id("ok")).click();
    assertThat(driver.switchTo().alert().getText(), is("Logout eseguito con successo"));
  }
}

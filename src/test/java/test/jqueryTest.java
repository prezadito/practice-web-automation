package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class jqueryTest {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\adity\\IdeaProjects\\practice-web-automation\\src\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://jqueryui.com/droppable/");

    }

    @Test
    public void Test() throws InterruptedException {
        //switch into frame
        WebElement frameWebElement = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frameWebElement);
        waitFor(3);
        //perform drop and drop action inside the frame
        Actions action = new Actions(driver);
        WebElement source = driver.findElement(By.id("draggable")); //source
        WebElement target = driver.findElement(By.id("droppable")); //target
        action.dragAndDrop(source, target).build().perform();
        waitFor(3);
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }
}

package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class practice {

    WebDriver driver;
    Actions move;
    Select select;

    @BeforeMethod
    public void SetUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\qwasd\\IdeaProjects\\web-automation-practice\\src\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("https://expertautomationteam.com");
        driver.findElement(By.cssSelector("a.nav-link[href='practice.html']")).click();
    }

    @Test
    public void filter() {
        Assert.assertEquals(driver.getTitle(), "Practice Page | QA Automation");
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("Select");
        waitFor(3);
        Assert.assertTrue(driver.findElement(By.xpath("//strong[contains(text(), 'Select')]")).isDisplayed());
    }

    @Test
    public void dragAndDrop() {
        Assert.assertEquals(driver.getTitle(), "Practice Page | QA Automation");
        move = new Actions(driver);
        WebElement logo = driver.findElement(By.cssSelector("img#drag1[draggable='true']"));
        WebElement dropoff = driver.findElement(By.cssSelector("div#div1[ondrop='drop(event)']"));
        move.dragAndDrop(logo, dropoff).perform();
        waitFor(3);
        // Assert
    }

    public void dropdownList() {
        Assert.assertEquals(driver.getTitle(), "Practice Page | QA Automation");

    }

    @Test
    public void dropdownListGroup() {
        Assert.assertEquals(driver.getTitle(), "Practice Page | QA Automation");
        WebElement dropdownGroup = driver.findElement(By.cssSelector("select[id='multipleCourse']"));
        select = new Select(dropdownGroup);
        select.selectByVisibleText("American History");
        waitFor(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("option[value='american']")).isDisplayed());
    }

    private void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }


}

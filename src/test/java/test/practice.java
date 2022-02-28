package test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\adity\\IdeaProjects\\practice-web-automation\\src\\drivers\\chromedriver.exe");
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
        WebElement logo = driver.findElement(By.xpath("//img[@id='drag1']"));
        WebElement dropoff = driver.findElement(By.xpath("//div[@id='div1']"));
        move.dragAndDrop(logo, dropoff).build().perform();
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

    @Test
    public void dragNdropJS() {
        WebElement ElementFrom = driver.findElement(By.xpath("//img[@id='drag1']"));
        WebElement ElementTo = driver.findElement(By.xpath("//div[@id='div1']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("function createEvent(typeOfEvent) {\\n\" +\"var event =document.createEvent(\\\"CustomEvent\\\");\\n\" +\"event.initCustomEvent(typeOfEvent,true, true, null);\\n\" +\"event.dataTransfer = {\\n\" +\"data: {},\\n\" +\"setData: function (key, value) {\\n\" +\"this.data[key] = value;\\n\" +\"},\\n\" +\"getData: function (key) {\\n\" +\"return this.data[key];\\n\" +\"}\\n\" +\"};\\n\" +\"return event;\\n\" +\"}\\n\" +\"\\n\" +\"function dispatchEvent(element, event,transferData) {\\n\" +\"if (transferData !== undefined) {\\n\" +\"event.dataTransfer = transferData;\\n\" +\"}\\n\" +\"if (element.dispatchEvent) {\\n\" + \"element.dispatchEvent(event);\\n\" +\"} else if (element.fireEvent) {\\n\" +\"element.fireEvent(\\\"on\\\" + event.type, event);\\n\" +\"}\\n\" +\"}\\n\" +\"\\n\" +\"function simulateHTML5DragAndDrop(element, destination) {\\n\" +\"var dragStartEvent =createEvent('dragstart');\\n\" +\"dispatchEvent(element, dragStartEvent);\\n\" +\"var dropEvent = createEvent('drop');\\n\" +\"dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\\n\" +\"var dragEndEvent = createEvent('dragend');\\n\" +\"dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\\n\" +\"}\\n\" +\"\\n\" +\"var source = arguments[0];\\n\" +\"var destination = arguments[1];\\n\" +\"simulateHTML5DragAndDrop(source,destination);", ElementFrom, ElementTo);

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

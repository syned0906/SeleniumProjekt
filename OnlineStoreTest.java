package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.time.Duration;
import static org.testng.Assert.*;

public class OnlineStoreTest {
    private WebDriver driver;
    private JavascriptExecutor js;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ivan\\Desktop\\kromdr\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
    }

    @Test(priority = 1)
    void testUserRegistrationAndLogin() {
        driver.get("https://www.demoblaze.com/");
        driver.findElement(By.id("signin2")).click();
        driver.findElement(By.id("sign-username")).sendKeys("ivanc1000");
        driver.findElement(By.id("sign-password")).sendKeys("sifra321");
        driver.findElement(By.xpath("//button[contains(text(),'Sign up')]")).click();
        assertEquals(closeAlertAndGetItsText(), "Sign up successful.");
        
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("ivanc1000");
        driver.findElement(By.id("loginpassword")).sendKeys("sifra321", Keys.ENTER);
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
    }

    @Test(priority = 2)
    void testAddToCart() {
        addItemToCart("Samsung galaxy s6");
        addItemToCart("Iphone 6 32gb");
        addItemToCart("MacBook air");
        addItemToCart("ASUS Full HD");
    }

    @Test(priority = 3)
    void testContactForm() {
        driver.findElement(By.linkText("Contact")).click();
        driver.findElement(By.id("recipient-email")).sendKeys("ivanc@gmail.com");
        driver.findElement(By.id("recipient-name")).sendKeys("ivan");
        driver.findElement(By.id("message-text")).sendKeys("hello");
        driver.findElement(By.xpath("//button[contains(text(),'Send message')]")).click();
        assertEquals(closeAlertAndGetItsText(), "Thanks for the message!!");
    }

    @Test(priority = 4)
    void testCheckoutProcess() {
        driver.findElement(By.id("cartur")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
        driver.findElement(By.id("name")).sendKeys("ivan");
        driver.findElement(By.id("country")).sendKeys("hrvatska");
        driver.findElement(By.id("city")).sendKeys("osijek");
        driver.findElement(By.id("card")).sendKeys("479182");
        driver.findElement(By.id("month")).sendKeys("2");
        driver.findElement(By.id("year")).sendKeys("2026");
        driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
    }

    @Test(priority = 5)
    void testLogout() {
        driver.findElement(By.id("logout2")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    private void addItemToCart(String itemName) {
        driver.findElement(By.linkText(itemName)).click();
        driver.findElement(By.linkText("Add to cart")).click();
        assertEquals(closeAlertAndGetItsText(), "Product added.");
        driver.findElement(By.linkText("Home (current)")).click();
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (NoAlertPresentException e) {
            return "";
        }
    }
}

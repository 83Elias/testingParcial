package com.automatization;

import com.automatization.base.BasePage;
import com.automatization.product.ProductPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

class ParcialTesting {

    private WebDriver driver;
    private BasePage basePage;
    private ProductPage productPage;

    @BeforeEach
    void initApp() throws IOException {
        Resource chromeDriverResource = new ClassPathResource("chromedriverv16.exe");
        File chromeDriverFile = chromeDriverResource.getFile();

        System.setProperty("webdriver.chrome.driver", chromeDriverFile.getAbsolutePath());
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        productPage = new ProductPage(driver);

    }


    @Test
    @Tag("smoke")
    void registerNewAccountAutomatization() throws InterruptedException {
        driver.get("https://opencart.abstracta.us/index.php?route=common/home");
        String title= driver.getTitle();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));


        driver.findElement(By.xpath("//a[@title='My Account']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[contains(text(),'Register')]")).click();
        Thread.sleep(2000);
        WebElement firstNameField=driver.findElement(By.id("input-firstname"));
        WebElement lastName=driver.findElement(By.id("input-lastname"));
        WebElement email=driver.findElement(By.id("input-email"));
        WebElement telephone=driver.findElement(By.id("input-telephone"));
        WebElement password=driver.findElement(By.id("input-password"));
        WebElement confirm=driver.findElement(By.id("input-confirm"));
        firstNameField.clear();
        lastName.clear();
        email.clear();
        telephone.clear();
        password.clear();
        confirm.clear();

        firstNameField.sendKeys("Jhon");
        lastName.sendKeys("Doe");
        email.sendKeys("johoen2385@gmail.com");
        telephone.sendKeys("321657093");
        password.sendKeys("Jhon304+");
        confirm.sendKeys("Jhon304+");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[2]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]")).click();
        Thread.sleep(1000);
        WebElement successText=driver.findElement(By.xpath("//*[@id=\"content\"]/p[1]"));

        Assertions.assertTrue(title.contains("Your Store"));
        Assertions.assertTrue(successText.getText().contains("Congratulations! Your new account has been successfully created!"));
    }

    @Test
    @Tag("smoke")
    void findProductAndAddToCartAutomatization() throws InterruptedException {

        driver.get("https://opencart.abstracta.us/index.php?route=common/home");
        String title= driver.getTitle();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        productPage.searchForProduct("iphone");
        productPage.addToCart();

        String successMessage = productPage.getSuccessMessage();

        Assertions.assertTrue(title.contains("Your Store"));
        Assertions.assertTrue(successMessage.contains("Success: You have added iPhone to your shopping cart!"));
    }

    @AfterEach
    void cleanWindows(){
        driver.quit();
    }

}

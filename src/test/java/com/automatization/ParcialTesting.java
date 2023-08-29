package com.automatization;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

class ParcialTesting {

    private  WebDriver driver;

    @BeforeEach
    void initApp() throws IOException {
        Resource chromeDriverResource = new ClassPathResource("chromedriverv16.exe");
        File chromeDriverFile = chromeDriverResource.getFile();

        System.setProperty("webdriver.chrome.driver", chromeDriverFile.getAbsolutePath());
        System.setProperty("webdriver.http.factory", "jdk-http-client");

         driver = new ChromeDriver();
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
        email.sendKeys("jhonDoe999@gmail.com");
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

        WebElement findProduct=driver.findElement(By.xpath("//*[@id=\"search\"]/input"));
        findProduct.clear();
        findProduct.sendKeys("iphone");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[1]")).click();
        Thread.sleep(1000);
        WebElement sucessMessageAdd=driver.findElement(By.xpath("//*[@id=\"product-search\"]/div[1]"));
        System.out.println(sucessMessageAdd.getText());
        Thread.sleep(1000);
        Assertions.assertTrue(title.contains("Your Store"));
        Assertions.assertTrue(sucessMessageAdd.getText().contains("Success: You have added iPhone to your shopping cart!"));
    }

    @AfterEach
    void cleanWindows(){
        driver.quit();
    }

}

package com.automatization.product;

import com.automatization.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {


    public ProductPage(WebDriver driver) {
        super(driver);
    }


    public void searchForProduct(String productName) {
        WebElement findProduct = driver.findElement(By.xpath("//*[@id='search']/input"));
        findProduct.clear();
        findProduct.sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='search']/span/button"))).click();
    }

    public void addToCart() throws InterruptedException {
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div[2]/button[1]")));
        addToCartButton.click();
        Thread.sleep(1000);
    }

    public String getSuccessMessage() {
        WebElement successMessageAdd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='product-search']/div[1]")));
        return successMessageAdd.getText();
    }
}

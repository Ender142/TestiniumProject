package com.testinium;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    WebDriverWait wait;
    protected WebDriver driver;

    private By prLocation = By.cssSelector("li.srp-item-list");
    //// *[@id="item-info-block-690424064"]/p/img
    private By addCart = By.id("add-to-basket");
    private By productPrice = By.id("sp-price-highPrice");
    String productPricePage;
    private static final Logger logger = LogManager.getLogger(ProductPage.class);

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        // this.wait = new WebDriverWait(driver, 90);
    }

    public ProductPage findAndAddProduct() throws InterruptedException {
        Thread.sleep(2000);
        logger.info("Trying to pick random product.");
        List<WebElement> products = driver.findElements(prLocation);
        Random generator = new Random();
        int randomIndex = generator.nextInt(products.size());
        System.out.println(randomIndex);
        products.get(randomIndex).click();
        Thread.sleep(1000);
        logger.info("Random product picked.");
        Thread.sleep(1000);
        productPricePage = driver.findElement(productPrice).getText().strip();
        logger.info("Got product price before adding to cart.");
        Thread.sleep(200);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(addCart));
        Thread.sleep(200);
        driver.findElement(addCart).click();
        logger.info("Product added to cart.");
        return new ProductPage(driver);
    }

    public String getPrice() {
        logger.info("Product price in search page sent to Test class.");
        return productPricePage;
    }

}

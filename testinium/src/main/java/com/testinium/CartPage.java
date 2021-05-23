package com.testinium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriverWait wait;
    protected WebDriver driver;

    private By increaseProduct = By.xpath("//select[@class='amount']");
    private By price = By.xpath("//strong[contains(text(),'TL')]");
    private By deleteProducts = By.xpath("//div[@class='text-box']//a[@title='Sil']");
    private static final Logger logger = LogManager.getLogger(CartPage.class);

    String productCartPrice;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 90);
    }

    public CartPage increaseAndDeleteProduct() throws InterruptedException {
        logger.info("Selenium on cart page.");
        Thread.sleep(1000);
        driver.get("https://www.gittigidiyor.com/sepetim");
        logger.info("Clickked on cart icon..");
        Thread.sleep(200);
        WebElement prc = driver.findElement(price);
        productCartPrice = prc.getText().strip();
        System.out.println(productCartPrice);
        driver.findElement(increaseProduct).sendKeys("2");
        logger.info("Product quantity increased to 2.");
        Thread.sleep(1000);
        driver.findElement(deleteProducts).click();
        logger.info("Products deleted.");
        Thread.sleep(200);
        return new CartPage(driver);
    }

    public String getCartPrice() {
        logger.info("Price in cart is sent to compare.");
        return productCartPrice;
    }

}

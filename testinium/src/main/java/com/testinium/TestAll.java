package com.testinium;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAll {
    private static ChromeDriver driver;

    private void setupDriver() {
        if (driver == null) {
            System.out.println("Initializing driver");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.gittigidiyor.com");
        } else {
            System.out.println("Driver already initialized, continuing.");
        }
    }

    private static final Logger logger = LogManager.getLogger(TestAll.class);
    String comparePrice;

    @Test
    public void AloginPage() throws InterruptedException {
        setupDriver();
        LoginPage loginAccount = new LoginPage(driver);
        loginAccount.loginValidUser(Settings.username, Settings.password);
        assertTrue(driver.getPageSource().contains("Siparişlerim"));
        logger.info("Asserted login test.");
    }

    @Test
    public void BsearchPage() {
        setupDriver();
        SearchPage secondPage = new SearchPage(driver);
        secondPage.searchAndGoToSecondPage(Settings.productName);
        assertEquals(driver.getCurrentUrl(), "https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2");
        logger.info("Asserted second page test.");
    }

    @Test
    public void CproductPage() throws InterruptedException {
        setupDriver();
        ProductPage productPage = new ProductPage(driver);
        productPage.findAndAddProduct();
        comparePrice = productPage.getPrice();
        System.out.println(comparePrice);
        logger.info("Asserted adding product to cart test.");
    }

    @Test
    public void DcartPage() throws InterruptedException {
        setupDriver();
        CartPage cartPage = new CartPage(driver);
        cartPage.increaseAndDeleteProduct();
        assertEquals(comparePrice, cartPage.getCartPrice());
        logger.info("Prices compared, product deleted.");
    }
}

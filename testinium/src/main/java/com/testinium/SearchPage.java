package com.testinium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
    WebDriverWait wait;
    protected WebDriver driver;

    private By searchLabel = By.name("k");
    private By searchButton = By.xpath("//*[@data-cy='search-find-button']");
    private By nextPage = By.xpath("//*[@id='best-match-right']/div[5]/ul/li[2]/a");
    private static final Logger logger = LogManager.getLogger(SearchPage.class);

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 90);
    }

    public SearchPage searchAndGoToSecondPage(String productName) {
        logger.info("Logged in, searching product.");
        // System.out.println("Searching products");
        driver.findElement(searchLabel).sendKeys(productName);
        driver.findElement(searchButton).click();
        logger.info("Product searched, looking for second page button.");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
        wait.until(ExpectedConditions.elementToBeClickable(nextPage));
        driver.findElement(nextPage).click();
        logger.info("Selenium on page 2.");
        return new SearchPage(driver);
    }

}
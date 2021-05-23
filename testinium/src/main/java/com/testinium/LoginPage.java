package com.testinium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriverWait wait;
    protected WebDriver driver;

    private By signin = By.xpath("//*[@class='gekhq4-4 egoSnI']");
    private By hover = By.xpath("//*[@class='sc-12t95ss-3 fDarBX']");
    private By userNameBy = By.id("L-UserNameField");
    private By passwordBy = By.id("L-PasswordField");
    private By loginButton = By.id("gg-login-enter");
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 90);
    }

    public LoginPage loginValidUser(String userName, String password) throws InterruptedException {
        logger.info("Logging in");
        wait.until(ExpectedConditions.elementToBeClickable(signin));
        driver.findElement(signin).click();
        wait.until(ExpectedConditions.elementToBeClickable(hover));
        driver.findElement(hover).click();
        driver.findElement(userNameBy).sendKeys(userName);
        Thread.sleep(200);
        driver.findElement(passwordBy).sendKeys(password);
        logger.info("User values sent.");
        Thread.sleep(500);
        driver.findElement(loginButton).click();
        logger.info("Clicked login button.");
        return new LoginPage(driver);
    }

}
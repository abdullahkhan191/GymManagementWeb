package com.gym;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    private WebDriver driver;
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        baseUrl = System.getenv("APP_URL") != null ? System.getenv("APP_URL") : "http://gym-container:8080";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testHomePageLoads() {
        driver.get(baseUrl);
        String title = driver.getTitle();
        assertNotNull(title);
        System.out.println("Page title is: " + title);
    }

    @Test
    public void testMembersPageLoads() {
        driver.get(baseUrl + "/members");
        String currentUrl = driver.getCurrentUrl();
        assertNotNull(currentUrl);
        System.out.println("Members page URL: " + currentUrl);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
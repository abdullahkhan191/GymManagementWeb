package com.gym;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testHomePageLoads() {
        driver.get("http://localhost:8081");
        String title = driver.getTitle();
        assertNotNull(title);
        System.out.println("Page title is: " + title);
    }

    @Test
    public void testMembersPageLoads() {
        driver.get("http://localhost:8081/members");
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("members"));
        System.out.println("Members page URL: " + currentUrl);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
package dev.harshit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClearTripScraper {
    public List<Flight> getFlightData(int day) throws InterruptedException {

        List<Flight> clearTripFlights = new ArrayList<>();

        // Setup WebDriver and navigate to Cleartrip
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.cleartrip.com/flights");

        // Create explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for "Where *from" input field and enter the city name
        WebElement fromCity = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[placeholder='Where from?']")));
        fromCity.click();
        driver.findElement(By.xpath("//p[contains(text(),'Bangalore')]")).click();

        WebElement toCity = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[placeholder='Where to?']")));
        toCity.click();
        driver.findElement(By.xpath("//p[contains(text(),'Delhi')]")).click();


        driver.findElement(By.xpath(
                "//div[@class='sc-aXZVg dSvAMK mr-2 mt-1']")).click();


        driver.findElement(By.xpath("//div[text()='"+day+"'][1]")).click();

        // Click the search button and wait for results
        driver.findElement(By.xpath("//h4[text()='Search flights']/../..")).click();
        Thread.sleep(1000);

        //driver.findElement(By.xpath("//p[text()='Non-stop']/../../../div//span")).click();

        List<WebElement> flightNameElements = driver.findElements(By.xpath("//p[@class='fw-500 fs-2 c-neutral-900']"));
        List<WebElement> flightNumberElements = driver.findElements(By.xpath("//p[@class='fs-1 c-neutral-400 pt-1']"));
        List<WebElement> flightPriceElements = driver.findElements(By.xpath("//p[@class='m-0 fs-5 fw-700 c-neutral-900 ta-right false']"));

        for (int i = 0; i < flightNumberElements.size(); i++) {
            String operator = flightNameElements.get(i).getText();
            String flightNumber = flightNumberElements.get(i).getText();
            String flightPrice = flightPriceElements.get(i).getText();

            // Create a new Flight object and add it to the list
            clearTripFlights.add(new Flight(operator, flightNumber, flightPrice));
        }
        return clearTripFlights;
    }
}

package dev.harshit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class ClearTripScraper {
    public List<Flight> getFlightData(String travelDate) {
        // Setup WebDriver and navigate to Cleartrip
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.cleartrip.com/flights");

        // Input fixed cities: Bangalore and Delhi
        WebElement fromCity = driver.findElement(By.id("FromTag"));
        fromCity.sendKeys("Bangalore");

        WebElement toCity = driver.findElement(By.id("ToTag"));
        toCity.sendKeys("Delhi");

        // Input travel date
        WebElement dateField = driver.findElement(By.id("DepartTag"));
        dateField.sendKeys(travelDate);

        // Click the search button and wait for results
        driver.findElement(By.id("SearchBtn")).click();

        // Extract flight details
        List<WebElement> flights = driver.findElements(By.cssSelector(".flightRow"));
        List<Flight> flightList = new ArrayList<>();

        for (WebElement flight : flights) {
            String operator = flight.findElement(By.cssSelector(".operator")).getText();
            String flightNumber = flight.findElement(By.cssSelector(".flightNumber")).getText();
            String price = flight.findElement(By.cssSelector(".price")).getText();
            flightList.add(new Flight(operator, flightNumber, price));
        }

        driver.quit();   // Close the browser
        return flightList;
    }
}

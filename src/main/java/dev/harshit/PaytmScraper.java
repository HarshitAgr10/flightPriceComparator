package dev.harshit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class PaytmScraper {
    public List<Flight> getFlightData(String travelDate) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://tickets.paytm.com/flights");

        WebElement fromCity = driver.findElement(By.id("fromCity"));
        fromCity.sendKeys("Bangalore");

        WebElement toCity = driver.findElement(By.id("toCity"));
        toCity.sendKeys("Delhi");

        WebElement dateField = driver.findElement(By.id("departureDate"));
        dateField.sendKeys(travelDate);

        driver.findElement(By.id("searchButton")).click();

        List<WebElement> flights = driver.findElements(By.cssSelector(".flightRow"));
        List<Flight> flightList = new ArrayList<>();

        for (WebElement flight : flights) {
            String operator = flight.findElement(By.cssSelector(".operator")).getText();
            String flightNumber = flight.findElement(By.cssSelector(".flightNumber")).getText();
            String price = flight.findElement(By.cssSelector(".price")).getText();
            flightList.add(new Flight(operator, flightNumber, price));
        }

        driver.quit();
        return flightList;
    }
}

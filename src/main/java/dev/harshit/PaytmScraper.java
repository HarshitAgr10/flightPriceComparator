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

// Class for scrapping flight data from Paytm
public class PaytmScraper {

    // Method to fetch flight data for a given day
    public List<Flight> getFlightData(int day) throws InterruptedException{

        List<Flight> paytmFlights = new ArrayList<>();


        WebDriver driver = new ChromeDriver();
        driver.get("https://tickets.paytm.com/flights");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // Wait for "From city" field and click on it
        WebElement fromCity = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@id='srcCode']")));
        fromCity.click();    // Click on the "From" input field
        // Select Bengaluru as the departure city from the dropdown
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='_2m6yl' and text() ='Bengaluru']"))).click();


        // Wait for "To city" field and click on it
        WebElement toCity = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@id='destCode']")));
        toCity.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='_2m6yl' and text() ='Delhi']"))).click();


        // Open the calendar and select the departure date
        driver.findElement(By.xpath(
                "//span[@id='departureDate']")).click();
        // Select the desired day from the date picker based on the input
        driver.findElement(By.xpath("//div[text()='"+day+"'][1]")).click();


        // Click the search button to fetch available flights
        driver.findElement(By.xpath("//button[@id='flightSearch']")).click();


        // Sleep for 30 seconds to wait for the search results to load fully
        Thread.sleep(30000);


//        wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//span[text()='Non-stop']/..//input"))).click();

//        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Non-stop']/..//i")));
//        element1.click();
//        WebElement element1 = driver.findElement(By.xpath("//span[text()='Non-stop']/..//input"));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(element1).click().perform();

//        WebElement element1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='nonStop']")));
//        element1.click();


        // Scrape the flight operator(Airline) names, flight numbers and flight prices
        List<WebElement> flightNameElements = driver.findElements(By.xpath("//span[@class='_2cP56']"));
        List<WebElement> flightDetailsButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[text()='Flight Details']")));
        List<WebElement> flightPriceElements = driver.findElements(By.xpath("//div[@class='_2MkSl']"));

        for (int i = 0; i < 15; i++) {
            String operator = flightNameElements.get(i).getText();    // Get flight operator

            // Click on the "Flight Details" button to fetch flight number
            flightDetailsButtons.get(i).click();
            Thread.sleep(2000);

            // Wait for flight number to appear and scrape it
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='inboundflightnumber']")));
            WebElement flightNumberElements = driver.findElement(By.xpath("//span[@id='inboundflightnumber']"));
            String flightNumber = flightNumberElements.getText();

            String flightPrice = flightPriceElements.get(i).getText();    // Get flight price

            // Create a new Flight object using scrapped data and add it to the list
            paytmFlights.add(new Flight(operator, flightNumber, flightPrice));
        }

        driver.quit();    // Close the browser after scraping is done

        return paytmFlights;    // Return the list of flights
    }
}

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

public class PaytmScraper {
    public List<Flight> getFlightData(int day) throws InterruptedException{

        List<Flight> paytmFlights = new ArrayList<>();
        WebDriver driver = new ChromeDriver();
        driver.get("https://tickets.paytm.com/flights");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement fromCity = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@id='srcCode']")));
        fromCity.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='_2m6yl' and text() ='Bengaluru']"))).click();


        WebElement toCity = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@id='destCode']")));
        toCity.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='_2m6yl' and text() ='Delhi']"))).click();


        driver.findElement(By.xpath(
                "//span[@id='departureDate']")).click();
        driver.findElement(By.xpath("//div[text()='"+day+"'][1]")).click();

        driver.findElement(By.xpath("//button[@id='flightSearch']")).click();

        Thread.sleep(30000);


//        wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//span[text()='Non-stop']/..//input"))).click();

        //WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Non-stop']/..//i")));
        //element1.click();
//        WebElement element1 = driver.findElement(By.xpath("//span[text()='Non-stop']/..//input"));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(element1).click().perform();

//        WebElement element1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='nonStop']")));
//        element1.click();


        List<WebElement> flightNameElements = driver.findElements(By.xpath("//span[@class='_2cP56']"));
        List<WebElement> flightDetailsButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[text()='Flight Details']")));
        List<WebElement> flightPriceElements = driver.findElements(By.xpath("//div[@class='_2MkSl']"));

        for (int i = 0; i < 15; i++) {
            String operator = flightNameElements.get(i).getText();

            flightDetailsButtons.get(i).click();
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='inboundflightnumber']")));
            WebElement flightNumberElements = driver.findElement(By.xpath("//span[@id='inboundflightnumber']"));
            String flightNumber = flightNumberElements.getText();

            String flightPrice = flightPriceElements.get(i).getText();
            paytmFlights.add(new Flight(operator, flightNumber, flightPrice));
        }

        driver.quit();
        return paytmFlights;
    }
}

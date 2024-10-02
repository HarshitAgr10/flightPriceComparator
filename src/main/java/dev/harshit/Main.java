package dev.harshit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Create a scanner object to take travelDate as input from the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter travel date (YYYY/MM/DD): ");
        String travelDate = scanner.nextLine();


        // Create instances of ClearTripScraper and PaytmScraper to scrape flight data
        ClearTripScraper clearTripScraper = new ClearTripScraper();
        PaytmScraper paytmScraper = new PaytmScraper();


        // Scrape flight data for the specified travel date from both ClearTrip and Paytm
        List<Flight> clearTripFlights = clearTripScraper.getFlightData(extractDateAndTime(travelDate));
        List<Flight> paytmFlights = paytmScraper.getFlightData(extractDateAndTime(travelDate));


        // Compare flight prices between ClearTrip and Paytm
        FlightPriceComparator.compareFlightPrices(clearTripFlights, paytmFlights);
    }


    // Method to extract the day of the month from the input date string
    // Input format is "YYYY/MM/DD" and it returns the day of the month as an integer
    private static int extractDateAndTime(String travelDate) {

        // Define a DateTimeFormatter to match the input date format (yyyy/MM/dd)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        // Parse the input string into a LocalDate object
        LocalDate date = LocalDate.parse(travelDate, formatter);
        // Extract day and return
        return date.getDayOfMonth();
    }
}
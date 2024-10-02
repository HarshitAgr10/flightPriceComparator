package dev.harshit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        /**
         * Take input for the travel date.
         * Fetch flight data from both Cleartrip and Paytm.
         * Compare the data.
         * Generate a CSV file with the comparison results.
         */

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter travel date (YYYY/MM/DD): ");
        String travelDate = scanner.nextLine();

        // Scrape flight date from Cleartrip and Paytm
        ClearTripScraper clearTripScraper = new ClearTripScraper();
        PaytmScraper paytmScraper = new PaytmScraper();

        List<Flight> clearTripFlights = clearTripScraper.getFlightData(extractDateAndTime(travelDate));
        List<Flight> paytmFlights = paytmScraper.getFlightData(extractDateAndTime(travelDate));

        Map<String, Flight> clearTripMap = new HashMap<>();
        for (Flight clearTripFlight : clearTripFlights) {
            clearTripMap.put(clearTripFlight.getFlightNumber(), clearTripFlight);
        }

        System.out.printf("%-20s %-15s %-10s %-10s%n", "Flight Name", "Flight Number", "Paytm Price", "ClearTrip Price");
        System.out.println("---------------------------------------------------------------");

        for (Flight paytmFlight : paytmFlights) {
            String flightNumber = paytmFlight.getFlightNumber();
            Flight clearTripFlight = clearTripMap.get(flightNumber);

            // Check if there is a matching flight in ClearTrip
            if (clearTripFlight != null) {
                System.out.printf("%-20s %-15s %-10s %-10s%n",
                        paytmFlight.getOperator(),
                        flightNumber,
                        paytmFlight.getPrice(),
                        clearTripFlight.getPrice()
                );
            }
        }

        // Compare flight data
        FlightComparison comparison = new FlightComparison();
//        List<FlightComparison> comparisonResults =  comparison.compareFlights(
//                cleartripFlights, paytmFlights);

        // Generate CSV file
        CSVWriter csvWriter = new CSVWriter();
        //csvWriter.writeToFile(comparisonResults);

        System.out.println("Comparison complete! Check the flight_comparison.csv file");
    }

    private static int extractDateAndTime(String travelDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // Parse the input string into a LocalDate object
        LocalDate date = LocalDate.parse(travelDate, formatter);

        // Extract day
        int day = date.getDayOfMonth();

        // Return day
        return day;
    }
}
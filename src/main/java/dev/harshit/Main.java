package dev.harshit;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
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

        List<Flight> cleartripFlights = clearTripScraper.getFlightData(travelDate);
        List<Flight> paytmFlights = paytmScraper.getFlightData(travelDate);

        // Compare flight data
        FlightComparison comparison = new FlightComparison();
        List<FlightComparison> comparisonResults =  comparison.compareFlights(
                cleartripFlights, paytmFlights);

        // Generate CSV file
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeToFile(comparisonResults);

        System.out.println("Comparison complete! Check the flight_comparison.csv file");
    }
}
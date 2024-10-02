package dev.harshit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Class to compare flights between two platforms (Paytm and ClearTrip)
public class FlightPriceComparator {

    // Method to compare flights from ClearTrip and Paytm based on flight number
    public static void compareFlightPrices(List<Flight> clearTripFlights, List<Flight> paytmFlights) {

        // Create a map to store ClearTrip flights with flight number as the key
        Map<String, Flight> clearTripMap = new HashMap<>();
        for (Flight clearTripFlight : clearTripFlights) {
            clearTripMap.put(clearTripFlight.getFlightNumber(), clearTripFlight);
        }


        // Print the header for the comparison table
        System.out.printf("%-20s %-15s %-10s %-10s%n", "Flight Name", "Flight Number", "Paytm Price", "ClearTrip Price");
        System.out.println("---------------------------------------------------------------");


        // Loop through each flight in the Paytm list and compare with the corresponding ClearTrip flight
        for (Flight paytmFlight : paytmFlights) {
            String flightNumber = paytmFlight.getFlightNumber();
            // Get the corresponding flight from ClearTrip using flight number
            Flight clearTripFlight = clearTripMap.get(flightNumber);


            // Check if there is a matching flight in ClearTrip, display the flight details
            if (clearTripFlight != null) {
                System.out.printf("%-20s %-15s %-10s %-10s%n",
                        paytmFlight.getOperator(),
                        flightNumber,
                        paytmFlight.getPrice(),
                        clearTripFlight.getPrice()
                );
            }
        }
    }
}

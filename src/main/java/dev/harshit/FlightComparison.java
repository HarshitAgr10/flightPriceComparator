package dev.harshit;

import java.util.ArrayList;
import java.util.List;

public class FlightComparison {
    private String operator;
    private String flightNumber;
    private String cleartripPrice;
    private String paytmPrice;

    public FlightComparison(String operator, String flightNumber, String cleartripPrice, String paytmPrice) {
        this.operator = operator;
        this.flightNumber = flightNumber;
        this.cleartripPrice = cleartripPrice;
        this.paytmPrice = paytmPrice;
    }

    public FlightComparison() {

    }

    public String getOperator() {
        return operator;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getCleartripPrice() {
        return cleartripPrice;
    }

    public String getPaytmPrice() {
        return paytmPrice;
    }

    public List<FlightComparison> compareFlights(List<Flight> cleartripFlights, List<Flight> paytmFlights) {
        List<FlightComparison> comparisonList = new ArrayList<>();

        for (Flight cleartripFlight : cleartripFlights) {
            String flightOperator = cleartripFlight.getOperator();
            String flightNumber = cleartripFlight.getFlightNumber();
            String cleartripPrice = cleartripFlight.getPrice();

            // Find the corresponding flight in Paytm data
            String paytmPrice = "Data Not Found";    // Default if flight not found on Paytm
            for (Flight paytmFlight : paytmFlights) {
                if (paytmFlight.getFlightNumber().equals(flightNumber) &&
                        paytmFlight.getOperator().equals(flightOperator)) {
                    paytmPrice = paytmFlight.getPrice();
                    break;
                }
            }

            comparisonList.add(new FlightComparison(flightOperator, flightNumber, cleartripPrice, paytmPrice));
        }
        return comparisonList;
    }
}

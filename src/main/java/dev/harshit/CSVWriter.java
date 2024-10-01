package dev.harshit;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    public void writeToFile(List<FlightComparison> comparisonData) {
        String filePath = "flight_comparison.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Flight Operator, Flight Number, Price on Cleartrip, Price on Paytm\n");

            for (FlightComparison comparison : comparisonData) {
                writer.append(String.format("%s, %s, %s, %s \n",
                        comparison.getOperator(),
                        comparison.getFlightNumber(),
                        comparison.getCleartripPrice(),
                        comparison.getPaytmPrice()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Write the fight comparison data to a CSV file named "flight_comparison.csv".
 * Each line contains the flight operator, flight number, price from Cleartrip, and price from Paytm.
 */
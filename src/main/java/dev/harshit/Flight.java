package dev.harshit;

public class Flight {
    private String operator;
    private String flightNumber;
    private String price;

    public Flight(String operator, String flightNumber, String price) {
        this.operator = operator;
        this.flightNumber = flightNumber;
        this.price = price;
    }

    public String getOperator() {
        return operator;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getPrice() {
        return price;
    }
}

package org.example.model;

public class TourRevenueStatistic extends Tour {
    private double averageNumberGuestsPerTour;
    private double totalRevenue;

    public TourRevenueStatistic() {
        super();
    }

    public TourRevenueStatistic(String codeTour, String tourName, String departureLocation, String destination, String description, double averageNumberGuestsPerTour, double totalRevenue) {
        super(codeTour, tourName, departureLocation, destination, description);
        this.averageNumberGuestsPerTour = averageNumberGuestsPerTour;
        this.totalRevenue = totalRevenue;
    }

    public double getAverageNumberGuestsPerTour() {
        return averageNumberGuestsPerTour;
    }

    public void setAverageNumberGuestsPerTour(double averageNumberGuestsPerTour) {
        this.averageNumberGuestsPerTour = averageNumberGuestsPerTour;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
} 
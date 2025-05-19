package org.example.model;

public class RevenueLocationStatistic extends Tour {
    private int totalGuestsByLocation;
    private double totalRevenue;
    private int totalToursByLocation;

    public int getTotalToursByLocation() { return totalToursByLocation; }
    public void setTotalToursByLocation(int totalToursByLocation) { this.totalToursByLocation = totalToursByLocation; }

    public RevenueLocationStatistic() {
        super();
    }

    public RevenueLocationStatistic(String codeTour, String tourName, String departureLocation, String destination, String description, int totalGuestsByLocation, double totalRevenue) {
        super(codeTour, tourName, departureLocation, destination, description);
        this.totalGuestsByLocation = totalGuestsByLocation;
        this.totalRevenue = totalRevenue;
    }

    public int getTotalGuestsByLocation() {
        return totalGuestsByLocation;
    }

    public void setTotalGuestsByLocation(int totalGuestsByLocation) {
        this.totalGuestsByLocation = totalGuestsByLocation;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
} 
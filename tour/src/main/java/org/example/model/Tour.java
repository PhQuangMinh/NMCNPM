package org.example.model;

import java.util.ArrayList;

public class Tour {
    private String codeTour;
    private String tourName;
    private String departureLocation;
    private String description;
    private String destination;
    private ArrayList<TourDeparture> tourDepartures;

    public Tour() {
        super();
    }

    public Tour(String codeTour, String tourName, String departureLocation, String description, String destination) {
        super();
        this.codeTour = codeTour;
        this.tourName = tourName;
        this.departureLocation = departureLocation;
        this.description = description;
        this.destination = destination;
    }

    public Tour(String codeTour, String tourName, String departureLocation, String description, String destination, ArrayList<TourDeparture> tourDepartures) {
        super();
        this.codeTour = codeTour;
        this.tourName = tourName;
        this.departureLocation = departureLocation;
        this.description = description;
        this.destination = destination;
        this.tourDepartures = tourDepartures;
    }

    public String getCodeTour() {
        return codeTour;
    }

    public void setCodeTour(String codeTour) {
        this.codeTour = codeTour;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public ArrayList<TourDeparture> getTourDepartures() {
        return tourDepartures;
    }

    public void setTourDepartures(ArrayList<TourDeparture> tourDepartures) {
        this.tourDepartures = tourDepartures;
    }
}
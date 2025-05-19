package org.example.model;

import java.util.ArrayList;
import java.util.Date;

public class TourDeparture {
    private int id;
    private Date departureDate;
    private Date endDate;
    private Tour tour;
    private ArrayList<Ticket> tickets;


    public TourDeparture() {
        super();
    }

    public TourDeparture(int id, Date departureDate, Date endDate, Tour tour, ArrayList<Ticket> tickets) {
        super();
        this.id = id;
        this.departureDate = departureDate;
        this.endDate = endDate;
        this.tour = tour;
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
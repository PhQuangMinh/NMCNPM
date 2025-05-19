package org.example.model;
public class PriceTicket {
    private int id;
    private int minGroupSize;
    private int maxGroupSize;
    private float pricePerPerson;
    private TourDeparture tourDeparture;

    public PriceTicket() {
        super();
    }

    public PriceTicket(int id, int minGroupSize, int maxGroupSize, float pricePerPerson, TourDeparture tourDeparture) {
        super();
        this.id = id;
        this.minGroupSize = minGroupSize;
        this.maxGroupSize = maxGroupSize;
        this.pricePerPerson = pricePerPerson;
        this.tourDeparture = tourDeparture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinGroupSize() {
        return minGroupSize;
    }

    public void setMinGroupSize(int minGroupSize) {
        this.minGroupSize = minGroupSize;
    }

    public int getMaxGroupSize() {
        return maxGroupSize;
    }

    public void setMaxGroupSize(int maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    public float getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(float pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public TourDeparture getTourDeparture() {
        return tourDeparture;
    }

    public void setTourDeparture(TourDeparture tourDeparture) {
        this.tourDeparture = tourDeparture;
    }
}
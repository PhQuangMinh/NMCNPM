package org.example.model;

import java.util.ArrayList;
import java.util.Date;

public class Bill {
    private String codeBill;
    private Date billDate;
    private String tourInfo;
    private int quantityTicket;
    private float totalPrice;
    private String representativeName;
    private User user;
    private Client client;
    private ArrayList<PenaltyBill> penaltyBills;
    private ArrayList<Ticket> tickets;


    public Bill() {
        super();
    }


    public Bill(String codeBill, Date billDate, String tourInfo, int quantityTicket, float totalPrice, User user, Client client, ArrayList<PenaltyBill> penaltyBills, ArrayList<Ticket> tickets) {
        this.codeBill = codeBill;
        this.billDate = billDate;
        this.tourInfo = tourInfo;
        this.quantityTicket = quantityTicket;
        this.totalPrice = totalPrice;
        this.user = user;
        this.client = client;
        this.penaltyBills = penaltyBills;
        this.tickets = tickets;
    }

    public String getCodeBill() {
        return codeBill;
    }

    public void setCodeBill(String codeBill) {
        this.codeBill = codeBill;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getTourInfo() {
        return tourInfo;
    }

    public void setTourInfo(String tourInfo) {
        this.tourInfo = tourInfo;
    }

    public int getQuantityTicket() {
        return quantityTicket;
    }

    public void setQuantityTicket(int quantityTicket) {
        this.quantityTicket = quantityTicket;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public ArrayList<PenaltyBill> getPenaltyBills() {
        return penaltyBills;
    }

    public void setPenaltyBills(ArrayList<PenaltyBill> penaltyBills) {
        this.penaltyBills = penaltyBills;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }
}
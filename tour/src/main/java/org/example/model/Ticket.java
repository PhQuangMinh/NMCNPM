package org.example.model;

import java.util.Date;

public class Ticket {
    private String codeTicket;
    private Date bookingDate;
    private String note;
    private String codeBill;
    private TourDeparture tourDeparture;

    public Ticket() {
        super();
    }

    public Ticket(String codeTicket, Date bookingDate, String note, TourDeparture tourDeparture, String codeBill) {
        super();
        this.codeTicket = codeTicket;
        this.bookingDate = bookingDate;
        this.note = note;
        this.tourDeparture = tourDeparture;
        this.codeBill = codeBill;
    }

    public String getCodeTicket() {
        return codeTicket;
    }

    public void setCodeTicket(String codeTicket) {
        this.codeTicket = codeTicket;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public TourDeparture getTourDeparture() {
        return tourDeparture;
    }

    public void setTourDeparture(TourDeparture tourDeparture) {
        this.tourDeparture = tourDeparture;
    }

    public String getCodeBill() {
        return codeBill;
    }

    public void setCodeBill(String codeBill) {
        this.codeBill = codeBill;
    }
} 
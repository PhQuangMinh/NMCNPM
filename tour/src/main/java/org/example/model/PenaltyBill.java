package org.example.model;

import java.util.Date;

public class PenaltyBill {
    private String codePenaltyBill;
    private Date cancellationDate;
    private float refundPrice;
    private String codeBill;

    public PenaltyBill() {
        super();
    }

    public PenaltyBill(String codePenaltyBill, Date cancellationDate, float refundPrice, String codeBill) {
        super();
        this.codePenaltyBill = codePenaltyBill;
        this.cancellationDate = cancellationDate;
        this.refundPrice = refundPrice;
        this.codeBill = codeBill;
    }

    public String getCodePenaltyBill() {
        return codePenaltyBill;
    }

    public void setCodePenaltyBill(String codePenaltyBill) {
        this.codePenaltyBill = codePenaltyBill;
    }

    public Date getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(Date cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public float getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(float refundPrice) {
        this.refundPrice = refundPrice;
    }

    public String getCodeBill() {
        return codeBill;
    }

    public void setCodeBill(String codeBill) {
        this.codeBill = codeBill;
    }
} 

package com.supportmanagement.dto;

import com.supportmanagement.entity.SupportAssignment;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AnalyticsResponse {

    private BigDecimal totalReceived = BigDecimal.ZERO;
    private BigDecimal totalPaid = BigDecimal.ZERO;
    private BigDecimal netProfit = BigDecimal.ZERO;
    private BigDecimal customerPendingDues = BigDecimal.ZERO;
    private BigDecimal supportPaymentsPending = BigDecimal.ZERO;
    private long paymentsDueInThreeDays;

    private List<SupportAssignment> assignments = new ArrayList<>();
    private List<SupportAssignment> customerDues = new ArrayList<>();
    private List<SupportAssignment> supportDues = new ArrayList<>();
    private List<SupportAssignment> upcomingPayments = new ArrayList<>();

    public BigDecimal getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(BigDecimal totalReceived) {
        this.totalReceived = totalReceived;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public BigDecimal getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(BigDecimal netProfit) {
        this.netProfit = netProfit;
    }

    public BigDecimal getCustomerPendingDues() {
        return customerPendingDues;
    }

    public void setCustomerPendingDues(BigDecimal value) {
        this.customerPendingDues = value;
    }

    public BigDecimal getSupportPaymentsPending() {
        return supportPaymentsPending;
    }

    public void setSupportPaymentsPending(BigDecimal value) {
        this.supportPaymentsPending = value;
    }

    public long getPaymentsDueInThreeDays() {
        return paymentsDueInThreeDays;
    }

    public void setPaymentsDueInThreeDays(long value) {
        this.paymentsDueInThreeDays = value;
    }

    public List<SupportAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<SupportAssignment> assignments) {
        this.assignments = assignments;
    }

    public List<SupportAssignment> getCustomerDues() {
        return customerDues;
    }

    public void setCustomerDues(List<SupportAssignment> customerDues) {
        this.customerDues = customerDues;
    }

    public List<SupportAssignment> getSupportDues() {
        return supportDues;
    }

    public void setSupportDues(List<SupportAssignment> supportDues) {
        this.supportDues = supportDues;
    }

    public List<SupportAssignment> getUpcomingPayments() {
        return upcomingPayments;
    }

    public void setUpcomingPayments(
            List<SupportAssignment> upcomingPayments) {
        this.upcomingPayments = upcomingPayments;
    }
}

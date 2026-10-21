
package com.supportmanagement.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "support_assignments")
public class SupportAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "support_person_id", nullable = false)
    private User supportPerson;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(nullable = false, length = 20)
    private String status = "Active";

    @Column(name = "agreed_amount",
            nullable = false, precision = 12, scale = 2)
    private BigDecimal agreedAmount;

    @Column(name = "support_agreed_amount",
            nullable = false, precision = 12, scale = 2)
    private BigDecimal supportAgreedAmount;

    @Column(name = "support_period_days")
    private Integer supportPeriodDays;

    @Column(name = "created_at",
            insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public SupportAssignment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getSupportPerson() {
        return supportPerson;
    }

    public void setSupportPerson(User supportPerson) {
        this.supportPerson = supportPerson;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAgreedAmount() {
        return agreedAmount;
    }

    public void setAgreedAmount(BigDecimal agreedAmount) {
        this.agreedAmount = agreedAmount;
    }

    public BigDecimal getSupportAgreedAmount() {
        return supportAgreedAmount;
    }

    public void setSupportAgreedAmount(BigDecimal supportAgreedAmount) {
        this.supportAgreedAmount = supportAgreedAmount;
    }

    public Integer getSupportPeriodDays() {
        return supportPeriodDays;
    }

    public void setSupportPeriodDays(Integer supportPeriodDays) {
        this.supportPeriodDays = supportPeriodDays;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

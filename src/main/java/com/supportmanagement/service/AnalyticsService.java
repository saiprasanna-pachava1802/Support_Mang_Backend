
package com.supportmanagement.service;

import com.supportmanagement.dto.AnalyticsResponse;
import com.supportmanagement.entity.Payment;
import com.supportmanagement.entity.SupportAssignment;
import com.supportmanagement.repository.PaymentRepository;
import com.supportmanagement.repository.SupportRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
public class AnalyticsService {

    private final SupportRepository supportRepository;
    private final PaymentRepository paymentRepository;

    public AnalyticsService(
            SupportRepository supportRepository,
            PaymentRepository paymentRepository) {
        this.supportRepository = supportRepository;
        this.paymentRepository = paymentRepository;
    }

    public AnalyticsResponse getAnalytics(
            LocalDate fromDate,
            LocalDate toDate,
            String personType,
            Long personId) {

        LocalDate from = fromDate != null
                ? fromDate
                : LocalDate.now().withDayOfMonth(1);

        LocalDate to = toDate != null
                ? toDate
                : LocalDate.now();

        if (from.isAfter(to)) {
            throw new IllegalArgumentException(
                    "From date cannot be after To date");
        }

        String type = personType == null
                ? "ALL"
                : personType.toUpperCase(Locale.ROOT);

        if (!List.of("ALL", "CUSTOMER", "SUPPORT").contains(type)) {
            throw new IllegalArgumentException(
                    "Person type must be ALL, CUSTOMER or SUPPORT");
        }

        List<SupportAssignment> assignments =
                supportRepository.findAll();

        List<SupportAssignment> filtered = assignments.stream()
                .filter(a -> a.getStartDate() != null
                        && !a.getStartDate().isBefore(from)
                        && !a.getStartDate().isAfter(to))
                .filter(a -> personId == null || switch (type) {
                    case "CUSTOMER" ->
                            a.getCustomer() != null
                                    && a.getCustomer().getId().equals(personId);
                    case "SUPPORT" ->
                            a.getSupportPerson() != null
                                    && a.getSupportPerson().getId().equals(personId);
                    default ->
                            (a.getCustomer() != null
                                    && a.getCustomer().getId().equals(personId))
                            || (a.getSupportPerson() != null
                                    && a.getSupportPerson().getId().equals(personId));
                })
                .toList();

        BigDecimal received = BigDecimal.ZERO;
        BigDecimal paid = BigDecimal.ZERO;
        BigDecimal customerPending = BigDecimal.ZERO;
        BigDecimal supportPending = BigDecimal.ZERO;

        LocalDate today = LocalDate.now();
        LocalDate threeDaysLater = today.plusDays(3);

        int paymentsDueInThreeDays = 0;

        List<SupportAssignment> customerDues =
                new java.util.ArrayList<>();

        List<SupportAssignment> supportDues =
                new java.util.ArrayList<>();

        List<SupportAssignment> upcoming =
                new java.util.ArrayList<>();

        for (SupportAssignment assignment : filtered) {

            BigDecimal customerAmount =
                    zeroIfNull(assignment.getAgreedAmount());

            BigDecimal supportAmount =
                    zeroIfNull(assignment.getSupportAgreedAmount());

            BigDecimal customerReceived = BigDecimal.ZERO;
            BigDecimal supportPaid = BigDecimal.ZERO;

            List<Payment> payments =
                    paymentRepository.findBySupportAssignmentId(
                            assignment.getId());

            for (Payment payment : payments) {

                customerReceived = customerReceived.add(
                        zeroIfNull(payment.getAmountReceived()));

                supportPaid = supportPaid.add(
                        zeroIfNull(payment.getAmountPaid()));

                LocalDate dueDate = payment.getNextDueDate();

                if (dueDate != null
                        && !dueDate.isBefore(today)
                        && !dueDate.isAfter(threeDaysLater)) {
                    paymentsDueInThreeDays++;
                }
            }

            received = received.add(customerReceived);
            paid = paid.add(supportPaid);

            BigDecimal customerBalance =
                    customerAmount.subtract(customerReceived);

            BigDecimal supportBalance =
                    supportAmount.subtract(supportPaid);

            if (customerBalance.compareTo(BigDecimal.ZERO) > 0) {
                customerPending =
                        customerPending.add(customerBalance);
                customerDues.add(assignment);
            }

            if (supportBalance.compareTo(BigDecimal.ZERO) > 0) {
                supportPending =
                        supportPending.add(supportBalance);
                supportDues.add(assignment);
            }

            if (payments.stream().anyMatch(payment -> {
                LocalDate dueDate = payment.getNextDueDate();

                return dueDate != null
                        && !dueDate.isBefore(today)
                        && !dueDate.isAfter(threeDaysLater)
                        && (customerBalance.compareTo(BigDecimal.ZERO) > 0
                        || supportBalance.compareTo(BigDecimal.ZERO) > 0);
            })) {
                upcoming.add(assignment);
            }
        }

        AnalyticsResponse response = new AnalyticsResponse();

        response.setAssignments(filtered);
        response.setTotalReceived(received);
        response.setTotalPaid(paid);
        response.setNetProfit(received.subtract(paid));
        response.setCustomerPendingDues(customerPending);
        response.setSupportPaymentsPending(supportPending);
        response.setPaymentsDueInThreeDays(paymentsDueInThreeDays);
        response.setCustomerDues(customerDues);
        response.setSupportDues(supportDues);
        response.setUpcomingPayments(upcoming);

        return response;
    }

    private BigDecimal zeroIfNull(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }
}


package com.supportmanagement.service;

import com.supportmanagement.entity.Payment;
import com.supportmanagement.entity.SupportAssignment;
import com.supportmanagement.repository.PaymentRepository;
import com.supportmanagement.repository.SupportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SupportRepository supportRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            SupportRepository supportRepository) {
        this.paymentRepository = paymentRepository;
        this.supportRepository = supportRepository;
    }

    public Payment savePayment(Payment payment) {

        Long supportId = payment.getSupportAssignment().getId();

        SupportAssignment assignment = supportRepository
                .findById(supportId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Support assignment not found with ID: "
                                        + supportId));

        payment.setSupportAssignment(assignment);

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsBySupportId(Long supportId) {
        return paymentRepository.findBySupportAssignmentId(supportId);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Payment not found with ID: " + id));
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}

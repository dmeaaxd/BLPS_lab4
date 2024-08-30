package ru.danmax.delegates.payments;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.entity.Bill;
import ru.danmax.service.PaymentService;

@Named("showCurrentBill")
@RequiredArgsConstructor
public class ShowCurrentBillDelegate implements JavaDelegate {
    private final PaymentService paymentService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long clientId = (Long) delegateExecution.getVariable("clientId");
        Bill bill = paymentService.getBill(clientId);

        delegateExecution.setVariable("currentBill", bill.getAccountBill() + " у.е.");
    }
}

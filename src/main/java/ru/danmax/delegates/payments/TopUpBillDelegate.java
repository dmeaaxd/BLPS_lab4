package ru.danmax.delegates.payments;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.service.PaymentService;

@Named("topUpBill")
@RequiredArgsConstructor
public class TopUpBillDelegate implements JavaDelegate {
    private final PaymentService paymentService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long clientId = (Long) delegateExecution.getVariable("clientId");
        Long amount = (Long) delegateExecution.getVariable("amount");

        paymentService.topUp(clientId, amount);
    }
}

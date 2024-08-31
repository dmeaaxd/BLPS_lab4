package ru.danmax.app.delegates.subscriptions;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.app.entity.Subscription;
import ru.danmax.app.exceptions.InsufficientFundsException;
import ru.danmax.app.service.SubscriptionService;

import java.time.Period;

@Named("subscribe")
@RequiredArgsConstructor
public class SubscribeDelegate implements JavaDelegate {
    private final SubscriptionService subscriptionService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long clientId = (Long) delegateExecution.getVariable("clientId");
        Long shopId = (Long) delegateExecution.getVariable("shopId");
        int duration = ((Long) delegateExecution.getVariable("duration")).intValue();

        try {
            Subscription subscription = subscriptionService.subscribe(clientId, shopId, duration);

            delegateExecution.setVariable("insufficientFundsFlag", false);
            delegateExecution.setVariable("shopId", subscription.getShop().getId());
            delegateExecution.setVariable("shopName", subscription.getShop().getName());
            delegateExecution.setVariable("endDate", subscription.getStartDate().plus(Period.ofDays(subscription.getDuration())).toString());
        } catch (Exception exception){
            if (exception instanceof InsufficientFundsException){
                delegateExecution.setVariable("insufficientFundsFlag", true);
            }
            else {
                throw exception;
            }
        }
    }
}

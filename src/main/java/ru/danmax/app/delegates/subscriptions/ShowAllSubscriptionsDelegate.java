package ru.danmax.app.delegates.subscriptions;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.app.entity.Subscription;
import ru.danmax.app.service.SubscriptionService;

import java.util.List;

@Named("showAllSubscriptions")
@RequiredArgsConstructor
public class ShowAllSubscriptionsDelegate implements JavaDelegate {
    private final SubscriptionService subscriptionService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long clientId = (Long) delegateExecution.getVariable("clientId");
        List<Subscription> subscriptions = subscriptionService.getSubscriptions(clientId);

        delegateExecution.setVariable("subscriptionCount", subscriptions.size());
        for (Subscription subscription : subscriptions) {
            delegateExecution.setVariable("subscription_" + subscription.getId(),
                    "shopId: " + subscription.getShop().getId() + "\nshopName: " + subscription.getShop().getName());
        }
    }
}

package ru.danmax.delegates.shopDiscount;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.service.ShopDiscountService;

@Named("deleteDiscount")
@RequiredArgsConstructor
public class DeleteDiscountDelegate implements JavaDelegate {
    private final ShopDiscountService shopDiscountService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long discountId = (Long) delegateExecution.getVariable("discountId");
        shopDiscountService.delete(discountId);
    }
}

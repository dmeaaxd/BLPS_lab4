package ru.danmax.delegates.shopDiscount;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.entity.Discount;
import ru.danmax.service.ShopDiscountService;

@Named("showCurrentDiscount")
@RequiredArgsConstructor
public class ShowCurrentDiscountDelegate implements JavaDelegate {
    private final ShopDiscountService shopDiscountService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long discountId = (Long) delegateExecution.getVariable("discountId");
        Discount discount = shopDiscountService.getCurrent(discountId);

        delegateExecution.setVariable("currentDiscount_id", discount.getId());
        delegateExecution.setVariable("currentDiscount_title", discount.getTitle());
        delegateExecution.setVariable("currentDiscount_description", discount.getDescription());
        delegateExecution.setVariable("currentDiscount_promoCode", discount.getPromoCode());
    }
}

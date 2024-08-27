package ru.danmax.delegates.shopDiscount;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.dto.CreateDiscountDTO;
import ru.danmax.service.ShopDiscountService;

@Named("createDiscount")
@RequiredArgsConstructor
public class CreateDiscountDelegate implements JavaDelegate {
    private final ShopDiscountService shopDiscountService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long shopId = (Long) delegateExecution.getVariable("shopId");
        String title = (String) delegateExecution.getVariable("discountTitle");
        String description = (String) delegateExecution.getVariable("discountDescription");
        String promoCode = (String) delegateExecution.getVariable("discountPromoCode");


        shopDiscountService.create(CreateDiscountDTO.builder()
                .shopId(shopId)
                .title(title)
                .description(description)
                .promoCode(promoCode)
                .build());
    }
}

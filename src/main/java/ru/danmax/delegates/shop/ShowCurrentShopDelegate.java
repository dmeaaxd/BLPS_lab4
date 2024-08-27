package ru.danmax.delegates.shop;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.entity.Discount;
import ru.danmax.entity.Shop;
import ru.danmax.service.ShopService;

@Named("showCurrentShop")
@RequiredArgsConstructor
public class ShowCurrentShopDelegate implements JavaDelegate {
    private final ShopService shopService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long shopId = (Long) delegateExecution.getVariable("shopId");
        Shop shop = shopService.getCurrent(shopId);

        delegateExecution.setVariable("currentShop_id", shop.getId());
        delegateExecution.setVariable("currentShop_name", shop.getName());
        delegateExecution.setVariable("currentShop_description", shop.getDescription());
        delegateExecution.setVariable("currentShop_category", shop.getCategory().getName());

        delegateExecution.setVariable("discountCount", shop.getDiscounts().size());
        for (Discount discount : shop.getDiscounts()){
            delegateExecution.setVariable("currentShop_discount_" + discount.getId(), discount.getTitle());
        }
    }
}

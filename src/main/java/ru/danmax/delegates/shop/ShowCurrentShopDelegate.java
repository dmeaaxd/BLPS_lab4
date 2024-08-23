package ru.danmax.delegates.shop;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.entity.Shop;
import ru.danmax.service.ShopService;

import java.util.List;

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

    }
}

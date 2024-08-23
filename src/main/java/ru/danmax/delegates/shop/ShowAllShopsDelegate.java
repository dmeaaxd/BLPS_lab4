package ru.danmax.delegates.shop;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.entity.Shop;
import ru.danmax.service.ShopService;

import java.util.List;

@Named("showAllShops")
@RequiredArgsConstructor
public class ShowAllShopsDelegate implements JavaDelegate {
    private final ShopService shopService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<Shop> shops = shopService.getAll();

        for (Shop shop : shops) {
            delegateExecution.setVariable("shop_" + shop.getId(), shop.getName());
        }
    }
}

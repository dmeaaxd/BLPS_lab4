package ru.danmax.delegates.shop;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.dto.CreateShopDTO;
import ru.danmax.service.ShopService;

@Named("deleteShop")
@RequiredArgsConstructor
public class DeleteShopDelegate implements JavaDelegate {
    private final ShopService shopService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long shopId = (Long) delegateExecution.getVariable("shopId");
        shopService.delete(shopId);
    }
}

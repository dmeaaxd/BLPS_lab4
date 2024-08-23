package ru.danmax.delegates.shop;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.dto.CreateShopDTO;
import ru.danmax.dto.UpdateShopDTO;
import ru.danmax.service.ShopService;

@Named("updateShop")
@RequiredArgsConstructor
public class UpdateShopDelegate implements JavaDelegate {
    private final ShopService shopService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long shopId = (Long) delegateExecution.getVariable("shopId");
        String name = (String) delegateExecution.getVariable("shopName");
        String description = (String) delegateExecution.getVariable("shopDescription");
        Long categoryId = (Long) delegateExecution.getVariable("shopCategory");

        shopService.update(UpdateShopDTO.builder()
                .shopId(shopId)
                .name(name)
                .description(description)
                .categoryId(categoryId)
                .build());
    }
}

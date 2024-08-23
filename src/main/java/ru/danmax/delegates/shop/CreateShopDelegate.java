package ru.danmax.delegates.shop;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.dto.CreateShopDTO;
import ru.danmax.service.CategoryService;
import ru.danmax.service.ShopService;

@Named("createShop")
@RequiredArgsConstructor
public class CreateShopDelegate implements JavaDelegate {
    private final ShopService shopService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String name = (String) delegateExecution.getVariable("shopName");
        String description = (String) delegateExecution.getVariable("shopDescription");
        Long categoryId = (Long) delegateExecution.getVariable("shopCategory");

        shopService.create(CreateShopDTO.builder()
                .name(name)
                .description(description)
                .categoryId(categoryId)
                .build());
    }
}

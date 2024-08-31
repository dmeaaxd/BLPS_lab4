package ru.danmax.app.delegates.shopAdmins;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.app.entity.Client;
import ru.danmax.app.service.ShopAdminsService;

import java.util.List;

@Named("showAllShopAdmins")
@RequiredArgsConstructor
public class ShowAllShopAdminsDelegate implements JavaDelegate {
    private final ShopAdminsService shopAdminsService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long shopId = (Long) delegateExecution.getVariable("shopId");
        List<Client> admins = shopAdminsService.getShopAdmins(shopId);

        delegateExecution.setVariable("currentShop_adminsCount", admins.size());
        for (Client admin : admins) {
            delegateExecution.setVariable("currentShop_admin_" + admin.getId(), admin.getUsername());
        }
    }
}

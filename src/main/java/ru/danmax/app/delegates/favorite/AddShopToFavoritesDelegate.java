package ru.danmax.app.delegates.favorite;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.app.service.FavoriteService;

@Named("addShopToFavorites")
@RequiredArgsConstructor
public class AddShopToFavoritesDelegate implements JavaDelegate {
    private final FavoriteService favoriteService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long clientId = (Long) delegateExecution.getVariable("clientId");
        Long shopId = (Long) delegateExecution.getVariable("shopId");

        favoriteService.add(clientId, shopId);
    }
}

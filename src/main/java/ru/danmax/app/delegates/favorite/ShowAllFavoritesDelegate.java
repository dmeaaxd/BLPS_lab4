package ru.danmax.app.delegates.favorite;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import ru.danmax.app.entity.Favorite;
import ru.danmax.app.service.FavoriteService;

import java.util.List;

@Named("showAllFavorites")
@RequiredArgsConstructor
public class ShowAllFavoritesDelegate implements JavaDelegate {
    private final FavoriteService favoriteService;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long clientId = (Long) delegateExecution.getVariable("clientId");

        List<Favorite> favorites = favoriteService.getAll(clientId);
        delegateExecution.setVariable("favoriteCount", favorites.size());

        for (Favorite favorite : favorites){
            delegateExecution.setVariable("favorite_" + favorite.getId(),
                    "shop_id: " + favorite.getShop().getId() + "\nshop_name: " + favorite.getShop().getName());
        }
    }
}

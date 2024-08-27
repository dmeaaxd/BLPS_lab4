package ru.danmax.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.danmax.entity.Client;
import ru.danmax.entity.Favorite;
import ru.danmax.entity.Shop;
import ru.danmax.repository.ClientRepository;
import ru.danmax.repository.FavoriteRepository;
import ru.danmax.repository.ShopRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ClientRepository clientRepository;
    private final ShopRepository shopRepository;

    public List<Favorite> getAll(Long clientId) throws Exception {
        if (clientId == null) {
            throw new Exception("Client id cannot be empty");
        }

        return favoriteRepository.findAllByClientId(clientId);
    }

    public void add(Long clientId, Long shopId) throws Exception {
        if (clientId == null) {
            throw new Exception("Client id cannot be empty");
        }

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new Exception("User not found"));

        if (shopId == null) {
            throw new Exception("Shop id cannot be empty");
        }

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new Exception("Shop not found"));

        if (favoriteRepository.existsByClientIdAndShopId(clientId, shopId)) {
            return;
        }

        favoriteRepository.save(Favorite.builder()
                .client(client)
                .shop(shop)
                .build());
    }

    public void remove(Long clientId, Long shopId) throws Exception {
        if (clientId == null) {
            throw new Exception("Client id cannot be empty");
        }

        if (shopId == null) {
            throw new Exception("Shop id cannot be empty");
        }

        Favorite favorite = favoriteRepository.findByClientIdAndShopId(clientId, shopId)
                .orElseThrow(() -> new Exception("Favorite not found"));

        favoriteRepository.delete(favorite);
    }
}

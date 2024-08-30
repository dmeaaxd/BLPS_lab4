package ru.danmax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danmax.entity.Client;
import ru.danmax.entity.Shop;
import ru.danmax.entity.Subscription;

import java.util.List;


public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByClientAndShop(Client client, Shop shop);

    List<Subscription> findAllByClientId(Long clientId);
    List<Subscription> findAllByShopId(Long shopId);
}

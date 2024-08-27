package ru.danmax.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.danmax.dto.CreateDiscountDTO;
import ru.danmax.dto.UpdateDiscountDTO;
import ru.danmax.entity.Shop;
import ru.danmax.entity.Discount;
import ru.danmax.repository.DiscountRepository;
import ru.danmax.repository.ShopRepository;

@Service
@AllArgsConstructor
public class ShopDiscountService {
    private final ShopRepository shopRepository;
    private final DiscountRepository discountRepository;
//    private final ClientRepository clientRepository;
//    private final SubscriptionRepository subscriptionRepository;

//    private final JmsNotificationSender jmsNotificationSender;

    public Discount getCurrent(Long discountId) throws Exception {
        if (discountId == null) {
            throw new Exception("Discount id cannot be empty");
        }

        return discountRepository.findById(discountId)
                .orElseThrow(() -> new Exception("Discount not found"));
    }

    public void create(CreateDiscountDTO createDiscountDTO) throws Exception {
        if (createDiscountDTO.isFieldEmpty()) {
            throw new Exception("Fields cannot be empty");
        }

        Shop shop = shopRepository.findById(createDiscountDTO.getShopId())
                .orElseThrow(() -> new Exception("Shop not found"));

        Discount discount = Discount.builder()
                .title(createDiscountDTO.getTitle())
                .description(createDiscountDTO.getDescription())
                .promoCode(createDiscountDTO.getPromoCode())
                .shop(shop)
                .build();
        discountRepository.save(discount);

// TODO: Добавить оповещение о новых предложениях, когда будет доступна функция

//        List<Subscription> subscriptions = subscriptionRepository.findAllByShopId(shop.getId());
//        for (Subscription subscription : subscriptions){
//            Client shopClient = subscription.getClient();
//            jmsNotificationSender.sendNotification(NotificationJmsMessage.builder()
//                    .to(shopClient.getEmail())
//                    .theme("Новое предложение магазина " + shop.getName())
//                    .text(discount.getTitle() + "\n" + discount.getDescription() + "\nПромокод: " + discount.getPromoCode())
//                    .build());
        }

    public void update(UpdateDiscountDTO updateDiscountDTO) throws Exception {
        if (updateDiscountDTO.isAllFieldsEmpty()) {
            throw new Exception("All fields cannot be empty");
        }

        Discount discount = getCurrent(updateDiscountDTO.getDiscountId());

        if (!(updateDiscountDTO.getTitle() == null || updateDiscountDTO.getTitle().isEmpty())) {
            discount.setTitle(updateDiscountDTO.getTitle());
        }

        if (!(updateDiscountDTO.getDescription() == null || updateDiscountDTO.getDescription().isEmpty())) {
            discount.setDescription(updateDiscountDTO.getDescription());
        }

        if (!(updateDiscountDTO.getPromoCode() == null || updateDiscountDTO.getPromoCode().isEmpty())) {
            discount.setPromoCode(updateDiscountDTO.getPromoCode());
        }

        discountRepository.save(discount);
    }

    public void delete(Long discountId) throws Exception {
        if (discountId == null) {
            throw new Exception("Discount id cannot be empty");
        }

        if (discountRepository.existsById(discountId)) discountRepository.deleteById(discountId);
        else throw new Exception("Discount not found");
    }

}

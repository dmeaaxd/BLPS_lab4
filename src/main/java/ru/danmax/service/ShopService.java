package ru.danmax.service;

import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danmax.dto.CreateShopDTO;
import ru.danmax.dto.UpdateShopDTO;
import ru.danmax.entity.Category;
import ru.danmax.entity.Shop;
import ru.danmax.repository.CategoryRepository;
import ru.danmax.repository.ClientRepository;
import ru.danmax.repository.ShopRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final ClientRepository clientRepository;
    private final CategoryRepository categoryRepository;

    public List<Shop> getAll() {
        return shopRepository.findAll();
    }


    public Shop getCurrent(Long shopId) throws Exception {
        if (shopId == null) {
            throw new Exception("Shop id cannot be empty");
        }

        return shopRepository.findById(shopId)
                .orElseThrow(() -> new Exception("Shop not found"));
    }


    public void create(CreateShopDTO createShopDTO) throws Exception {
        if (createShopDTO.isFieldEmpty()) {
            throw new Exception("Fields cannot be empty");
        }

        Category category = categoryRepository.findById(createShopDTO.getCategoryId())
                .orElseThrow(() -> new Exception("Category not found"));

        if (shopRepository.existsByName(createShopDTO.getName())) {
            throw new Exception("Shop name is already taken");
        }

        Shop shop = Shop.builder()
                .name(createShopDTO.getName())
                .description(createShopDTO.getDescription())
                .category(category)
                .build();

        shopRepository.save(shop);
    }


    public void update(UpdateShopDTO updateShopDTO) throws Exception {
        if (updateShopDTO.isAllFieldsEmpty()) {
            throw new Exception("All fields cannot be empty");
        }

        Shop shop = getCurrent(updateShopDTO.getShopId());

        if (!(updateShopDTO.getName() == null || updateShopDTO.getName().isEmpty())) {
            if (shopRepository.existsByName(updateShopDTO.getName())) {
                throw new Exception("Shop name is already taken");
            }

            shop.setName(updateShopDTO.getName());
        }

        if (!(updateShopDTO.getDescription() == null || updateShopDTO.getDescription().isEmpty())) {
            shop.setDescription(updateShopDTO.getDescription());
        }

        if (updateShopDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(updateShopDTO.getCategoryId())
                    .orElseThrow(() -> new Exception("Category not found"));

            shop.setCategory(category);
        }

        shopRepository.save(shop);
    }

    @Transactional(rollbackFor = ObjectNotFoundException.class)
    public void delete(Long shopId) throws Exception {
        if (shopId == null) {
            throw new Exception("Shop id cannot be empty");
        }

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ObjectNotFoundException(shopId, "Shop"));

        try {
            clientRepository.deleteAll(shop.getAdmins());
// TODO: Поправить, когда будет возможность

//            discountRepository.deleteAll(shop.getDiscounts());
//            favoriteRepository.deleteAll(favoriteRepository.findAllByShopId(shop.getId()));
//            subscriptionRepository.deleteAll(subscriptionRepository.findAllByShopId(shop.getId()));
        } catch (Exception e) {
            throw new Exception("System error: Cannot delete related posts");
        }

        shopRepository.deleteById(shopId);
    }
}

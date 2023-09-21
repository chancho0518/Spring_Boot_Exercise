package com.crud.miniproject.service;

import com.crud.miniproject.repository.items.ItemEntity;
import com.crud.miniproject.repository.items.ItemRepository;
import com.crud.miniproject.repository.storeSales.StoreSales;
import com.crud.miniproject.repository.storeSales.StoreSalesRepository;
import com.crud.miniproject.web.dto.BuyOrder;
import com.crud.miniproject.web.dto.Item;
import com.crud.miniproject.web.dto.ItemBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final StoreSalesRepository storeSalesRepository;

    public List<Item> findAllItem() {
        List<ItemEntity> itemEntities = itemRepository.findAllItems();

        return itemEntities.stream().map(Item::new).collect(Collectors.toList());
    }

    public Integer saveItem(ItemBody itemBody) {
        ItemEntity itemEntity = new ItemEntity(null, itemBody.getName(), itemBody.getType(), itemBody.getPrice(), itemBody.getSpec().getCpu(), itemBody.getSpec().getCapacity());

        return itemRepository.saveItem(itemEntity);
    }

    public Item findItemById(String id) {
        Integer idInt = Integer.valueOf(id);
        ItemEntity itemEntity = itemRepository.findItemById(idInt);

        return new Item(itemEntity);
    }

    public List<Item> findItemsByIds(List<String> ids) {
        List<ItemEntity> itemEntities = itemRepository.findAllItems();
        List<Item> items = itemEntities.stream()
                                       .map(Item::new)
                                       .filter((item -> ids.contains(item.getId())))
                                       .collect(Collectors.toList());

        return items;
    }

    public void deleteItem(String id) {
        Integer idInt = Integer.valueOf(id);
        itemRepository.deleteItem(idInt);
    }

    public Item updateItem(String id, ItemBody itemBody) {
        Integer idInt = Integer.valueOf(id);

        ItemEntity itemEntity = new ItemEntity(idInt, itemBody.getName(), itemBody.getType(), itemBody.getPrice(), itemBody.getSpec().getCpu(), itemBody.getSpec().getCapacity());
        ItemEntity itemEntityUpdated = itemRepository.updateItem(idInt, itemEntity);

        return new Item(itemEntityUpdated);
    }

    @Transactional
    public Integer buyItems(BuyOrder buyOrder) {
        // 1. BuyOrder 에서 상품 ID와 수량을 얻어낸다.
        // 2. 상품을 조회하여 수량을 확인한다.
        // 3. 상품의 수량과 가격으로 총 구매 가격을 계산한다.
        // 4. 상품의 재고에 기존에 계산한 재고를 적용한다.
        // 5. 상품으로 사용한 재고 * 가격 만큼 가계의 매상을 올린다.
        // (단, 재고가 없거나 매장을 찾을 수 없으면 구매할 수 없다.)

        Integer itemId = buyOrder.getItemId();
        Integer itemNums = buyOrder.getItemNums();
        ItemEntity itemEntity = itemRepository.findItemById(itemId);

        if(itemEntity.getStoreId() == null) throw new RuntimeException("매장을 찾을 수 없습니다.");
        if(itemEntity.getStock() <= 0) throw  new RuntimeException("상품의 재고가 없습니다.");

        Integer buyItemNums;
        if(itemNums >= itemEntity.getStock()) buyItemNums = itemEntity.getStock();
        else buyItemNums = itemNums;

        Integer totalPrice = buyItemNums * itemEntity.getPrice();

        // Item 재고 감소
        Integer finalStock = itemEntity.getStock() - buyItemNums;
        itemRepository.updateItemStock(itemId, finalStock);

        // 매장 매상 추가
        StoreSales storeSales = storeSalesRepository.findStoreSalesById(itemEntity.getStoreId());
        Integer finalAmount = storeSales.getAmount() + totalPrice;
        storeSalesRepository.updateSalesAmount(itemEntity.getStoreId(), finalAmount);

        return buyItemNums;
    }
}

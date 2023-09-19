package com.crud.miniproject.service;

import com.crud.miniproject.repository.ItemEntity;
import com.crud.miniproject.repository.ItemRepository;
import com.crud.miniproject.web.dto.Item;
import com.crud.miniproject.web.dto.ItemBody;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAllItem() {
        List<ItemEntity> itemEntities = itemRepository.findAllItems();

        return itemEntities.stream().map(Item::new).collect(Collectors.toList());
    }

    public Integer saveItem(ItemBody itemBody) {
        ItemEntity itemEntity = new ItemEntity(null, itemBody.getName(), itemBody.getType(), itemBody.getPrice(), itemBody.getSpec().getCpu(), itemBody.getSpec().getCapacity());

        return itemRepository.saveItem(itemEntity);
    }

    public Item findItemById(String id) {
        Integer idInt = Integer.parseInt(id);
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
        Integer idInt = Integer.parseInt(id);
        itemRepository.deleteItem(idInt);
    }

    public Item updateItem(String id, ItemBody itemBody) {
        Integer idInt = Integer.parseInt(id);

        ItemEntity itemEntity = new ItemEntity(idInt, itemBody.getName(), itemBody.getType(), itemBody.getPrice(), itemBody.getSpec().getCpu(), itemBody.getSpec().getCapacity());
        ItemEntity itemEntityUpdated = itemRepository.updateItem(idInt, itemEntity);

        return new Item(itemEntityUpdated);
    }
}

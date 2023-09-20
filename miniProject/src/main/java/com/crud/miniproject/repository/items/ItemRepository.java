package com.crud.miniproject.repository.items;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository {

    List<ItemEntity> findAllItems();

    Integer saveItem(ItemEntity itemEntity);

    ItemEntity updateItem(Integer idInt, ItemEntity itemEntity);

    ItemEntity findItemById(Integer idInt);

    void deleteItem(Integer idInt);

    void updateItemStock(Integer itemId, Integer finalStock);
}

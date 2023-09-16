package com.crud.miniproject.repository;

import com.crud.miniproject.web.dto.Item;
import com.crud.miniproject.web.dto.ItemBody;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository {

    List<ItemEntity> findAllItems();

    Integer saveItem(ItemEntity itemEntity);

    ItemEntity updateItem(Integer idInt, ItemEntity itemEntity);
}

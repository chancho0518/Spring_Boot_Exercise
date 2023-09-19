package com.crud.miniproject.repository;

import com.crud.miniproject.web.dto.Item;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ItemJavaListDao implements ItemRepository {

    private static int serialItemId = 1;

    private List<ItemEntity> items = Arrays.asList(
            new ItemEntity(serialItemId++, "Apple iPhone 12Pro Max", "Smartphone", 1490000, "A14 Bionic", "512GB"),
            new ItemEntity(serialItemId++, "Samsung Galaxy S21 Ultra", "Smartphone", 1690000, "Exynos 2100", "256GB"),
            new ItemEntity(serialItemId++, "Google Pixel 6 Pro", "Smartphone", 1290000, "Google Tensor", "128GB"),
            new ItemEntity(serialItemId++, "Dell XPS 15", "Laptop", 2290000, "Intel Core 9", "1TB SSD")
    );

    @Override
    public List<ItemEntity> findAllItems() {

        return items;
    }

    @Override
    public Integer saveItem(ItemEntity itemEntity) {
        itemEntity.setId(serialItemId++);
        items.add(itemEntity);

        return itemEntity.getId();
    }

    @Override
    public ItemEntity findItemById(Integer idInt) {
        return null;
    }

    @Override
    public void deleteItem(Integer idInt) {

    }

    @Override
    public ItemEntity updateItem(Integer idInt, ItemEntity itemEntity) {
        ItemEntity itemFounded = items.stream()
                .filter((item) -> item.getId().equals(idInt))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());

        items.remove(itemFounded);

        itemEntity.setId(idInt);
        items.add(itemEntity);

        return itemEntity;
    }
}

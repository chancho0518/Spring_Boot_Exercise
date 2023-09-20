package com.crud.miniproject.repository.items;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class ItemJdbcDao implements ItemRepository {

    private JdbcTemplate jdbcTemplate;

    static RowMapper<ItemEntity> itemEntityRowMapper = (((rs, rowNum) ->
                new ItemEntity(
                        rs.getInt("id"),
                        rs.getNString("name"),
                        rs.getNString("type"),
                        rs.getInt("price"),
                        rs.getInt("store_id"),
                        rs.getInt("stock"),
                        rs.getNString("cpu"),
                        rs.getNString("capacity")
                )
    ));

    public ItemJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ItemEntity> findAllItems() {
        return jdbcTemplate.query("SELECT * FROM new_items", itemEntityRowMapper);
    }

    @Override
    public Integer saveItem(ItemEntity itemEntity) {
        jdbcTemplate.update("INSERT INTO new_items(name, type, price, cpu, capacity) VALUES(?, ?, ?, ?, ?)",
                itemEntity.getName(),
                itemEntity.getType(),
                itemEntity.getPrice(),
                itemEntity.getCpu(),
                itemEntity.getCapacity()
        );

        ItemEntity itemEntityFound = jdbcTemplate.queryForObject("SELECT * FROM new_items WHERE name=?", itemEntityRowMapper, itemEntity.getName());

        return itemEntityFound.getId();
    }

    @Override
    public ItemEntity findItemById(Integer idInt) {
        return jdbcTemplate.queryForObject("SELECT * FROM new_items WHERE id=?", itemEntityRowMapper, idInt);
    }

    @Override
    public void deleteItem(Integer idInt) {
        jdbcTemplate.update("DELETE FROM new_items WHERE id=?", idInt);
    }

    @Override
    public void updateItemStock(Integer itemId, Integer stock) {
        jdbcTemplate.update("UPDATE new_items SET stock=? WHERE id=?", stock, itemId);
    }

    @Override
    public ItemEntity updateItem(Integer idInt, ItemEntity itemEntity) {
        jdbcTemplate.update("UPDATE new_items SET name=?, type=?, price=?, cpu=?, capacity=? WHERE id=?",
                itemEntity.getName(),
                itemEntity.getType(),
                itemEntity.getPrice(),
                itemEntity.getCpu(),
                itemEntity.getCapacity(),
                idInt
        );

        return jdbcTemplate.queryForObject("SELECT * FROM new_items WHERE id = ?", itemEntityRowMapper, idInt);
    }
}

package com.crud.miniproject.repository;

import com.crud.miniproject.web.dto.Item;
import com.crud.miniproject.web.dto.ItemBody;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemJdbcDao implements ItemRepository {

    private JdbcTemplate jdbcTemplate;

    static RowMapper<ItemEntity> itemEntityRowMapper = (((rs, rowNum) ->
                new ItemEntity(
                        rs.getInt("id"),
                        rs.getNString("name"),
                        rs.getNString("type"),
                        rs.getInt("price"),
                        rs.getNString("cpu"),
                        rs.getNString("capacity")
                )
    ));

    public ItemJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ItemEntity> findAllItems() {

        return jdbcTemplate.query("SELECT * FROM items", itemEntityRowMapper);
    }

    @Override
    public Integer saveItem(ItemEntity itemEntity) {
        jdbcTemplate.update("INSERT INTO items(name, type, price, cpu, capacity) VALUES (?, ?, ?, ?, ?)",
                itemEntity.getName(),
                itemEntity.getType(),
                itemEntity.getPrice(),
                itemEntity.getCpu(),
                itemEntity.getCapacity()
        );

        ItemEntity itemEntityFound = jdbcTemplate.queryForObject("SELECT * FROM item WHERE name = ?", itemEntityRowMapper, itemEntity.getName());

        return itemEntityFound.getId();
    }

    @Override
    public ItemEntity updateItem(Integer idInt, ItemEntity itemEntity) {
        jdbcTemplate.update("UPDATE items SET name=?, type=?, price=?, cpu=?, capacity=? WHERE id=?",
                itemEntity.getName(),
                itemEntity.getType(),
                itemEntity.getPrice(),
                itemEntity.getCpu(),
                itemEntity.getCapacity(),
                idInt
        );

        return jdbcTemplate.queryForObject("SELECT * FROM items WHERE id = ?", itemEntityRowMapper,idInt);
    }
}

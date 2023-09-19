package com.crud.miniproject.web.controller;

import com.crud.miniproject.repository.ItemEntity;
import com.crud.miniproject.repository.ItemRepository;
import com.crud.miniproject.service.ItemService;
import com.crud.miniproject.web.dto.Item;
import com.crud.miniproject.web.dto.ItemBody;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class Controller {

    private ItemService itemService;

    public Controller(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public List<Item> findAllItem() {
        return itemService.findAllItem();
    }

    @PostMapping("/items")
    public String registerItem(@RequestBody ItemBody itemBody) {
        return "ID: " + itemService.saveItem(itemBody);
    }

    @GetMapping("/items/{id}")
    public Item findItemByPathId(@PathVariable String id) {
        return itemService.findItemById(id);
    }

    @GetMapping("/items-query")
    public Item findItemByQueryId(@RequestParam("id") String id) {
        return itemService.findItemById(id);
    }

    @GetMapping("/items-queries")
    public List<Item> findItemsByQueryIds(@RequestParam("id") List<String> ids) {
        return itemService.findItemsByIds(ids);
    }

    @DeleteMapping("/items/{id}")
    public String DeleteItemByPathId(@PathVariable String id) {
        itemService.deleteItem(id);
        return "Object with id = " + id + " has been deleted!!";
    }

    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable String id, @RequestBody ItemBody itemBody) {
        return itemService.updateItem(id, itemBody);
    }
}

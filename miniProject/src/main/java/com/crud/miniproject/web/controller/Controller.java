package com.crud.miniproject.web.controller;

import com.crud.miniproject.repository.ItemRepository;
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

    private ItemRepository itemRepository;

    public Controller(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private static int serialItemId = 1;
    private List<Item> items = new ArrayList<>(Arrays.asList(
            new Item(String.valueOf(serialItemId++), "Apple iPhone 12Pro Max", "Smartphone", 1490000, "A14 Bionic", "512GB"),
            new Item(String.valueOf(serialItemId++), "Samsung Galaxy S21 Ultra", "Smartphone", 1690000, "Exynos 2100", "256GB"),
            new Item(String.valueOf(serialItemId++), "Google Pixel 6 Pro", "Smartphone", 1290000, "Google Tensor", "128GB"),
            new Item(String.valueOf(serialItemId++), "Dell XPS 15", "Laptop", 2290000, "Intel Core 9", "1TB SSD")
    ));

    @GetMapping("/items")
    public List<Item> findAllItem() {
        // return items;
        return itemRepository.findAllItems();
    }

    @PostMapping("/items")
    public String registerItem(@RequestBody ItemBody itemBody) {
        Item newItem = new Item(serialItemId++, itemBody);
        items.add(newItem);

        return "ID: " + newItem.getId();
    }

    @GetMapping("/items/{id}")
    public Item findItemByPathId(@PathVariable String id) {
        Item itemFounded = items.stream()
                                .filter((item -> item.getId().equals(id)))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException());

        return itemFounded;
    }

    @GetMapping("/items-query")
    public Item findItemByQueryId(@RequestParam("id") String id) {
        Item itemFounded = items.stream()
                .filter((item -> item.getId().equals(id)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());

        return itemFounded;
    }

    @GetMapping("/items-queries")
    public List<Item> findItemByQueryIds(@RequestParam("id") List<String> ids) {
        Set<String> idSet = ids.stream().collect(Collectors.toSet());

        List<Item> itemsFound = items.stream()
                .filter((item -> idSet.contains(item.getId())))
                .collect(Collectors.toList());

        return itemsFound;
    }

    @DeleteMapping("/items/{id}")
    public String DeleteItemByPathId(@PathVariable String id) {
        Item itemFounded = items.stream()
                .filter((item -> item.getId().equals(id)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());

        items.remove(itemFounded);

        return "Object with id = " + itemFounded.getId() +"(" + itemFounded.getName() + ") " + "has been deleted!!";
    }

    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable String id, @RequestBody ItemBody itemBody) {
        Item itemFounded = items.stream()
                .filter((item -> item.getId().equals(id)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());

        items.remove(itemFounded);

        Item itemUpdated = new Item(Integer.valueOf(id), itemBody);
        items.add(itemUpdated);

        return itemUpdated;
    }
}

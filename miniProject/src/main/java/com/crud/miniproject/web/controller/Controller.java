package com.crud.miniproject.web.controller;

import com.crud.miniproject.service.ItemService;
import com.crud.miniproject.web.dto.BuyOrder;
import com.crud.miniproject.web.dto.Item;
import com.crud.miniproject.web.dto.ItemBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/items/buy")
    public String buyItem(@RequestBody BuyOrder buyOrder) {
        Integer orderItemNums = itemService.buyItems(buyOrder);
        return "요청하신 Item 중 " + orderItemNums + " 개를 구매 하였습니다.";
    }
}

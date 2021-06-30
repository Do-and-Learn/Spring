package idv.teddy.controller;

import idv.teddy.domain.Item;
import idv.teddy.repository.ItemRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping(path = "item")
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping(path = "all")
    public Iterable<Item> items() {
        return itemRepository.findAll();
    }

    @PostMapping
    public Item postItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }
}

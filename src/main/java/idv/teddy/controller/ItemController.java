package idv.teddy.controller;

import idv.teddy.domain.Item;
import idv.teddy.repository.ItemRepository;
import lombok.Data;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@RestController
@RequestMapping(path = "item")
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping(path = "all")
    public CollectionModel<EntityModel<Item>> items() {
        Iterable<EntityModel<Item>> all = StreamSupport.stream(itemRepository.findAll().spliterator(), false).map(item ->
                EntityModel.of(item, linkTo(methodOn(ItemController.class).item(item.getId())).withSelfRel())
        )::iterator;
        return CollectionModel.of(all, linkTo(methodOn(ItemController.class).items()).withSelfRel());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EntityModel<Item>> item(@PathVariable("id") Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return ResponseEntity.of(item.map(value -> EntityModel.of(value,
                    linkTo(methodOn(ItemController.class).item(id)).withSelfRel())));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EntityModel<Item> postItem(@RequestBody Item item) {
        Item newItem = itemRepository.save(item);
        return EntityModel.of(newItem,
                linkTo(methodOn(ItemController.class).postItem(newItem)).withSelfRel(),
                linkTo(methodOn(ItemController.class).items()).withRel("all")
        );
    }
}

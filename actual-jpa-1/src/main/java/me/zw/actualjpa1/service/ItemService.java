package me.zw.actualjpa1.service;

import lombok.RequiredArgsConstructor;
import me.zw.actualjpa1.domain.item.Book;
import me.zw.actualjpa1.domain.item.Item;
import me.zw.actualjpa1.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(long itemId) {
        return itemRepository.findOne(itemId);
    }

    // Dirty checking 을 통한 데이터 변경
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }
}

package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repoistory.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired private ItemService itemService;
    @Autowired private ItemRepository itemRepository;
    @Autowired private EntityManager em;

    @Test
    void saveItem() {
        //given
        Item book = new Book();
        book.setName("alice");
        book.setPrice(10000);

        //when
        Long bookId = itemService.saveItem(book);

        //then
        assertEquals(itemRepository.findOne(bookId), book);
    }
}
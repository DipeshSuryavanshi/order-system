package com.order.system.repository;

import com.order.system.entity.Item;
import com.order.system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    public Item findByItemId(Long id);
    //public List<Order> findAllByItems_ItemId(Long itemId);

}

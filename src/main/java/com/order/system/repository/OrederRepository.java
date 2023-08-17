package com.order.system.repository;

import com.order.system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrederRepository extends JpaRepository<Order,Long> {
    public Order findByOrderId(Long id);
//    @Query(value = "SELECT DISTINCT o.order_id,i.item_id FROM order_detail o INNER JOIN item_detail i ON o.order_id = i.order_id WHERE i.item_id = :itemId", nativeQuery = true)
//   public List<Order[]> findOrderIdsAndItemIdsByItemId(Long itemId);
//@Query("SELECT DISTINCT o.orderId, i.itemId FROM Order o JOIN o.itemList i WHERE i.itemId = :itemId")
//public List<Order[]> findOrderIdsAndItemIdsByItemId(@Param("itemId") Long itemId);
@Query("SELECT o.orderId FROM Order o JOIN  o.itemList i WHERE i.itemId=:itemId")
public List<Long> findOrderIdsAndItemIdsByItemId(Long itemId);



    // List<Long> findOrderIdsByItemId(Long itemId);
}

    //public List<Order> findAllByItemList_ItemId(Long itemId);




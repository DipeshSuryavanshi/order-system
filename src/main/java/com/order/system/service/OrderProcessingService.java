package com.order.system.service;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderItemDTO;
import com.order.system.dto.Status;
import com.order.system.entity.Item;
import com.order.system.entity.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface OrderProcessingService {
    public Order saveOrderWithItems(Order order);
    public Order saveItem(Set<Item> item,Long id);
    public List<Order> getOrderWithItems();
    public Order getOrderWithId(Long id);
    public Order updateOrderWithItems(Order order, Long id);
    public String deleteOrderWithItems(Long id);
    public List<Item> getAllListOfOrderedItems();
    public Set<Item> getAllListOfOrderedItemsById(Long id);
    public ItemDTO updateExistingOrderItem(Item item, Long orderid) throws SQLException
            ;
    public String deleteOrderedItemById(Long itemId);
    public OrderItemDTO findOrderIdsAndItemIdsByItemId(Long itemId);

    public String updateOrderStatus(Long orderId,Status newstatus);

}

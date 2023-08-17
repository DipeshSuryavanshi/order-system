package com.order.system.controller;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderItemDTO;
import com.order.system.dto.Status;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.service.OrderProcessingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@RestController
public class OrderProcessingController {
    @Autowired
    OrderProcessingServiceImpl orderProcessingService;


    @PostMapping(value="/api/orders", consumes = "application/json")
    public ResponseEntity<Order> addOrderWithItems(@RequestBody Order order) throws SQLException {
        Order Order=	orderProcessingService.saveOrderWithItems(order);
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }
    @GetMapping (value="/api/orders")
    public ResponseEntity<List<Order>> getOrderWithItems() throws SQLException {
       List<Order> OrderList=	orderProcessingService.getOrderWithItems();
        return new ResponseEntity<>(OrderList, HttpStatus.OK);
    }
    @GetMapping (value="/api/orders/{id}")
    public ResponseEntity<Order> getOrderWithId(@PathVariable Long id) throws SQLException {
        Order Order=	orderProcessingService.getOrderWithId(id);
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }
    @PutMapping (value="/api/orders/{id}")
    public ResponseEntity<Order> updateOrderWithItems(@RequestBody Order order ,@PathVariable Long id) throws SQLException {
        Order Order=	orderProcessingService.updateOrderWithItems(order,id);
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }
    @DeleteMapping (value="/api/orders/{id}")
    public ResponseEntity<String> deleteeOrderWithItems(@PathVariable Long id) throws SQLException {
        orderProcessingService.deleteOrderWithItems(id);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }
    @GetMapping (value="/api/order-items")
    public ResponseEntity<List<Item>> getAllListOfOrderedItems() throws SQLException {
        List<Item> itemList=	orderProcessingService.getAllListOfOrderedItems();
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }
    @GetMapping (value="/api/order-items/{id}")
    public ResponseEntity<Set<Item>> getAllListOfOrderedItemsById(@PathVariable Long id) throws SQLException {
        Set<Item> itemList=	orderProcessingService.getAllListOfOrderedItemsById(id);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }
    @PutMapping (value="/api/order-items/{id}")
    public ResponseEntity<ItemDTO> updateExistingOrderItem(@RequestBody Item item, @PathVariable Long orderid) throws SQLException {
        ItemDTO ite=	orderProcessingService.updateExistingOrderItem(item,orderid);
        return new ResponseEntity<>(ite, HttpStatus.OK);
    }
    @DeleteMapping (value="/api/order-items/{id}")
    public ResponseEntity<String> deleteOrderedItemById(@PathVariable Long id) throws SQLException {
        orderProcessingService.deleteOrderedItemById(id);
        return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);
    }
    @PostMapping (value="/api/order-items/{id}")
    public ResponseEntity<Order> addOrderedItem(@RequestBody Set<Item> item, @PathVariable Long id ) throws SQLException {
        Order order=new Order();
        order=orderProcessingService.saveItem(item,id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
//    @GetMapping("/api/item/{itemId}")
//    public ResponseEntity<List<Order>> getOrdersByItemId(@PathVariable Long itemId) {
//      List<Order> orderList = orderProcessingService.findOrdersByItemId(itemId);
//      return new ResponseEntity<>(orderList,HttpStatus.OK);
//    }
//    @GetMapping("/api/item/{itemId}")
//    public ResponseEntity<List<Order[]>> getOrdersByItemId(@PathVariable Long itemId) {
//        List<Order[]> orderList = orderProcessingService.findOrderIdsAndItemIdsByItemId(itemId);
//        return new ResponseEntity<>(orderList,HttpStatus.OK);
@GetMapping("api/item/{itemId}")
public OrderItemDTO getOrderIdsAndItemIdsByItemId(@PathVariable Long itemId) {
    OrderItemDTO orderItemDTO = orderProcessingService.findOrderIdsAndItemIdsByItemId(itemId);
    return orderItemDTO;
}
    @PutMapping("api/order/{orderId}/status/{status}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @PathVariable Status status) {
        String updatedOrder = orderProcessingService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updatedOrder);
    }

}



package com.order.system.service;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderItemDTO;
import com.order.system.dto.Status;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.repository.ItemRepository;
import com.order.system.repository.OrederRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderProcessingServiceTest {

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @InjectMocks
    OrderProcessingServiceImpl orderProcessingService;
    @Mock
    OrederRepository orederRepository;
    @Mock
    ItemRepository itemRepository;

    @Test
    void testSaveOrderWithItems(){
        Long orderId = 1L;

        Item item = new Item();
        item.setItemId(2L);
        item.setItemName("Test Item");
        item.setItemQuantity(5);
        item.setItemPrice(10.0f);
        item.setShippedDate("2023-08-15");

        Order existingOrder = new Order();
        existingOrder.setOrderId(orderId);

        when(orederRepository.findByOrderId(orderId)).thenReturn(existingOrder);
        when(orederRepository.save(any(Order.class))).thenReturn(existingOrder);
        Order savedOrder = orderProcessingService.saveItem(Collections.singleton(item), orderId);

        verify(orederRepository, times(1)).save(existingOrder);

//        assertEquals(1, savedOrder.getItemList().size());
//        Item savedItem = savedOrder.getItemList().iterator().next();
//        assertEquals("Test Item", savedItem.getItemName());
//        assertEquals(5, savedItem.getItemQuantity());
//        assertEquals(10.0f, savedItem.getItemPrice());
//        assertEquals("2023-08-15", savedItem.getShippedDate());
//        MockitoAnnotations.initMocks(this);
//        Order order=new Order();
//        order.setOrderId(Long.valueOf("01"));
//        order.setCustomerName("xyz");
//        order.setCustomerAddress("abc");
//        //  order.setOrderDate(LocalDate.parse("20230801"));
//        order.setDispatchDate("4566666");
//        order.setOrderAmount(666);
//        order.setNumberOfItems(23);
//       // order.setItemList(List.of());
//        when(orederRepository.save(order)).thenReturn(order) ;
//        orderProcessingService.saveOrderWithItems(order);
    }
    @Test
    void testGetOrderWithItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        List<Order>orderList=new ArrayList<>();
        order.setOrderId(Long.valueOf("01"));
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
       // order.setItemList(List.of());
        orderList.add(order);
        when(orederRepository.findAll()).thenReturn(orderList) ;
        orderProcessingService.getOrderWithItems();

    }
    @Test
    void testGetOrderWithId() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();

        List<Order>orderList=new ArrayList<>();
        Long id;
        id=1L;
        order.setOrderId(Long.valueOf("01"));
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
      //  order.setItemList(List.of());
        orderList.add(order);
        when(orederRepository.findByOrderId( id)).thenReturn(order);
        orderProcessingService.getOrderWithId(id);

    }
    @Test
    void testUpdateOrderWithItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();

        List<Order>orderList=new ArrayList<>();
        Long id;
        id=1L;
        order.setOrderId(id);
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
       // order.setOrderDate(LocalDate)"123445");
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
       // order.setItemList(List.of());
        orderList.add(order);
        when(orederRepository.findByOrderId(id)).thenReturn(order);
        when(orederRepository.save(order)).thenReturn(order);
        orderProcessingService.updateOrderWithItems(order,id);
       // Assert.assertNotNull(order);

    }
    @Test
    void testUpdateOrderWithItemsNull() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        order=null;
        List<Order>orderList=new ArrayList<>();
        Long id;
        id=1L;
//        order.setOrderId(Long.valueOf("01"));
//        order.setCustomerName("xyz");
//        order.setCustomerAddress("abc");
//        //  order.setOrderDate(LocalDate.parse("20230801"));
//        order.setDispatchDate("4566666");
//        order.setOrderAmount(666);
//        order.setNumberOfItems(23);
//        order.setItemList(List.of());
        orderList.add(order);
        when(orederRepository.save( order)).thenReturn(null);
        orderProcessingService.updateOrderWithItems(order,id);

    }
    @Test
    void testDeleteeOrderWithItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();

        List<Order>orderList=new ArrayList<>();
        Long id;
        id=1L;
        order.setOrderId(Long.valueOf("01"));
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
      //  order.setItemList(List.of());
        orderList.add(order);
        orederRepository.deleteById(id);
        orderProcessingService.deleteOrderWithItems(id);

    }
    @Test
    void testGetAllListOfOrderedItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        List<Item>itemList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(1L);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        when(itemRepository.findAll()).thenReturn(itemList) ;
        orderProcessingService.getAllListOfOrderedItems();

    }
    @Test
    void testGetAllListOfOrderedItemsById() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        Long id;
        id=1L;
        List<Item> itemList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(id);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        when(itemRepository.findAll()).thenReturn(itemList) ;
        orderProcessingService.getAllListOfOrderedItemsById(id);

    }
    @Test
    void testUpdateExistingOrderItem() throws SQLException {
        MockitoAnnotations.initMocks(this);
        ItemDTO itemDTO=new ItemDTO();

        Long id;
        id=1L;
        Item item=new Item();
        item.setItemId(1L);
        Order order = new Order();
        order.setCustomerName("ankit");
            order.setCustomerAddress("abc");
            //  order.setOrderDate(LocalDate.parse("20230801"));
            order.setDispatchDate("4566666");
            order.setOrderAmount(666);
            order.setNumberOfItems(23);
        order.setOrderId(id);
     //  Long o1 = order.getOrderId();
       // Assertions.assertNotNull(order);
        Set<Item> itemList = new HashSet<>();
        item.setItemName("dipesh");
        item.setItemPrice(5.0f);
        item.setItemId(id);
        item.setItemQuantity(6);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        order.setItemList(itemList);
        Mockito.when(orederRepository.findByOrderId(id)).thenReturn(order);
        Set<Long>ids=itemList.stream().map(x->x.getItemId()).collect(Collectors.toSet());
            Mockito.when(itemRepository.findByItemId(id)).thenReturn(item);
        Mockito.when(itemRepository.save(item)).thenReturn(item);
            itemDTO.setItemId(id);
            itemDTO.setItemPrice(item.getItemPrice());
            itemDTO.setItemName(item.getItemName());
            Mockito.when(itemRepository.save(item)).thenReturn(item);
        Mockito.when(orderProcessingService.updateExistingOrderItem(item,id)).thenReturn(itemDTO);


    }
    @Test
    void testDeleteOrderedItemById() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        Long id;
        id=1L;
        List<Item> itemList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(id);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        itemRepository.deleteById(id) ;
       String s1= orderProcessingService.deleteOrderedItemById(id);
        Assertions.assertNotNull(s1);

    }
    @Test
    void testSaveItem() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        List<Item>itemList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(1L);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        Mockito.when(itemRepository.save(item)).thenReturn(item) ;
       // orderProcessingService.saveItem(item);

    }
    @Test
    public void testUpdateOrderStatus_Success() {
        Long orderId = 1L;
        Status newStatus = Status.SHIPPED;

        Order existingOrder = new Order();
        existingOrder.setOrderId(orderId);
        existingOrder.setStatus(Status.PENDING); // Initial status

       Mockito.when(orederRepository.findByOrderId(orderId)).thenReturn(existingOrder);
        Mockito.when(orederRepository.save(existingOrder)).thenReturn(existingOrder);
        orderProcessingService.updateOrderStatus(orderId, newStatus);

    }
    @Test
    public void testFindOrderIdsAndItemIdsByItemId(){
        Long itemId1 = 11L;
        List<Long> listOrderId=List.of(1L,2L,3L);
        OrderItemDTO  orderItemDTO = new OrderItemDTO();
        //Set<Item> itemList= new HashSet<>();
Mockito.when(orederRepository.findOrderIdsAndItemIdsByItemId(itemId1)).thenReturn(listOrderId);

        orderItemDTO.setItemId(itemId1);
        orderItemDTO.setOrderId(listOrderId);
        orderProcessingService.findOrderIdsAndItemIdsByItemId(itemId1);
        // existingOrder.setOrderId(orderId);
       // existingOrder.setItemList(itemList.itemId1);
    }



}

package com.kodilla.ecommercee.user;

import com.kodilla.ecommercee.cart.domain.Cart;
import com.kodilla.ecommercee.order.domain.Order;
import com.kodilla.ecommercee.order.repository.OrderDao;
import com.kodilla.ecommercee.user.domain.User;
import com.kodilla.ecommercee.user.repository.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserTestSuite {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    private User user;

    List<Order> orders = new ArrayList<>();

    @Before
    public void setup() {
        Cart cart = new Cart();
        user = new User(1L, "john", false, "14113", "test@test.co", "test123", false, cart, orders);
    }

    @Test
    public void testSaveUser() {
        //Given
        userDao.save(user);
        //When
        List<User> users = userDao.findAll();
        //Then
        assertEquals(1, users.size());
        //Cleanup
        userDao.delete(user);
    }

    @Test
    public void testUpdateUser() {
        //Given
        userDao.save(user);
        String username = user.getUsername();
        //When
        user.setUsername("Bartłomiej");
        userDao.save(user);
        //Then
        assertNotEquals(username, user.getUsername());
        assertEquals("Bartłomiej", user.getUsername());
        //Cleanup
        userDao.delete(user);
    }

    @Test
    public void testDeleteUser() {
        //Given
        userDao.save(user);
        //When
        userDao.delete(user);
        //Then
        assertFalse(userDao.existsById(user.getId()));
    }

    @Test
    public void testRelationUserOrder() {
        //Given
        Order order = new Order(LocalDate.of(2021, 7, 15), new BigDecimal("525"), user);
        Order order2 = new Order(LocalDate.of(2021, 7, 15), new BigDecimal("122.5"), user);
        Order order3 = new Order(LocalDate.of(2021, 7, 15), new BigDecimal("10524.5"), user);
        orders.add(order);
        orders.add(order2);
        orders.add(order3);
        user.setOrdersId(orders);
        orderDao.save(order);
        orderDao.save(order2);
        orderDao.save(order3);
        userDao.save(user);
        //When
        userDao.delete(user);
        //Then
        assertEquals(user.getOrdersId().size(), 3);
        //Cleanup
        userDao.delete(user);
        orderDao.delete(order);
        orderDao.delete(order2);
        orderDao.delete(order3);
    }

    @Test
    public void testDeleteUserDoesNotDeleteOrders() {
        //Given
        Order order = new Order(LocalDate.of(2021, 7, 15), new BigDecimal("525"), user);
        Order order2 = new Order(LocalDate.of(2021, 7, 15), new BigDecimal("122.5"), user);
        Order order3 = new Order(LocalDate.of(2021, 7, 15), new BigDecimal("10524.5"), user);
        orders.add(order);
        orders.add(order2);
        orders.add(order3);
        user.setOrdersId(orders);
        orderDao.save(order);
        orderDao.save(order2);
        orderDao.save(order3);
        userDao.save(user);
        //When
        userDao.delete(user);
        //Then
        assertTrue(orderDao.findById(order.getId()).isPresent());
        //Cleanup
        userDao.delete(user);
        orderDao.delete(order);
        orderDao.delete(order2);
        orderDao.delete(order3);
    }
}

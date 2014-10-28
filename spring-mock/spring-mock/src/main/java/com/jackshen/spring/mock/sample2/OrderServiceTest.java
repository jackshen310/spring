package com.jackshen.spring.mock.sample2;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 * see also http://lkrnac.net/blog/2014/01/mock-autowired-fields/
 * @ClassName: OrderServiceTest
 * @Description: TODO
 * @author jackshen
 * @Date: 2014-10-29 上午12:01:06
 * 
 */
public class OrderServiceTest
{
    private static final int TEST_ORDER_ID = 15;

    private static final int TEST_SHOES_PRICE = 2;

    private static final int TEST_SHIRT_PRICE = 1;

    // Instantiates testing object instance and tries to inject fields annotated
    // with @Mock or @Spy into private fields of testing object
    @InjectMocks
    private OrderService testingObject;

    // Creates spy for instance of annotated field
    @Spy
    private PriceService priceService;

    // Creates mock instance of the field it annotates
    @Mock
    private OrderDao orderDao;

    @Before
    public void initMocks()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetOrderService()
    {
        Order order = new Order(Arrays.asList(Item.SHOES, Item.SHIRT));
        Mockito.when(orderDao.getOrder(TEST_ORDER_ID)).thenReturn(order);

        // notice different Mockito syntax for spy
        Mockito.doReturn(TEST_SHIRT_PRICE).when(priceService).getActualPrice(Item.SHIRT);
        Mockito.doReturn(TEST_SHOES_PRICE).when(priceService).getActualPrice(Item.SHOES);

        // call testing method
        int actualOrderPrice = testingObject.getOrderPrice(TEST_ORDER_ID);

        Assert.assertEquals(TEST_SHIRT_PRICE + TEST_SHOES_PRICE, actualOrderPrice);
    }
}
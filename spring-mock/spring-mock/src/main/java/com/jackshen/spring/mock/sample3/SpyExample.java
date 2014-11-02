package com.jackshen.spring.mock.sample3;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpyExample
{
    // 只是模拟部分方法，需通过doReturn().when()...指定
    @Spy
    private List<String> list = new ArrayList<String>();

    @Test
    public void spyExample()
    {
        try
        {
            Mockito.doReturn("hey").when(list).get(32);// will find
            // when(list.get(32)).thenReturn("hey"); throw IndexOutOfBoundException.

            assertThat(list.get(32), equalTo("hey"));//will work

            Mockito.doThrow(IndexOutOfBoundsException.class).when(list).remove(32);//doesn't mock,so throw Exceptin.

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail("some thing wrong");
        }

    }

    @Test
    public void test01()
    {
        List list = new LinkedList();
        List spy = Mockito.spy(list); // also can use method Mockito.spy(...)  inner a method,is the same as annotation @spy.

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        // Mockito.when(spy.get(0)).thenReturn("foo");

        //You have to use doReturn() for stubbing
        Mockito.doReturn("foo").when(spy).get(0);// only in this way does correct.

    }
}

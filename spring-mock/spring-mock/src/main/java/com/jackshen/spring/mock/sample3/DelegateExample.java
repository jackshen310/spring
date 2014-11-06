package com.jackshen.spring.mock.sample3;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

public class DelegateExample
{
    interface IFoo
    {
        public String doFoo();
    }

    final class Foo implements IFoo
    {
        public String doFoo()
        {
            return null;
        }
    }

    @Test
    public void _test()
    {
        //can mock final class.
        Foo foo = new Foo();
        IFoo mockFoo = Mockito.mock(IFoo.class, AdditionalAnswers.delegatesTo(foo));

        // not right in this way.
        //Mockito.when(mockFoo.doFoo()).thenReturn("some");

        //only this way does right.
        Mockito.doReturn("some").when(mockFoo).doFoo();

        Assert.assertEquals("some", mockFoo.doFoo());

    }
}

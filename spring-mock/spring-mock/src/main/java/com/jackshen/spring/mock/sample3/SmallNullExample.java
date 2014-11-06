package com.jackshen.spring.mock.sample3;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

public class SmallNullExample
{
    class Foo
    {
        public Stuff doFoo()
        {
            return null;
        }

        public Bar getBar()
        {
            return null;
        }
    }

    class Stuff
    {
        public String doStuff()
        {
            return null;
        }
    }

    public class Bar
    {
        public String getName()
        {
            return null;
        }
    }

    @Test
    public void _test()
    {
        Foo foo = Mockito.mock(Foo.class, Mockito.RETURNS_SMART_NULLS);
        //calling unstubbed method here:
        Stuff stuff = foo.doFoo();

        //using object returned by unstubbed call:
        stuff.doStuff();

        //Above doesn't yield NullPointerException this time!

        //Instead, SmartNullPointerException is thrown.

        //Exception's cause links to unstubbed mock.getStuff() - just click on the stack trace.

    }

    @Test
    public void _test2()
    {
        Foo foo = Mockito.mock(Foo.class, Mockito.RETURNS_DEEP_STUBS);

        Mockito.when(foo.getBar().getName()).thenReturn("bar");

        Assert.assertEquals("bar", foo.getBar().getName());
    }
}

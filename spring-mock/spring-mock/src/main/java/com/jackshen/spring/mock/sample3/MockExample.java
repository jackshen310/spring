package com.jackshen.spring.mock.sample3;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockExample
{
    public static class Foo
    {
        public String f1()
        {
            return null;
        }

        public String f2()
        {
            throw new NullPointerException();
        }

        public List f3()
        {
            throw new NullPointerException();
        }
    }

    @Mock(answer = Answers.RETURNS_DEFAULTS)
    //when unstubbed,return  zeros, empty collections, nulls, etc
    private Foo foo;

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    //when unstubbed,return "",[],
    private Foo foo2;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private Foo foo3;

    @Test
    public void mockExample()
    {
        try
        {
            Mockito.when(foo.f1()).thenReturn("foo");
            Assert.assertEquals(foo.f1(), "foo");//right as we expected.
            Assert.assertEquals(foo.f2(), null);//when use @Mock(answer=Answers.RETURNS_DEFAULTS) returns zeros, empty collections, nulls, etc.)
            Assert.assertEquals(foo.f3().size(), 0);

            Assert.assertEquals(foo2.f2(), "");//not return null,but ""

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail("some thing wrong");
        }

    }

}

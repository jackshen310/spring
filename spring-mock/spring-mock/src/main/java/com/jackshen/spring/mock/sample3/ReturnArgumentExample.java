package com.jackshen.spring.mock.sample3;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class ReturnArgumentExample
{
    public static class Foo
    {

        public String f1(String name)
        {
            return null;
        }

    }

    @Mock
    Foo foo;

    @Test
    public void mockExample()
    {
        try
        {
            //first solution.
            Mockito.when(foo.f1(Matchers.anyString())).then(AdditionalAnswers.returnsFirstArg());
            Assert.assertEquals(foo.f1("jackshen"), "jackshen");

            //secode solution.(a little complex,but more powerful)
            Mockito.when(foo.f1(Matchers.anyString())).thenAnswer(new Answer<String>()
            {

                public String answer(InvocationOnMock invocation) throws Throwable
                {
                    Object[] args = invocation.getArguments();
                    return (String) args[0];
                }

            });

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail("some thing wrong");
        }

    }

}

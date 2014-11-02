package com.jackshen.spring.mock.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Static.class)
public class Sample
{
    @Test
    public void testMethodThatCallsStaticMethod()
    {
        // mock all the static methods in a class called "Static"
        PowerMockito.mockStatic(Static.class);
        // use Mockito to set up your expectation
        Mockito.when(Static.firstStaticMethod("abc")).thenReturn("jackshen");
        Mockito.when(Static.secondStaticMethod()).thenReturn(123);

        // execute your test
        Static.firstStaticMethod("abc");
        Static.secondStaticMethod();

        // Different from Mockito, always use PowerMockito.verifyStatic() first
        PowerMockito.verifyStatic(Mockito.times(2));
        // Use EasyMock-like verification semantic per static method invocation
        Static.firstStaticMethod("ab4c");

        // Remember to call verifyStatic() again
        PowerMockito.verifyStatic(); // default times is once
        Static.secondStaticMethod();

        // Again, remember to call verifyStatic()
        PowerMockito.verifyStatic(Mockito.never());
        Static.thirdStaticMethod();
    }

    public void classCallStaticMethodObj()
    {
        Static.firstStaticMethod("abc");
        Static.secondStaticMethod();
    }
}

class Static
{
    public static String firstStaticMethod(String param)
    {
        return null;
    }

    public static int secondStaticMethod()
    {
        return 0;
    }

    public static void thirdStaticMethod()
    {

    }
}

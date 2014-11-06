package com.jackshen.spring.mock.sample3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MatchesExample
{
    public static class Foo
    {

        public void savePerson(String name, int age)
        {
        }

    }

    @Mock
    Foo foo;

    @Test
    public void mockExample()
    {
        try
        {
            List list = Mockito.mock(ArrayList.class);
            Mockito.when(list.get(Matchers.anyInt())).thenReturn("element");
            Assert.assertEquals(list.get(333), "element");

            String name = "jackshen";
            int age = 22;
            foo.savePerson(name, age);

            Mockito.verify(foo).savePerson(Matchers.anyString(), Matchers.anyInt());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail("some thing wrong");
        }

    }

    @Test
    public void testSayHi() throws Exception
    {
        File mock = Mockito.mock(File.class); //首先mock File类。
        //注意new IsAnyFiles()并不是一个matcher，需要调用argThat(new IsAnyFiles()))才返回一个matcher。

        //下句中stub：当调用renameTo方法时，返回false。该方法参数可以是任意file对象。

        Mockito.when(mock.renameTo(Matchers.argThat(new IsAnyFiles()))).thenReturn(false);
        mock.renameTo(new File("test"));

        //下句verify renameTo方法被调用了一次，同时输入参数是任意file。
        Mockito.verify(mock).renameTo(Matchers.argThat(new IsAnyFiles()));
    }

    @Test
    public void testMatche_all_arguments()
    {

        foo.savePerson("jack", 12);
        Mockito.verify(foo).savePerson(Matchers.anyString(), Matchers.eq(12));
        //above is correct - eq() is also an argument matcher

        Mockito.verify(foo).savePerson(Matchers.anyString(), 12);
        //above is incorrect - exception will be thrown because third argument is given without an argument matcher.

    }
}

class IsAnyFiles extends ArgumentMatcher<File>
{
    public boolean matches(Object file)
    {
        return file.getClass() == File.class;
    }

}

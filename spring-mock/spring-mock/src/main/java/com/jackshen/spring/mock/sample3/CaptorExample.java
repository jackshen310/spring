package com.jackshen.spring.mock.sample3;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CaptorExample
{
    public static class Foo
    {
        private Bar bar;

        public void savePerson(String name, int age)
        {
            //create a domain to save the args.
            Person person = new Person();
            person.setAge(age);
            person.setName(name);

            //invoke 
            bar.sendPerson(person);
        }

    }

    public static class Bar
    {
        public void sendPerson(Person person)
        {
            // something  to do... 
        }

    }

    @Captor
    private ArgumentCaptor<Person> personCaptor;

    @InjectMocks
    //use this annotation to ensure that the attribute 'bar' in the Foo  is mocked but the class Foo itself doesn't.
    Foo foo;

    @Mock
    Bar bar;

    @Test
    public void mockExample()
    {
        try
        {
            // Foo foo = new Foo();
            String name = "jackshen";
            int age = 22;
            foo.savePerson(name, age);

            //because Bar#sendPerson is invoked when call Foo.savePerson,we can use personCaptor.capture() to store the argument
            Mockito.verify(bar).sendPerson(personCaptor.capture());

            Person person = personCaptor.getValue();
            Assert.assertEquals(person.getName(), name);
            Assert.assertEquals(person.getAge(), age);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail("some thing wrong");
        }

    }

    //test domain with name and age attrs.
    public static class Person
    {
        private String name;

        private int age;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public int getAge()
        {
            return age;
        }

        public void setAge(int age)
        {
            this.age = age;
        }

    }

}

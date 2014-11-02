package com.jackshen.spring.mock.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Use the @RunWith(PowerMockRunner.class) annotation at the class-level of the
 * test case.
 * Use the @PrepareForTest(ClassThatContainsStaticMethod.class) annotation at
 * the class-level of the test case.
 * Use PowerMock.mockStatic(ClassThatContainsStaticMethod.class) to mock all
 * methods of this class.
 * Use PowerMock.replay(ClassThatContainsStaticMethod.class) to change the class
 * to replay mode.
 * Use PowerMock.verify(ClassThatContainsStaticMethod.class) to change the class
 * to verify mode.
 * @ClassName: MockStaticTest
 * @Description: TODO
 * @author jackshen
 * @Date: 2014-11-3 上午1:27:29
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(
{ IdGenerator.class })
public class MockStaticTest
{
    @Test
    public void testMockStatic() throws Exception
    {
        long expectedId = 42;

        // We create a new instance of test class under test as usually.
        ServiceRegistartor tested = new ServiceRegistartor();

        // This is the way to tell PowerMock to mock all static methods of a
        // given class
        PowerMockito.mockStatic(IdGenerator.class);

        /*
         * The static method call to IdGenerator.generateNewId() expectation.
         * This is why we need PowerMock.
         */
        PowerMockito.when(IdGenerator.generateNewId()).thenReturn(expectedId);

        // Note how we replay the class, not the instance!
        // PowerMockito.replay(IdGenerator.class);
        // PowerMockito.doAnswer(answer)
        long actualId = tested.registerService(new Object());

        // Note how we verify the class, not the instance!
        Mockito.verify(IdGenerator.class);

        // Assert that the ID is correct
        Assert.assertEquals(expectedId, actualId);

    }
}

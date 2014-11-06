package com.jackshen.spring.mock.sample3;

import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.mockito.exceptions.verification.NoInteractionsWanted;

public class IgnoreStubsExample
{
    @Test
    public void _test()
    {
        //mocking lists for the sake of the example (if you mock List in real you will burn in hell)
        List mock1 = mock(List.class), mock2 = mock(List.class);

        //stubbing mocks:
        when(mock1.get(0)).thenReturn(10);
        when(mock2.get(0)).thenReturn(20);

        //using mocks by calling stubbed get(0) methods:
        System.out.println(mock1.get(0)); //prints 10
        System.out.println(mock2.get(0)); //prints 20

        //using mocks by calling clear() methods:
        mock1.clear();
        mock2.clear();

        //verification:
        verify(mock1).clear();
        verify(mock2).clear();

        //verifyNoMoreInteractions() fails because get() methods were not accounted for.
        try
        {
            verifyNoMoreInteractions(mock1, mock2);
        }
        catch (NoInteractionsWanted e)
        {
        }

        //However, if we ignore stubbed methods then we can verifyNoMoreInteractions()
        verifyNoMoreInteractions(ignoreStubs(mock1, mock2));

        //Remember that ignoreStubs() *changes* the input mocks and returns them for convenience.    
    }
}

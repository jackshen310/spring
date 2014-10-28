package com.jackshen.spring.mock.sample2;

import java.util.Collection;
import java.util.Collections;

public class Order
{
    private Collection<Item> items;

    public Order(Collection<Item> items)
    {
        this.items = items;
    }

    public Collection<Item> getItems()
    {
        return Collections.unmodifiableCollection(items);
    }
}
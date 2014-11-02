package com.jackshen.spring.mock.powermock;

public class ServiceRegistartor
{
    public long registerService(Object service)
    {
        final long id = IdGenerator.generateNewId();
        // serviceRegistrations.put(id, service);
        return id;
    }
}

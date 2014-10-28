package com.jackshen.spring.mock.sample1;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class TaxCalculator
{

    public BigDecimal calculateTaxes(BigDecimal salary)
    {
        BigDecimal result = salary.multiply(new BigDecimal(12));
        // some other weird calculation ....
        return result;
    }
}
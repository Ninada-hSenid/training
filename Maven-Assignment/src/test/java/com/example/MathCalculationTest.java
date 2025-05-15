package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MathCalculationTest {

    @Test
    public void testSquare() {
        MathCalculation mathUtils = new MathCalculation();
        assertEquals(25, mathUtils.square(5));
        assertEquals(0, mathUtils.square(0));
        assertEquals(1, mathUtils.square(-1));
    }
}
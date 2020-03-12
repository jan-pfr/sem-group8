package com.napier.semgroup;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PopStatsAppTest {

    @Test
    void unitTest()
    {
        assertEquals(5, 5);
    }
    @Test
    void unitTest6()
    {
        assertTrue(5 == 5);
    }

    @Test
    void unitTest7()
    {
        assertFalse(5 == 4);
    }

    @Test
    void unitTest8()
    {
        assertNull(null);
    }

    @Test
    void unitTest9()
    {
        assertNotNull("Hello");
    }
}

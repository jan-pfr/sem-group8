package com.napier.semgroup;

import com.napier.semgroup.app.PopulationStatsApplication;
import com.napier.semgroup.datalayer.DatabaseConnection;
import com.napier.semgroup.logic.BusinessLogic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PopStatsAppIntegrationTests {
    static PopulationStatsApplication a;
    static DatabaseConnection databaseConnection;
    static BusinessLogic businessLogic;


    @BeforeAll
    static void init()
    {
        a = new PopulationStatsApplication();

    }

    @Test
    void unitTest()
    {
        assertEquals(5, 5);
    }
}

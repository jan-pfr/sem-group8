package com.napier.semgroup;

import com.napier.semgroup.app.PopulationStatsApplication;
import com.napier.semgroup.datalayer.MySQLConnectionHandler;
import com.napier.semgroup.logic.BusinessLogic;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PopStatsAppTest {
    static PopulationStatsApplication a;
    static MySQLConnectionHandler mySQLConnectionHandler;
    static BusinessLogic businessLogic;
    @BeforeAll
    static void init()
    {
        a = new PopulationStatsApplication();
        mySQLConnectionHandler = new MySQLConnectionHandler();
        mySQLConnectionHandler.connect("localhost:33080");
        businessLogic = new BusinessLogic(mySQLConnectionHandler);
    }

    @Test
    void validateContinent (){

        assertTrue(businessLogic.validateContinent("Africa"));
    }
    @Test
    void validateRegion(){

        assertTrue(businessLogic.validateRegion("Middle East"));
    }
}

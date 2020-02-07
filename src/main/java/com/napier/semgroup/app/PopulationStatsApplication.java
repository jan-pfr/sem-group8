package com.napier.semgroup.app;

import com.napier.semgroup.datalayer.DatabaseConnection;
import com.napier.semgroup.datalayer.MySQLConnectionHandler;
import com.napier.semgroup.logic.BusinessLogic;
import com.napier.semgroup.reports.Country;

/**
 * This class starts you program to easy access population information.
 */
public class PopulationStatsApplication {
    /**
     * The main method
     * @param args The Arguments
     */
    public static void main (String[] args){

        System.out.println("Hello World, this is the main class of the coursework 8.");
        DatabaseConnection dbcon = new MySQLConnectionHandler();
        dbcon.connect();
        BusinessLogic businessLogic = new BusinessLogic(dbcon);
    System.out.println("Code");
        for (Country c : businessLogic.getAllCountries()) {
            System.out.println(c);
        }

        dbcon.disconnect();
    }
}

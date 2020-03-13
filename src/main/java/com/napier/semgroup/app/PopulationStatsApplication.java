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

        DatabaseConnection dbcon = new MySQLConnectionHandler(); //new object
        if (args.length < 1)
        {
            dbcon.connect("localhost:33080");
        }
        else
        {
            dbcon.connect(args[0]);
        }
        BusinessLogic businessLogic = new BusinessLogic(dbcon);
        //new object
        dbcon.disconnect(); // disconnect database.
    }
}

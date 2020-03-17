package com.napier.semgroup.app;

import com.napier.semgroup.datalayer.DatabaseConnection;
import com.napier.semgroup.datalayer.MySQLConnectionHandler;
import com.napier.semgroup.logic.BusinessLogic;
import com.napier.semgroup.reports.*;
import com.napier.semgroup.ui.CmdUserInterface;

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

        BusinessLogic businessLogic = new BusinessLogic(dbcon); //new object
        if (args.length < 2)
        {
            CmdUserInterface cmdUserInterface = new CmdUserInterface(businessLogic);
            cmdUserInterface.clearScreen();
            cmdUserInterface.showMenu();
        }
        else
        {
            businessLogic.noUserInterface(args[1]);
        }
        System.out.println("#23______________________________________");
        System.out.println(String.format("%-32s %-32s %-32s %-32s %-32s %-32s",
                "Location","TotalPopulation","PopulationCities","PopulationCitiesPercent","PopulationNotInCities","PopulationNotInCitiesPercent"));
        for (Population p : businessLogic.PopPopCitiesPopNotCitiesInContinent()) {
            System.out.println(p);
        }
        System.out.println("#25______________________________________");
        System.out.println(String.format("%-32s %-32s %-32s %-32s %-32s %-32s",
                "Location","TotalPopulation","PopulationCities","PopulationCitiesPercent","PopulationNotInCities","PopulationNotInCitiesPercent"));
        for (Population p : businessLogic.PopPopCitiesPopNotCitiesInRegion()) {
            System.out.println(p);
        }
        System.out.println("#26______________________________________");
        System.out.println(String.format("%-32s %-32s %-32s %-32s %-32s %-32s",
                "Location","TotalPopulation","PopulationCities","PopulationCitiesPercent","PopulationNotInCities","PopulationNotInCitiesPercent"));
        for (Population p : businessLogic.PopPopCitiesPopNotCitiesInCountry()) {
            System.out.println(p);
        }
        dbcon.disconnect(); // disconnect database.
    }
}

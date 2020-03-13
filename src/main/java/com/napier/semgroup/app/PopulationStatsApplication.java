package com.napier.semgroup.app;

import com.napier.semgroup.datalayer.DatabaseConnection;
import com.napier.semgroup.datalayer.MySQLConnectionHandler;
import com.napier.semgroup.logic.BusinessLogic;
import com.napier.semgroup.reports.*;

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

        //execute all methods and output for debug.
        System.out.println("#1______________________________________");
        System.out.println(String.format("%-3s %-52s %-13s %-26s %-10s %-52s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country c : businessLogic.getAllCountriesOrganizedByPopulation()) {
            System.out.println(c);
        }

        System.out.println("\n#2______________________________________");
        System.out.println(String.format("%-3s %-52s %-13s %-26s %-10s %-52s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country c : businessLogic.getAllCountriesInContinentOrganizedByPopulation("Europe")) {
            System.out.println(c);
        }

        System.out.println("\n#3______________________________________");
        System.out.println(String.format("%-3s %-52s %-13s %-26s %-10s %-52s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country c : businessLogic.getAllCountriesInRegionOrganizedByPopulation( "Middle East")) {
            System.out.println(c);
        }

        System.out.println("\n#4______________________________________");
        System.out.println(String.format("%-3s %-52s %-13s %-26s %-10s %-52s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country c : businessLogic.getNCountriesOrganizedByPopulation(20)) {
            System.out.println(c);

        BusinessLogic businessLogic = new BusinessLogic(dbcon);
        //new object
        dbcon.disconnect(); // disconnect database.
    }
}

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
        /*
         * Start the Application via the docker compose file. The DB had to be started once.
         * When you change something in the code and want to test it, make sure you have packed the jar over marven
         * and deleted the old image in docker, otherwise the new code will not be executed.
         * Do this steps all the time you want to try new code snippets.
         */
        DatabaseConnection dbcon = new MySQLConnectionHandler(); //new object
        dbcon.connect(); //connection to database
        BusinessLogic businessLogic = new BusinessLogic(dbcon); //new object

        //execute the method getAllCountries and output.
        for (Country c : businessLogic.getNCountriesOrganizedByPopulation(5)) {
            System.out.println(c);
        }

        for (Country c : businessLogic.getNCountriesInContinentOrganizedByPopulation(7, "Europe")) {
            System.out.println(c);
        }

        for (Country c : businessLogic.getNCountriesInContinentOrganizedByPopulation(1, "South America")) {
            System.out.println(c);
        }

        for (Country c : businessLogic.getNCountriesInRegionOrganizedByPopulation(9, "Middle East")) {
            System.out.println(c);
        }

        for (Country c : businessLogic.getNCountriesInRegionOrganizedByPopulation(2, "Southeast Asia")) {
            System.out.println(c);
        }

        dbcon.disconnect(); // disconnect database.
    }
}

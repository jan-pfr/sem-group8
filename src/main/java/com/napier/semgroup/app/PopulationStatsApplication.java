package com.napier.semgroup.app;

import com.napier.semgroup.datalayer.DatabaseConnection;
import com.napier.semgroup.datalayer.MySQLConnectionHandler;
import com.napier.semgroup.gui.ViewController;
import com.napier.semgroup.logic.BusinessLogic;
import com.napier.semgroup.reports.Country;
import javafx.application.Application;
import javafx.stage.Stage;

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
        dbcon.connect(); //connection to database
        BusinessLogic businessLogic = new BusinessLogic(dbcon); //new object

        ViewController viewController = new ViewController();
        ViewController.launch(ViewController.class);

        //execute the method getAllCountries and output.
        System.out.println(String.format("%-3s %-52s %-13s %-26s %-10s %-52s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country c : businessLogic.getNCountriesOrganizedByPopulation(5)) {
            System.out.println(c);
        }
        System.out.println("______________________________________");
        System.out.println(String.format("%-3s %-52s %-13s %-26s %-10s %-52s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country c : businessLogic.getNCountriesInContinentOrganizedByPopulation(7, "Europe")) {
            System.out.println(c);
        }
        System.out.println("______________________________________");
        System.out.println(String.format("%-3s %-52s %-13s %-26s %-10s %-52s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country c : businessLogic.getNCountriesInContinentOrganizedByPopulation(1, "South America")) {
            System.out.println(c);
        }
        System.out.println("______________________________________");
        System.out.println(String.format("%-3s %-52s %-13s %-26s %-10s %-52s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country c : businessLogic.getNCountriesInRegionOrganizedByPopulation(9, "Middle East")) {
            System.out.println(c);
        }
        System.out.println("______________________________________");
        System.out.println(String.format("%-3s %-52s %-13s %-26s %-10s %-52s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country c : businessLogic.getNCountriesInRegionOrganizedByPopulation(2, "Southeast Asia")) {
            System.out.println(c);
        }

        dbcon.disconnect(); // disconnect database.
    }
}

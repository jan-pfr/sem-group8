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
        }

        System.out.println("\n#5______________________________________");
        System.out.println(String.format("%-3s %-52s %-13s %-26s %-10s %-52s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country c : businessLogic.getNCountriesInContinentOrganizedByPopulation(20, "Africa")) {
            System.out.println(c);
        }

        System.out.println("\n#6______________________________________");
        System.out.println(String.format("%-3s %-52s %-13s %-26s %-10s %-52s",
                "Code", "Name", "Continent", "Region", "Population", "Capital"));
        for (Country c : businessLogic.getNCountriesInRegionOrganizedByPopulation(20, "Southern Europe")) {
            System.out.println(c);
        }

        System.out.println("\n#7______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getAllCitiesOrganizedByPopulation()) {
            System.out.println(c);
        }

        System.out.println("\n#8______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getAllCitiesInContinentOrganizedByPopulation("South America")) {
            System.out.println(c);
        }

        System.out.println("\n#9______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getAllCitiesInRegionOrganizedByPopulation("Middle East")) {
            System.out.println(c);
        }

        System.out.println("\n#10______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getAllCitiesInCountryOrganizedByPopulation("Poland")) {
            System.out.println(c);
        }

        System.out.println("\n#11______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getAllCitiesInDistrictOrganizedByPopulation("Hokkaido")) {
            System.out.println(c);
        }

        System.out.println("\n#12______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getNCitiesOrganizedByPopulation(12)) {
            System.out.println(c);
        }

        System.out.println("\n#13______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getNCitiesInContinentOrganizedByPopulation("North America", 17)) {
            System.out.println(c);
        }

        System.out.println("\n#14______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getNCitiesInRegionOrganizedByPopulation("Baltic Countries", 5)) {
            System.out.println(c);
        }

        System.out.println("\n#15______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getNCitiesInCountryOrganizedByPopulation("France", 8)) {
            System.out.println(c);
        }

        System.out.println("\n#16______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getNCitiesInDistrictOrganizedByPopulation("England", 11)) {
            System.out.println(c);
        }

        System.out.println("\n#17______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getAllCapitalCitiesOrganizedByPopulation()) {
            System.out.println(c);
        }

        System.out.println("\n#18______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getAllCapitalCitiesInContinentOrganizedByPopulation("Oceania")) {
            System.out.println(c);
        }

        System.out.println("\n#19______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getAllCapitalCitiesInRegionOrganizedByPopulation("Central Africa")) {
            System.out.println(c);
        }

        System.out.println("\n#20______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getNCapitalCitiesOrganizedByPopulation(7)) {
            System.out.println(c);
        }

        System.out.println("\n#21______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getNCapitalCitiesInContinentOrganizedByPopulation("Europe", 9)) {
            System.out.println(c);
        }

        System.out.println("\n#22______________________________________");
        System.out.println(String.format("%-37s %-23s %-41s %-10s",
                "Name", "District", "Country", "Population"));
        for (City c : businessLogic.getNCapitalCitiesInRegionOrganizedByPopulation("Polynesia", 6)) {
            System.out.println(c);
        }
        dbcon.disconnect(); // disconnect database.
    }
}

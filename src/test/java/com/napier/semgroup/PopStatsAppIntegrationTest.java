package com.napier.semgroup;

import com.napier.semgroup.app.PopulationStatsApplication;
import com.napier.semgroup.datalayer.DatabaseConnection;
import com.napier.semgroup.datalayer.MySQLConnectionHandler;
import com.napier.semgroup.logic.BusinessLogic;
import com.napier.semgroup.reports.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PopStatsAppIntegrationTest {

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

    @Test
    void executeQueryAndParseCountries(){
        String query = "select country.Name as countryName, city.name as capitalName, country.Code as countryCode, country.Continent as countryContinent, country.Region as countryRegion, country.Population as countryPopulation \n" +
                "from country \n" +
                "left join city on Capital=city.ID \n" +
                "WHERE country.Name = 'Germany' ";
        for (Country c : businessLogic.executeQueryAndParseCountries(query)) {
            System.out.println(c);
        }

    }
    @Test
    void getAllCountriesOrganizedByPopulation(){
        for (Country c : businessLogic.getAllCountriesOrganizedByPopulation()) {
            System.out.println(c);
        }

    }
    @Test
    void getAllCountriesInContinentOrganizedByPopulation() {

        for (Country c : businessLogic.getAllCountriesInContinentOrganizedByPopulation("Europe")) {
            System.out.println(c);
        }
    }
    @Test
    void getAllCountriesInRegionOrganizedByPopulation(){
        for (Country c : businessLogic.getAllCountriesInRegionOrganizedByPopulation( "Middle East")) {
            System.out.println(c);
        }
    }
    @Test
    void getNCountriesOrganizedByPopulation(){
        for (Country c : businessLogic.getNCountriesOrganizedByPopulation(20)) {
            System.out.println(c);
        }
    }

    @Test
    void getNCountriesInContinentOrganizedByPopulation() {
        for (Country c : businessLogic.getNCountriesInContinentOrganizedByPopulation(20, "Africa")) {
            System.out.println(c);
        }
    }
    @Test
    void getNCountriesInRegionOrganizedByPopulation() {
        for (Country c : businessLogic.getNCountriesInRegionOrganizedByPopulation(20, "Southern Europe")) {
            System.out.println(c);
        }
    }

    @Test
    void disconnect(){
        mySQLConnectionHandler.disconnect();
       mySQLConnectionHandler.connect("localhost:33080");
    }



}

package com.napier.semgroup;

import com.napier.semgroup.app.PopulationStatsApplication;
import com.napier.semgroup.datalayer.DatabaseConnection;
import com.napier.semgroup.datalayer.MySQLConnectionHandler;
import com.napier.semgroup.logic.BusinessLogic;
import com.napier.semgroup.reports.*;
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
    void getAllCitiesOrganizedByPopulation() {
        for (City c : businessLogic.getAllCitiesOrganizedByPopulation()) {
            System.out.println(c);
        }
    }

    @Test
    void getAllCitiesInContinentOrganizedByPopulation() {
        for (City c : businessLogic.getAllCitiesInContinentOrganizedByPopulation("South America")) {
            System.out.println(c);
        }
    }

    @Test
    void getAllCitiesInRegionOrganizedByPopulation() {
        for (City c : businessLogic.getAllCitiesInRegionOrganizedByPopulation("Middle East")) {
            System.out.println(c);
        }
    }

    @Test
    void getAllCitiesInCountryOrganizedByPopulation() {
        for (City c : businessLogic.getAllCitiesInCountryOrganizedByPopulation("Poland")) {
            System.out.println(c);
        }
    }

    @Test
    void getAllCitiesInDistrictOrganizedByPopulation() {
        for (City c : businessLogic.getAllCitiesInDistrictOrganizedByPopulation("Hokkaido")) {
            System.out.println(c);
        }
    }

    @Test
    void getNCitiesOrganizedByPopulation() {
        for (City c : businessLogic.getNCitiesOrganizedByPopulation(12)) {
            System.out.println(c);
        }
    }

    @Test
    void getNCitiesInContinentOrganizedByPopulation() {
        for (City c : businessLogic.getNCitiesInContinentOrganizedByPopulation("North America", 17)) {
            System.out.println(c);
        }
    }

    @Test
    void getNCitiesInRegionOrganizedByPopulation() {
        for (City c : businessLogic.getNCitiesInRegionOrganizedByPopulation("Baltic Countries", 5)) {
            System.out.println(c);
        }
    }

    @Test
    void getNCitiesInCountryOrganizedByPopulation() {
        for (City c : businessLogic.getNCitiesInCountryOrganizedByPopulation("France", 8)) {
            System.out.println(c);
        }
    }

    @Test
    void getNCitiesInDistrictOrganizedByPopulation() {
        for (City c : businessLogic.getNCitiesInDistrictOrganizedByPopulation("England", 11)) {
            System.out.println(c);
        }
    }

    @Test
    void getAllCapitalCitiesOrganizedByPopulation() {
        for (City c : businessLogic.getAllCapitalCitiesOrganizedByPopulation()) {
            System.out.println(c);
        }
    }

    @Test
    void getAllCapitalCitiesInContinentOrganizedByPopulation() {
        for (City c : businessLogic.getAllCapitalCitiesInContinentOrganizedByPopulation("Oceania")) {
            System.out.println(c);
        }
    }

    @Test
    void getAllCapitalCitiesInRegionOrganizedByPopulation() {
        for (City c : businessLogic.getAllCapitalCitiesInRegionOrganizedByPopulation("Central Africa")) {
            System.out.println(c);
        }
    }

    @Test
    void getNCapitalCitiesOrganizedByPopulation() {
        for (City c : businessLogic.getNCapitalCitiesOrganizedByPopulation(7)) {
            System.out.println(c);
        }
    }

    @Test
    void getNCapitalCitiesInContinentOrganizedByPopulation() {
        for (City c : businessLogic.getNCapitalCitiesInContinentOrganizedByPopulation("Europe", 9)) {
            System.out.println(c);
        }
    }

    @Test
    void getNCapitalCitiesInRegionOrganizedByPopulation() {
        for (City c : businessLogic.getNCapitalCitiesInRegionOrganizedByPopulation("Polynesia", 6)) {
            System.out.println(c);
        }
    }
    

    @Test
    void disconnect(){
        mySQLConnectionHandler.disconnect();
       mySQLConnectionHandler.connect("localhost:33080");
    }



}

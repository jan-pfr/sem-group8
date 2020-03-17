package com.napier.semgroup;

import com.napier.semgroup.app.PopulationStatsApplication;
import com.napier.semgroup.datalayer.DatabaseConnection;
import com.napier.semgroup.datalayer.MySQLConnectionHandler;
import com.napier.semgroup.logic.BusinessLogic;
import com.napier.semgroup.reports.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.commons.util.ClassUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        assertEquals("China",businessLogic.getAllCountriesOrganizedByPopulation().get(0).name);
    }
    @Test
    void getAllCountriesInContinentOrganizedByPopulation() {
        assertEquals("Germany", businessLogic.getAllCountriesInContinentOrganizedByPopulation("Europe").get(1).name);
    }
    @Test
    void getAllCountriesInRegionOrganizedByPopulation(){
        assertEquals("Turkey",businessLogic.getAllCountriesInRegionOrganizedByPopulation( "Middle East").get(0).name);
    }
    @Test
    void getNCountriesOrganizedByPopulation(){
        assertEquals(20,businessLogic.getNCountriesOrganizedByPopulation(20).size());
    }

    @Test
    void getNCountriesInContinentOrganizedByPopulation() {
        assertEquals(20, businessLogic.getNCountriesInContinentOrganizedByPopulation(20, "Africa").size());
        assertEquals("Nigeria", businessLogic.getNCountriesInContinentOrganizedByPopulation(20, "Africa").get(0).name);
    }
    @Test
    void getNCountriesInRegionOrganizedByPopulation() {
        assertEquals (15, businessLogic.getNCountriesInRegionOrganizedByPopulation(20, "Southern Europe").size());
        assertEquals ("Italy", businessLogic.getNCountriesInRegionOrganizedByPopulation(20, "Southern Europe").get(0).name);
    }

    @Test
    void getAllCitiesOrganizedByPopulation() {
       assertEquals(4079, businessLogic.getAllCitiesOrganizedByPopulation().size());
    }

    @Test
    void getAllCitiesInContinentOrganizedByPopulation() {
        assertEquals(470, businessLogic.getAllCitiesInContinentOrganizedByPopulation("South America").size());
    }

    @Test
    void getAllCitiesInRegionOrganizedByPopulation() {
        assertEquals (174, businessLogic.getAllCitiesInRegionOrganizedByPopulation("Middle East").size());
        assertEquals ("Istanbul", businessLogic.getAllCitiesInRegionOrganizedByPopulation("Middle East").get(0).name);
    }

    @Test
    void getAllCitiesInCountryOrganizedByPopulation() {
       assertEquals(44, businessLogic.getAllCitiesInCountryOrganizedByPopulation("Poland").size());
    }

    @Test
    void getAllCitiesInDistrictOrganizedByPopulation() {
        assertEquals(10, businessLogic.getAllCitiesInDistrictOrganizedByPopulation("Hokkaido").size());
    }

    @Test
    void getNCitiesOrganizedByPopulation() {
        assertEquals (12, businessLogic.getNCitiesOrganizedByPopulation(12).size());
        assertEquals ("Seoul", businessLogic.getNCitiesOrganizedByPopulation(12).get(1).name);
    }

    @Test
    void getNCitiesInContinentOrganizedByPopulation() {
        assertEquals (17, businessLogic.getNCitiesInContinentOrganizedByPopulation("North America", 17).size());
        assertEquals("New York", businessLogic.getNCitiesInContinentOrganizedByPopulation("North America", 17).get(1).name);
    }

    @Test
    void getNCitiesInRegionOrganizedByPopulation() {
        assertEquals(5,businessLogic.getNCitiesInRegionOrganizedByPopulation("Baltic Countries", 5).size());
        assertEquals("Riga",businessLogic.getNCitiesInRegionOrganizedByPopulation("Baltic Countries", 5).get(0).name);
    }

    @Test
    void getNCitiesInCountryOrganizedByPopulation() {
        assertEquals(8, businessLogic.getNCitiesInCountryOrganizedByPopulation("France", 8).size());
        assertEquals("Paris", businessLogic.getNCitiesInCountryOrganizedByPopulation("France", 8).get(0).name);
    }

    @Test
    void getNCitiesInDistrictOrganizedByPopulation() {
        assertEquals (11, businessLogic.getNCitiesInDistrictOrganizedByPopulation("England", 11).size());
        assertEquals ("London", businessLogic.getNCitiesInDistrictOrganizedByPopulation("England", 11).get(0).name);
    }

    @Test
    void getAllCapitalCitiesOrganizedByPopulation() {
        assertEquals(232, businessLogic.getAllCapitalCitiesOrganizedByPopulation().size());
    }

    @Test
    void getAllCapitalCitiesInContinentOrganizedByPopulation() {
        assertEquals (27, businessLogic.getAllCapitalCitiesInContinentOrganizedByPopulation("Oceania").size());
    }

    @Test
    void getAllCapitalCitiesInRegionOrganizedByPopulation() {
        assertEquals (9, businessLogic.getAllCapitalCitiesInRegionOrganizedByPopulation("Central Africa").size());
        assertEquals ("Kinshasa", businessLogic.getAllCapitalCitiesInRegionOrganizedByPopulation("Central Africa").get(0).name);
    }

    @Test
    void getNCapitalCitiesOrganizedByPopulation() {
        assertEquals(7, businessLogic.getNCapitalCitiesOrganizedByPopulation(7).size());
    }

    @Test
    void getNCapitalCitiesInContinentOrganizedByPopulation() {
        assertEquals(9, businessLogic.getNCapitalCitiesInContinentOrganizedByPopulation("Europe", 9).size());
        assertEquals("Moscow", businessLogic.getNCapitalCitiesInContinentOrganizedByPopulation("Europe", 9).get(0).name);
    }

    @Test
    void getNCapitalCitiesInRegionOrganizedByPopulation() {
        assertEquals (6, businessLogic.getNCapitalCitiesInRegionOrganizedByPopulation("Polynesia", 6).size());
        assertEquals ("Apia", businessLogic.getNCapitalCitiesInRegionOrganizedByPopulation("Polynesia", 6).get(0).name);
    }
    

    @Test
    void disconnect(){
        mySQLConnectionHandler.disconnect();
       mySQLConnectionHandler.connect("localhost:33080");
    }

    @Test
    void getPopPopCitiesPopNotCitiesInContinent(){
        assertEquals(7, businessLogic.getPopPopCitiesPopNotCitiesInContinent().size());
    }

    @Test
    void getPopPopCitiesPopNotCitiesInRegion(){
        assertEquals(25, businessLogic.getPopPopCitiesPopNotCitiesInRegion().size());
    }

    @Test
    void getPopPopCitiesPopNotCitiesInCountry(){
        assertEquals(239, businessLogic.getPopPopCitiesPopNotCitiesInCountry().size());
    }


}

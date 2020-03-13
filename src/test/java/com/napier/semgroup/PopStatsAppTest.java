package com.napier.semgroup;

import com.napier.semgroup.app.PopulationStatsApplication;
import com.napier.semgroup.datalayer.MySQLConnectionHandler;
import com.napier.semgroup.logic.BusinessLogic;
import com.napier.semgroup.reports.*;
import org.junit.jupiter.api.*;

import java.awt.font.TextLayout;

import static org.junit.jupiter.api.Assertions.*;

public class PopStatsAppTest {

    static CapitalCity capitalCity;
    static City city;
    static Country country;
    static LanguageReport languageReport;
    static Population population;


    @BeforeAll
    static void init()
    {
     capitalCity = new CapitalCity();
     city = new City();
     country = new Country();
     languageReport = new LanguageReport();
     population = new Population();
    }

  @Test
  void capitalCityReport(){
        capitalCity.country = "Germany";
        capitalCity.name = "Berlin";
        capitalCity.population = 80000000;
        capitalCity.toString();
  }
  @Test
    void cityReport(){
        city.country = "Deutschland";
        city.district = "Baijeri";
        city.name = "Munich";
        city.population = 2390030;
        city.toString();

  }
  @Test
    void countryReport(){
        country.capital = "Paris";
        country.code = "FRA";
        country.continent = "Europe";
        country.name = "France";
        country.population = 59225700;
        country.region = "Western Europe";
        country.toString();
  }

  @Test
    void languageReport(){
        languageReport.name = "";
        languageReport.percentage = 20.0;
        languageReport.toString();
  }

  @Test
    void populationReport(){
       population.name = "";
       population.populationCities = 200;
       population.populationCitiesNot = 20;
       population.populationCitiesNotPercent = 1.0;
       population.populationCitiesPercent = 2.0;
       population.totalPopulation = 3000;
       population.toString();

  }

}

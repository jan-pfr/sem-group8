package com.napier.semgroup.logic;

import com.napier.semgroup.datalayer.DatabaseConnection;
import com.napier.semgroup.reports.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BusinessLogic {

    private DatabaseConnection databaseConnection;

    public BusinessLogic(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public List<Country> executeQueryAndParseCountries(String query)
    {
        ResultSet rset = databaseConnection.execute(query);

        try {
            ArrayList <Country> countryArrayList = new ArrayList<>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("countryCode");
                country.name = rset.getString("countryName");
                country.continent = rset.getString("countryContinent");
                country.region = rset.getString("countryRegion");
                country.population = rset.getInt("countryPopulation");
                country.capital = rset.getString("capitalName");
                countryArrayList.add(country);
            }
            return countryArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private List<String> continents = List.of("Africa", "Europe", "Asia", "North America", "South America", "Oceania", "Antarctica");

    // Case insensitive check for the supplied string being a valid name for a continent.
    public boolean validateContinent(String continentName)
    {
        // Checks if the supplied string converted to uppercase is equal to any of the members of the continent list converted to uppercase.
        return continents.stream().map(String::toUpperCase).collect(Collectors.toList()).contains(continentName.toUpperCase());
    }


    private List<String> regions = List.of("Central Africa", "Southern Europe", "Middle East", "South America", "Polynesia", "Antarctica", "Australia and New Zealand", "Western Europe", "Eastern Africa", "Western Africa", "Eastern Europe", "Central America", "North America", "Southeast Asia", "Southern Africa", "Eastern Asia", "Nordic Countries", "Northern Africa", "Baltic Countries", "Melanesia", "Micronesia", "British Islands", "Micronesia/Caribbean");

    // Case insensitive check for the supplied string being a valid name for a region.
    public boolean validateRegion(String regionName)
    {
        // Checks if the supplied string converted to uppercase is equal to any of the members of the region list converted to uppercase.
        return regions.stream().map(String::toUpperCase).collect(Collectors.toList()).contains(regionName.toUpperCase());
    }



    // #1 - All the countries in the world organized by largest population to smallest
    public List<Country> getAllCountriesOrganizedByPopulation()
    {
        return executeQueryAndParseCountries("select country.Name as countryName, city.name as capitalName, country.Code as countryCode, country.Continent as countryContinent, country.Region as countryRegion, country.Population as countryPopulation \n" +
                "from country \n" +
                "left join city on Capital=city.ID\n" +
                "order by country.population desc");
    }

    // #2 - All the countries in a continent organised by largest population to smallest.
    public List<Country> getAllCountriesInContinentOrganizedByPopulation(String continentName)
    {
        if (!validateContinent(continentName))
        {
            System.out.println("Error: " + continentName + " is an invalid continent name.");
            return null;
        }

        return executeQueryAndParseCountries("select country.Name as countryName, city.name as capitalName, country.Code as countryCode, country.Continent as countryContinent, country.Region as countryRegion, country.Population as countryPopulation \n" +
                "from country \n" +
                "left join city on Capital=city.ID\n" +
                "where country.Continent='" + continentName + "' \n" +
                "order by country.population desc");
    }

    // #3 - All the countries in a region organised by largest population to smallest.
    public List<Country> getAllCountriesInRegionOrganizedByPopulation(String regionName)
    {
        if (!validateRegion(regionName))
        {
            System.out.println("Error: " + regionName + " is an invalid region name.");
            return null;
        }

        return executeQueryAndParseCountries("select country.Name as countryName, city.name as capitalName, country.Code as countryCode, country.Continent as countryContinent, country.Region as countryRegion, country.Population as countryPopulation \n" +
                "from country \n" +
                "left join city on Capital=city.ID\n" +
                "where country.Region='" + regionName + "' \n" +
                "order by country.population desc");
    }

    // #4 - The top N populated countries in the world organized by largest population to smallest
    public List<Country> getNCountriesOrganizedByPopulation(int N)
    {
        if (N < 1)
        {
            System.out.println("Error: Amount of countries provided must be positive- " + N + " provided.");
            return null;
        }

        return executeQueryAndParseCountries("select country.Name as countryName, city.name as capitalName, country.Code as countryCode, country.Continent as countryContinent, country.Region as countryRegion, country.Population as countryPopulation \n" +
                "from country \n" +
                "left join city on Capital=city.ID\n" +
                "order by country.population desc limit " + N);
    }

    // #5 - The top N populated countries in a continent organised by largest population to smallest.
    public List<Country> getNCountriesInContinentOrganizedByPopulation(int N, String continentName)
    {
        if (N < 1)
        {
            System.out.println("Error: Amount of countries provided must be positive- " + N + " provided.");
            return null;
        }

        if (!validateContinent(continentName))
        {
            System.out.println("Error: " + continentName + " is an invalid continent name.");
            return null;
        }

        return executeQueryAndParseCountries("select country.Name as countryName, city.name as capitalName, country.Code as countryCode, country.Continent as countryContinent, country.Region as countryRegion, country.Population as countryPopulation \n" +
                "from country \n" +
                "left join city on Capital=city.ID\n" +
                "where country.Continent='" + continentName + "' \n" +
                "order by country.population desc limit " + N);
    }

    // #6 - The top N populated countries in a region organised by largest population to smallest.
    public List<Country> getNCountriesInRegionOrganizedByPopulation(int N, String regionName)
    {
        if (N < 1)
        {
            System.out.println("Error: Amount of countries provided must be positive- " + N + " provided.");
            return null;
        }

        if (!validateRegion(regionName))
        {
            System.out.println("Error: " + regionName + " is an invalid region name.");
            return null;
        }

        return executeQueryAndParseCountries("select country.Name as countryName, city.name as capitalName, country.Code as countryCode, country.Continent as countryContinent, country.Region as countryRegion, country.Population as countryPopulation \n" +
                "from country \n" +
                "left join city on Capital=city.ID\n" +
                "where country.Region='" + regionName + "' \n" +
                "order by country.population desc limit " + N);
    }

}

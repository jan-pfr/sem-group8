package com.napier.semgroup.logic;

import com.napier.semgroup.datalayer.DatabaseConnection;
import com.napier.semgroup.reports.Country;
import com.napier.semgroup.reports.Population;

import java.beans.JavaBean;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BusinessLogic {

    private DatabaseConnection databaseConnection;

    public BusinessLogic(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    private List<String> continents = List.of("Africa", "Europe", "Asia", "North America", "South America", "Oceania", "Antarctica");

    // Case insensitive check for the supplied string being a valid name for a continent.
    public boolean validateContinent(String continentName) {
        // Checks if the supplied string converted to uppercase is equal to any of the members of the continent list converted to uppercase.
        return continents.stream().map(String::toUpperCase).collect(Collectors.toList()).contains(continentName.toUpperCase());
    }


    private List<String> regions = List.of("Central Africa", "Southern Europe", "Middle East", "South America", "Polynesia", "Antarctica", "Australia and New Zealand", "Western Europe", "Eastern Africa", "Western Africa", "Eastern Europe", "Central America", "North America", "Southeast Asia", "Southern Africa", "Eastern Asia", "Nordic Countries", "Northern Africa", "Baltic Countries", "Melanesia", "Micronesia", "British Islands", "Micronesia/Caribbean","Caribbean");

    // Case insensitive check for the supplied string being a valid name for a region.
    public boolean validateRegion(String regionName) {
        // Checks if the supplied string converted to uppercase is equal to any of the members of the region list converted to uppercase.
        return regions.stream().map(String::toUpperCase).collect(Collectors.toList()).contains(regionName.toUpperCase());
    }

    //validate country name (!case sensitive)
    public boolean validateCountry(String countryName) {
        //lowers country names case
        countryName = countryName.toUpperCase();

        try {
            //fetches all countries (hopefully!)
            ResultSet rset = databaseConnection.execute("select country.Name as name from country");

            while (rset.next()) {
                //checks if countryname matches any other country fetched
                if (countryName.equals(rset.getString("name").toUpperCase())) {
                    return true;
                }
            }
            return false;
        }
        //oh no... something went wrong :(
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Country> executeQueryAndParseCountries(String query) {
        ResultSet rset = databaseConnection.execute(query);

        try {
            ArrayList<Country> countryArrayList = new ArrayList<>();
            while (rset.next()) {
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

    // #1 - All the countries in the world organized by largest population to smallest
    public List<Country> getAllCountriesOrganizedByPopulation() {
        return executeQueryAndParseCountries("select country.Name as countryName, city.name as capitalName, country.Code as countryCode, country.Continent as countryContinent, country.Region as countryRegion, country.Population as countryPopulation \n" +
                "from country \n" +
                "left join city on Capital=city.ID\n" +
                "order by country.population desc");
    }

    // #2 - All the countries in a continent organised by largest population to smallest.
    public List<Country> getAllCountriesInContinentOrganizedByPopulation(String continentName) {
        if (!validateContinent(continentName)) {
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
    public List<Country> getAllCountriesInRegionOrganizedByPopulation(String regionName) {
        if (!validateRegion(regionName)) {
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
    public List<Country> getNCountriesOrganizedByPopulation(int N) {
        if (N < 1) {
            System.out.println("Error: Amount of countries provided must be positive- " + N + " provided.");
            return null;
        }

        return executeQueryAndParseCountries("select country.Name as countryName, city.name as capitalName, country.Code as countryCode, country.Continent as countryContinent, country.Region as countryRegion, country.Population as countryPopulation \n" +
                "from country \n" +
                "left join city on Capital=city.ID\n" +
                "order by country.population desc limit " + N);
    }

    // #5 - The top N populated countries in a continent organised by largest population to smallest.
    public List<Country> getNCountriesInContinentOrganizedByPopulation(int N, String continentName) {
        if (N < 1) {
            System.out.println("Error: Amount of countries provided must be positive- " + N + " provided.");
            return null;
        }

        if (!validateContinent(continentName)) {
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
    public List<Country> getNCountriesInRegionOrganizedByPopulation(int N, String regionName) {
        if (N < 1) {
            System.out.println("Error: Amount of countries provided must be positive- " + N + " provided.");
            return null;
        }

        if (!validateRegion(regionName)) {
            System.out.println("Error: " + regionName + " is an invalid region name.");
            return null;
        }

        return executeQueryAndParseCountries("select country.Name as countryName, city.name as capitalName, country.Code as countryCode, country.Continent as countryContinent, country.Region as countryRegion, country.Population as countryPopulation \n" +
                "from country \n" +
                "left join city on Capital=city.ID\n" +
                "where country.Region='" + regionName + "' \n" +
                "order by country.population desc limit " + N);
    }

    public List<Population> PopPopCitiesPopNotCitiesInX(String location)
    {
        try
        {
            ArrayList<Population> popList=new ArrayList<Population>();

            //queries database
            ResultSet rset = databaseConnection.execute("select "+location+", sum(Population) as Population " +
                    "from country " +
                    "group by "+location);

            //get Continent and total population
            while (rset.next()) {
                Population population = new Population();
                population.name = rset.getString(location);
                population.totalPopulation= rset.getLong("Population");
                popList.add(population);
            }

            //index location
            Integer index=0;

            //queries database
            rset = databaseConnection.execute("select country."+location+" as "+location+", sum(city.Population) as CityPopulation " +
                    "from city " +
                    "inner join country on city.CountryCode = country.Code " +
                    "group by country."+location);

                while (rset.next()) {
                    Population population=new Population();
                    population.name = rset.getString(location);

                    for(int i=0;i<popList.size();i++) {

                        //get index
                        if(popList.get(i).name.equals(population.name))
                        {
                            //set population name and total population
                            population.totalPopulation=popList.get(i).totalPopulation;
                            //and store index
                            index=i;
                        }
                    }

                    //get query data
                    population.populationCities=rset.getLong("CityPopulation");
                    if(population.totalPopulation<=0) {
                        population.populationCitiesPercent = 0;
                        population.populationCitiesNot = 0;
                        population.populationCitiesNotPercent = 0;
                    }else if (population.populationCities<=0){
                        population.populationCitiesPercent = 0;
                        population.populationCitiesNot = population.totalPopulation - population.populationCities;
                        population.populationCitiesNotPercent = (int) ((population.populationCitiesNot * 100) / population.totalPopulation);
                        }else{
                        population.populationCitiesPercent = (int) ((population.populationCities * 100) / population.totalPopulation);
                        population.populationCitiesNot = population.totalPopulation - population.populationCities;
                        population.populationCitiesNotPercent = (int) ((population.populationCitiesNot * 100) / population.totalPopulation);
                    }

                    //set query data with matching list element
                    popList.set(index,population);
                }

                return popList;

        } catch (SQLException e)
        {
                e.printStackTrace();
                return null;
        }
    }

    // #23 The population of people, people living in cities, and people not living in cities in each continent.
    public List<Population> PopPopCitiesPopNotCitiesInContinent() {
        List<Population> popList;
        popList = PopPopCitiesPopNotCitiesInX("Continent");
        return popList;
    }

    // #25 The population of people, people living in cities, and people not living in cities in each region.
    public List<Population> PopPopCitiesPopNotCitiesInRegion()
    {
        List<Population> popList;
       popList=PopPopCitiesPopNotCitiesInX("Region");
        return popList;
    }

    // #26 The population of people, people living in cities, and people not living in cities in each country.
    public List<Population> PopPopCitiesPopNotCitiesInCountry()
    {
        List<Population> popList;
        popList=PopPopCitiesPopNotCitiesInX("Name");
        return popList;
    }
}

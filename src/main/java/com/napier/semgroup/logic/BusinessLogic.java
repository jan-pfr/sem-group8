package com.napier.semgroup.logic;

import com.napier.semgroup.datalayer.DatabaseConnection;
import com.napier.semgroup.reports.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BusinessLogic {

    // BACKEND

    private DatabaseConnection databaseConnection;

    public BusinessLogic(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }


    // INPUT VALIDATION

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

    // Case insensitive check for the supplied string being a valid name for a country.
    public boolean validateCountry(String countryName)
    {
        // Because there are 239 distinct countries within the database, keeping them all here is grossly impractical
        // Which means that the best way to see if a country exists is to consult the database itself

        // This query will return a single row with a single field with the count of all countries with a supplied name, or 0 if such country doesn't exist
        ResultSet rset = databaseConnection.execute("select count(*) as res from country where name='" + countryName + "'");

        // Getting the single field
        try {
            rset.next();
            return (rset.getInt("res") > 0);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Case insensitive check for the supplied string being a valid name for a district.
    public boolean validateDistrict(String districtName)
    {
        // Because there are 1367 distinct districts within the database, keeping them all here is grossly impractical
        // Which means that the best way to see if a district exists is to consult the database itself

        // This query will return a single row with a single field with the count of all cities in a supplied district, or 0 if such district doesn't exist
        ResultSet rset = databaseConnection.execute("select count(*) as res from city where district='" + districtName + "'");

        // Getting the single field
        try {
            rset.next();
            return (rset.getInt("res") > 0);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public enum areaFilter {World, Region, Continent, District, Country}


    /// IMPLEMENTATION + PUBLIC FUNCTIONS

    // Used internally by functions as a number large enough to never omit any results
    // When used in a 'limit' clause. (No. Countries = 239, No. Cities = 4079, No. Langs = 984)
    private static final int many = 42069;

    // Implements #1 through #6.
    public List<Country> countriesConditionalPopulation(areaFilter conditionType, String condition, int N)
    {
        // This string will contain any conditions we restrict countries to, such as belonging to a specific continent or region
        // In case of searching through the entire world, it will be empty and thus have no effect
        String filter = "";

        // Checking for invalid lengths of lists
        if (N < 1)
        {
            System.out.println("Error: Amount of countries provided must be positive- " + N + " provided.");
            return null;
        }


        // If searching in a specific continent, validate and add to filter
        if (conditionType == areaFilter.Continent)
        {
            if (!validateContinent(condition))
            {
                System.out.println("Error: " + condition + " is an invalid continent name.");
                return null;
            }
            filter = "where country.Continent='" + condition + "' \n";
        }

        // If searching in a specific region, validate and add to filter
        else if (conditionType == areaFilter.Region)
        {
            if (!validateRegion(condition))
            {
                System.out.println("Error: " + condition + " is an invalid region name.");
                return null;
            }
            filter = "where country.Region='" + condition + "' \n";
        }

        // Construct the query
        String query = "select country.Name as countryName, city.name as capitalName, country.Code as countryCode, country.Continent as countryContinent, country.Region as countryRegion, country.Population as countryPopulation \n" +
                "from country \n" +
                "left join city on Capital=city.ID\n" +
                filter +
                "order by country.population desc limit " + N;

        // Execute the query
        ResultSet rset = databaseConnection.execute(query);

        // In case of backend errors, watch out of exceptions
        try {

            // ArrayList to hold the results
            ArrayList <Country> countryArrayList = new ArrayList<>();

            // Iterate over the result set and fill countryArrayList with results
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

            // Return the results
            return countryArrayList;

        } catch (SQLException e) {
            // In case of an error, just print the stack trace and return a null
            e.printStackTrace();
            return null;
        }
    }

    // #1 - All the countries in the world organized by largest population to smallest
    public List<Country> getAllCountriesOrganizedByPopulation()
    {
        return countriesConditionalPopulation(areaFilter.World, "", many);
    }

    // #2 - All the countries in a continent organised by largest population to smallest.
    public List<Country> getAllCountriesInContinentOrganizedByPopulation(String continentName)
    {
        return countriesConditionalPopulation(areaFilter.Continent, continentName, many);
    }

    // #3 - All the countries in a region organised by largest population to smallest.
    public List<Country> getAllCountriesInRegionOrganizedByPopulation(String regionName)
    {
        return countriesConditionalPopulation(areaFilter.Region, regionName, many);
    }

    // #4 - The top N populated countries in the world organized by largest population to smallest
    public List<Country> getNCountriesOrganizedByPopulation(int N)
    {
        return countriesConditionalPopulation(areaFilter.World, "", N);
    }

    // #5 - The top N populated countries in a continent organised by largest population to smallest.
    public List<Country> getNCountriesInContinentOrganizedByPopulation(int N, String continentName)
    {
        return countriesConditionalPopulation(areaFilter.Continent, continentName, N);
    }

    // #6 - The top N populated countries in a region organised by largest population to smallest.
    public List<Country> getNCountriesInRegionOrganizedByPopulation(int N, String regionName)
    {
        return countriesConditionalPopulation(areaFilter.Region, regionName, N);
    }

    //---

    // Implements #7 through #22.
    public List<City> citiesConditionalPopulation(areaFilter conditionType, String condition, boolean capital, int N)
    {
        // This string will contain any conditions we restrict cities to, such as belonging to a specific continent or region
        // In case of searching through the entire world, it will be empty and thus have no effect
        // All filters have the 'and city.Name is not null' clause to handle the pathological case
        // Of a country not having a capital (the case for countries with 0 population)
        String filter = "where city.Name is not null \n";

        // This string will contain the join condition for our query, which will be different depending on whether
        // We're searching for capital cities or not
        String joinCondition = "";

        // Checking for invalid lengths of lists
        if (N < 1)
        {
            System.out.println("Error: Amount of cities provided must be positive- " + N + " provided.");
            return null;
        }


        // If searching in a specific continent, validate and add to filter
        if (conditionType == areaFilter.Continent)
        {
            if (!validateContinent(condition))
            {
                System.out.println("Error: " + condition + " is an invalid continent name.");
                return null;
            }
            filter = "where country.Continent='" + condition + "' and city.Name is not null \n";
        }

        // If searching in a specific region, validate and add to filter
        else if (conditionType == areaFilter.Region)
        {
            if (!validateRegion(condition))
            {
                System.out.println("Error: " + condition + " is an invalid region name.");
                return null;
            }
            filter = "where country.Region='" + condition + "' and city.Name is not null \n";
        }

        // If searching in a specific country, validate and add to filter
        else if (conditionType == areaFilter.Country)
        {
            if (!validateCountry(condition))
            {
                System.out.println("Error: " + condition + " is an invalid country name.");
                return null;
            }
            filter = "where country.Name='" + condition + "' and city.Name is not null \n";
        }

        // If searching in a specific district, validate and add to filter
        else if (conditionType == areaFilter.District)
        {
            if (!validateDistrict(condition))
            {
                System.out.println("Error: " + condition + " is an invalid district name.");
                return null;
            }
            filter = "where city.District='" + condition + "' and city.Name is not null \n";
        }

        // If searching for capitals, right join to countries to only keep capitals
        if (capital)
        {
            joinCondition = "right join country on country.Capital = city.ID\n";
        }
        // Otherwise, left join to cities to keep all cities
        else
        {
            joinCondition = "left join country on city.CountryCode = country.Code\n";
        }


        String query =  "select country.Name as countryName, city.name as cityName, city.population as cityPopulation, city.district as cityDistrict \n" +
                        "from city\n" +
                        joinCondition +
                        filter +
                        "order by city.population desc limit " + N;

        // Execute the query
        ResultSet rset = databaseConnection.execute(query);

        // In case of backend errors, watch out of exceptions
        try {

            // ArrayList to hold the results
            ArrayList <City> cityArrayList = new ArrayList<>();

            // Iterate over the result set and fill cityArrayList with results
            while (rset.next())
            {
                City city = new City();
                city.name = rset.getString("cityName");
                city.district = rset.getString("cityDistrict");
                city.population = rset.getInt("cityPopulation");
                city.country = rset.getString("countryName");
                cityArrayList.add(city);
            }

            // Return the results
            return cityArrayList;

        } catch (SQLException e) {
            // In case of an error, just print the stack trace and return a null
            e.printStackTrace();
            return null;
        }
    }

    // #7 - All the cities in the world organised by largest population to smallest.
    public List<City> getAllCitiesOrganizedByPopulation()
    {
        return citiesConditionalPopulation(areaFilter.World, "", false, many);
    }

    // #8 - All the cities in a continent organised by largest population to smallest.
    public List<City> getAllCitiesInContinentOrganizedByPopulation(String continentName)
    {
        return citiesConditionalPopulation(areaFilter.Continent, continentName, false, many);
    }

    // #9 - All the cities in a region organised by largest population to smallest.
    public List<City> getAllCitiesInRegionOrganizedByPopulation(String regionName)
    {
        return citiesConditionalPopulation(areaFilter.Region, regionName, false, many);
    }

    // #10 - All the cities in a country organised by largest population to smallest.
    public List<City> getAllCitiesInCountryOrganizedByPopulation(String countryName)
    {
        return citiesConditionalPopulation(areaFilter.Country, countryName, false, many);
    }

    // #11 - All the cities in a district organised by largest population to smallest.
    public List<City> getAllCitiesInDistrictOrganizedByPopulation(String districtName)
    {
        return citiesConditionalPopulation(areaFilter.District, districtName, false, many);
    }

    // #12 - The top N populated cities in the world organised by largest population to smallest.
    public List<City> getNCitiesOrganizedByPopulation(int N)
    {
        return citiesConditionalPopulation(areaFilter.World, "", false, N);
    }

    // #13 - The top N populated cities in a continent organised by largest population to smallest.
    public List<City> getNCitiesInContinentOrganizedByPopulation(String continentName, int N)
    {
        return citiesConditionalPopulation(areaFilter.Continent, continentName, false, N);
    }

    // #14 - The top N populated cities in a region organised by largest population to smallest.
    public List<City> getNCitiesInRegionOrganizedByPopulation(String regionName, int N)
    {
        return citiesConditionalPopulation(areaFilter.Region, regionName, false, N);
    }

    // #15 - The top N populated cities in a country organised by largest population to smallest.
    public List<City> getNCitiesInCountryOrganizedByPopulation(String countryName, int N)
    {
        return citiesConditionalPopulation(areaFilter.Country, countryName, false, N);
    }

    // #16 - The top N populated cities in a district organised by largest population to smallest.
    public List<City> getNCitiesInDistrictOrganizedByPopulation(String districtName, int N)
    {
        return citiesConditionalPopulation(areaFilter.District, districtName, false, N);
    }

    // #17 - All the capital cities in the world organised by largest population to smallest.
    public List<City> getAllCapitalCitiesOrganizedByPopulation()
    {
        return citiesConditionalPopulation(areaFilter.World, "", true, many);
    }

    // #18 - All the capital cities in a continent organised by largest population to smallest.
    public List<City> getAllCapitalCitiesInContinentOrganizedByPopulation(String continentName)
    {
        return citiesConditionalPopulation(areaFilter.Continent, continentName, true, many);
    }

    // #19 - All the capital cities in a region organised by largest population to smallest.
    public List<City> getAllCapitalCitiesInRegionOrganizedByPopulation(String regionName)
    {
        return citiesConditionalPopulation(areaFilter.Region, regionName, true, many);
    }

    // #20 - The top N capital cities in the world organised by largest population to smallest.
    public List<City> getNCapitalCitiesOrganizedByPopulation(int N)
    {
        return citiesConditionalPopulation(areaFilter.World, "", true, N);
    }

    // #21 - The top N capital cities in a continent organised by largest population to smallest.
    public List<City> getNCapitalCitiesInContinentOrganizedByPopulation(String continentName, int N)
    {
        return citiesConditionalPopulation(areaFilter.Continent, continentName, true, N);
    }

    // #22 - The top N capital cities in a region organised by largest population to smallest.
    public List<City> getNCapitalCitiesInRegionOrganizedByPopulation(String regionName, int N)
    {
        return citiesConditionalPopulation(areaFilter.Region, regionName, true, N);
    }
    public void noUserInterface(String param){
        if (param.equals("headless")){
            System.out.println("It looks like that you started the Application without the UI. Please make sure, that you are not trying to start the UI as a Docker container");
        }
    }

    public List<Population> getPopPopCitiesPopNotCitiesInX(String location)
    {
        try
        {
            ArrayList<Population> popList=new ArrayList<>();

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
            Integer index = 0;

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
    public List<Population> getPopPopCitiesPopNotCitiesInContinent() {
        List<Population> popList;
        popList = getPopPopCitiesPopNotCitiesInX("Continent");
        return popList;
    }

    // #25 The population of people, people living in cities, and people not living in cities in each region.
    public List<Population> getPopPopCitiesPopNotCitiesInRegion()
    {
        List<Population> popList;
       popList= getPopPopCitiesPopNotCitiesInX("Region");
        return popList;
    }

    // #26 The population of people, people living in cities, and people not living in cities in each country.
    public List<Population> getPopPopCitiesPopNotCitiesInCountry()
    {
        List<Population> popList;
        popList= getPopPopCitiesPopNotCitiesInX("Name");
        return popList;
    }
}

package com.napier.semgroup.logic;

import com.napier.semgroup.datalayer.DatabaseConnection;
import com.napier.semgroup.reports.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessLogic {

    private DatabaseConnection databaseConnection;

    public BusinessLogic(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }



    //this is a example method how to handle the result of the query.
    public List <Country> getAllCountries() {
        ResultSet rset = databaseConnection.execute("SELECT * FROM country");

        try {
            ArrayList <Country> countryArrayList = new ArrayList<>();
            while (rset.next())
            {
            Country country = new Country();
            country.code = rset.getString("Code");
            country.name = rset.getString("Name");
            country.continent = rset.getString("Continent");
            country.region = rset.getString("Region");
            country.population = rset.getInt("Population");
            country.capital = rset.getInt("Capital");
            countryArrayList.add(country);
            }
            return countryArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

}

package com.napier.semgroup;

import java.sql.ResultSet;

public interface DatabaseConnection {

    void connect();

    void disconnect();


    ResultSet execute(String query, Country country);

    ResultSet execute(String query, City city);

    ResultSet execute(String query, CountryLanguage countryLanguage);


}

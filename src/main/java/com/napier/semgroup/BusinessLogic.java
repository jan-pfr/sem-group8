package com.napier.semgroup;

import java.sql.ResultSet;

public class BusinessLogic {

    private DatabaseConnection databaseConnection;

    public BusinessLogic(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public void getAllCountries() {
        //...
        ResultSet rset = databaseConnection.execute("SELECT * FROM country");
        

        //... return

    }

}

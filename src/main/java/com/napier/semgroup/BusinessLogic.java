package com.napier.semgroup;

public class BusinessLogic {

    private DatabaseConnection databaseConnection;

    public BusinessLogic(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public void getAllCountries() {
        //...
        Country country = new Country();
        databaseConnection.execute("SELECT * FROM country", country );
        //... return

    }

}

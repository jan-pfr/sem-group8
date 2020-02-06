package com.napier.semgroup;

import java.sql.ResultSet;

public interface DatabaseConnection {

    void connect();

    void disconnect();


    ResultSet execute(String query);



}

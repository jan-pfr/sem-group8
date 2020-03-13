package com.napier.semgroup.datalayer;

import java.sql.ResultSet;

public interface DatabaseConnection {

    void connect(String connection);

    void disconnect();


    ResultSet execute(String query);



}

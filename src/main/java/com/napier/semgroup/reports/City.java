package com.napier.semgroup.reports;


public class City {
    /*
     *City's Name
     */
    public String name;

    /*
     *City's Country
     */
    public String country;

    /*
     *City's District
     */
    public String district;

    /*
     *City's Population
     */
    public Integer population;

    public String toString() {
        return String.format("%-37s %-23s %-41s %-10s",
                name, district, country, population);

    }
}

package com.napier.semgroup.reports;


public class City {
    /*
     *City's Name
     */
    public String name;

    /*
     *City's CountryCode
     */
    public String countryCode;

    /*
     *City's District
     */
    public String district;

    /*
     *City's Population
     */
    public Integer population;

    public String toString() {
        return String.format("%-52s %-5s %-26s %-10s",
                name, countryCode, district, population);

    }
}

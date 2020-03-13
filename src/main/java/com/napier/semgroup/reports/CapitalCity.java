package com.napier.semgroup.reports;

public class CapitalCity {
    /*
     *The Name of the capital city
     */
    public String name;

    /*
     * The Country where the city is based
     */
    public String country;

    /*
     * The capital city population
     */
    public Integer population;

    public String toString() {
        return String.format("%-52s %-26s %-10s",
                name, country, population);
    }
}

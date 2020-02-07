package com.napier.semgroup;

public class Population {

    /*
     * The name of the continent/region/country
     */
    public String name;

    /*
     * The total population of the continent/region/country
     */
    public Integer totalPopulation;

    /*
     * The total population of the continent/region/country living in cities.
     */
    public Integer populationCities;

    /*
     * The total population of the continent/region/country living in cities in %.
     */
    public double populationCitiesPercent;
    /*
     * The total population of the continent/region/country living not in cities.
     */
    public Integer populationCitiesNot;

    /*
     * The total population of the continent/region/country living not in cities in %.
     */
    public double populationCitiesNotPercent;
}

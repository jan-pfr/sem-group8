package com.napier.semgroup.reports;

public class Population {

    /*
     * The name of the continent/region/country
     */
    public String name;

    /*
     * The total population of the continent/region/country
     */
    public long totalPopulation;

    /*
     * The total population of the continent/region/country living in cities.
     */
    public long populationCities;

    /*
     * The total population of the continent/region/country living in cities in %.
     */
    public double populationCitiesPercent;
    /*
     * The total population of the continent/region/country living not in cities.
     */
    public long populationCitiesNot;

    /*
     * The total population of the continent/region/country living not in cities in %.
     */
    public double populationCitiesNotPercent;

    public String toString() {
        return String.format("%-52s %-26s %-26s %-26s %-26s %-26s",
                name, totalPopulation, populationCities, populationCitiesPercent, populationCitiesNot, populationCitiesNotPercent);
    }
}

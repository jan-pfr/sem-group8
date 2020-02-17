package com.napier.semgroup.reports;

public class Country {

    /*
     *Country's Code
     */
    public String code;

    /*
     *Country's Name
     */
    public String  name;

    /*
     *Country's Continent
     */
    public String  continent;

    /*
     *Country's Region
     */
    public String  region;

    /*
     *Country's Population
     */
    public Integer  population;

    /*
     *Country's Capital
     */
    public String  capital;


    public String toString() {
        return String.format("%-3s %-52s %-13s %-26s %-10s %-52s",
                code, name, continent, region, population, capital);
    }
}
